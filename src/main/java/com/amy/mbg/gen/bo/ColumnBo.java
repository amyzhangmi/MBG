package com.amy.mbg.gen.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 张咪
 * @history 2020/2/19 新建
 * @since JDK1.7
 */
@Getter
@Setter
public class ColumnBo implements Serializable {

    private String columnName;

    private String columnType;

    private String columnComment;

    private String columnKey;

    public boolean isPk(){
        return (null != columnKey && columnKey.length() > 0 && "PRI".equals(columnKey.toUpperCase()));
    }


}
