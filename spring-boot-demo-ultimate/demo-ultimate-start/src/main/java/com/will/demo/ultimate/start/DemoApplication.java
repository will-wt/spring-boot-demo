package com.will.demo.ultimate.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Will.WT
 * @date 2022/10/19 12:15
 */
@SpringBootApplication(
        scanBasePackages = {"com.will.demo.ultimate"}
)
@MapperScan("com.will.demo.ultimate.dal.mapper") //通知mybatis扫描所有mapper接口
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
