package com.will.demo.ultimate.dal.mapper

import com.will.demo.ultimate.dal.dataobject.UserDO
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 *
 * @author Will.WT
 * @date 2022/10/24 00:01
 */
@Mapper
interface UserMapper {

    /**
     * 查询单个用户
     * @param userId
     * @return
     */
    @Select('''
        select * 
        from user_info 
        where id = #{userId}
    ''')
    UserDO getUser(@Param("userId") Long userId);

    /**
     * 带有mybatis标签（如：<if></if>）的sql语句需要放在<script></script>之内
     * @param offset
     * @param pageSize
     * @return
     */
    @Select('''<script>
        select * 
        from user_info
        limit #{offset}, #{pageSize}
    </script>''')
    List<UserDO> queryUser(@Param("offset") int offset, @Param("pageSize") int pageSize);

}