package com.amy.mbg.gen.service;

import com.amy.mbg.gen.common.base.BaseService;
import com.amy.mbg.gen.dto.GenDto;
import com.amy.mbg.gen.vo.GenVo;

import java.util.List;

public interface GenService extends BaseService<GenDto, GenVo> {

    byte[] genCode(List<String> tableNameList);
}
