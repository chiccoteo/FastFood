package ai.ecma.library.entity;

import ai.ecma.library.entity.templete.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Kholmakhmatov on 16/06/2022 !
 **/
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class QuantityPromotion extends AbsEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_product_id")
    private Product purchaseProduct;

    @Column(name = "purchase_product_count")
    private int purchaseProductCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bonus_product_id")
    private Product bonusProduct;

    @Column(name = "bonus_product_count")
    private int bonusProductCount;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_promotion_id")
    private ParentPromotion parentPromotion;

}
