package com.jiubo.buildstore.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author dx
 * @since 2020-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("role")
public class RoleBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    @TableField(exist = false)
    private List<Integer> idList;
    /**
     * 角色名
     */
    private String roleName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    private List<Integer> firstIdList;
    private List<Integer> secondIdList;
}
