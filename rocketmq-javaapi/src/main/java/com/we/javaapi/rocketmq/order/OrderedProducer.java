package com.we.javaapi.rocketmq.order;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

/**
 * @author BigRedCaps
 * @date 2021/10/2 9:40
 */
public class OrderedProducer {
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        MQProducer producer = new DefaultMQProducer("pg");
        ((DefaultMQProducer) producer).setNamesrvAddr("rocketmqOS:9876");
        // 若为全局有序，则需要设置Queue的数量为1
        // producer.setDefaultTopicQueueNums(1);

        //Launch the instance.
        producer.start();
        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 100; i++) {
            int orderId = i % 10;
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTestjjj", tags[i % tags.length], "KEY" + i,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            // send()方法的第三个参数值会传递给选择器的select()方法的第三个参数
            // 该send()方法为同步发送
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                // 具体的选择算法在该方法中定义
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    // 以下是使用消息key作为选择key
                    String keys = msg.getKeys();
                    Integer id = Integer.valueOf(keys);


                    // 以下是使用arg作为选择key的选择算法
                    // Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
                }
            }, orderId);

            System.out.printf("%s%n", sendResult);
        }
        //server shutdown
        producer.shutdown();
    }
}
