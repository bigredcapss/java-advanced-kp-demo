package com.we.javaapi.rocketmq.transaction;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author BigRedCaps
 * @date 2021/10/3 1:04
 */
public class ICBCTransactionListener implements TransactionListener
{
    /**
     * 消息预提交成功就触发该回调操作
     * @param msg
     * @param arg
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction (Message msg, Object arg)
    {
        System.out.println("预提交消息成功："+msg);
        /**
         * 假设接收到TAGA的消息则表示扣款成功；若接收到TAGB的消息则表示扣款失败；
         * 若收到TAGC的消息则表示扣款结果不清楚，需要执行消息回查
         */
        if(StringUtils.equals("TAGA",msg.getTags())){
            return LocalTransactionState.COMMIT_MESSAGE;
        }else if(StringUtils.equals("TAGB",msg.getTags())){
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }else if(StringUtils.equals("TAGC",msg.getTags())){
            return LocalTransactionState.UNKNOW;
        }
        return LocalTransactionState.UNKNOW;
    }
    /**
     * 消息回查
     * @param msg
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction (MessageExt msg)
    {
        System.out.println("执行消息回查"+msg.getTags());
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
