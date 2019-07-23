package com.springboot.rabbitmq.consumer;


import com.springboot.rabbitmq.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MsgReceiverC_one {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = RabbitConfig.QUEUE_A)
    public void process(Message message) {
        logger.info("1号监听者处理器one接收处理队列A当中的消息： " + new String(message.getBody()));
    }
}

