package com.we.javaapi.rpc;

import com.rabbitmq.client.*;
import com.we.javaapi.util.ResourceUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Rpc客户端，后启动
 * @author we
 * @date 2021-07-15 11:01
 **/
public class RpcClient {
    private final static String REQUEST_QUEUE_NAME="RPC_REQUEST";
    private final static String RESPONSE_QUEUE_NAME="RPC_RESPONSE";
    private Channel channel;
    private Consumer consumer;

    //构造函数 初始化连接
    public RpcClient() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));

        //创建一个新的连接 即TCP连接
        Connection connection = factory.newConnection();
        //创建一个通道
        channel = connection.createChannel();
        //创建一个请求队列
        channel.queueDeclare(REQUEST_QUEUE_NAME, true, false, false, null);
        //创建一个回调队列
        channel.queueDeclare(RESPONSE_QUEUE_NAME,true,false,false,null);
    }

    /**
     * PRC 远程调用计算平方
     * @param message
     * @return
     * @throws Exception
     */
    public String getSquare(String message) throws  Exception{
        //定义消息属性中的correlationId
        String correlationId = java.util.UUID.randomUUID().toString();

        //设置消息属性的replyTo和correlationId
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .correlationId(correlationId)
                .replyTo(RESPONSE_QUEUE_NAME)
                .build();

        // 发送消息到请求队列rpc_request队列
        // 消息发送到与routingKey参数相同的队列中
        channel.basicPublish("",REQUEST_QUEUE_NAME, properties,message.getBytes());

        // 从匿名内部类中获取返回值
        final BlockingQueue<String> response = new ArrayBlockingQueue<String>(1);

        // 创建消费者
        consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                response.offer(new String(body, "UTF-8"));
            }
        };

        // 开始获取消息
        // String queue, boolean autoAck, Consumer callback
        channel.basicConsume(RESPONSE_QUEUE_NAME, true, consumer);

        return response.take();
    }

    public static void main(String[] args) throws Exception {
        RpcClient rpcClient = new RpcClient();
        String result = rpcClient.getSquare("4");
        System.out.println("response is : " + result);
    }
}
