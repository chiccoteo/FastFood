package ai.ecma.appbranchservice;

import ai.ecma.appbranchservice.utils.AppConstant;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients
@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories(basePackages = "ai.ecma.library.repository")
@EntityScan(basePackages = "ai.ecma.library.entity")
@RestController
@RequestMapping(AppConstant.BASE_PATH)
public class AppBranchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppBranchServiceApplication.class, args);

    }

    @Bean
    public Gson gson(){
        return new Gson();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @PostMapping("/actuator/refresh")
    public void refresh(@RequestBody Object o) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        restTemplate().exchange("http://localhost:8888/actuator/refresh", HttpMethod.POST, httpEntity, Object.class);
    }

}
