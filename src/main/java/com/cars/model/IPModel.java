package com.cars.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by liuyanchao
 * on 2018/8/24.
 */
@Component
public class IPModel {
    @Value("${zgs.ip}")
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "IPModel{" +
                "ip='" + ip + '\'' +
                '}';
    }
}
