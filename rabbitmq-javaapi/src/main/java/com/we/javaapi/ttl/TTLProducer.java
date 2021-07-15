package com.we.javaapi.ttl;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.we.javaapi.util.ResourceUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息生产者，通过TTL测试死信队列
 * @author we
 * @date 2021-07-15 10:04
 **/
public class TTLProducer {
    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));

        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();

        String msg = "Hello world,RabbitMQ,DLX MSG";
        // 通过队列属性设置消息过期时间
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-message-ttl",6000);

        // 声明队列（默认交换机AMQP default,Direct）
        // String queue,boolean durable,boolean exclusive,boolean autoDelete,Map<String,Object> arguments
        channel.queueDeclare("TEST_TTL_QUEUE",false,false,false,arguments);

        // 对每条消息设置过期时间
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .deliveryMode(2) // 持久化消息
                .contentEncoding("UTF-8")
                .expiration("10000")
                .build();

        // 此处两种方式设置消息过期时间的方式都使用了，将以较小的数值为准

        // 发送消息
        channel.basicPublish("", "TEST_DLX_QUEUE", properties, msg.getBytes());

        channel.close();
        conn.close();
    }

}
