package ai.ecma.appauthservice.service;

import ai.ecma.appauthservice.mapper.UserMapper;
import ai.ecma.appauthservice.payload.AuthUser;
import ai.ecma.library.entity.User;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public ApiResult<UserDTO> me(AuthUser authUser) {
        User user = authUser.getUser();
        return ApiResult.successResponse(userMapper.toDTO(user));
    }
}
