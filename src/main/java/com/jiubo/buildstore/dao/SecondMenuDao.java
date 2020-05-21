package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.SecondMenuBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dx
 * @since 2020-05-20
 */
public interface SecondMenuDao extends BaseMapper<SecondMenuBean> {
    public List<SecondMenuBean> getSMByCondition(@Param("secondMenuBean") SecondMenuBean secondMenuBean);

    public int addSM(@Param("secondMenuBean") SecondMenuBean secondMenuBean);

    public void patchSMById(@Param("secondMenuBean") SecondMenuBean secondMenuBean);
}
