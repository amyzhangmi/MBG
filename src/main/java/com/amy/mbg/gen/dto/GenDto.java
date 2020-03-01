package com.amy.mbg.gen.dto;

import com.amy.mbg.gen.common.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 张咪
 * @history 2020/2/19 新建
 * @since JDK1.7
 */
@Getter
@Setter
public class GenDto extends BaseDto {

    private String dataBaseName;

    private String tableName;

    private String tableComment;
}
