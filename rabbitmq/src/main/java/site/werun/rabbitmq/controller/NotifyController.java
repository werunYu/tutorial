package site.werun.rabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.werun.rabbitmq.domain.NotifyObject;

/**
 * @author werun
 * @date 2024/10/14 9:08
 * @version 1.0
 * @description 类描述
 */
@RestController
@RequestMapping("/notify")
public class NotifyController {

  @Autowired private RabbitTemplate rabbitTemplate;

  @RequestMapping("/")
  public String notifyResult() {
    rabbitTemplate.convertAndSend("main_routing_key", new NotifyObject());
    return "发起通知";
  }
}
