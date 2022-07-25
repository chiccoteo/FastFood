package ai.ecma.library.entity;

import ai.ecma.library.entity.templete.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Kholmakhmatov on 16/06/2022 !
 **/
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"branch_id","product_id"}))
public class BranchProduct extends AbsEntity {
    private boolean active;
    @ManyToOne(fetch = FetchType.LAZY)
    private Branch branch;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
