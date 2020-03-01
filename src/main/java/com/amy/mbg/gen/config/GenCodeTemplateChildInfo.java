package com.amy.mbg.gen.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 张咪
 * @history 2020/2/20 新建
 * @since JDK1.7
 */
@Getter
@Setter
public class GenCodeTemplateChildInfo {

    /**
     * 数据库列名
     */
    private String columnName;
    /**
     * 列类型
     */
    private String columnType;

    /**
     * 是否为主键
     */
    private boolean pk;
    /**
     * 列注释
     */
    private String columnComment;
    /**
     * 驼峰命名
     */
    private String camelName;
    /**
     * 首字母大写
     */
    private String upperFirstName;
    /**
     * java类型
     */
    private String javaType;
    /**
     * Vue类型
     */
    private String vueType;

    private boolean boField;
    private boolean voField;
    private boolean dtoField;
    private boolean vueField;

}
