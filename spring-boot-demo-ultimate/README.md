# spring-boot-demo-ultimate
> 一个简洁、功能较齐全的web工程，包含多模块工程结构，集成了logback、lombok、mybatis、druid、mysql等功能。

## pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.will.demo</groupId>
    <artifactId>spring-boot-demo-ultimate</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <properties>
        <java.version>1.8</java.version>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <spring.boot.version>2.7.2</spring.boot.version>
        <autoconfig-plugin-version>1.2</autoconfig-plugin-version>
    </properties>

    <modules>
        <module>demo-ultimate-start</module>
        <module>demo-ultimate-dal</module>
        <module>demo-ultimate-biz</module>
        <module>demo-ultimate-common</module>
    </modules>

    <dependencyManagement>
        <dependencies>
            <!-- all project modules -->
            <dependency>
                <groupId>com.will.demo</groupId>
                <artifactId>demo-ultimate-common</artifactId>
                <version>${project.version}</version>
            </dependency>
            <dependency>
                <groupId>com.will.demo</groupId>
                <artifactId>demo-ultimate-dal</artifactId>
                <version>${project.version}</version>
            </dependency>
            <dependency>
                <groupId>com.will.demo</groupId>
                <artifactId>demo-ultimate-biz</artifactId>
                <version>${project.version}</version>
            </dependency>
            <dependency>
                <groupId>com.will.demo</groupId>
                <artifactId>demo-ultimate-start</artifactId>
                <version>${project.version}</version>
            </dependency>


            <!-- SpringBoot框架依赖-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring.boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

            <!-- mybatis spring boot-->
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>2.2.2</version>
            </dependency>
            <!-- mysql驱动-->
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>8.0.21</version>
                <scope>runtime</scope>
            </dependency>

            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>1.18.20</version>
            </dependency>

            <dependency>
                <groupId>org.apache.commons</groupId>
                <artifactId>commons-lang3</artifactId>
                <version>3.8.1</version>
            </dependency>

            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>4.12</version>
                <scope>test</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.10.1</version>
                </plugin>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-jar-plugin</artifactId>
                    <version>3.2.2</version>
                </plugin>
                <plugin>
                    <groupId>com.alibaba.citrus.tool</groupId>
                    <artifactId>autoconfig-maven-plugin</artifactId>
                    <version>${autoconfig-plugin-version}</version>
                </plugin>
                <plugin>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-maven-plugin</artifactId>
                    <version>${spring.boot.version}</version>
                    <executions>
                        <execution>
                            <goals>
                                <goal>repackage</goal>
                            </goals>
                        </execution>
                    </executions>
                    <configuration>
                        <mainClass>${start-class}</mainClass>
                    </configuration>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>
</project>
```


## SQL
SQLite https://www.sqlite.net.cn/tutorial/2.html