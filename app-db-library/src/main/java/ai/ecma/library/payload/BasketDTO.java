package ai.ecma.library.payload;

import ai.ecma.library.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketDTO {

    private UUID orderId;

    private String serialNumber;

    private boolean isDelivery;


    private String description;

    private double totalSumma;

    private double deliveryPrice;

    private PercentPromotionDTO percentPromotion;

    private List<PromotionProductDTO> promotionProductList;

    private boolean freeDeliveryPromotion;

    private QuantityPromotionDTO quantityPromotion;

    private List<OrderProductDTO> productList;
}
