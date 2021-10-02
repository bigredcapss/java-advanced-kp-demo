package com.we.javaapi.rocketmq.general;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author BigRedCaps
 * @date 2021/9/30 20:25
 */
public class SyncProducer
{
    public static void main(String[] args) throws Exception {
        //创建一个Producer，参数为Producer Group名字
        DefaultMQProducer producer = new
                DefaultMQProducer("we");
        //指定NameServer地址
        producer.setNamesrvAddr("192.168.197.129:9876");
        //启动生产者
        producer.start();
        //生产并发送100条消息
        for (int i = 0; i < 100; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("someTopic" /* Topic */,
                    "someTag" /* Tag */,
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
