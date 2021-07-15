package com.we.javaapi.returnlistener;

import com.rabbitmq.client.*;
import com.we.javaapi.util.ResourceUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 当消息无法匹配到队列，会发回给生产者
 * @author we
 * @date 2021-07-15 10:42
 **/
public class ReturnListenerProducer {
    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode,
                                     String replyText,
                                     String exchange,
                                     String routingKey,
                                     AMQP.BasicProperties basicProperties,
                                     byte[] body) throws IOException {
                System.out.println("=========监听器收到了无法路由，被返回的消息============");
                System.out.println("replyText:"+replyText);
                System.out.println("exchange:"+exchange);
                System.out.println("routingKey:"+routingKey);
                System.out.println("message:"+new String(body));
            }
        });

        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().deliveryMode(2).
                contentEncoding("UTF-8").build();

        // 在声明交换机的时候指定备份交换机
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("alternate-exchange","ALTERNATE_EXCHANGE");
        channel.exchangeDeclare("TEST_EXCHANGE","topic",false,false,false,arguments);

        // 发送到了默认的交换机上，由于没有任何队列使用这个关键字跟交换机绑定，所以会被退回
        // 第三个参数是设置的mandatory，如果mandatory是false，消息也会被直接丢弃
        channel.basicPublish("","gupaodirect",true, properties,"只为更好的你".getBytes());

        TimeUnit.SECONDS.sleep(10);

        channel.close();
        connection.close();
    }
}
