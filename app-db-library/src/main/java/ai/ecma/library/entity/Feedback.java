package ai.ecma.library.entity;

import ai.ecma.library.enums.FeedbackTypeEnum;
import ai.ecma.library.entity.templete.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by Kholmakhmatov on 17/06/2022 !
 **/

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Where(clause = "deleted = false")
@SQLDelete(sql = "update feedback set deleted = true where id = ?")

public class Feedback extends AbsEntity {
    @Column(columnDefinition = "text")
    private String description;

    private int rate;

    @Enumerated(EnumType.STRING)
    private FeedbackTypeEnum feedbackType;

    @ManyToOne(fetch = FetchType.LAZY)
    private User courier;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
