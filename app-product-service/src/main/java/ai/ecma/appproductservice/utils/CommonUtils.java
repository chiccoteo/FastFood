package ai.ecma.appproductservice.utils;

import ai.ecma.appproductservice.exception.RestException;
import ai.ecma.library.payload.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class CommonUtils {

    public static HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        return requestAttributes.getRequest();
    }

    public static String getTokenByRequest() {
        HttpServletRequest currentRequest = getCurrentRequest();

        String token = currentRequest.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) throw RestException.forbidden();

        return token;
    }

    public static UserDTO getCurrentUser() {
        try {

            HttpServletRequest currentRequest = getCurrentRequest();
            UserDTO userDTO = (UserDTO) currentRequest.getAttribute(AppConstant.CURRENT_USER_REQUEST_ATTRIBUTE);
            if (userDTO == null || userDTO.getId() == null) {
                throw RestException.forbidden();
            }
            return userDTO;

        } catch (Exception e) {
            e.printStackTrace();
            throw RestException.forbidden();
        }
    }
}
