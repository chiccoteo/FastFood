package ai.ecma.library.entity;

import ai.ecma.library.entity.templete.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Created by Kholmakhmatov on 16/06/2022 !
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Where(clause = "deleted = false")
@SQLDelete(sql = "update district set deleted = true where id = ?")
@FieldNameConstants
public class District extends AbsEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private Region region;

}
