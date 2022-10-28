# spring-boot-demo-mybatis
> SpringBoot集成mybatis访问mysql数据库，使用多个druid数据源。

## pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.7.2</version>
        <relativePath/>
    </parent>

    <groupId>com.will.demo</groupId>
    <artifactId>spring-boot-demo-mybatis-multidruid</artifactId>
    <version>1.0.0-SNAPSHOT</version>

    <properties>
        <java.version>1.8</java.version>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- mybatis spring boot-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.2.2</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.21</version>
            <scope>runtime</scope>
        </dependency>

        <!-- druid数据源 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.2.13</version>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <finalName>${project.artifactId}</finalName>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

## application.properties
```properties
## tomcat port
server.port=7001
management.port=7002

## datasource 配置
## Spring Boot 2.X 版本不再支持配置继承，多数据源的话每个数据源的所有配置都需要单独配置，否则配置不会生效
## user数据源
spring.datasource.druid.user.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.druid.user.url=jdbc:mysql://127.0.0.1:3306/demo_db?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&serverTimezone=UTC
spring.datasource.druid.user.username=root
spring.datasource.druid.user.password=taobao1234
spring.datasource.druid.user.type=com.alibaba.druid.pool.DruidDataSource

## druid 数据源配置
spring.datasource.druid.user.initial-size=6
spring.datasource.druid.user.min-idle=6
spring.datasource.druid.user.max-active=12
# 从连接池中获取连接时的最大等待时间
spring.datasource.druid.user.max-wait=3000
# 配置检测可以关闭的空闲连接的间隔时间
spring.datasource.druid.user.time-between-eviction-runs-millis=60000
# 配置连接在池中的最小生存时间
spring.datasource.druid.user.min-evictable-idle-time-millis=300000
spring.datasource.druid.user.validation-query=select 1
spring.datasource.druid.user.test-while-idle=true
spring.datasource.druid.user.test-on-borrow=false
spring.datasource.druid.user.test-on-return=false

# v1.2.12 支持新的配置connectTimeout和socketTimeout，分别都是10秒。
# 这个默认值会减少因为网络丢包时导致的连接池无法创建链接。
spring.datasource.druid.user.connect-timeout=5000
spring.datasource.druid.user.socket-timeout=10000

# 打开PSCache，并且指定每个连接上PSCache的大小
spring.datasource.druid.user.pool-prepared-statements=true
spring.datasource.druid.user.max-open-prepared-statements=20
spring.datasource.druid.user.max-pool-prepared-statement-per-connection-size=20

# 配置监控统计拦截的filters, 去掉后监控界面sql无法统计, 'wall'用于防火墙
spring.datasource.druid.user.filters=stat,wall


## trade数据源
spring.datasource.druid.trade.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.druid.trade.url=jdbc:mysql://127.0.0.1:3306/demo2_db?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&serverTimezone=UTC
spring.datasource.druid.trade.username=root
spring.datasource.druid.trade.password=taobao1234
spring.datasource.druid.trade.type=com.alibaba.druid.pool.DruidDataSource

## druid 数据源配置
spring.datasource.druid.trade.initial-size=6
spring.datasource.druid.trade.min-idle=6
spring.datasource.druid.trade.max-active=12
# 从连接池中获取连接时的最大等待时间
spring.datasource.druid.trade.max-wait=3000
# 配置检测可以关闭的空闲连接的间隔时间
spring.datasource.druid.trade.time-between-eviction-runs-millis=60000
# 配置连接在池中的最小生存时间
spring.datasource.druid.trade.min-evictable-idle-time-millis=300000
spring.datasource.druid.trade.validation-query=select 1
spring.datasource.druid.trade.test-while-idle=true
spring.datasource.druid.trade.test-on-borrow=false
spring.datasource.druid.trade.test-on-return=false

# v1.2.12 支持新的配置connectTimeout和socketTimeout，分别都是10秒。
# 这个默认值会减少因为网络丢包时导致的连接池无法创建链接。
spring.datasource.druid.trade.connect-timeout=5000
spring.datasource.druid.trade.socket-timeout=10000

# 打开PSCache，并且指定每个连接上PSCache的大小
spring.datasource.druid.trade.pool-prepared-statements=true
spring.datasource.druid.trade.max-open-prepared-statements=20
spring.datasource.druid.trade.max-pool-prepared-statement-per-connection-size=20

# 配置监控统计拦截的filters, 去掉后监控界面sql无法统计, 'wall'用于防火墙
spring.datasource.druid.trade.filters=stat,wall



# druid StatFilter配置
spring.datasource.druid.filter.stat.log-slow-sql=true
spring.datasource.druid.filter.stat.slow-sql-millis=3000

# druid WebStatFilter配置
spring.datasource.druid.web-stat-filter.enabled=true
# 添加过滤规则
spring.datasource.druid.web-stat-filter.url-pattern=/*
# 忽略过滤的格式
spring.datasource.druid.web-stat-filter.exclusions=*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*
#spring.datasource.druid.web-stat-filter.session-stat-enable=
#spring.datasource.druid.web-stat-filter.session-stat-max-count=
#spring.datasource.druid.web-stat-filter.principal-session-name=
#spring.datasource.druid.web-stat-filter.principal-cookie-name=
#spring.datasource.druid.web-stat-filter.profile-enable=

# druid StatViewServlet配置
#是否启用StatViewServlet（监控页面）默认值为false（考虑到安全问题默认并未启动，如需启用建议设置密码或白名单以保障安全）
spring.datasource.druid.stat-view-servlet.enabled=true
# 需要账号密码才能访问监控控制台
spring.datasource.druid.stat-view-servlet.login-username=admin
spring.datasource.druid.stat-view-servlet.login-password=admin
# 访问路径为/druid时，跳转到StatViewServlet
spring.datasource.druid.stat-view-servlet.url-pattern=/druid/*
# 是否能够重置数据
spring.datasource.druid.stat-view-servlet.reset-enable=false
# IP白名单
spring.datasource.druid.stat-view-servlet.allow=127.0.0.1
#　IP黑名单（共同存在时，deny优先于allow）
spring.datasource.druid.stat-view-servlet.deny=



## mybatis
# 下划线转驼峰
mybatis.configuration.map-underscore-to-camel-case=true
```

