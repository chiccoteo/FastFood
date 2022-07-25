package ai.ecma.appauthservice.controller;

import ai.ecma.appauthservice.payload.RoleDTO;
import ai.ecma.appauthservice.service.RoleService;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.RegionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleController {
    private final RoleService roleService;

    @Override
    public ApiResult<List<RoleDTO>> getAll() {
        return roleService.getAll();
    }

    @Override
    public ApiResult<RoleDTO> getById(UUID id) {
        return roleService.getById(id);
    }

    @Override
    @PreAuthorize("hasAuthority('CREATE_ROLE')")
    public ApiResult<RoleDTO> createRole(RoleDTO roleDTO) {
        return roleService.createRole(roleDTO);
    }

    @Override
    public ApiResult<RoleDTO> editRole(UUID id, RoleDTO roleDTO) {
        return roleService.editRole(id,roleDTO);
    }

    @Override
    public ApiResult<?> deleteRole(UUID id) {
        return roleService.deleteRole(id);
    }
}
