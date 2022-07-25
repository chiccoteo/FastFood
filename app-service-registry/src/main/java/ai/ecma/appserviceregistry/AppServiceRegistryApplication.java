package ai.ecma.appserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AppServiceRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppServiceRegistryApplication.class, args);
    }

}
