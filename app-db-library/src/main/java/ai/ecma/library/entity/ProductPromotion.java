package ai.ecma.library.entity;

import ai.ecma.library.entity.templete.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Kholmakhmatov on 16/06/2022 !
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ProductPromotion extends AbsEntity {

    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_promotion_id")
    private ParentPromotion parentPromotion;
}
