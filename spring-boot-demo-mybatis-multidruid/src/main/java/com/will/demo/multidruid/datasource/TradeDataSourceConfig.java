package com.will.demo.multidruid.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 交易模块DataSource
 * @author Will.WT
 * @date 2022/10/27 15:58
 */
@Configuration
@MapperScan(basePackages = "com.will.demo.multidruid.mapper.trade", sqlSessionFactoryRef = "tradeSqlSessionFactory")
public class TradeDataSourceConfig {

    // mybatis mapper.xml 加载地址
    //private static final String MAPPER_LOCATION = "classpath:sqlmap/trace/*-mapper.xml";

//    @Primary
    @Bean("tradeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.trade")
    public DataSource dataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "tradeSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("tradeDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 如果使用xml方式配置mapper，则需要指定mapper.xml的加载地址
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource(MAPPER_LOCATION));
        return bean.getObject();
    }

    @Bean(name = "tradeTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("tradeDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "tradeSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("tradeSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
