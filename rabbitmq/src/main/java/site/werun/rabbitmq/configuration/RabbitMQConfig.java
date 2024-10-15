package site.werun.rabbitmq.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author werun
 * @date 2024/10/14 8:59
 * @version 1.0
 * @description RabbitMQ配置类.
 */
@Configuration
public class RabbitMQConfig {

    // 死信交换机
    @Bean
    public Exchange dlxExchange() {
        return ExchangeBuilder.directExchange("dlx_exchange").durable(true).build();
    }

    // 死信队列
    @Bean
    public Queue dlxQueue() {
        return QueueBuilder.durable("dlx_queue").build();
    }

    // 将死信队列绑定到死信交换机
    @Bean
    public Binding dlxBinding(Queue dlxQueue, Exchange dlxExchange) {
        return BindingBuilder.bind(dlxQueue).to(dlxExchange).with("dlx_routing_key").noargs();
    }

    // 主队列，并指定死信交换机
    @Bean
    public Queue mainQueue() {
        return QueueBuilder.durable("main_queue")
                .withArgument("x-dead-letter-exchange", "dlx_exchange") // 死信交换机
                .withArgument("x-dead-letter-routing-key", "dlx_routing_key") // 死信路由键
                .build();
    }

    // 延迟队列 1分钟
    @Bean
    public Queue delayQueue1Min() {
        return QueueBuilder.durable("delay_queue_1min")
                .withArgument("x-message-ttl", 60000) // 1分钟
                .withArgument("x-dead-letter-exchange", "main_exchange") // 重新发送到主交换机
                .withArgument("x-dead-letter-routing-key", "main_routing_key")
                .build();
    }

    // 延迟队列 2分钟
    @Bean
    public Queue delayQueue2Min() {
        return QueueBuilder.durable("delay_queue_2min")
                .withArgument("x-message-ttl", 120000) // 2分钟
                .withArgument("x-dead-letter-exchange", "main_exchange")
                .withArgument("x-dead-letter-routing-key", "main_routing_key")
                .build();
    }

    // 主交换机
    @Bean
    public Exchange mainExchange() {
        return ExchangeBuilder.directExchange("main_exchange").durable(true).build();
    }

    // 将主队列绑定到主交换机
    @Bean
    public Binding mainBinding() {
        return BindingBuilder.bind(mainQueue()).to(mainExchange()).with("main_routing_key").noargs();
    }
}
