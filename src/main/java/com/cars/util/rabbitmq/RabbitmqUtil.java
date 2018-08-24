package com.cars.util.rabbitmq;

import com.cars.model.SysRabbitmq;
import com.cars.util.queue.QueueName;
import com.cars.util.spring.SpringContextHolder;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**Rabbitmq 工具类
 * Created by liuyanchao
 * on 2018/4/15.
 */
public class RabbitmqUtil {
    private static SysRabbitmq sysRabbitmq;

    static {
        sysRabbitmq = SpringContextHolder.getBean("sysRabbitmq");
    }

    /**
     * 获取队列未消费的消息数
     * @return
     */
    public static SysRabbitmq getSysRabbitmq(){
        //链接mq
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(sysRabbitmq.getIp());
        factory.setPort(sysRabbitmq.getPort());
        factory.setUsername(sysRabbitmq.getUsername());
        factory.setPassword(sysRabbitmq.getPassword());
        factory.setVirtualHost(sysRabbitmq.getVhost());
        try {
            Connection conn = factory.newConnection();
            Channel channel = conn.createChannel();
            sysRabbitmq.setMessageCount1(channel.messageCount(QueueName.HD_HDHZ_QUEUE));//查询队列未消费的消息数
            sysRabbitmq.setMessageCount2(channel.messageCount(QueueName.HD_LCRQHZ_QUEUE));//查询队列未消费的消息数
            channel.close();
            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return sysRabbitmq;
    }

}
