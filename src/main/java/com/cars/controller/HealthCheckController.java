package com.cars.controller;

import com.cars.service.HealthCheckService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**健康检查-路局端的心跳监控功能
 * Created by liuyanchao
 * on 2018/8/17.
 */
@Controller
public class HealthCheckController {
    @Value("#{healthCheckService}")
    private HealthCheckService healthCheckService;

    /**
     * 健康检查请求
     * @param response
     */
    @RequestMapping("/healthCheck")
    public void healthCheck(HttpServletResponse response){
        try {
            response.setContentType("text/html;charset=UTF-8");
//            response.getWriter().print(healthCheckService.getDete());
            response.getWriter().print(healthCheckService.getLjdm());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
