package ai.ecma.library.entity;

import ai.ecma.library.entity.templete.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

/**
 * Created by Kholmakhmatov on 16/06/2022 !
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Where(clause = "deleted = false")
@SQLDelete(sql = "update delivery_tariff set deleted = true where id = ?")
public class DeliveryTariff extends AbsEntity {
    @Column(name = "initial_distance")
    private double initialDistance; // km

    @Column(name = "initial_price")
    private double initialPrice;

    @Column(name = "every_distance")
    private double everyDistance;

    @Column(name = "every_price")
    private double everyPrice;

    @OneToOne(fetch = FetchType.LAZY)
    private Branch branch;

}
