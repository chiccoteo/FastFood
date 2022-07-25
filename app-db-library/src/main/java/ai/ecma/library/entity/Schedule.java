package ai.ecma.library.entity;

import ai.ecma.library.enums.Week;
import ai.ecma.library.entity.templete.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.time.LocalTime;

/**
 * Created by Kholmakhmatov on 16/06/2022 !
 **/
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Schedule extends AbsEntity {

    private LocalTime startTime;

    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    private Week week;

    @ManyToOne
    private Branch branch;
}
