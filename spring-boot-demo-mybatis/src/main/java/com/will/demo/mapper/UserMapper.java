package com.will.demo.mapper;

import com.will.demo.dataobject.UserDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Will.WT
 * @date 2022/10/20 20:33
 */
@Mapper
public interface UserMapper {

    /**
     * 查询单个用户
     * @param userId
     * @return
     */
    @Select("select * from user_info where id = #{userId}")
    UserDO getUser(@Param("userId") Long userId);

    /**
     * 分页查询用户，sql写在UserMapper.xml
     * @param offset
     * @param pageSize
     * @return
     */
    List<UserDO> queryUser(@Param("offset") int offset, @Param("pageSize") int pageSize);

    @Insert("insert into user_info(gmt_create, gmt_modified, name, age, address) " +
            "values(now(), now(), #{name}, #{age}, #{address})")
    long addUser(UserDO userDO);

}
