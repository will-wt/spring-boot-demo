# spring-boot-demo-logback
> SpringBoot默认使用logback作为日志系统，已引入logback的依赖包。

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
    <artifactId>spring-boot-demo-logback</artifactId>
    <version>1.0.0-SNAPSHOT</version>

    <properties>
        <java.version>1.8</java.version>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
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
    </dependencies>

    <build>
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

## logback
Spring Boot官方推荐优先使用带有-spring 的文件名作为你的日志配置（如使用logback-spring.xml，而不是logback.xml），
命名为logback-spring.xml的日志配置文件，将xml放至 src/main/resource下面。
> 也可以使用自定义的名称，比如logback-config.xml，但需要在application.properties文件中使用logging.config=classpath:logback-config.xml指定即可。

为了在高并发下保持稳定性，提升日志写入性能，做了如下设置：
1. 异步输出日志，不阻塞队列
2. 控制单个日志文件的大小，防止在日志滚动时导致IO阻塞（日志滚动时删除大文件会比较慢）。maxFileSize=256MB比较合适，在线上高并发场景下验证过了。
3. 控制日志总量，防止耗尽硬盘。设置totalSizeCap/maxHistory，cleanHistoryOnStart=true清理历史文件。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--
scan：配置文件如发生改变，是否要重新加载，默认值为true。
scanPeriod：扫描配置文件是否有修改的时间间隔，如果没有给出时间单位，默认单位是毫秒，当scan为true时，此属性生效。默认的时间间隔为1分钟。
debug：是否打印出logback内部日志信息，实时查看logback运行状态。默认值为false。
-->
<configuration scan="true" scanPeriod="30 seconds" debug="false">

    <!-- logback.xml加载顺序早于application.properties，需要通过springProperty标签来获取应用变量 -->
    <springProperty scope="context" name="LOG_HOME" source="log.basedir"/>

    <!-- 日志输出格式：
            %d表示日期时间,
            %thread表示线程名,
            %-5level：级别从左显示5个字符宽度
            %logger{50} 表示logger名字最长50个字符,否则按照句点分割。
            %msg：日志消息,
            %n是换行符
     -->
    <property name="LOG_PATTERN" value="%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level ${PID:-} --- [%t] %logger{50} - %msg%n"/>

    <!--
        Appender: 设置日志信息的去向,常用的有以下几个
            ch.qos.logback.core.ConsoleAppender (控制台)
            ch.qos.logback.core.rolling.RollingFileAppender (文件大小到达指定条件的时候产生一个新文件)
            ch.qos.logback.core.FileAppender (文件)
    -->
    <appender name="stdout" class="ch.qos.logback.core.ConsoleAppender">
        <!-- 字符串System.out（默认）或者 System.err -->
        <target>System.out</target>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>${LOG_PATTERN}</pattern>
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <appender name="appAppenderSync" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${LOG_HOME}/app.log</file>
        <!-- 日志输出格式 -->
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>${LOG_PATTERN}</pattern>
            <charset>UTF-8</charset>
        </encoder>
        <!-- 日志级别过滤器
            LevelFilter：级别过滤器，根据日志级别进行过滤。如果日志级别等于配置级别，过滤器会根据onMath 和 onMismatch接收或拒绝日志。
            ThresholdFilter：临界值过滤器，过滤掉低于指定临界值的日志
         -->
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>INFO</level>

            <!-- LevelFilter模式，用于配置符合过滤条件的操作。
                 ACCEPT：日志会被立即处理，不再经过剩余过滤器 -->
            <!-- <onMatch>ACCEPT</onMatch>-->
            <!-- 用于配置不符合过滤条件的操作 DENY：日志将立即被抛弃不再经过其他过滤器 -->
            <!-- <onMismatch>DENY</onMismatch>-->
        </filter>

        <!--当发生滚动时,决定 RollingFileAppender 的行为，涉及文件移动和重命名。
            TimeBasedRollingPolicy：最常用的滚动策略，它根据时间来制定滚动策略，既负责滚动也负责触发滚动。
            “%d”可以包含一个java.text.SimpleDateFormat指定的时间格式。
         -->
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--滚动时产生的文件的存放位置及文件名称 %d{yyyy-MM-dd}：按天进行日志滚动
                %i：当文件大小超过maxFileSize时，按照i进行数字自增的文件滚动 -->
            <fileNamePattern>${LOG_HOME}/app.%d{yyyy-MM-dd}.%i.log</fileNamePattern>

            <!--可选，控制保留的归档文件的最大数量，超出数量就删除旧文件。
                假设设置每天滚动，若maxHistory是15，则只保存最近15天的文件,删除之前的旧文件。
                注意：删除旧文件时，那些为了归档而创建的目录也会被删除。-->
            <maxHistory>14</maxHistory>
            <!--可选，设置日志总量，该属性在 1.1.6版本后才开始支持-->
            <totalSizeCap>10GB</totalSizeCap>

            <!--当日志文件超过maxFileSize指定的大小时，根据上面提到的%i进行日志文件滚动。
                注意此处配置 SizeBasedTriggeringPolicy 是无法实现按文件大小进行滚动的，必须配置 timeBasedFileNamingAndTriggeringPolicy
            -->
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>256MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
            <cleanHistoryOnStart>true</cleanHistoryOnStart>
        </rollingPolicy>
    </appender>
    <!-- 异步输出日志，防止队列阻塞，提升性能-->
    <appender name="appAppender" class="ch.qos.logback.classic.AsyncAppender">
        <appender-ref ref="appAppenderSync"/>
        <!-- 设置队列满了时是否会阻塞，默认为false，为true时，当队列满时会直接丢弃日志，可以提升性能 -->
        <neverBlock>true</neverBlock>
        <!-- 设置异步阻塞队列的大小，设置大一些可以降低日志丢失的概率，但该值会影响性能。默认值为256 -->
        <queueSize>512</queueSize>
    </appender>


    <appender name="errorAppenderSync" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${LOG_HOME}/error.log</file>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>${LOG_PATTERN}</pattern>
            <charset>UTF-8</charset>
        </encoder>
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>ERROR</level>
        </filter>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${LOG_HOME}/error.%d{yyyy-MM-dd}.%i.log</fileNamePattern>
            <maxHistory>14</maxHistory>
            <totalSizeCap>10GB</totalSizeCap>
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>256MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
            <cleanHistoryOnStart>true</cleanHistoryOnStart>
        </rollingPolicy>
    </appender>
    <appender name="errorAppender" class="ch.qos.logback.classic.AsyncAppender">
        <appender-ref ref="errorAppenderSync"/>
        <neverBlock>true</neverBlock>
    </appender>


    <!--
        用来设置某一个包或者具体的某一个类的日志打印级别、以及指定<appender>。
        <logger>有一个name属性，一个可选的level和一个可选的additivity属性
        name:
            用来指定受此logger约束的某一个包或者具体的某一个类。
        level:
            用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF，
            如果未设置此属性，那么当前logger将会继承上级的级别。
        additivity:
            是否向上级logger传递打印信息。默认是true。
        <logger>可以包含零个或多个<appender-ref>元素，标识这个appender将会添加到这个logger
    -->
    <logger name="java.sql" level="info" additivity="false">
        <appender-ref ref="appAppender"/>
        <appender-ref ref="errorAppender"/>
    </logger>

    <logger name="appLog" level="info" additivity="false">
        <appender-ref ref="appAppender"/>
        <appender-ref ref="errorAppender"/>
    </logger>


    <!-- root是默认的logger，没有匹配到上面自定义的logger，就会使用root作为logger。
         任何一个类只会和一个logger对应，要么是定义的logger，要么是root，判断的关键在于找到这个logger，然后判断这个logger的appender和level。
     -->
    <root level="info">
        <appender-ref ref="stdout"/>
        <appender-ref ref="appAppender"/>
        <appender-ref ref="errorAppender"/>
    </root>
</configuration>
```