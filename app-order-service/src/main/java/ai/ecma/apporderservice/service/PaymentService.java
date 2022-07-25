package ai.ecma.apporderservice.service;

import ai.ecma.library.payload.ApiResult;

import java.util.UUID;

public interface PaymentService {

    ApiResult<String> payOrder(UUID orderId, String token);

}
