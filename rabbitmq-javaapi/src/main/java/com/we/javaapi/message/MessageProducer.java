package com.we.javaapi.message;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.we.javaapi.util.ResourceUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * 在消息中添加更多属性
 * @author we
 * @date 2021-07-15 10:21
 **/
public class MessageProducer {
    public static final String QUEUE_NAME = "ORIGIN_QUEUE";

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));

        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();

        Map<String, Object> headers = new HashMap<>();
        headers.put("name","we");
        headers.put("level","top");

        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .deliveryMode(2) // 2代表持久化
                .contentEncoding("UTF-8") // 编码
                .expiration("10000") // TTL 过期时间
                .headers(headers) // 自定义属性
                .priority(5) // 优先级，默认是5，配合队列的x-max-priority属性使用
                .messageId(String.valueOf(UUID.randomUUID()))
                .build();

        String msg = "Hello world,RabbitMQ";
        // 声明队列（默认交换机AMQP default，Direct）
        // String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 发送消息
        // String exchange, String routingKey, BasicProperties props, byte[] body
        channel.basicPublish("", QUEUE_NAME, properties, msg.getBytes());

        channel.close();
        conn.close();
    }
}
