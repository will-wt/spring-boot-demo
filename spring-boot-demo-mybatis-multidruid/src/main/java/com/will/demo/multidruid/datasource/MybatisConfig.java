package com.will.demo.multidruid.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Will.WT
 * @date 2022/10/28 00:29
 */
@Configuration
public class MybatisConfig {

    /**
     * 多数据源时，需要配置独立的mybatis.configuration，否则会出现mybatis.configuration.environment
     * 被覆盖，导致前面的数据源找不到数据库schema
     */
    @Bean(name = "userMybatisConfiguration")
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration userMybatisConfiguration(){
        return new org.apache.ibatis.session.Configuration();
    }

    @Bean(name = "tradeMybatisConfiguration")
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration tradeMybatisConfiguration(){
        return new org.apache.ibatis.session.Configuration();
    }

}
