package ai.ecma.appauthservice;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories(basePackages = "ai.ecma.library")
@EntityScan(basePackages = "ai.ecma.library.entity")
public class AppAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppAuthServiceApplication.class, args);

    }

    @Bean
    public Gson gson(){
        return new Gson();
    }


}
