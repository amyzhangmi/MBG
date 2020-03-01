package com.amy.mbg.gen.common.base;

import com.amy.mbg.gen.common.entity.Result;

/**
 * @author 张咪
 * @history 2020/2/13 新建
 * @since JDK1.7
 */
public interface BaseService<Dto,Vo> {


    Result insert(Dto dto);

    Result update(Dto dto);

    Result findById(Long id);

    Result findPage(Dto dto);

    Result delete(Long id);
}
