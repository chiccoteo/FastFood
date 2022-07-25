package ai.ecma.appauthservice.controller;

import ai.ecma.appauthservice.payload.EmployeeCreateDTO;
import ai.ecma.appauthservice.payload.EmployeeEditDTO;
import ai.ecma.appauthservice.payload.EmployeeGetDTO;
import ai.ecma.appauthservice.service.EmployeeService;
import ai.ecma.library.payload.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class EmployeeControllerImpl implements EmployeeController{
    private final EmployeeService employeeService;

    @Override
    public ApiResult<List<EmployeeGetDTO>> getAll(UUID roleId) {
        return employeeService.getAll(roleId);
    }

    @Override
    public ApiResult<EmployeeGetDTO> getById(UUID id) {
        return employeeService.getById(id);
    }

    @Override
    public ApiResult<EmployeeGetDTO> addEmployee(EmployeeCreateDTO employeeDTO) {
        return employeeService.addEmployee(employeeDTO);
    }

    @Override
    public ApiResult<EmployeeGetDTO> editEmployee(UUID id, EmployeeEditDTO employeeDTO) {
        return employeeService.editEmployee(id,employeeDTO);
    }

    @Override
    public ApiResult<?> deleteEmployee(UUID id) {
        return employeeService.deleteEmployee(id);
    }
}
