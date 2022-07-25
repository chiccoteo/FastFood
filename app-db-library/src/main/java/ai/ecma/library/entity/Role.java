package ai.ecma.library.entity;

import ai.ecma.library.enums.Permission;
import ai.ecma.library.enums.RoleType;
import ai.ecma.library.entity.templete.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Kholmakhmatov on 14/06/2022 !
 **/
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Role extends AbsEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private RoleType type;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Permission> permissions;

}
