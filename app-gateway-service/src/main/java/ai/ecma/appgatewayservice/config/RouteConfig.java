package ai.ecma.appgatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r
                        .path("/api/branch/v1/**")
                        .filters(f -> f
                                .addRequestHeader("serviceUsername", "gatewayServiceUsername123")
                                .addRequestHeader("servicePassword", "gatewayServicePassword123")
                        )
                        .uri("lb://BRANCH-SERVICE")
                )
                .route(r -> r
                        .path("/api/order/v1/**")
                        .filters(f -> f
                                .addRequestHeader("serviceUsername", "gatewayServiceUsername123")
                                .addRequestHeader("servicePassword", "gatewayServicePassword123")
                        )
                        .uri("lb://ORDER-SERVICE"))
                .route(r -> r
                        .path("/api/auth/v1/**")
                        .filters(f -> f
                                .addRequestHeader("serviceUsername", "gatewayServiceUsername123")
                                .addRequestHeader("servicePassword", "gatewayServicePassword123")
                        )
                        .uri("lb://AUTH-SERVICE"))
                .route(r -> r
                        .path("/api/product/v1/**")
                        .filters(f -> f
                                .addRequestHeader("serviceUsername", "gatewayServiceUsername123")
                                .addRequestHeader("servicePassword", "gatewayServicePassword123")
                        )
                        .uri("lb://PRODUCT-SERVICE"))
                .build();

    }
}
// serviceUsername -> gatewayServiceUsername123
// servicePassword -> gatewayServicePassword123

// gatewayServiceUsername123=gatewayServicePassword123