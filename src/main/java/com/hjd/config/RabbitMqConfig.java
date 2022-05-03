package com.hjd.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class RabbitMqConfig {


    @Bean("queueOne")
    public Queue queueOne()
    {
        //
        return QueueBuilder.durable("queueOne")
                // 正常的队列，在消息失效后，需要将消息丢入 死信 交换机中
                // 这里只需要针对名称进行绑定
                .withArgument("x-dead-letter-exchange","getDeadExchange")
                // 丢入 死信交换机，需要设定指定的 routingkey
                .withArgument("x-dead-letter-routing-key", "dead")
                // 设置正常队列中消息的存活时间为 10s，当然也可以针对单个消息进行设定不同的过期时间
                .withArgument("x-message-ttl",10000)
                // 设定当前队列中，允许存放的最大消息数目
                .withArgument("x-max-length",10)
                .build();
    }
    @Bean("fanoutExchange")
    public FanoutExchange fanoutExchange()
    {
        return new FanoutExchange("fanoutExchange");
    }
    @Bean
    public Binding bingingOne (@Qualifier("queueOne") Queue queue, @Qualifier("fanoutExchange") FanoutExchange fanoutExchange)
    {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    /**
     * 定义死信队列
     * @return
     */
    @Bean(value = "getDealQueue")
    public Queue getDealQueue(){
        return QueueBuilder.durable("getDealQueue").build();
    }

    /**
     * 定义死信交换机
     * @return
     */
    @Bean(value = "getDeadExchange")
    public Exchange getDeadExchange(){
        return ExchangeBuilder.fanoutExchange("getDeadExchange").durable(true).build();
    }

    /**
     * 将死信交换机和死信队列进行绑定
     * @param deadQueue
     * @param directDeadExchange
     * @return
     */
    @Bean
    public Binding bindDeadExchangeAndQueue(
            @Qualifier(value = "getDealQueue") Queue deadQueue,
            @Qualifier(value = "getDeadExchange") Exchange directDeadExchange
    ){
        return BindingBuilder.bind(deadQueue).to(directDeadExchange).with("").noargs();
    }

}

