# spring-boot-demo-multi-env
> 多环境测试工程。

## 多环境配置
application.properties
application-dev.properties
application-test.properties
application-prod.properties

## 多环境打包
在pom.xml中增加
```xml
<!-- 多环境配置，还必须设置 build/resources -->
<profiles>
    <!-- 本地开发环境 -->
    <profile>
        <!-- 不同环境profile的唯一id -->
        <id>dev</id>
        <properties>
            <!-- 可以自定义多个属性 -->
            <spring.profiles.active>dev</spring.profiles.active>
        </properties>
        <activation>
            <!-- 是否默认激活该环境 -->
            <activeByDefault>true</activeByDefault>
        </activation>
    </profile>
    <!-- 测试环境 -->
    <profile>
        <id>test</id>
        <properties>
            <spring.profiles.active>test</spring.profiles.active>
        </properties>
    </profile>
    <!-- 正式环境 -->
    <profile>
        <id>prod</id>
        <properties>
            <spring.profiles.active>prod</spring.profiles.active>
        </properties>
    </profile>
</profiles>
```
打包命令：
mvn clean package -Dmaven.test.skip=true -P dev
