package com.jiubo.buildstore.service.impl;

import com.jiubo.buildstore.bean.RoleBean;
import com.jiubo.buildstore.bean.RoleMenuRefBean;
import com.jiubo.buildstore.bean.SecondMenuBean;
import com.jiubo.buildstore.dao.RoleDao;
import com.jiubo.buildstore.dao.RoleMenuRefDao;
import com.jiubo.buildstore.dao.SecondMenuDao;
import com.jiubo.buildstore.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiubo.buildstore.util.CollectionsUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleBean> implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleMenuRefDao roleMenuRefDao;
    @Autowired
    private SecondMenuDao secondMenuDao;
    @Override
    public List<RoleBean> getAllRole() {
        return roleDao.getRoleByIdList(new RoleBean());
    }

    @Override
    public void addRole(RoleBean roleBean) {
        roleBean.setCreateTime(new Date());
        roleDao.addRole(roleBean);
        List<Integer> secondIdList = roleBean.getSecondIdList();
        List<RoleMenuRefBean> roleMenuRefBeanList = new ArrayList<>();
        if (!CollectionsUtils.isEmpty(secondIdList)) {
            List<SecondMenuBean> secondMenuBeans = secondMenuDao.getSMByCondition(new SecondMenuBean().setIdList(secondIdList));
            bindRoleMenuRef(roleBean, roleMenuRefBeanList, secondMenuBeans);
        } else {
            List<Integer> firstIdList = roleBean.getFirstIdList();
            if (!CollectionsUtils.isEmpty(firstIdList)) {
                List<SecondMenuBean> secondMenuBeans = secondMenuDao.getSMByCondition(new SecondMenuBean().setFirstIdList(firstIdList));
                if (!CollectionsUtils.isEmpty(secondMenuBeans)) {
                    bindRoleMenuRef(roleBean, roleMenuRefBeanList, secondMenuBeans);
                }
            } else {
                log.warn("添加角色时没给角色添加菜单"+roleBean);
            }
        }
        if (!CollectionsUtils.isEmpty(roleMenuRefBeanList)) {
            roleMenuRefDao.addRMR(roleMenuRefBeanList);
        }
    }

    private void bindRoleMenuRef(RoleBean roleBean, List<RoleMenuRefBean> roleMenuRefBeanList, List<SecondMenuBean> secondMenuBeans) {
        for (SecondMenuBean secondMenuBean : secondMenuBeans) {
            RoleMenuRefBean roleMenuRefBean = new RoleMenuRefBean();
            roleMenuRefBean.setRoleId(roleBean.getId());
            roleMenuRefBean.setFirstId(secondMenuBean.getFirstId());
            roleMenuRefBean.setSecondId(secondMenuBean.getId());
            roleMenuRefBean.setCreateTime(new Date());
            roleMenuRefBeanList.add(roleMenuRefBean);
        }
    }

    @Override
    public void patchRoleById(RoleBean roleBean) {
        if (!StringUtils.isBlank(roleBean.getRoleName())) {
            roleDao.patchRoleById(roleBean);
        }
        List<Integer> secondIdList = roleBean.getSecondIdList();
        List<RoleMenuRefBean> roleMenuRefBeanList = new ArrayList<>();
        if (!CollectionsUtils.isEmpty(secondIdList)) {
            // 解绑
            roleMenuRefDao.deleteRMRByRoleId(roleBean.getId());
            // 重新绑定
            List<SecondMenuBean> secondMenuBeans = secondMenuDao.getSMByCondition(new SecondMenuBean().setIdList(secondIdList));
            bindRoleMenuRef(roleBean, roleMenuRefBeanList, secondMenuBeans);
        } else {

            List<Integer> firstIdList = roleBean.getFirstIdList();
            if (!CollectionsUtils.isEmpty(firstIdList)) {
                // 解绑
                roleMenuRefDao.deleteRMRByRoleId(roleBean.getId());
                // 重新绑定
                List<SecondMenuBean> secondMenuBeans = secondMenuDao.getSMByCondition(new SecondMenuBean().setFirstIdList(firstIdList));
                if (!CollectionsUtils.isEmpty(secondMenuBeans)) {
                    bindRoleMenuRef(roleBean, roleMenuRefBeanList, secondMenuBeans);
                }
            }
        }
        if (!CollectionsUtils.isEmpty(roleMenuRefBeanList)) {
            roleMenuRefDao.addRMR(roleMenuRefBeanList);
        }
    }
}
