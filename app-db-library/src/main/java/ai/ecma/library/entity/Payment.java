package ai.ecma.library.entity;

import ai.ecma.library.enums.PaymentStatusEnum;
import ai.ecma.library.entity.templete.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by Kholmakhmatov on 17/06/2022 !
 **/
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Payment extends AbsEntity {

    private double sum;

    @Enumerated(EnumType.STRING)
    private PaymentStatusEnum status;

    @ManyToOne
    private PaymentType paymentType;

    @ManyToOne
    private Order order;

    @Column
    private String chargeId;
}

