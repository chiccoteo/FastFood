package ai.ecma.appauthservice.controller;

import ai.ecma.appauthservice.payload.*;
import ai.ecma.appauthservice.utils.AppConstant;
import ai.ecma.library.payload.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(AuthController.AUTH_CONTROLLER)
public interface AuthController {
    String AUTH_CONTROLLER =AppConstant.BASE_PATH_V1+"/auth";

    @PostMapping("/check-phone")
    ApiResult<?> checkPhoneNumber(@Valid @RequestBody PhoneNumberDTO phoneNumberDTO);

    @PostMapping("/verify-phone")
    ApiResult<TokenDTO> checkVerifyPhone(@Valid @RequestBody VerifyPhoneDTO verifyPhoneDTO);

    @PostMapping("/register")
    ApiResult<TokenDTO> registration(@RequestBody RegisterDTO registerDTO);

    @PostMapping("/login")
    ApiResult<?> login(@RequestBody LoginDTO loginDTO);


}
