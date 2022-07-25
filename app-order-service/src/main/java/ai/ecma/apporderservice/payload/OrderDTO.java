package ai.ecma.apporderservice.payload;

import ai.ecma.library.entity.PaymentType;
import ai.ecma.library.enums.OrderStatus;
import ai.ecma.library.payload.AddressDTO;
import ai.ecma.library.payload.BranchDTO;
import ai.ecma.library.payload.OrderProductDTO;
import ai.ecma.library.payload.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private UUID id;
    private String serialNumber;
    private boolean isDelivery;
    private String description;
    private LocalTime receiveTime;  // clientga yetkazilgan vaqti;
    private double totalSumma;
    private OrderStatus status;
    private double deliveryPrice;
    private AddressDTO address;// yetkazish uchun address
    private BranchDTO branch;
    private PaymentType paymentType;
    private UserDTO client;
    private UserDTO courier;
    private ParentPromotionDTO parentPromotion;
    private double percent;
    private List<OrderProductDTO> orderProductList;


}