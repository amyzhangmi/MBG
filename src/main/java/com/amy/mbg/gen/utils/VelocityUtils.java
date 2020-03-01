package com.amy.mbg.gen.utils;

import com.amy.mbg.gen.bo.ColumnBo;
import com.amy.mbg.gen.config.GenCodeTemplateChildInfo;
import com.amy.mbg.gen.config.GenCodeTemplateInfo;
import com.amy.mbg.gen.config.GenConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.velocity.VelocityContext;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 张咪
 * @history 2020/2/19 新建
 * @since JDK1.7
 */
@Slf4j
public class VelocityUtils {

    public static GenCodeTemplateInfo buildGenCodeTemplateInfo(String tableName, List<ColumnBo> columnBos, GenConfig genConfig) {
        GenCodeTemplateInfo info = new GenCodeTemplateInfo();
        info.setTableName(tableName);
        info.setClassName(getClassName(tableName));
        info.setModuleName(genConfig.getModuleName());
        info.setTableComment("commentInfo");
        info.setCamelName(getCamelCaseName(info.getTableName()));

        info.setAuthor(genConfig.getAuthor());
        info.setDate(new SimpleDateFormat(genConfig.getDateFmt()).format(new Date()));
        info.setSince(genConfig.getSince());
        info.setBasePackage(genConfig.getBasePackage());
        info.setBasePackage(genConfig.getCommonBasePackage());

        List<GenCodeTemplateChildInfo> childInfos = new ArrayList<>();
        for (int i = 0; i < columnBos.size(); i++) {
            ColumnBo columnBo = columnBos.get(i);
            GenCodeTemplateChildInfo childInfo = new GenCodeTemplateChildInfo();
            BeanUtils.copyProperties(columnBo, childInfo);
            setType(childInfo.getColumnType(), childInfo);
            childInfo.setCamelName(getCamelCaseName(childInfo.getColumnName()));
            childInfo.setUpperFirstName(getClassName(childInfo.getColumnName()));
            childInfo.setBoField(isXXXFields(genConfig.getBoNotContainFields(), childInfo.getCamelName()));
            childInfo.setVoField(isXXXFields(genConfig.getVoNotContainFields(), childInfo.getCamelName()));
            childInfo.setDtoField(isXXXFields(genConfig.getDtoNotContainFields(), childInfo.getCamelName()));
            childInfo.setVueField(isXXXFields(genConfig.getVueNotContainFields(), childInfo.getCamelName()));
            childInfo.setPk(columnBo.isPk());
            childInfos.add(childInfo);
        }
        info.setColumnInfos(childInfos);
        info.setBoImportList(getBoImportList(info.getColumnInfos(), genConfig.getBoNotContainFields()));
        info.setVoImportList(getBoImportList(info.getColumnInfos(), genConfig.getVoNotContainFields()));
        info.setDtoImportList(getBoImportList(info.getColumnInfos(), genConfig.getDtoNotContainFields()));
        return info;
    }

    private static List<String> getBoImportList(List<GenCodeTemplateChildInfo> childInfos, List<String> notContainFields) {
        List<String> importList = new ArrayList<>();
        for (GenCodeTemplateChildInfo childInfo : childInfos) {
            if (isXXXFields(notContainFields, childInfo.getCamelName()) && "Date".equals(childInfo.getJavaType())) {
                String importField = "import java.util.Date;";
                if (!importList.contains(importField)) {
                    importList.add("import java.util.Date;");
                }
            }
            if (isXXXFields(notContainFields, childInfo.getCamelName()) && "BigDecimal".equals(childInfo.getJavaType())) {
                String importField = "import java.math.BigDecimal;";
                if (!importList.contains(importField)) {
                    importList.add("import java.math.BigDecimal;");
                }
            }
        }
        return importList;
    }

    private static boolean isXXXFields(List<String> notInFileds, String fieldName) {
        if(CollectionUtils.isEmpty(notInFileds)){
            return Boolean.TRUE;
        }
        return !notInFileds.contains(fieldName);
    }

