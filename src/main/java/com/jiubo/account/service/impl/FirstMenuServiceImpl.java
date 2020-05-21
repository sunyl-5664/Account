package com.jiubo.account.service.impl;

import com.jiubo.account.bean.FirstMenuBean;
import com.jiubo.account.dao.FirstMenuDao;
import com.jiubo.account.service.FirstMenuService;
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
public class FirstMenuServiceImpl extends ServiceImpl<FirstMenuDao, FirstMenuBean> implements FirstMenuService {

    @Autowired
    private FirstMenuDao firstMenuDao;
    @Override
    public List<FirstMenuBean> getFMByCondition() {
        return firstMenuDao.getFMByCondition(new FirstMenuBean());
    }

    @Override
    public void addFM(FirstMenuBean firstMenuBean) {
        firstMenuBean.setCreateTime(new Date());
        firstMenuDao.addFM(firstMenuBean);
    }

    @Override
    public void patchFMById(FirstMenuBean firstMenuBean) {
        firstMenuDao.patchFMById(firstMenuBean);
    }
}
