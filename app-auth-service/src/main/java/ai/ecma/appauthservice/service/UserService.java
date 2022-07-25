package ai.ecma.appauthservice.service;

import ai.ecma.appauthservice.annotation.CurrentUser;
import ai.ecma.appauthservice.payload.AuthUser;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.UserDTO;

public interface UserService {

    ApiResult<UserDTO> me(AuthUser authUser);
}
