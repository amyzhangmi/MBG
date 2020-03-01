package com.amy.mbg.gen.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author 张咪
 * @history 2020/2/13 新建
 * @since JDK1.7
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Page implements Serializable {

    /**
     * 当前页码
     */
    private int pageNow = 1;

    /**
     * 每页记录条数
     */
    private int pageSize = 10;

    private long total;

    private List<?> rows;
}
