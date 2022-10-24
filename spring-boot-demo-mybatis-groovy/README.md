# spring-boot-demo-mybatis-groovy
> SpringBoot集成mybatis，并且引入groovy作为mapper实现，方便在mapper注解上编写sql语句。


## application.properties
去除了mapper.xml方式的sql映射。
```properties
## tomcat port
server.port=7001
management.port=7002

## spring datasource
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/demo_db?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=taobao1234


## mybatis
# 配置mapper.xml文件位置，也可以直接写在mapper接口的注解上
# mybatis.mapper-locations=classpath:mapper/*.xml
# 自定义POJO的包名，然后在mapper中可以使用类名代替全限定名
mybatis.type-aliases-package=com.will.demo.mybatisplus.dataobject
# 下划线转驼峰
mybatis.configuration.map-underscore-to-camel-case=true
```

## mapper
```java
@Mapper
interface UserMapper {

    @Select('''
        select * 
        from user_info
        where id = #{userId}
    ''')
    UserDO getUser(@Param("userId") Long userId)

    @Select('''<script>
        select * 
        from user_info
        limit #{offset}, #{pageSize}
    </script>''')
    List<UserDO> queryUser(@Param("offset") int offset, @Param("pageSize") int pageSize)

}
```