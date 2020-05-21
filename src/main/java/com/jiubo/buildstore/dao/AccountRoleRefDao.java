package com.jiubo.buildstore.dao;

import com.jiubo.buildstore.bean.AccountRoleRefBean;
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
public interface AccountRoleRefDao extends BaseMapper<AccountRoleRefBean> {

    public List<AccountRoleRefBean> getARRefByAccId(@Param("accountId") Integer accountId);

    public void addARRef(List<AccountRoleRefBean> accountRoleRefBeanList);

    public void deleteARRefByAccountId(@Param("accountId") Integer accountId);
}
