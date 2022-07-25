package ai.ecma.appauthservice.service;

import ai.ecma.appauthservice.common.MessageService;
import ai.ecma.appauthservice.exception.RestException;
import ai.ecma.appauthservice.filter.JwtProvider;
import ai.ecma.appauthservice.payload.*;
import ai.ecma.appauthservice.utils.CommonUtils;
import ai.ecma.library.entity.District;
import ai.ecma.library.entity.Role;
import ai.ecma.library.entity.User;
import ai.ecma.library.entity.VerifyCode;
import ai.ecma.library.enums.RoleType;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.repository.DistrictRepository;
import ai.ecma.library.repository.RoleRepository;
import ai.ecma.library.repository.UserRepository;
import ai.ecma.library.repository.VerifyCodeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final VerifyCodeRepository verifyCodeRepository;
    private final UserRepository userRepository;
    private final TwilioService twilioService;
    private final JwtProvider jwtProvider;
    private final DistrictRepository districtRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;

    @Value("${app.max-sms-count}")
    private long maxSmsCount;

    @Value("${app.max-sms-waiting-time}")
    private long maxSmsWaitingTime;

    public AuthServiceImpl(VerifyCodeRepository verifyCodeRepository, UserRepository userRepository, TwilioService twilioService, JwtProvider jwtProvider, DistrictRepository districtRepository, RoleRepository roleRepository,@Lazy AuthenticationManager authenticationManager) {
        this.verifyCodeRepository = verifyCodeRepository;
        this.userRepository = userRepository;
        this.twilioService = twilioService;
        this.jwtProvider = jwtProvider;
        this.districtRepository = districtRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ApiResult<?> checkPhoneNumber(PhoneNumberDTO phoneNumberDTO) {
        Timestamp pastTime = new Timestamp(System.currentTimeMillis() - 43_200_000);
        long count = verifyCodeRepository.countAllByPhoneNumberAndResetIsFalseAndCreatedAtAfter(phoneNumberDTO.getPhoneNumber(), pastTime);
        if (count == maxSmsCount) {
            throw RestException.restThrow("TO_MANY_REQUEST", HttpStatus.TOO_MANY_REQUESTS);
        }

        String code = CommonUtils.getRandomNumberString();
        twilioService.sendSMS(phoneNumberDTO.getPhoneNumber(), CommonUtils.generateVerificationCodeMessage(code));

        verifyCodeRepository.save(new VerifyCode(code, phoneNumberDTO.getPhoneNumber()));

        return ApiResult.successResponse(MessageService.getMessage("VERIFICATION_CODE_SEND_YOUR_PHONE_NUMBER"));
    }

    @Override
    public ApiResult<TokenDTO> checkVerifyPhone(VerifyPhoneDTO verifyPhoneDTO) {
        Timestamp pastTime = new Timestamp(System.currentTimeMillis() - maxSmsWaitingTime);
        VerifyCode verifyCode = verifyCodeRepository.findFirstByPhoneNumberAndConfirmedIsFalseAndCreatedAtAfterOrderByCreatedAtDesc
                (verifyPhoneDTO.getPhoneNumber(), pastTime).orElseThrow(() -> RestException.conflict("PLEASE_TRY_AGAIN!!"));

        if (!verifyCode.getCode().equals(verifyPhoneDTO.getVerifyCode()))
            throw RestException.conflict("WRONG_VERIFICATION_CODE");

        verifyCode.setConfirmed(true);
        verifyCodeRepository.save(verifyCode);
        Optional<User> optionalUser = userRepository.findByPhoneNumber(verifyPhoneDTO.getPhoneNumber());
        if (optionalUser.isEmpty())
            return ApiResult.successResponse(new TokenDTO());

        String accessToken = jwtProvider.generateToken(optionalUser.get().getPhoneNumber(), true);
        String refreshToken = jwtProvider.generateToken(optionalUser.get().getPhoneNumber(), false);

        return ApiResult.successResponse(new TokenDTO(accessToken, refreshToken));
    }

    @Override
    public ApiResult<TokenDTO> registration(RegisterDTO registerDTO) {
        verifyCodeRepository.findFirstByPhoneNumberAndCodeAndConfirmedIsTrueOrderByCreatedAtDesc(registerDTO.getPhoneNumber(), registerDTO.getVerifyCode()).
                orElseThrow(RestException::forbidden);

        District district = districtRepository.findById(registerDTO.getDistrictId()).orElseThrow(() -> RestException.notFound("DISTRICT_NOT_FOUND"));
        Role role = roleRepository.findByType(RoleType.CLIENT).orElseThrow(() -> RestException.notFound("ROLE_NOT_FOUND"));

        User user = new User();

        user.setFirstName(registerDTO.getFirstname());
        user.setLastName(registerDTO.getLastname());
        user.setPhoneNumber(registerDTO.getPhoneNumber());
        user.setEnabled(true);
        user.setDistrict(district);
        user.setLanguage(registerDTO.getLanguage());
        user.setRole(role);

        userRepository.save(user);

        String accessToken = jwtProvider.generateToken(user.getPhoneNumber(), true);
        String refreshToken = jwtProvider.generateToken(user.getPhoneNumber(), false);

        return ApiResult.successResponse(new TokenDTO(accessToken, refreshToken));
    }

    @Override
    public ApiResult<?> login(LoginDTO loginDTO) {

        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getPhoneNumber(), loginDTO.getPassword()));
            UserDetails user = (UserDetails)authenticate.getPrincipal();

            String accessToken = jwtProvider.generateToken(user.getUsername(), true);
            String refreshToken = jwtProvider.generateToken(user.getUsername(), false);

            return ApiResult.successResponse(new TokenDTO(accessToken, refreshToken));
        }catch (Exception e){
            e.printStackTrace();
            throw RestException.unauthorized("WRONG_USERNAME_OR_PASSWORD");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneNumber(username).orElseThrow(() -> RestException.unauthorized("WRONG_USERNAME_OR_PASSWORD"));
        return new AuthUser(user);
    }

}
