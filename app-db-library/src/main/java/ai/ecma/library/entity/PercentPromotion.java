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
public class PercentPromotion extends AbsEntity {
    private double price;

    private double percent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_promotion_id")
    private ParentPromotion parentPromotion;
}