    public static VelocityContext prepareContext(GenCodeTemplateInfo info) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("tableName", info.getTableName());
        velocityContext.put("className", info.getClassName());
        velocityContext.put("moduleName", info.getModuleName());
        velocityContext.put("tableComment", info.getTableComment());
        velocityContext.put("table", info);
        velocityContext.put("columns", info.getColumnInfos());
        return velocityContext;
    }

    private static String getClassName(String tableName) {
        String className = "";
        String[] arr = tableName.split("_");
        for (int i = 0; i < arr.length; i++) {
            char[] chars = arr[i].toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            className += new String(chars);
        }

        return className;

    }

    public static String getCamelCaseName(String name) {
        String camelCaseName = "";
        String[] arr = name.split("_");
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                char[] chars = arr[i].toCharArray();
                chars[0] = Character.toUpperCase(chars[0]);
                camelCaseName += new String(chars);
            } else {
                camelCaseName += arr[i];
            }
        }
        return camelCaseName;
    }

    public static String getFileName(String templateName, GenCodeTemplateInfo info, GenConfig genConfig) {
        String fileName = "";
        List<String> templateNames = genConfig.getTemplateNames();
        String javaBasePackage = genConfig.getBasePackage().replace(".","/") + "/"+genConfig.getModuleName();
        if (templateNames.contains(templateName)) {
            for (String tempName : templateNames) {
                if (tempName.equals(templateName)) {
                    tempName = tempName.replace("Demo", info.getClassName());
                    fileName = tempName.substring(0, tempName.lastIndexOf("."));
                    if (fileName.endsWith(".java")) {
                        if (fileName.contains("Bo")) {
                            fileName = "src/main/java/" + javaBasePackage + "/bo/" + fileName;
                        } else if (fileName.contains("Dto")) {
                            fileName = "src/main/java/" + javaBasePackage + "/dto/" + fileName;
                        } else if (fileName.contains("Vo")) {
                            fileName = "src/main/java/" + javaBasePackage + "/vo/" + fileName;
                        } else if (fileName.contains("Controller")) {
                            fileName = "src/main/java/" + javaBasePackage + "/controller/" + fileName;
                        } else if (fileName.contains("ServiceImpl")) {
                            fileName = "src/main/java/" + javaBasePackage + "/service/impl/" + fileName;
                        } else if (fileName.contains("Service")) {
                            fileName = "src/main/java/" + javaBasePackage + "/service/" + fileName;
                        } else if (fileName.contains("Mapper")) {
                            fileName = "src/main/java/" + javaBasePackage + "/mapper/" + fileName;
                        }
                    }else if(fileName.endsWith(".xml")){
                        if(fileName.contains("Mapper")){
                            fileName = "src/main/resources" + "/mapper/" + genConfig.getModuleName() + "/" + fileName;
                        }
                    }else if (fileName.endsWith(".js")){
                        fileName = "src/api/" + genConfig.getModuleName() + "/" + fileName;
                    }else if(fileName.endsWith(".vue")){
                        fileName = "src/views/" + genConfig.getModuleName() + "/" + fileName.substring(0,fileName.lastIndexOf(".")).toLowerCase() + "/" +fileName;
                    }else{
                        log.error("未知的模板名称，无法获取文件全路径");
                    }
                }
            }
        } else {
            log.error("加载模板错误");
        }
        return fileName;
    }

    public static void setType(String columnType, GenCodeTemplateChildInfo childInfo) {
        columnType = columnType.toLowerCase();
        String javaType = "String";
        String vueType = "string";
        if (columnType.startsWith("varchar")) {
            javaType = "String";
        }
        if (columnType.startsWith("bigint")) {
            javaType = "Long";
            vueType = "number";
        }
        if (columnType.startsWith("int")) {
            javaType = "Integer";
            vueType = "number";
        }
        if (columnType.startsWith("tinyint")) {
            javaType = "Integer";
            vueType = "number";
        }
        if (columnType.startsWith("decimal")) {
            javaType = "BigDecimal";
            vueType = "number";
        }
        if (columnType.startsWith("double")) {
            javaType = "BigDecimal";
            vueType = "number";
        }
        if (columnType.startsWith("text")) {
            javaType = "String";
        }
        if (columnType.startsWith("timestamp")) {
            javaType = "Date";
            vueType = "date";
        }
        if (columnType.startsWith("datetime")) {
            javaType = "Date";
            vueType = "date";
        }
        childInfo.setJavaType(javaType);
        childInfo.setVueType(vueType);
    }

    public static void main(String[] args) {
        System.out.println(getCamelCaseName("sys_menu"));
        System.out.println("Demo.vue.vm".substring(0, "Demo.vue.vm".lastIndexOf(".")));
    }
}
