package com.teamall.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.teamall.common.domain.validate.AddGroup;
import com.teamall.common.domain.validate.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 类目表
 * @TableName tea_category
 */
@TableName(value ="tea_category")
@Data
public class TeaCategory implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    @Null(groups = AddGroup.class)
    @NotNull(groups = UpdateGroup.class)
    private Integer id;

    /**
     * 类目名称
     */
    @NotEmpty(groups = AddGroup.class)
    private String name;

    /**
     * 类目关键字，以JSON数组格式
     */
    private String keywords;

    /**
     * 类目广告语介绍
     */
    private String desc;

    /**
     * 父类目ID
     */
    private Integer pid;

    /**
     * 类目图标
     */
    private String iconUrl;

    /**
     * 类目图片
     */
    private String picUrl;

    /**
     * 
     */
    private String level;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除
     */
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}