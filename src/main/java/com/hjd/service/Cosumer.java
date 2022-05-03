package com.hjd.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


import java.util.concurrent.TimeUnit;

/**
 * @author hujiande
 */
@Service
public class Cosumer {

    @RabbitListener(queues = "queueOne")
    public void cus1(String msg, Channel channel) throws InterruptedException {
        System.out.println("开始消费");
        TimeUnit.SECONDS.sleep(10);
        System.out.println(msg);
        System.out.println("消费结束");

    }
}
