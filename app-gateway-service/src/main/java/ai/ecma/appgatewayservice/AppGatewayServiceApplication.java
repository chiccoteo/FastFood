package ai.ecma.appgatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AppGatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppGatewayServiceApplication.class, args);
    }

}