## 数据源1 - 用户数据
> user数据源，负责用户相关的数据。

```java
@Configuration
@MapperScan(basePackages = "com.will.demo.multidruid.mapper.user", sqlSessionFactoryRef = "userSqlSessionFactory")
public class UserDataSourceConfig {

    // mybatis mapper.xml 加载地址
    //private static final String MAPPER_LOCATION = "classpath:sqlmap/user/*-mapper.xml";

    @Resource(name = "userMybatisConfiguration")
    private org.apache.ibatis.session.Configuration mybatisConfiguration;


    @Primary
    @Bean(name = "userDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.user")
    public DataSource dataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "userSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("userDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 注入独立的mybatis configuration，解决多数据源时驼峰映射未生效问题
        bean.setConfiguration(mybatisConfiguration);

        // 如果使用xml方式配置mapper，则需要指定mapper.xml的加载地址
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource(MAPPER_LOCATION));
        return bean.getObject();
    }

    @Primary
    @Bean(name = "userTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("userDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "userSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("userSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
```

## 数据源2 - 交易数据
> trade数据源，负责交易相关的数据。

```java
@Configuration
@MapperScan(basePackages = "com.will.demo.multidruid.mapper.trade", sqlSessionFactoryRef = "tradeSqlSessionFactory")
public class TradeDataSourceConfig {

    // mybatis mapper.xml 加载地址
    //private static final String MAPPER_LOCATION = "classpath:sqlmap/trace/*-mapper.xml";

    @Resource(name = "tradeMybatisConfiguration")
    private org.apache.ibatis.session.Configuration mybatisConfiguration;

    @Primary
    @Bean("tradeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.trade")
    public DataSource dataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "tradeSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("tradeDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 注入独立的mybatis configuration，解决多数据源时驼峰映射未生效问题
        bean.setConfiguration(mybatisConfiguration);

        // 如果使用xml方式配置mapper，则需要指定mapper.xml的加载地址
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource(MAPPER_LOCATION));
        return bean.getObject();
    }

    @Primary
    @Bean(name = "tradeTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("tradeDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "tradeSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("tradeSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
```

## mybatis配置
> 多数据源时解决：
> 1. MapUnderscoreToCamelCase 驼峰映射未生效问题
> 2. 先加载的数据源找不到数据库schema问题

```java
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
```
