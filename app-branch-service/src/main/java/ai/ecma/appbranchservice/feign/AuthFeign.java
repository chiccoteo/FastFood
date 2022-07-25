package ai.ecma.appbranchservice.feign;

import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "AUTH-SERVICE/api/auth/v1", configuration = FeignConfig.class)
public interface AuthFeign {

    @GetMapping("/user")
    ApiResult<UserDTO> me(@RequestHeader("Authorization") String token);
}
