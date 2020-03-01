package com.amy.mbg.gen.config;

import com.amy.mbg.gen.constant.GenConstant;
import org.apache.velocity.app.Velocity;

import java.util.Properties;

/**
 * @author 张咪
 * @history 2020/2/19 新建
 * @since JDK1.7
 */
public class VelocityInitializer {

    /**
     * 初始化vm方法
     */
    public static void initVelocity()
    {
        Properties p = new Properties();
        try
        {
            // 加载classpath目录下的vm文件
            p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
            p.setProperty(Velocity.ENCODING_DEFAULT, GenConstant.UTF8);
            p.setProperty(Velocity.OUTPUT_ENCODING, GenConstant.UTF8);
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
