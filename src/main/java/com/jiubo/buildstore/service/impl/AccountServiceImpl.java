package com.jiubo.buildstore.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiubo.buildstore.Exception.MessageException;
import com.jiubo.buildstore.bean.*;
import com.jiubo.buildstore.common.Constant;
import com.jiubo.buildstore.dao.*;
import com.jiubo.buildstore.service.AccountService;
import com.jiubo.buildstore.util.CollectionsUtils;
import com.jiubo.buildstore.util.CookieTools;
import com.jiubo.buildstore.util.MD5Util;
import com.jiubo.buildstore.util.TokenUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mwl
 * @since 2020-02-10
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Value("${tokenLife}")
    private int tokenLife;

    @Value("${accountLife}")
    private int accountLife;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private FirstMenuDao firstMenuDao;
    @Autowired
    private SecondMenuDao secondMenuDao;
    @Autowired
    private RoleMenuRefDao roleMenuRefDao;
    @Autowired
    private AccountRoleRefDao accountRoleRefDao;
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<AccountBean> queryAccountList(AccountBean accountBean) throws Exception {
        return accountDao.queryAccountList(accountBean);
    }

    @Override

    public JSONObject login(AccountBean accountBean) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isBlank(accountBean.getAccount())) throw new MessageException("账号不能为空!");
        if (StringUtils.isBlank(accountBean.getPwd())) throw new MessageException("密码不能为空!");
        String pwd = MD5Util.md5Encrypt32Lower(accountBean.getPwd());
        System.out.println("加密：" + pwd);
        accountBean.setPwd(pwd);
        List<AccountBean> accountBeans = queryAccountList(accountBean);
        AccountBean bean = null;
        if (accountBeans.isEmpty()) {
            throw new MessageException("账号或密码错误!");
        } else {
            bean = accountBeans.get(0);
            bean.setPwd("");

            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
//            String accessToken = URLEncoder.encode(bean.getAccount().concat("604800"), Constant.Charset.UTF8);
            // 用户个人信息及token
            String accessToken = TokenUtils.token(accountBean.getAccount(),accountBean.getPwd());
            jsonObject.put("accessToken", accessToken);
            jsonObject.put("accountData", bean);
            // 用户权限
            Integer saId = bean.getSaId();
            List<AccountRoleRefBean> arRefByAccIdList = accountRoleRefDao.getARRefByAccId(saId);
            // 用户角色
            if (!CollectionsUtils.isEmpty(arRefByAccIdList)) {
                List<Integer> roleIdList = arRefByAccIdList.stream().map(AccountRoleRefBean::getRoleId).collect(Collectors.toList());
                List<RoleBean> roleBeanList = roleDao.getRoleByIdList(new RoleBean().setIdList(roleIdList));
                bean.setRoleBeanList(roleBeanList);

                // 根据角色查出对应菜单
                List<RoleMenuRefBean> rmrByConditionList = roleMenuRefDao.getRMRByCondition(new RoleMenuRefBean().setRoleIdList(roleIdList));

                if (!CollectionsUtils.isEmpty(rmrByConditionList)) {
                    // 一级菜单id集合
                    List<Integer> firstIdList = rmrByConditionList.stream().map(RoleMenuRefBean::getFirstId).distinct().collect(Collectors.toList());
                    // 一级菜单集合
                    List<FirstMenuBean> fmByConditionList = firstMenuDao.getFMByCondition(new FirstMenuBean().setIdList(firstIdList));
                    // 二级菜单id集合
                    List<Integer> secondIdList = rmrByConditionList.stream().map(RoleMenuRefBean::getSecondId).distinct().collect(Collectors.toList());
                    // 二级菜单集合
                    List<SecondMenuBean> smByConditionList = secondMenuDao.getSMByCondition(new SecondMenuBean().setIdList(secondIdList));
                    Map<Integer, List<SecondMenuBean>> secondMap = null;
                    if (!CollectionsUtils.isEmpty(smByConditionList)) {
                        secondMap = smByConditionList.stream().collect(Collectors.groupingBy(SecondMenuBean::getFirstId));
                    }
                    // 一级 二级菜单绑定关系
                    for (FirstMenuBean firstMenuBean : fmByConditionList) {
                        if (null != secondMap) {
                            List<SecondMenuBean> secondMenuBeans = secondMap.get(firstMenuBean.getId());
                            firstMenuBean.setSecondMenuBeanList(secondMenuBeans);
                        }
                    }
                    jsonObject.put("menuData",fmByConditionList);
                }
            }

            CookieTools.addCookie(response, "accessToken", accessToken, tokenLife);
            CookieTools.addCookie(response, "accountData", URLEncoder.encode(JSON.toJSONString(bean), Constant.Charset.UTF8), accountLife);
        }
        return jsonObject;
    }


    public AccountBean addAccount(AccountBean accountBean) throws Exception {
        List<AccountBean> accountBeans = queryAccountList(accountBean);
        if (StringUtils.isBlank(accountBean.getPwd())) throw new MessageException("密码为空");
        if (null != accountBeans && accountBeans.size() > 0 ) throw new MessageException("该账号已存在");
        accountDao.addAccount(accountBean);

        List<Integer> roleIdList = accountBean.getRoleIdList();
        if (!CollectionsUtils.isEmpty(roleIdList)) {
            List<RoleBean> roleBeanList = roleDao.getRoleByIdList(new RoleBean().setIdList(roleIdList));
            List<AccountRoleRefBean> accountRoleRefBeans = new ArrayList<>();
            for (Integer roleId : roleIdList) {
                AccountRoleRefBean accountRoleRefBean = new AccountRoleRefBean();
                accountRoleRefBean.setAccountId(accountBean.getSaId());
                accountRoleRefBean.setRoleId(roleId);
                accountRoleRefBean.setCreateTime(new Date());
                accountRoleRefBeans.add(accountRoleRefBean);
            }
            accountRoleRefDao.addARRef(accountRoleRefBeans);
            accountBean.setRoleBeanList(roleBeanList);
        }

        return accountBean;
    }

    @Override
    public void patchAccount(AccountBean accountBean) throws Exception {
        List<AccountBean> accountBeans = queryAccountList(new AccountBean().setAccount(accountBean.getAccount()));
        if (null != accountBeans && accountBeans.size() > 0 ) throw new MessageException("该账号已存在");
//        List<AccountBean> accountBeanList = queryAccountList(new AccountBean().setPwd(accountBean.getPwd()));
//        if (null == accountBeanList || accountBeanList.size() == 0 ) throw new MessageException("原密码输入不正确");
        accountDao.patchAccount(accountBean);
    }
}
