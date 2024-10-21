package site.werun.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author werun
 * @date 2024/10/21 20:17
 * @version 1.0
 * @description 类描述
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }
}
