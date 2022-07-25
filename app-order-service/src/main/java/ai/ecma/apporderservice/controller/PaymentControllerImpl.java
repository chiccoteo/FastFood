package ai.ecma.apporderservice.controller;

import ai.ecma.apporderservice.service.PaymentService;
import ai.ecma.library.payload.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PaymentControllerImpl implements PaymentController {
    private final PaymentService paymentService;

    @Override
    public ApiResult<String> payOrder(UUID orderId, String token) {
        return paymentService.payOrder(orderId, token);
    }
}
