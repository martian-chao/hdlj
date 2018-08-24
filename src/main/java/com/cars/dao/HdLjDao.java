package com.cars.dao;

import com.cars.model.HdLcrqModel;
import com.cars.model.HdModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 接收端dao
 * Created by liuyanchao
 * on 2018/8/14.
 */
@Repository
public class HdLjDao {
    @Value("#{jdbcTemplate}")
    private JdbcTemplate jdbcTemplate;
    @Value("#{namedParameterJdbcTemplate}")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private String hdLjdm;

    /**
     * 根据日期获取 货调数据
     *
     * @param rq
     * @return
     */
    public List<HdModel> listHdLj(String rq) {
        String sql = "select zcrq,lwdw,seqnum,pzycfh,jhid,qqlx,to_char(qqcs) qqcs,to_char(qqds) qqds, qqcz, " +
                "   to_char(pzcs) pzcs, to_char(pzds) pzds, pzcz, wpyyhz, rjhh, substr(sprdm, 1, 1) sprdm, to_char(ybjh) ybjh," +
                "    to_char(ybjhds) ybjhds, to_char(dyjdjh) dyjdjh, to_char(dyjdjhds) dyjdjhds, to_char(jdzc1) jdzc1, to_char(jdzcds1) jdzcds1," +
                "    to_char(jddz1) jddz1, to_char(yjcs1) yjcs1, to_char(yjds1) yjds1, ope1, to_char(jdzc2) jdzc2, to_char(jdzcds2) jdzcds2, " +
                "   to_char(jddz2) jddz2, to_char(jdtz1) jdtz1, to_char(jdtzds1) jdtzds1, to_char(yjcs2) yjcs2, to_char(yjds2) yjds2, " +
                "   to_char(yjqr) yjqr, to_char(yjqrds) yjqrds, ope2, to_char(jdzc3) jdzc3, to_char(jdzcds3) jdzcds3, to_char(jddz3) jddz3, " +
                "   to_char(yjcs3) yjcs3, to_char(yjds3) yjds3, ope3, to_char(jdzc4) jdzc4, to_char(jdzcds4) jdzcds4, to_char(jddz4) jddz4," +
                "    zccz, ope4, htddh, fj, fz, fzhzzm, fhdwdm, fhdwsrm, fhdwmc, fhdwtz, fhdwtzm, fhbm, fhbmm, " +
                "   dfj, dfjhz, dj, djhz, dz, dzhzzm, shdwdm, shdwsrm, shdwmc, shdwtz, shdwtzm, shbm, shbmm, " +
                "   pm, hzpm, hzpl, ystz, ystzhz, hzg, hzghz, zdg, zdghz, to_char(dcf) dcf, th, cc, jy, hzjy, zyxm, " +
                "   txzbz, hqhw, to_char(czsh) czsh, to_char(lkyy1cs) lkyy1cs, to_char(lkyy2cs) lkyy2cs, to_char(lkyy3cs) lkyy3cs, " +
                "   to_char(lkyy4cs) lkyy4cs, to_char(lkyy5cs) lkyy5cs, to_char(lkyy6cs) lkyy6cs, to_char(hpsr) hpsr, to_char(hysr) hysr, " +
                "   to_char(jsjj) jsjj, to_char(yl1) yl1, yl2, yl3,to_char(yl4) yl4,to_char(yl5) yl5,to_char(yl6) yl6, " +
                "   to_char(yl7) yl7, dzyxm, ddh, ddtz, ddtzhz,qsqqcs,hscs,shjycs,jhtz,tzyybz,lkyy,lkyybz,auto," +
                "   (select csdm from zdzhcs t where t.cslb='LJ' and t.csbs=1) ljdm " +
                "   from v_z_zcrbjh " +
                "   where zcrq>=? ";
        return jdbcTemplate.query(sql, new Object[]{rq}, BeanPropertyRowMapper.newInstance(HdModel.class));
    }

    /**
     * 落成日期
     *
     * @return
     */
    public HdLcrqModel listHdLjLcrq() {
        String sql = "select csdm ljdm,(select dmhz from zdzhcs t where t.cslb='RQ' and t.csdm='WC') lcrq " +
                "   from zdzhcs t where t.cslb='LJ' and t.csbs=1";
        List<HdLcrqModel> list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(HdLcrqModel.class));
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
