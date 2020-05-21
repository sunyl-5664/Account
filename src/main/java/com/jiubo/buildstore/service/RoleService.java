package com.jiubo.buildstore.service;

import com.jiubo.buildstore.bean.RoleBean;
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
public interface RoleService extends IService<RoleBean> {
    public List<RoleBean> getAllRole();

    public void addRole(RoleBean roleBean);

    public void patchRoleById(RoleBean roleBean);
}
