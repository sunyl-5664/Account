package com.jiubo.buildstore.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author mwl
 * @since 2020-02-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("account")
public class AccountBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "SA_ID", type = IdType.AUTO)
    private Integer saId;

    private String name;

    private String phone;

    private String account;

    private String pwd;

    @TableField(exist = false)
    private List<RoleBean> roleBeanList;
}
