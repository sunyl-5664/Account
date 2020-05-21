package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.FirstMenuBean;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dx
 * @since 2020-05-20
 */
public interface FirstMenuService extends IService<FirstMenuBean> {

    public List<FirstMenuBean> getFMByCondition();

    public void addFM(FirstMenuBean firstMenuBean);

    public void patchFMById(FirstMenuBean firstMenuBean);
}
