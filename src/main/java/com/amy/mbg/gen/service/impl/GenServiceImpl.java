package com.amy.mbg.gen.service.impl;

import com.amy.mbg.gen.bo.ColumnBo;
import com.amy.mbg.gen.bo.TableBo;
import com.amy.mbg.gen.common.entity.Page;
import com.amy.mbg.gen.common.entity.Result;
import com.amy.mbg.gen.config.GenCodeTemplateInfo;
import com.amy.mbg.gen.config.GenConfig;
import com.amy.mbg.gen.config.VelocityInitializer;
import com.amy.mbg.gen.constant.GenConstant;
import com.amy.mbg.gen.dto.GenDto;
import com.amy.mbg.gen.mapper.GenMapper;
import com.amy.mbg.gen.service.GenService;
import com.amy.mbg.gen.utils.VelocityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author 张咪
 * @history 2020/2/19 新建
 * @since JDK1.7
 */
@Slf4j
@Service
public class GenServiceImpl implements GenService {

    @Autowired
    private GenMapper genMapper;

    @Autowired
    GenConfig genConfig;

    @Override
    public Result insert(GenDto genDto) {
        return Result.success();
    }

    @Override
    public Result update(GenDto genDto) {
        return Result.success();
    }

    @Override
    public Result findById(Long id) {
        return Result.success();
    }

    @Override
    public Result findPage(GenDto genDto) {
        long count = genMapper.findCount(genDto);
        List<TableBo> list = genMapper.findPage(genDto);
        Page page = new Page(genDto.getPageNow(),genDto.getPageSize(),count,list);
        return Result.success(page);
    }

    @Override
    public Result delete(Long id) {
        return Result.success();
    }

    @Override
    public byte[] genCode(List<String> tableNameList) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNameList)
        {
            generatorCode(tableName, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    /**
     * 查询表信息并生成代码
     */
    private void generatorCode(String tableName, ZipOutputStream zip)
    {
        // 查询表信息
        List<ColumnBo> columnBos = genMapper.selectColumnInfo(tableName);

        GenCodeTemplateInfo info = VelocityUtils.buildGenCodeTemplateInfo(tableName, columnBos,genConfig);

        VelocityInitializer.initVelocity();

        VelocityContext context = VelocityUtils.prepareContext(info);

        // 获取模板列表
        List<String> templateNames = genConfig.getTemplateNames();
        for (String templateName : templateNames)
        {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(genConfig.getTemplatePath() + "/" + templateName, GenConstant.UTF8);
            tpl.merge(context, sw);
            try
            {
                // 添加到zip
                zip.putNextEntry(new ZipEntry(VelocityUtils.getFileName(templateName,info,genConfig)));
                IOUtils.write(sw.toString(), zip, GenConstant.UTF8);
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            }
            catch (IOException e)
            {
                log.error("渲染模板失败，表名：" + tableName, e);
            }
        }
    }
}
