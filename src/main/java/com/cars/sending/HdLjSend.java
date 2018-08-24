package com.cars.sending;

import com.cars.model.HdLcrqModel;
import com.cars.model.HdModel;
import com.cars.service.HdLjService;
import com.cars.util.date.DateUtil;
import com.cars.util.json.JsonUtil;
import com.cars.util.log.LogUtil;
import com.cars.util.rabbitmq.RabbitmqUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 货调mq数据接收端
 * Created by liuyanchao
 * on 2018/8/13.
 */
@Component
public class HdLjSend {
    @Value("#{hdLjService}")
    private HdLjService hdLjService;
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 发送-路局货调数据
     *  5分钟上报一次数据
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void saveHd() {
        LogUtil.infoHttp("发送-路局货调-汇总数据-开始时间"+DateUtil.getSystemTime()
                +",当前汇总队列中未消费的个数为："+ RabbitmqUtil.getSysRabbitmq().getMessageCount1());
        //获取路局数据数据
        String rq = DateUtil.getShortSystemDate();//当日日期
//        String rq = "20180501";//当日日期
        List<HdModel> list = hdLjService.listHdLj(rq);
        if (list!=null && list.size()>0){
            String json = JsonUtil.toStr(list);
            //将数据发送到mq中
            hdLjService.sendHdLj(json);
        }else {
            LogUtil.infoHttp("数据为空，没有数据发送");
        }
    }

    /**
     * 落成日期
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void saveHdLcrq(){
        LogUtil.infoHttp("发送-路局货调-落成日期数据-开始时间"+DateUtil.getSystemTime()
                +",当前落成日期队列中未消费的个数为："+ RabbitmqUtil.getSysRabbitmq().getMessageCount2());
        //获取路局落成日期数据
        HdLcrqModel hdLcrqModel = hdLjService.listHdLjLcrq();
        if (hdLcrqModel!=null){
            String json = JsonUtil.toStr(hdLcrqModel);
            //将数据发送到mq中
            hdLjService.sendHdLcrq(json);
        }else {
            LogUtil.infoHttp("数据为空，没有数据发送");
        }
    }

}
