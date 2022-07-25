package ai.ecma.library.entity;

import ai.ecma.library.entity.templete.AbsEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
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
@SQLDelete(sql = "update address set deleted = true where id = ?")
@FieldNameConstants
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Address extends AbsEntity {
    @Column(nullable = false)
    private String name;

    private double lat;

    private double lan;

    @ManyToOne(fetch = FetchType.LAZY)
    private District district;
}
