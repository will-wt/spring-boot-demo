package com.will.demo.multidruid.mapper.user;

import com.will.demo.multidruid.dataobject.UserDO;
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
     * 分页查询用户
     * @param offset
     * @param pageSize
     * @return
     */
    @Select("select * from user_info limit #{offset}, #{pageSize}")
    List<UserDO> queryUser(@Param("offset") int offset, @Param("pageSize") int pageSize);

}
