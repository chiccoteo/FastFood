package ai.ecma.appauthservice.controller;

import ai.ecma.appauthservice.payload.AuthUser;
import ai.ecma.appauthservice.service.AuthService;
import ai.ecma.appauthservice.service.UserService;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController{
    private final UserService userService;

    @Override
    public ApiResult<UserDTO> me(AuthUser authUser) {
        return userService.me(authUser);
    }
}
