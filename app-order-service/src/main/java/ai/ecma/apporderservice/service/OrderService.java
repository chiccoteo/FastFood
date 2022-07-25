package ai.ecma.apporderservice.service;

import ai.ecma.apporderservice.payload.ConfirmOrderDTO;
import ai.ecma.apporderservice.payload.CreateBasketDTO;
import ai.ecma.apporderservice.payload.OrderDTO;
import ai.ecma.library.enums.OrderStatus;
import ai.ecma.library.payload.ApiResult;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    ApiResult<?> basket(List<CreateBasketDTO> createBasketDTOList);

    ApiResult<?> confirmOrder(ConfirmOrderDTO confirmOrderDTO);

    ApiResult<?> completeOrder(UUID orderId);

    ApiResult<List<OrderDTO>> getAllNewOrders(int page, int size);

    ApiResult<String> transferOrderToCanteen(UUID id, OrderStatus orderStatus);

}
