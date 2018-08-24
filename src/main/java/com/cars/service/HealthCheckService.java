package com.cars.service;

import com.cars.dao.HealthCheckDao;
import com.cars.model.IPModel;
import com.cars.util.http.HttpUtil;
import com.cars.util.log.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**健康检查
 * Created by liuyanchao
 * on 2018/8/17.
 */
@Service
public class HealthCheckService {
    @Value("#{healthCheckDao}")
    private HealthCheckDao healthCheckDao;
    @Autowired
    private IPModel ipModel;

    /**
     * 获取服务时间
     * @return
     */
    public String getDete() {
        return healthCheckDao.getDete();
    }
    /**
     * 获取路局代码
     * @return
     */
    public String getLjdm() {
        return healthCheckDao.getLjdm();
    }

    /**
     * 传送ip
     */
    @Scheduled(cron = "0 0/30 * * * ?")
    public void toSendIp(){
        String ip = HttpUtil.httpGet("http://"+ipModel.getIp() + ":8090/hdzgs/getIp");
        LogUtil.infoHttp("发送ip:"+ip);
    }
}
