package ai.ecma.library.entity;

import ai.ecma.library.entity.templete.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by Kholmakhmatov on 16/06/2022 !
 **/
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@FieldNameConstants
public class Category extends AbsEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment attachment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category parentCategory;

}
