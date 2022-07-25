package ai.ecma.library.entity;

import ai.ecma.library.entity.templete.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Kholmakhmatov on 16/06/2022 !
 **/
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Where(clause = "deleted = false")
@SQLDelete(sql = "update region set deleted = true where id = ?")
@FieldNameConstants
public class Region extends AbsEntity {
    @Column(nullable = false,unique = true)
    private String name;

}
