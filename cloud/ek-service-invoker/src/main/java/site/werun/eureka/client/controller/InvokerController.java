package site.werun.eureka.client.controller;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author werun
 * @date 2024/10/21 21:09
 * @version 1.0
 * @description 类描述
 */
@RestController
public class InvokerController {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @RequestMapping(value = "/router", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String router() {
        RestTemplate restTemplate = restTemplate();

        // 根据应用名称调用服务
        String json = restTemplate.getForObject("http://FIRST-SERVICE-PROVIDER/person/1", String.class);
        return json;
    }
}
