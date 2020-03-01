package com.amy.mbg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 张咪
 * @history 2020/2/29 新建
 * @since JDK1.7
 */
@SpringBootApplication
@MapperScan("com.amy.mbg.*.mapper")
public class MBGApplication {

    public static void main(String[] args) {
        SpringApplication.run(MBGApplication.class,args);
        System.out.println("server started successfully !");
    }
}
