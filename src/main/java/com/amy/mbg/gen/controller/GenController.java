package com.amy.mbg.gen.controller;

import com.alibaba.fastjson.JSONObject;
import com.amy.mbg.gen.common.base.BaseController;
import com.amy.mbg.gen.common.entity.Result;
import com.amy.mbg.gen.dto.GenDto;
import com.amy.mbg.gen.service.GenService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author 张咪
 * @history 2020/2/19 新建
 * @since JDK1.7
 */
@RestController
@RequestMapping("/gen")
@CrossOrigin
public class GenController extends BaseController {

    @Autowired
    private GenService genService;


    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public Result list(@RequestBody GenDto dto){
        System.out.println(JSONObject.toJSONString(dto));
        return genService.findPage(dto);
    }

    @RequestMapping(value = "/gen",method = RequestMethod.GET)
    public void batchGenCode(HttpServletResponse response, String tableNames) throws IOException
    {
        byte[] data = genService.genCode(Arrays.asList(tableNames.split(",")));
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException
    {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"mbg.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}
