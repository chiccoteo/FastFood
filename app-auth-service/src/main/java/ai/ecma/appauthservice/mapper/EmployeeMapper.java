package ai.ecma.appauthservice.mapper;

import ai.ecma.appauthservice.payload.EmployeeCreateDTO;
import ai.ecma.appauthservice.payload.EmployeeGetDTO;
import ai.ecma.appauthservice.payload.RoleDTO;
import ai.ecma.library.entity.Role;
import ai.ecma.library.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeGetDTO toDTOForGet(User employee);
    EmployeeCreateDTO toDTOForCreate(User employee);
    List<EmployeeGetDTO> toDTO(List<User> employees);
}
