package ai.ecma.appbranchservice.feign;

import com.google.gson.Gson;
import feign.Feign;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.apache.http.entity.ContentType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@RequiredArgsConstructor
public class FeignConfig {
    private final Gson gson;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("serviceUsername", "branchServiceUsername123");
            requestTemplate.header("servicePassword", "branchServicePassword123");
            requestTemplate.header("Accept", ContentType.APPLICATION_JSON.getMimeType());
        };
    }

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }
    @Bean
    public CustomErrorDecoder errorDecoder() {
        return new CustomErrorDecoder(gson);
    }
}
