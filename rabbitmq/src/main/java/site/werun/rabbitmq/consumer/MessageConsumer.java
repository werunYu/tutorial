package site.werun.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import site.werun.rabbitmq.domain.NotifyObject;

/**
 * @author werun
 * @date 2024/10/14 9:02
 * @version 1.0
 * @description 类描述
 */
@Service
@Slf4j
public class MessageConsumer {

  @Autowired private RabbitTemplate rabbitTemplate;

  // 消费者处理消息
  @RabbitListener(queues = "main_queue")
  public void consumeMessage(@Payload NotifyObject message) {
    // 模拟消息处理
    log.info("Received message:{}", message);
    try {
      // 假设处理失败，抛出异常
      throw new RuntimeException("Processing failed");

    } catch (Exception e) {
      // 处理失败，将消息重新发送到延迟队列
      log.error("处理消息异常", e);
      handleFailedMessage(message, e);
    }
  }

  // 处理失败的消息
  private void handleFailedMessage(NotifyObject message, Exception exception) {
    // 模拟从消息头中获取重试次数（在实际中可以从header获取）
    int retryCount = getRetryCountFromMessage(message);

    // 根据重试次数选择不同的延迟队列
    String nextQueue = "";
    if (retryCount == 0) {
      nextQueue = "delay_queue_1min";
    } else if (retryCount == 1) {
      nextQueue = "delay_queue_2min";
    } // 继续添加更多重试间隔...

    // 发送消息到相应的延迟队列，更新重试次数
    if (retryCount <= 1) {
      message.setRetryTimes(message.getRetryTimes() + 1);
      rabbitTemplate.convertAndSend(nextQueue, message);
    }
  }

  // 假设从消息中获取重试次数
  private int getRetryCountFromMessage(NotifyObject message) {
    return message.getRetryTimes() + 1;
  }
}
