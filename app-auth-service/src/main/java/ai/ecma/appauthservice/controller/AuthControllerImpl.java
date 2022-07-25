package ai.ecma.appauthservice.controller;

import ai.ecma.appauthservice.payload.*;
import ai.ecma.appauthservice.service.AuthService;
import ai.ecma.library.payload.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController{
    private final AuthService authService;

    @Override
    public ApiResult<?> checkPhoneNumber(PhoneNumberDTO phoneNumberDTO) {
        return authService.checkPhoneNumber(phoneNumberDTO);
    }

    @Override
    public ApiResult<TokenDTO> checkVerifyPhone(VerifyPhoneDTO verifyPhoneDTO) {
       return authService.checkVerifyPhone(verifyPhoneDTO);
    }

    @Override
    public ApiResult<TokenDTO> registration(RegisterDTO registerDTO) {
        return authService.registration(registerDTO);
    }

    @Override
    public ApiResult<?> login(LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }
}
