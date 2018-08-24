package com.cars.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**健康检查
 * Created by liuyanchao
 * on 2018/8/17.
 */
@Repository
public class HealthCheckDao {
    @Value("#{jdbcTemplate}")
    private JdbcTemplate jdbcTemplate;
    /**
     * 获取服务时间
     * @return
     */
    public String getDete() {
        String sql = "select to_char(sysdate,'yyyy-MM-dd hh24:mi:ss') sj from dual";
        return jdbcTemplate.queryForObject(sql,String.class);
    }

    /**
     * 获取路局代码
     * @return
     */
    public String getLjdm() {
        String sql = "select csdm from zdzhcs t where t.cslb='LJ' and t.csbs=1 ";
        return jdbcTemplate.queryForObject(sql,String.class);
    }
}
