package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.SecondMenuBean;
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
public interface SecondMenuService extends IService<SecondMenuBean> {

    public List<SecondMenuBean> getAllSM();

    public void addSM(SecondMenuBean secondMenuBean);

    public void patchSMById(SecondMenuBean secondMenuBean);
}
