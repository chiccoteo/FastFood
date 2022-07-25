package ai.ecma.apporderservice.service;

import ai.ecma.apporderservice.feign.AuthFeign;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class CacheService {

    private final AuthFeign authFeign;

    @Cacheable(value = "users")
    public UserDTO users(String token) {
        ApiResult<UserDTO> result = authFeign.me(token);
        return result.getData();
    }
}
