package com.cars.test;

import com.cars.model.SysRabbitmq;
import com.cars.sending.HdLjSend;
import com.cars.util.rabbitmq.RabbitmqUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**测试mq队列深度
 * Created by liuyanchao
 * on 2018/8/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRabbitmq {
    @Autowired
    private HdLjSend hdLjSend;
    @Test
    public void testRabbitmq(){
        SysRabbitmq sysRabbitmq = RabbitmqUtil.getSysRabbitmq();
        System.out.println("!!!!!"+sysRabbitmq);

    }
    @Test
    public void testGetHz(){
        hdLjSend.saveHd();
    }
}
