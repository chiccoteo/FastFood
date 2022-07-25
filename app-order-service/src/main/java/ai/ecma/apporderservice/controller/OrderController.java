package ai.ecma.apporderservice.controller;

import ai.ecma.apporderservice.payload.ConfirmOrderDTO;
import ai.ecma.apporderservice.payload.CreateBasketDTO;
import ai.ecma.apporderservice.payload.OrderDTO;
import ai.ecma.apporderservice.utils.AppConstant;
import ai.ecma.library.enums.OrderStatus;
import ai.ecma.library.payload.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@RequestMapping(OrderController.ORDER_CONTROLLER)
public interface OrderController {
    String ORDER_CONTROLLER = AppConstant.BASE_PATH_V1 + "/order";
    String BASKET = "/basket";
    String CONFIRM = "/confirm";
    String COMPLETE = "/complete";
    String GET_NEW_ORDERS = "/getNewOrders";
    String TRANSFER_TO_CANTEEN = "/transfer";

    @PostMapping(BASKET)
    ApiResult<?> basket(@RequestBody List<CreateBasketDTO> createBasketDTOList);

    @GetMapping()
    ApiResult<?> getALlOrders(@RequestParam Timestamp startTime, @RequestParam Timestamp endTime);

    @PostMapping(CONFIRM)
    ApiResult<?> confirmOrder(@Valid @RequestBody ConfirmOrderDTO confirmOrderDTO);

    @PutMapping(COMPLETE)
    ApiResult<?> completeOrder(@RequestParam UUID orderId);

    @GetMapping(GET_NEW_ORDERS)
    ApiResult<List<OrderDTO>> getAllNewOrders(
            @RequestParam(defaultValue = AppConstant.DEFAULT_PAGE_NUMBER, required = false) int page,
            @RequestParam(defaultValue = AppConstant.DEFAULT_PAGE_SIZE, required = false) int size);

    @PostMapping(TRANSFER_TO_CANTEEN)
    ApiResult<String> transferOrderToCanteen(@RequestParam UUID id, @RequestParam OrderStatus orderStatus);

}
