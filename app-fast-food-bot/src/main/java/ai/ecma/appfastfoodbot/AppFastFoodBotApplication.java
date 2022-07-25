package ai.ecma.appfastfoodbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableEurekaClient
@EnableJpaRepositories(basePackages = "ai.ecma.library.repository")
@EntityScan(basePackages = "ai.ecma.library.entity")
@EnableFeignClients
public class AppFastFoodBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppFastFoodBotApplication.class, args);
    }

}
