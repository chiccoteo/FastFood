package ai.ecma.library.entity;

import ai.ecma.library.enums.DiscountTypeEnum;
import ai.ecma.library.entity.templete.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Kholmakhmatov on 16/06/2022 !
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Discount extends AbsEntity {
    @Column(nullable = false)
    private String name;

    private double amount;

    private boolean active;

    @Enumerated(EnumType.STRING)
    private DiscountTypeEnum type;

    @Column(name = "start_time", nullable = false)
    private LocalTime starTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

}
