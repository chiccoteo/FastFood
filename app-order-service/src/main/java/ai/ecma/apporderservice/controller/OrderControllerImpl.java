package ai.ecma.apporderservice.controller;

import ai.ecma.apporderservice.aop.Authorize;
import ai.ecma.apporderservice.payload.ConfirmOrderDTO;
import ai.ecma.apporderservice.payload.CreateBasketDTO;
import ai.ecma.apporderservice.payload.OrderDTO;
import ai.ecma.apporderservice.service.OrderService;
import ai.ecma.library.enums.OrderStatus;
import ai.ecma.library.payload.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {
    private final OrderService orderService;


    @Override
    @Authorize
    public ApiResult<?> basket(List<CreateBasketDTO> createBasketDTOList) {
        return orderService.basket(createBasketDTOList);
    }


    @Override
    public ApiResult<?> getALlOrders(Timestamp startTime, Timestamp endTime) {
        return null;
    }

    @Override
    public ApiResult<?> confirmOrder(ConfirmOrderDTO confirmOrderDTO) {
        return orderService.confirmOrder(confirmOrderDTO);
    }

    @Override
    @Authorize
    public ApiResult<?> completeOrder(UUID orderId) {
        return orderService.completeOrder(orderId);
    }

    @Override
    public ApiResult<List<OrderDTO>> getAllNewOrders(int page, int size) {
        return orderService.getAllNewOrders(page, size);
    }

    @Override
    public ApiResult<String> transferOrderToCanteen(UUID id, OrderStatus orderStatus) {
        return orderService.transferOrderToCanteen(id, orderStatus);
    }


}
