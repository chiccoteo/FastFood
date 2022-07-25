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
@Where(clause = "deleted = false")
@SQLDelete(sql = "update branch set deleted = true where id = ?")
@Data
@FieldNameConstants
public class Branch extends AbsEntity {
    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    private boolean active;

    private boolean autoDistributed;

    private double maxRadius; // km

    @OneToOne(fetch = FetchType.LAZY)
    private Address address;

}
