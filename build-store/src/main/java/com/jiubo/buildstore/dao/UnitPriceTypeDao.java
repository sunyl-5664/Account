package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.UnitPriceTypeBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author syl
 * @since 2020-04-10
 */
public interface UnitPriceTypeDao extends BaseMapper<UnitPriceTypeBean> {
    public List<UnitPriceTypeBean> getAllUnitPrice();
}
