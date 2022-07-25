package ai.ecma.apporderservice.service;

import ai.ecma.apporderservice.common.MessageService;
import ai.ecma.apporderservice.exception.RestException;
import ai.ecma.apporderservice.mapper.OrderMapper;
import ai.ecma.apporderservice.mapper.OrderProductMapper;
import ai.ecma.apporderservice.mapper.PromotionProductMapper;
import ai.ecma.apporderservice.mapper.QuantityPromotionMapper;
import ai.ecma.apporderservice.payload.*;
import ai.ecma.apporderservice.utils.CommonUtils;
import ai.ecma.library.entity.*;
import ai.ecma.library.enums.DiscountTypeEnum;
import ai.ecma.library.enums.OrderStatus;
import ai.ecma.library.enums.PaymentStatusEnum;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.BasketDTO;
import ai.ecma.library.payload.PromotionProductDTO;
import ai.ecma.library.payload.UserDTO;
import ai.ecma.library.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;
    private final OrdersRepository ordersRepository;
    private final ParentPromotionRepository parentPromotionRepository;
    private final PercentPromotionRepository percentPromotionRepository;
    private final PromotionProductRepository promotionProductRepository;
    private final DeliveryPromotionRepository deliveryPromotionRepository;
    private final QuantityPromotionRepository quantityPromotionRepository;
    private final PromotionProductMapper promotionProductMapper;
    private final OrderProductMapper orderProductMapper;
    private final QuantityPromotionMapper quantityPromotionMapper;
    private final UserRepository userRepository;
    private final OrderProductRepository orderProductRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentTypeRepository paymentTypeRepository;

    private final AddressRepository addressRepository;
    private final BranchRepository branchRepository;
    private final StripeService stripeService;

    @Override
    public ApiResult<?> basket(List<CreateBasketDTO> createBasketDTOList) {

        Set<UUID> productIdSet = createBasketDTOList.stream().map(CreateBasketDTO::getProductId).collect(Collectors.toSet());

        List<Product> productList = productRepository.findAllById(productIdSet);

        Order order = new Order();
        List<OrderProduct> orderProductList = makeOrderProduct(createBasketDTOList, productList, order);

        BasketDTO basketDTO = totalSumma(orderProductList, order);

        return ApiResult.successResponse(basketDTO);

    }

    @Override
    public ApiResult<?> confirmOrder(ConfirmOrderDTO confirmOrderDTO) {
        Order order = ordersRepository.findById(confirmOrderDTO.getId()).orElseThrow(() -> RestException.notFound("ORDER NOT FOUND"));
        PaymentType payType = paymentTypeRepository.findById(confirmOrderDTO.getPayTypeId()).orElseThrow(() -> RestException.notFound("PAY_TYPE NOT FOUND"));
        order.setPaymentType(payType);
        order.setDelivery(order.isDelivery());
        order.setDescription(confirmOrderDTO.getDescription());
        boolean reliable = ordersRepository.isReliable(order.getClient().getId());
        order.setStatus(reliable ? OrderStatus.IN_PROGRESS : OrderStatus.NEW);
        setPickUpOrDelivery(order, confirmOrderDTO);
        ordersRepository.save(order);
        return ApiResult.successResponse(order.getId(), MessageService.getMessage("ORDER_ACCEPTED"));
    }

    @Override
    public ApiResult<?> completeOrder(UUID orderId) {
        Order order = ordersRepository.findById(orderId).orElseThrow(() -> RestException.notFound("ORDER_NOT_FOUND"));
        UserDTO currentUser = CommonUtils.getCurrentUser();
        if (currentUser.getId().equals(order.getClient().getId()) || currentUser.getId().equals(order.getCourier().getId())) {

            order.setStatus(OrderStatus.SUCCESS);
            Payment payment = paymentRepository.findByOrderId(orderId).orElseThrow(() -> RestException.notFound("PAYMENT_NOT_FOUND"));
            payment.setStatus(PaymentStatusEnum.SUCCESS);

            stripeService.captureCharge(payment.getChargeId());

            ordersRepository.save(order);
            paymentRepository.save(payment);
            return ApiResult.successResponse("TASHAKKURLLAR !!");
        }
        throw RestException.forbidden();
    }


    /**
     * OREDERGA DELIVER TARIFGA QARAB ADDRESNI SET QILADI
     *
     * @param order
     * @param confirmOrderDTO
     */
    private void setPickUpOrDelivery(Order order, ConfirmOrderDTO confirmOrderDTO) {
        if (confirmOrderDTO.isDelivery()) {
            Address address;
            AddressDTO addressDTO = confirmOrderDTO.getAddressDTO();
            if (confirmOrderDTO.getAddressDTO().getId() != null) {
                address = addressRepository.findById(addressDTO.getId()).orElseThrow(() -> RestException.notFound("ADDRESS_ID"));
            } else {
                address = new Address(addressDTO.getName(), addressDTO.getLat(), addressDTO.getLan(), null);
                addressRepository.save(address);
            }
            order.setAddress(address);
            Branch closestBranch = branchRepository.getClosestBranch(LocalTime.now(), 69.22871268742759, 41.32643672692621).orElseThrow(() -> RestException.notFound("NO_BRANCH_AVAILABLE"));
            order.setBranch(closestBranch);

            List<OrderProduct> orderProductList = orderProductRepository.findByOrderId(order.getId());

            recalculate(orderProductList, order, confirmOrderDTO);


        } else {
            PickUpDTO pickUpDTO = confirmOrderDTO.getPickUpDTO();
            Branch branch = branchRepository.findById(pickUpDTO.getBranchId()).orElseThrow(() -> RestException.notFound("BRANCH_ID_NOT_FOUND"));
            order.setBranch(branch);
            order.setAddress(branch.getAddress());
        }
    }

    private void recalculate(List<OrderProduct> orderProductList, Order order, ConfirmOrderDTO confirmOrderDTO) {
        double totalSumma = 0, productTotalSumma = 0;
        for (OrderProduct orderProduct : orderProductList) {
            Product product = orderProduct.getProduct();
            if (product.getDiscount() == null) {
                totalSumma += product.getPrice();
            } else if (product.getDiscount().getType().equals(DiscountTypeEnum.SUM)) {
                totalSumma += (product.getPrice() - product.getDiscount().getAmount()) * orderProduct.getCount();
            } else {
                totalSumma += (product.getPrice() * (product.getDiscount().getAmount() / 100)) * orderProduct.getCount();
            }
            productTotalSumma += product.getPrice() * orderProduct.getCount();
        }

        Optional<ParentPromotion> optionalParentPromotion = parentPromotionRepository.getCurrentParentPromotion();
        if (optionalParentPromotion.isPresent()) {
            ParentPromotion parentPromotion = optionalParentPromotion.get();
            switch (parentPromotion.getType()) {
                case PERCENT: {
                    Optional<PercentPromotion> optionalPercentPromotion = percentPromotionRepository.findByParentPromotionAndPriceLessThanEqual(parentPromotion, productTotalSumma);
                    if (optionalPercentPromotion.isPresent()) {
                        PercentPromotion percentPromotion = optionalPercentPromotion.get();
                        totalSumma = totalSumma * (percentPromotion.getPercent() / 100);
                    }
                }
                break;
                case PRODUCT: {
                    if (confirmOrderDTO.getPromotionProductIdList().size() > 0) {
                        List<PromotionProduct> promotionProductList = promotionProductRepository.getPromotionProductByProductPromotionPrice(totalSumma);
                        Set<OrderProduct> orderProductSet = promotionProductList.stream()
                                .filter(promotionProduct -> confirmOrderDTO.getPromotionProductIdList().contains(promotionProduct.getId()))
                                .map(promotionProduct -> new OrderProduct(promotionProduct.getProduct(), order,
                                        promotionProduct.getCount(), true))
                                .collect(Collectors.toSet());
                        orderProductRepository.saveAll(orderProductSet);
                    }
                }
                break;
                case DELIVERY: {
                    boolean existsActive = deliveryPromotionRepository.existsActive();
                    if (existsActive) order.setDeliveryPrice(0);
                }
                break;
                case QUANTITY: {
                    Optional<QuantityPromotion> optionalQuantityPromotion = quantityPromotionRepository.findByParentPromotionId(parentPromotion.getId());

                    if (optionalQuantityPromotion.isPresent()) {
                        QuantityPromotion quantityPromotion = optionalQuantityPromotion.get();
                        for (OrderProduct orderProduct : orderProductList) {
                            Product product = orderProduct.getProduct();
                            if (product.getId() == quantityPromotion.getId() && orderProduct.getCount() >= quantityPromotion.getPurchaseProductCount()) {
                                OrderProduct newOrderProduct = new OrderProduct(quantityPromotion.getBonusProduct(), order, quantityPromotion.getBonusProductCount(), true);
                                orderProductRepository.save(newOrderProduct);
                                break;
                            }

                        }
                    }
                }
                break;
            }
        }
    }

    private List<OrderProduct> makeOrderProduct(List<CreateBasketDTO> createBasketDTOList, List<Product> productList, Order order) {
        List<OrderProduct> orderProductList = new LinkedList<>();

        for (Product product : productList) {
            for (CreateBasketDTO createBasketDTO : createBasketDTOList) {
                if (product.getId().equals(createBasketDTO.getProductId())) {
                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setProduct(product);
                    orderProduct.setCount(createBasketDTO.getCount());
                    orderProduct.setOrder(order);
                    orderProduct.setPrice(product.getPrice());
                    orderProduct.setDiscountAmount(product.getDiscount().getAmount());
                    orderProduct.setDiscountType(product.getDiscount().getType());
                    orderProduct.setFree(false);
                    orderProductList.add(orderProduct);
                }
            }
        }
        return orderProductList;
    }

    private BasketDTO totalSumma(List<OrderProduct> orderProductList, Order order) {

        User user = userRepository.findById(CommonUtils.getCurrentUser().getId()).orElseThrow(() -> RestException.notFound("USER_NOT_FOUND"));
        BasketDTO basketDTO = new BasketDTO();

        order.setClient(user);
        order.setSerialNumber(generateOrderSerialNumber());
        order.setStatus(OrderStatus.BASKET);

        double totalSumma = 0, productTotalSumma = 0;

        for (OrderProduct orderProduct : orderProductList) {
            Product product = orderProduct.getProduct();
            if (product.getDiscount() == null) {
                totalSumma += product.getPrice();
            } else if (product.getDiscount().getType().equals(DiscountTypeEnum.SUM)) {
                totalSumma += (product.getPrice() - product.getDiscount().getAmount()) * orderProduct.getCount();
            } else {
                totalSumma += (product.getPrice() * (product.getDiscount().getAmount() / 100)) * orderProduct.getCount();
            }
            productTotalSumma += product.getPrice() * orderProduct.getCount();
        }

        Optional<ParentPromotion> optionalParentPromotion = parentPromotionRepository.getCurrentParentPromotion();
        if (optionalParentPromotion.isPresent()) {
            ParentPromotion parentPromotion = optionalParentPromotion.get();
            switch (parentPromotion.getType()) {
                case PERCENT: {
                    Optional<PercentPromotion> optionalPercentPromotion = percentPromotionRepository.findByParentPromotionAndPriceLessThanEqual(parentPromotion, productTotalSumma);
                    if (optionalPercentPromotion.isPresent()) {
                        PercentPromotion percentPromotion = optionalPercentPromotion.get();
                        totalSumma = totalSumma * (percentPromotion.getPercent() / 100);
                    }
                }
                break;
                case PRODUCT: {
                    List<PromotionProduct> promotionProductList = promotionProductRepository.getPromotionProductByProductPromotionPrice(totalSumma);

                    List<PromotionProductDTO> promotionProductDTOList = promotionProductMapper.toDTO(promotionProductList);

                    basketDTO.setPromotionProductList(promotionProductDTOList);
                }
                break;
                case DELIVERY: {
                    boolean existsActive = deliveryPromotionRepository.existsActive();
                    if (existsActive) order.setDeliveryPrice(0);
                    basketDTO.setFreeDeliveryPromotion(true);
                }
                break;
                case QUANTITY: {
                    Optional<QuantityPromotion> optionalQuantityPromotion = quantityPromotionRepository.findByParentPromotionId(parentPromotion.getId());
                    if (optionalQuantityPromotion.isPresent()) {
                        QuantityPromotion quantityPromotion = optionalQuantityPromotion.get();
                        for (OrderProduct orderProduct : orderProductList) {
                            Product product = orderProduct.getProduct();
                            if (product.getId() == quantityPromotion.getId() && orderProduct.getCount() >= quantityPromotion.getPurchaseProductCount()) {
                                OrderProduct newOrderProduct = new OrderProduct(quantityPromotion.getBonusProduct(), order, quantityPromotion.getBonusProductCount(), true);
                                orderProductList.add(newOrderProduct);
                                basketDTO.setQuantityPromotion(quantityPromotionMapper.toDTO(quantityPromotion));
                                break;
                            }

                        }
                    }
                }
                break;
            }
        }
        order.setTotalSumma(totalSumma);
        ordersRepository.save(order);
        orderProductRepository.saveAll(orderProductList);


        basketDTO.setOrderId(order.getId());
        basketDTO.setSerialNumber(order.getSerialNumber());
        basketDTO.setProductList(orderProductMapper.toDTO(orderProductList));
        basketDTO.setTotalSumma(order.getTotalSumma());

        return basketDTO;

    }

    private String generateOrderSerialNumber() {
        int number = (int) (Math.random() * 899999 + 100000);
        boolean isExist = ordersRepository.existsBySerialNumber(String.valueOf(number));
        if (isExist) {
            generateOrderSerialNumber();
        }
        return String.valueOf(number);
    }


    @Override
    public ApiResult<List<OrderDTO>> getAllNewOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Order> allOrdersByStatus = ordersRepository.findAllByStatus(OrderStatus.NEW, pageable);
        return ApiResult.successResponse(orderMapper.toDTO(allOrdersByStatus));
    }

    @Override
    public ApiResult<String> transferOrderToCanteen(UUID id, OrderStatus orderStatus) {
        Order order = ordersRepository.findById(id).orElseThrow(() -> RestException.notFound("ORDER_NOT_FOUND"));
        if (order.getStatus().equals(OrderStatus.NEW) && (orderStatus.equals(OrderStatus.PENDING) || orderStatus.equals(OrderStatus.REJECT))) {
            order.setStatus(orderStatus);
            ordersRepository.save(order);
            return ApiResult.successResponse(MessageService.getMessage("ORDER_ACCEPTED"));
        } else {
            throw RestException.forbidden();
        }
    }

}
