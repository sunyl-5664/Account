package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.SecondMenuBean;
import com.jiubo.buildstore.dao.SecondMenuDao;
import com.jiubo.buildstore.service.SecondMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dx
 * @since 2020-05-20
 */
@Service
public class SecondMenuServiceImpl extends ServiceImpl<SecondMenuDao, SecondMenuBean> implements SecondMenuService {

    @Autowired
    private SecondMenuDao secondMenuDao;
    @Override
    public List<SecondMenuBean> getAllSM() {
        return secondMenuDao.getSMByCondition(new SecondMenuBean());
    }

    @Override
    public void addSM(SecondMenuBean secondMenuBean) {
        secondMenuBean.setCreateTime(new Date());
        secondMenuDao.addSM(secondMenuBean);
    }

    @Override
    public void patchSMById(SecondMenuBean secondMenuBean) {
        secondMenuDao.patchSMById(secondMenuBean);
    }
}
