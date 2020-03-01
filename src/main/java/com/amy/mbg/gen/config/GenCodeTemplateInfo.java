package com.amy.mbg.gen.config;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author 张咪
 * @history 2020/2/19 新建
 * @since JDK1.7
 */
@Getter
@Setter
public class GenCodeTemplateInfo {

    private String tableName;

    private String tableComment;

    private List<GenCodeTemplateChildInfo> columnInfos;

    private List<String> boImportList;
    private List<String> voImportList;
    private List<String> dtoImportList;

    private String author;
    private String date;
    private String since;

    private String moduleName;
    private String className;
    private String camelName;
    private String commonBasePackage;
    private String basePackage;

}
