package com.amy.mbg.gen.mapper;

import com.amy.mbg.gen.bo.ColumnBo;
import com.amy.mbg.gen.bo.TableBo;
import com.amy.mbg.gen.common.base.BaseMapper;
import com.amy.mbg.gen.dto.GenDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GenMapper extends BaseMapper<TableBo, GenDto> {

    List<ColumnBo> selectColumnInfo(@Param("tableName") String tableName);
}
