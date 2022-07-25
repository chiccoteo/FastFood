package ai.ecma.appauthservice.service;

import ai.ecma.appauthservice.payload.EmployeeCreateDTO;
import ai.ecma.appauthservice.payload.EmployeeEditDTO;
import ai.ecma.appauthservice.payload.EmployeeGetDTO;
import ai.ecma.library.enums.RoleType;
import ai.ecma.library.payload.ApiResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    ApiResult<List<EmployeeGetDTO>> getAll( UUID roleId);
    ApiResult<EmployeeGetDTO> getById(UUID id);
    ApiResult<EmployeeGetDTO> addEmployee( EmployeeCreateDTO employeeDTO);
    ApiResult<EmployeeGetDTO> editEmployee(UUID id, EmployeeEditDTO employeeDTO );
    ApiResult<?> deleteEmployee(UUID id);
}
