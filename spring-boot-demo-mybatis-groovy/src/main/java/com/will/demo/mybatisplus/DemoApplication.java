package com.will.demo.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Will.WT
 * @date 2022/10/17 14:22
 */
@SpringBootApplication
@MapperScan("com.will.demo.mybatisplus.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


}
