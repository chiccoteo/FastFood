package ai.ecma.library.entity;

import ai.ecma.library.entity.templete.AbsEntity;
import ai.ecma.library.enums.DiscountTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Kholmakhmatov on 17/06/2022 !
 **/
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class OrderProduct extends AbsEntity {
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Order order;

    private double price;

    private int count;

    private double discountAmount;

    @Enumerated(EnumType.STRING)
    private DiscountTypeEnum discountType;

    private boolean free;

    public OrderProduct(Product product, Order order, int count, boolean free) {
        this.product = product;
        this.order = order;
        this.count = count;
        this.free = free;
    }

}
