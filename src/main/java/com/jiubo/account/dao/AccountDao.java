package com.jiubo.account.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiubo.account.bean.AccountBean;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author mwl
 * @since 2020-02-10
 */
public interface AccountDao extends BaseMapper<AccountBean> {
    public List<AccountBean> queryAccountList(AccountBean accountBean);

    public int addAccount(AccountBean accountBean);

    public void patchAccount(AccountBean accountBean);
}
