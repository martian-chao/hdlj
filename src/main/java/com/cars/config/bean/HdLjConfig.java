package com.cars.config.bean;

import com.cars.util.spring.SpringContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**配置
 * Created by liuyanchao
 * on 2018/8/17.
 */
@Configuration
public class HdLjConfig {

    @Bean
    public SpringContextHolder springContextHolder() {
         return new SpringContextHolder();
    }

}
