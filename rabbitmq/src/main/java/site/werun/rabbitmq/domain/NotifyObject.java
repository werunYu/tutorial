package site.werun.rabbitmq.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author werun
 * @date 2024/10/14 9:05
 * @version 1.0
 * @description 类描述
 */
@Data
public class NotifyObject implements Serializable {
    private int retryTimes;
}
