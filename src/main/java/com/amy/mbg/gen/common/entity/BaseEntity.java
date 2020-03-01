package com.amy.mbg.gen.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 张咪
 * @history 2020/2/13 新建
 * @since JDK1.7
 */
@Getter
@Setter
public class BaseEntity implements Serializable {

    /**
     * 创建人ID
     */
    private Long createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人ID
     */
    private Long updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 是否删除(0未删除 1已删除)
     */
    private Integer isDeleted;


}
