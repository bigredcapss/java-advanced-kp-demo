package com.we.javaapi.rocketmq.filter;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @author BigRedCaps
 * @date 2021/10/3 15:07
 */
public class FilterBySqlProducer
{
    public static void main (String[] args) throws Exception
    {
        DefaultMQProducer producer = new DefaultMQProducer("pg");
        producer.setNamesrvAddr("rocketmqOS:9876");
        producer.start();

        for (int i = 0; i < 10; i++)
        {
            try{
                byte[] body = ("Hi," + i).getBytes();
                Message msg = new Message("myTopic", "myTag", body);
                // 事先埋入用户属性age
                msg.putUserProperty("age",i+"");
                SendResult sendResult = producer.send(msg);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        producer.shutdown();
    }
}
