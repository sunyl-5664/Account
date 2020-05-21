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
@TableName("first_menu")
public class FirstMenuBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;
    @TableField(exist = false)
    private List<Integer> idList;
    @TableField(exist = false)
    private List<SecondMenuBean> secondMenuBeanList;
    /**
     * 菜单名
     */
    private String menuName;

    /**
     * 菜单唯一标识
     */
    private String menuCode;

    /**
     * 菜单路径
     */
    private String menuPath;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;


}
