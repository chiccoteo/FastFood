package ai.ecma.library.payload;

import ai.ecma.library.entity.Order;
import ai.ecma.library.entity.Product;
import ai.ecma.library.enums.DiscountTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductDTO {

    private ProductDTO product;

    private UUID orderId;

    private double price;

    private int count;

    private double discountAmount;

    private DiscountTypeEnum discountType;

    private boolean free;
}
