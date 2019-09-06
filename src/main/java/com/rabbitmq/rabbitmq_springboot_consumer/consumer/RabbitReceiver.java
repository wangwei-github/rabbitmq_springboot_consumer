package com.rabbitmq.rabbitmq_springboot_consumer.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RabbitReceiver {

    /**
     * spring.rabbitmq.listener.order.queue=queue-1
     * spring.rabbitmq.listener.order.true=true
     * spring.rabbitmq.listener.order.false=false
     * spring.rabbitmq.listener.order.exchange=exchange-1
     * spring.rabbitmq.listener.order.exchange-type=topic
     * spring.rabbitmq.listener.order.routing-key=springboot.*
     * @param
     * @param
     * @throws Exception
     */
   /* @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.order.queue}",
                    durable = "${spring.rabbitmq.listener.order.true}",
                    autoDelete = "${spring.rabbitmq.listener.order.false}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.order.exchange}",
                    type = "${spring.rabbitmq.listener.order.exchange-type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.order.true}"),
            key ="${spring.rabbitmq.listener.order.routing-key}" )
    )
    @RabbitHandler
    public void onMessage(Message message, Channel channel)throws Exception{
        System.err.println("Body:"+message.getPayload());
        System.err.println(message.getHeaders().get("send_time")+"  :  "+message.getHeaders().get("num"));
        long deliveryTag = (long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag,false);
    }*/

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.order.queue}",
                    durable = "${spring.rabbitmq.listener.order.true}",
                    autoDelete = "${spring.rabbitmq.listener.order.false}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.order.exchange}",
                    type = "${spring.rabbitmq.listener.order.exchange-type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.order.true}"),
            key ="${spring.rabbitmq.listener.order.routing-key}" )
    )
    @RabbitHandler
    public void onOrderMessage(@Payload com.rabbitmq.rabbitmq_springboot_consumer.entity.Order order, Channel channel, @Headers
                               Map<String,Object> headers)throws Exception{
        System.err.println("消费端："+order.getName());
        long deliveryTag = (long)headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag,false);
    }
}
