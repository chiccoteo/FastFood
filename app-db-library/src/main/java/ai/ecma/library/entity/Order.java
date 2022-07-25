package ai.ecma.library.entity;

import ai.ecma.library.enums.OrderStatus;
import ai.ecma.library.entity.templete.AbsEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Kholmakhmatov on 16/06/2022 !
 **/
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "orders")
@Where(clause = "deleted = false")
@SQLDelete(sql = "update orders set deleted = true where id = ?")
@Builder
public class Order extends AbsEntity {
    @Column(nullable = false, unique = true, name = "serial_number")
    private String serialNumber;

    private boolean isDelivery;

    private String description;

    private LocalTime receiveTime;  // clientga yetkazilgan vaqti;

    private double totalSumma;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private double deliveryPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;// yetkazish uchun address

    @ManyToOne(fetch = FetchType.LAZY)
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    private User client;

    @ManyToOne(fetch = FetchType.LAZY)
    private User courier;

    @ManyToOne(fetch = FetchType.LAZY)
    private ParentPromotion parentPromotion;

    private double percent;
    @OneToMany(fetch = FetchType.LAZY,mappedBy ="order")
    private List<OrderProduct> orderProductList;

}
