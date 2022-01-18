package world.inetum.realdolmen.playground.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import world.inetum.realdolmen.playground.service.config.properties.JmsProperties;

@SpringBootApplication
@EnableConfigurationProperties({JmsProperties.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
