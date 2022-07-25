package ai.ecma.appbranchservice.aop;

import ai.ecma.appbranchservice.exception.RestException;
import ai.ecma.appbranchservice.service.CacheService;
import ai.ecma.appbranchservice.utils.AppConstant;
import ai.ecma.appbranchservice.utils.CommonUtils;
import ai.ecma.library.enums.Permission;
import ai.ecma.library.payload.UserDTO;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Aspect
@Order(1)
@Component
@RequiredArgsConstructor
public class AuthorizeAspect {
    private final CacheService cacheService;

    @Before(value = "@annotation(authorize)")
    public void authorize(Authorize authorize) {
        Permission[] permissions = authorize.permissions();

        HttpServletRequest request = CommonUtils.getCurrentRequest();
        String token = CommonUtils.getTokenByRequest();

        UserDTO userDTO = cacheService.users(token);
        Set<Permission> permissionList = userDTO.getRole().getPermissions();

        boolean access = false;
        for (Permission permission : permissions) {
            if (permissionList.contains(permission)) {
                access = true;
                break;
            }
        }
        if (!access) throw RestException.forbidden();

        request.setAttribute(AppConstant.CURRENT_USER_REQUEST_ATTRIBUTE, userDTO);
    }
}
