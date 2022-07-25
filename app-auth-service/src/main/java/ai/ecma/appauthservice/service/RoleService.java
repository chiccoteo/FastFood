package ai.ecma.appauthservice.service;

import ai.ecma.appauthservice.payload.RoleDTO;
import ai.ecma.library.payload.ApiResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    ApiResult<List<RoleDTO>> getAll();

    ApiResult<RoleDTO> getById(UUID id);

    ApiResult<RoleDTO> createRole(@RequestBody RoleDTO roleDTO);

    ApiResult<RoleDTO> editRole(@PathVariable UUID id, @RequestBody RoleDTO roleDTO);

    ApiResult<?> deleteRole(@PathVariable UUID id);
}
