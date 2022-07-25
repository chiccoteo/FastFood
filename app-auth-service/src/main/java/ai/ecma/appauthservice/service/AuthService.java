package ai.ecma.appauthservice.service;

import ai.ecma.appauthservice.payload.*;
import ai.ecma.library.payload.ApiResult;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AuthService extends UserDetailsService {

    ApiResult<?> checkPhoneNumber(PhoneNumberDTO phoneNumberDTO);

    ApiResult<TokenDTO> checkVerifyPhone(VerifyPhoneDTO verifyPhoneDTO);

    ApiResult<TokenDTO> registration(RegisterDTO registerDTO);

    ApiResult<?> login(LoginDTO loginDTO);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
