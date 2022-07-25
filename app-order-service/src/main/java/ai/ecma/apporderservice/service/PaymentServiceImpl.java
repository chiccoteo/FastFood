package ai.ecma.apporderservice.service;

import ai.ecma.apporderservice.common.MessageService;
import ai.ecma.apporderservice.exception.RestException;
import ai.ecma.library.entity.Order;
import ai.ecma.library.entity.Payment;
import ai.ecma.library.enums.PaymentStatusEnum;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.repository.OrdersRepository;
import ai.ecma.library.repository.PaymentRepository;
import com.stripe.model.Charge;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final StripeService stripeService;
    private final OrdersRepository ordersRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public ApiResult<String> payOrder(UUID orderId, String token) {
        Order order = ordersRepository.findById(orderId).orElseThrow(() -> RestException.notFound("ORDER_NOT_FOUND"));
        Charge charge = stripeService.createCharge(token, order.getTotalSumma());
        Payment payment = new Payment(order.getTotalSumma(), PaymentStatusEnum.CAPTURED, order.getPaymentType(), order, charge.getId());
        paymentRepository.save(payment);
        return ApiResult.successResponse(MessageService.getMessage("SUCCESSFULLY_PAID"));

    }
}
