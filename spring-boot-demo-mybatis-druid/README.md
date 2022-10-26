# spring-boot-demo-mybatis
> SpringBoot集成mybatis访问mysql数据库，使用druid作为数据源。

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
    <artifactId>spring-boot-demo-mybatis-druid</artifactId>
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

## datasource 通用配置
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/demo_db?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=taobao1234
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource


## druid 数据源配置
spring.datasource.druid.initial-size=6
spring.datasource.druid.min-idle=6
spring.datasource.druid.max-active=12
# 从连接池中获取连接时的最大等待时间
spring.datasource.druid.max-wait=3000
# 配置检测可以关闭的空闲连接的间隔时间
spring.datasource.druid.time-between-eviction-runs-millis=60000
# 配置连接在池中的最小生存时间
spring.datasource.druid.min-evictable-idle-time-millis=300000
spring.datasource.druid.validation-query=select 1
spring.datasource.druid.test-while-idle=true
spring.datasource.druid.test-on-borrow=false
spring.datasource.druid.test-on-return=false

# v1.2.12 支持新的配置connectTimeout和socketTimeout，分别都是10秒。
# 这个默认值会减少因为网络丢包时导致的连接池无法创建链接。
spring.datasource.druid.connect-timeout=5000
spring.datasource.druid.socket-timeout=10000

# 打开PSCache，并且指定每个连接上PSCache的大小
spring.datasource.druid.pool-prepared-statements=true
spring.datasource.druid.max-open-prepared-statements=20
spring.datasource.druid.max-pool-prepared-statement-per-connection-size=20

# 配置监控统计拦截的filters, 去掉后监控界面sql无法统计, 'wall'用于防火墙
spring.datasource.druid.filters=stat,wall

# druid StatFilter
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
# 配置mapper.xml文件位置，也可以直接写在mapper接口的注解上
# mybatis.mapper-locations=classpath:mapper/*.xml
# 自定义POJO的包名，然后在mapper中可以使用类名代替全限定名
mybatis.type-aliases-package=com.will.demo.druid.dataobject
# 下划线转驼峰
mybatis.configuration.map-underscore-to-camel-case=true
```

## DemoApplication
> 指定要扫描的mapper包
```java
@SpringBootApplication
@MapperScan("com.will.demo.druid.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

