package ai.ecma.appauthservice.payload;

import ai.ecma.library.enums.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDTO {
    private UUID id;

    @NotBlank
    private String name;


    private Set<Permission> permissionList;
}
