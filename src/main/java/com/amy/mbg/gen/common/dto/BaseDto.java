package com.amy.mbg.gen.common.dto;

import com.amy.mbg.gen.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 张咪
 * @history 2020/2/13 新建
 * @since JDK1.7
 */
@Getter
@Setter
public class BaseDto extends BaseEntity {

    /**
     * 当前页码
     */
    private int pageNow = 1;

    /**
     * 每页记录条数
     */
    private int pageSize = 10;

    /**
     * 起始行数
     */
    private int startRow;

    public int getStartRow() {
        return (pageNow-1)*pageSize;
    }
}
