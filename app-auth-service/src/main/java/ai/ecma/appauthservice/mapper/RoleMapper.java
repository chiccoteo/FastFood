package ai.ecma.appauthservice.mapper;

import ai.ecma.appauthservice.payload.RoleDTO;
import ai.ecma.library.entity.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO toDTO(Role role);
    List<RoleDTO> toDTO(List<Role> regions);
}
