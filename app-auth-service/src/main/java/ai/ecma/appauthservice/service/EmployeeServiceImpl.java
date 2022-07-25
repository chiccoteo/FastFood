package ai.ecma.appauthservice.service;

import ai.ecma.appauthservice.exception.RestException;
import ai.ecma.appauthservice.mapper.EmployeeMapper;
import ai.ecma.appauthservice.payload.EmployeeCreateDTO;
import ai.ecma.appauthservice.payload.EmployeeEditDTO;
import ai.ecma.appauthservice.payload.EmployeeGetDTO;
import ai.ecma.library.entity.Role;
import ai.ecma.library.entity.User;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.repository.RoleRepository;
import ai.ecma.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeMapper employeeMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApiResult<List<EmployeeGetDTO>> getAll(UUID roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> RestException.notFound("ROLE_NOT_FOUND"));
        List<User> userListByRole = userRepository.findByRoleId(role.getId());
        return ApiResult.successResponse(employeeMapper.toDTO(userListByRole));
    }

    @Override
    public ApiResult<EmployeeGetDTO> getById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> RestException.notFound("EMPLOYEE_NOT_FOUND"));
        return ApiResult.successResponse(employeeMapper.toDTOForGet(user));
    }

    @Override
    public ApiResult<EmployeeGetDTO> addEmployee(EmployeeCreateDTO employeeDTO) {
        Role role = roleRepository.findById(employeeDTO.getRoleId()).orElseThrow(() -> RestException.notFound("ROLE_NOT_FOUND"));
        User user= new User();
        user.setLanguage(employeeDTO.getLanguage());
        user.setFirstName(employeeDTO.getFirstName());
        user.setLastName(employeeDTO.getLastName());
        user.setPhoneNumber(employeeDTO.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));
        user.setRole(role);
        userRepository.save(user);

        return ApiResult.successResponse(employeeMapper.toDTOForGet(user),"EMPLOYEE_SAVED");
    }

    @Override
    public ApiResult<EmployeeGetDTO> editEmployee(UUID id, EmployeeEditDTO employeeDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> RestException.notFound("USER_NOT_FOUND"));
        Role role = roleRepository.findById(employeeDTO.getRoleId()).orElseThrow(() -> RestException.notFound("ROLE_NOT_FOUND"));

        user.setFirstName(employeeDTO.getFirstName());
        user.setLastName(employeeDTO.getLastName());
        user.setRole(role);
        user.setPhoneNumber(employeeDTO.getPhoneNumber());
        user.setExtraPhoneNumber(employeeDTO.getExtraPhoneNumber());
        user.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));
        userRepository.save(user);
        return ApiResult.successResponse(employeeMapper.toDTOForGet(user),"EMPLOYEE_EDITED");
    }

    @Override
    public ApiResult<?> deleteEmployee(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> RestException.notFound("USER_NOT_FOUND"));
        userRepository.delete(user);
        return ApiResult.successResponse("EMPLOYEE_DELETED");
    }
}
