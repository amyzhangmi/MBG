package com.amy.mbg.gen.common.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMapper<Bo,Dto> {

    long insert(Bo bo);

    long update(Bo bo);

    Bo findById(@Param(value = "id") Long id);

    List<Bo> findPage(Dto dto);

    long findCount(Dto dto);

    long delete(@Param(value = "id") Long id);

}
