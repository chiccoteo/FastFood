package ai.ecma.appauthservice.controller;

import ai.ecma.appauthservice.payload.RoleDTO;
import ai.ecma.appauthservice.utils.AppConstant;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.RegionDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping(RoleController.ROLE_CONTROLLER)
public interface RoleController {
    String ROLE_CONTROLLER = AppConstant.BASE_PATH_V1+"/auth";

    @GetMapping
    ApiResult<List<RoleDTO>> getAll();

    @GetMapping("/{id}")
    ApiResult<RoleDTO> getById(UUID id);

    @PostMapping
    ApiResult<RoleDTO> createRole(@RequestBody @Valid RoleDTO roleDTO);

    @PutMapping("{id}")
    ApiResult<RoleDTO> editRole(@PathVariable UUID id, @Valid @RequestBody RoleDTO roleDTO);

    @DeleteMapping("/{id}")
    ApiResult<?> deleteRole(@PathVariable UUID id);


}
