package ai.ecma.appbranchservice.service;

import ai.ecma.appbranchservice.feign.AuthFeign;
import ai.ecma.library.payload.ApiResult;
import ai.ecma.library.payload.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CacheService {

    private final AuthFeign authFeign;

    @Cacheable(key = "#token",value = "users")
    public UserDTO users(String token) {
        ApiResult<UserDTO> result = authFeign.me(token);
        return result.getData();
    }
}
