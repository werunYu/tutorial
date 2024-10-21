package site.werun.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author werun
 * @version 1.0
 * @date 2024/10/20 20:25
 * @description
 **/
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args){
        new SpringApplicationBuilder(EurekaServerApplication.class).run(args);
    }
}
