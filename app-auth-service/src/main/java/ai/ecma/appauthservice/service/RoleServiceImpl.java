package ai.ecma.appauthservice.service;

import ai.ecma.appauthservice.common.MessageService;
import ai.ecma.appauthservice.exception.RestException;
import ai.ecma.appauthservice.mapper.RoleMapper;
import ai.ecma.appauthservice.payload.RoleDTO;
import ai.ecma.library.repository.RoleRepository;
import ai.ecma.library.entity.Role;
import ai.ecma.library.enums.RoleType;
import ai.ecma.library.payload.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;


    @Override
    public ApiResult<List<RoleDTO>> getAll() {
        List<Role> allRoles = roleRepository.findAll();
        List<RoleDTO> roleDTOS = roleMapper.toDTO(allRoles);
        return ApiResult.successResponse(roleDTOS);
    }

    @Override
    public ApiResult<RoleDTO> getById(UUID id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> RestException.notFound("ROLE_NOT_FOUND"));
        return ApiResult.successResponse(roleMapper.toDTO(role));
    }

    @Override
    public ApiResult<RoleDTO> createRole(RoleDTO roleDTO) {
        if (!(roleRepository.existsByName(roleDTO.getName()))) {
            Role role = new Role();
            role.setName(roleDTO.getName());
            role.setPermissions(roleDTO.getPermissionList());
            role.setType(RoleType.CUSTOM);
            Role savedRole = roleRepository.save(role);
            return ApiResult.successResponse(roleMapper.toDTO(savedRole), MessageService.getMessage("ROLE_SAVED"));
        }
        return ApiResult.errorResponse(MessageService.getMessage("ROLE_DUPLICATE"));
    }

    @Override
    public ApiResult<RoleDTO> editRole(UUID id, RoleDTO roleDTO) {
        Role role = roleRepository.findById(id).orElseThrow(() -> RestException.notFound("ROLE_NOT_FOUND"));
        if (!(roleRepository.existsByName(roleDTO.getName()))) {
            if (role.getType().equals(RoleType.CUSTOM)) {
                role.setName(roleDTO.getName());
                role.setPermissions(roleDTO.getPermissionList());
                role.setType(RoleType.CUSTOM);
                return ApiResult.successResponse(roleMapper.toDTO(role), MessageService.getMessage("ROLE_SAVED"));
            }
            return ApiResult.errorResponse(MessageService.getMessage("ROLE_CHANGE_RESTRICTED"));
        } else {
            return ApiResult.errorResponse(MessageService.getMessage("ROLE_DUPLICATE"));
        }
    }

    @Override
    public ApiResult<?> deleteRole(UUID id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> RestException.notFound("ROLE_NOT_FOUND"));
        if (role.getType().equals(RoleType.CUSTOM)) {
            roleRepository.delete(role);
            return ApiResult.successResponse(MessageService.getMessage("ROLE_DELETED"));
        } else {
            return ApiResult.errorResponse(MessageService.getMessage("ROLE_CHANGE_RESTRICTION"));
        }
    }
}
