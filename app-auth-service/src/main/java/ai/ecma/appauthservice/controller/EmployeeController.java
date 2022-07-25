package ai.ecma.appauthservice.controller;


import ai.ecma.appauthservice.payload.EmployeeCreateDTO;
import ai.ecma.appauthservice.payload.EmployeeEditDTO;
import ai.ecma.appauthservice.payload.EmployeeGetDTO;
import ai.ecma.appauthservice.utils.AppConstant;
import ai.ecma.library.payload.ApiResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(EmployeeController.EMPLOYEE_CONTROLLER)
public interface EmployeeController {
    String EMPLOYEE_CONTROLLER= AppConstant.BASE_PATH_V1+"/employee";

    @GetMapping
     ApiResult<List<EmployeeGetDTO>> getAll(@PathVariable UUID roleId);
    @GetMapping("/{id}")
     ApiResult<EmployeeGetDTO> getById(@PathVariable UUID id);
    @PostMapping()
     ApiResult<EmployeeGetDTO> addEmployee(@RequestBody EmployeeCreateDTO employeeDTO);
    @PutMapping("/{id}")
     ApiResult<EmployeeGetDTO> editEmployee(@PathVariable UUID id, @RequestBody EmployeeEditDTO employeeDTO );
    @DeleteMapping("/{id}")
    ApiResult<?> deleteEmployee(@PathVariable UUID id);


}
