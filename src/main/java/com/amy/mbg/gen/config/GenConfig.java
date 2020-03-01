package com.amy.mbg.gen.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author 张咪
 * @history 2020/2/19 新建
 * @since JDK1.7
 */
@Configuration
@ConfigurationProperties(prefix = "gen")
@Getter
@Setter
public class GenConfig {

    private String author;
    private String dateFmt;
    private String since;

    private String moduleName;

    private String commonBasePackage;
    private String basePackage;

    private String templatePath;
    private List<String> templateNames;
    private List<String> boNotContainFields;
    private List<String> voNotContainFields;
    private List<String> dtoNotContainFields;
    private List<String> vueNotContainFields;
}
