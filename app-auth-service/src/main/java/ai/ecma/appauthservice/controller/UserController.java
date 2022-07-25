package ai.ecma.appauthservice.controller;

import ai.ecma.appauthservice.annotation.CurrentUser;
import ai.ecma.appauthservice.payload.AuthUser;
import ai.ecma.appauthservice.utils.AppConstant;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping(UserController.AUTH_CONTROLLER)
public interface UserController {
    String AUTH_CONTROLLER= AppConstant.BASE_PATH_V1+"/user";

    @GetMapping
    ApiResult<UserDTO> me(@CurrentUser AuthUser authUser);
}
