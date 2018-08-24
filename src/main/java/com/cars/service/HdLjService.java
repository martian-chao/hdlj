package com.cars.service;

import com.cars.dao.HdLjDao;
import com.cars.model.HdLcrqModel;
import com.cars.model.HdModel;
import com.cars.util.queue.QueueName;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 接收端service
 * Created by liuyanchao
 * on 2018/8/14.
 */
@Service
public class HdLjService {
    @Value("#{hdLjDao}")
    private HdLjDao hdLjDao;
    @Value("#{rabbitTemplate}")
    private RabbitTemplate rabbitTemplate;


    /**
     * 根据日期获取 货调数据
     * @param rq
     * @return
     */
    public List<HdModel> listHdLj(String rq) {
        return hdLjDao.listHdLj(rq);
    }

    /**将货调数据发送到mq中
     *
     * @param json
     */
    public void sendHdLj(String json) {
        rabbitTemplate.convertAndSend(QueueName.HD_HDHZ_QUEUE, json);
    }

    /** 落成日期
     *
     * @return
     */
    public HdLcrqModel listHdLjLcrq() {
        return hdLjDao.listHdLjLcrq();
    }

    /**
     * 将落成日期数据发送到mq中
     * @param json
     */
    public void sendHdLcrq(String json) {
        rabbitTemplate.convertAndSend(QueueName.HD_LCRQHZ_QUEUE, json.getBytes());
    }
}
