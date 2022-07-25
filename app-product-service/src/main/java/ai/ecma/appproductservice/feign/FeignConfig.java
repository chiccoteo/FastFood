package ai.ecma.appproductservice.feign;

import feign.RequestInterceptor;
import org.apache.http.entity.ContentType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("serviceUsername", "productServiceUsername123");
            requestTemplate.header("servicePassword", "productServicePassword123");
            requestTemplate.header("Accept", ContentType.APPLICATION_JSON.getMimeType());
        };
    }
}
