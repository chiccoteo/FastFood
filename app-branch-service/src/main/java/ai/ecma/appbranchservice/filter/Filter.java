package ai.ecma.appbranchservice.filter;

import ai.ecma.appbranchservice.common.MessageService;
import ai.ecma.library.payload.ApiResult;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class Filter extends OncePerRequestFilter {
    private final Gson gson;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String serviceUsername = request.getHeader("serviceUsername");
        String servicePassword = request.getHeader("servicePassword");

        System.out.println(serviceUsername);
        System.out.println(servicePassword);

        if (!isSecuredCommunication(serviceUsername, servicePassword)) {
            ApiResult<Object> result = ApiResult.errorResponse(MessageService.getMessage("FORBIDDEN"));
            response.setStatus(403);
            response.setContentType("application/json");
            response.getWriter().write(gson.toJson(result));
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean isSecuredCommunication(String serviceUsername, String servicePassword) {
        if (serviceUsername != null && servicePassword != null) {
            String password = microservices.get(serviceUsername);
            return password != null && password.equals(servicePassword);
        }
        return false;
    }

    private final Map<String, String> microservices = Map.of(
            "productServiceUsername123", "productServicePassword123",
            "gatewayServiceUsername123", "gatewayServicePassword123",
            "authServiceUsername123", "authServicePassword123",
            "orderServiceUsername123", "orderServicePassword123",
            "branchServiceUsername123", "branchServicePassword123"
    );
}
