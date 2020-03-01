package com.amy.mbg.gen.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 表注释
 *
 * @author 张咪
 * @history 2020/2/19 新建
 * @since JDK1.7
 */
@Getter
@Setter
public class TableBo implements Serializable {

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表注释
     */
    private String tableComment;

    /**
     * 创建时间
     */
    private Date createTime;
}
