package ai.ecma.apporderservice.controller;

import ai.ecma.apporderservice.utils.AppConstant;
import ai.ecma.library.payload.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@RequestMapping(PaymentController.PAYMENT_CONTROLLER)
public interface PaymentController {
    String PAYMENT_CONTROLLER = AppConstant.BASE_PATH_V1 + "/payment";

    @PostMapping
    ApiResult<String> payOrder(@RequestParam UUID orderId, @RequestParam String token);

}
