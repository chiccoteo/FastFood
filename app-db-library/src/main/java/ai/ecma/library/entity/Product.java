package ai.ecma.library.entity;

import ai.ecma.library.entity.templete.AbsEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by Kholmakhmatov on 16/06/2022 !
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@EqualsAndHashCode(callSuper = false)
@Where(clause = "deleted = false")
@SQLDelete(sql = "update product set deleted = true where id = ?")
public class Product extends AbsEntity {
    @Column(nullable = false)
    private String name;

    private double price;

    @Column(columnDefinition = "text")
    private String description;

    private boolean active;

    private double rate = 0d; // baholash tizimi

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment attachment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Discount discount;

}
