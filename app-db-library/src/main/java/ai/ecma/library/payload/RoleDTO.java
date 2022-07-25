package ai.ecma.library.payload;

import ai.ecma.library.enums.Permission;
import ai.ecma.library.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDTO implements Serializable {
    private UUID id;

    @NotBlank
    private String name;

    private RoleType type;

    private Set<Permission> permissions;
}
