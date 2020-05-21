package com.jiubo.account.service;

import com.jiubo.account.bean.FirstMenuBean;
import com.baomidou.mybatisplus.extension.service.IService;

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
