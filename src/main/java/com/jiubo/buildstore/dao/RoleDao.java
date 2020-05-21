package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.RoleBean;
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
public interface RoleDao extends BaseMapper<RoleBean> {
    public List<RoleBean> getRoleByIdList(@Param("roleBean") RoleBean roleBean);

    public int addRole(@Param("roleBean") RoleBean roleBean);

    public void patchRoleById(@Param("roleBean") RoleBean roleBean);
}
