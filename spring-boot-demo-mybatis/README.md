# spring-boot-demo-mybatis
> SpringBoot集成mybatis访问mysql数据库，默认使用Hikari数据源，sql编写支持mapper注解和xml两种方式。

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
    <artifactId>spring-boot-demo-mybatis</artifactId>
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
默认使用hikari数据源
```properties
## tomcat port
server.port=7001
management.port=7002

## datasource 通用配置
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/demo_db?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=taobao1234

## hikari 数据源配置
# 最小空闲连接，默认值10，小于0或大于maximum-pool-size，都会重置为maximum-pool-size
spring.datasource.hikari.minimum-idle=6
# 最大连接数，小于等于0会被重置为默认值10；大于零小于1会被重置为minimum-idle的值
spring.datasource.hikari.maximum-pool-size=12
# 连接测试查询
spring.datasource.hikari.connection-test-query=SELECT 1 FROM DUAL
spring.datasource.hikari.auto-commit=true
# 空闲连接超时时间，默认值600000（10分钟），大于等于max-lifetime且max-lifetime>0，会被重置为0；不等于0且小于10秒，会被重置为10秒。
# 只有空闲连接数大于最大连接数且空闲时间超过该值，才会被释放
spring.datasource.hikari.idle-timeout=300000
# 连接最大存活时间。不等于0且小于30秒，会被重置为默认值30分钟。设置应该比mysql设置的超时时间短
spring.datasource.hikari.max-lifetime=600000
# 连接超时时间:毫秒，小于250毫秒，否则被重置为默认值30秒
spring.datasource.hikari.connection-timeout=3000


## mybatis
# 配置mapper.xml文件位置，也可以直接写在mapper接口的注解上
mybatis.mapper-locations=classpath:mapper/*.xml
# 自定义POJO的包名，然后在mapper中可以使用类名代替全限定名
mybatis.type-aliases-package=com.will.demo.dataobject
# 下划线转驼峰
mybatis.configuration.map-underscore-to-camel-case=true
```

## DemoApplication
> 指定要扫描的mapper包
```java
@SpringBootApplication
@MapperScan("com.will.demo.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

