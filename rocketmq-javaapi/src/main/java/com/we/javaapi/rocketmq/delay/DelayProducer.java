package com.we.javaapi.rocketmq.delay;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import javax.sound.midi.Soundbank;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author BigRedCaps
 * @date 2021/10/2 10:13
 */
public class DelayProducer
{
    public static void main (String[] args) throws Exception
    {
        DefaultMQProducer producer = new DefaultMQProducer("pg");
        producer.setNamesrvAddr("rocketmqOS:9876");
        producer.start();

        for (int i = 0; i < 10; i++)
        {
            byte[] body = ("Hi," + i).getBytes();
            Message msg = new Message("TopicB", "someTag", body);
            // 指定消息延迟等级为3级,即延时10s
            msg.setDelayTimeLevel(3);
            SendResult sendResult = producer.send(msg);
            System.out.println(new SimpleDateFormat("mm:ss").format(new Date()));
            System.out.println(","+sendResult);
        }
        producer.shutdown();
    }
}
