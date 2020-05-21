package com.jiubo.account.dao;

import com.jiubo.account.bean.FirstMenuBean;
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
public interface FirstMenuDao extends BaseMapper<FirstMenuBean> {
    public List<FirstMenuBean> getFMByCondition(@Param("firstMenuBean") FirstMenuBean firstMenuBean);

    public int addFM(@Param("firstMenuBean") FirstMenuBean firstMenuBean);

    public void patchFMById(@Param("firstMenuBean") FirstMenuBean firstMenuBean);
}
