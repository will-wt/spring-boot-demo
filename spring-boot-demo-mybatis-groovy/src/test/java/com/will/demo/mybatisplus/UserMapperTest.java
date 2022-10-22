package com.will.demo.mybatisplus;

import com.will.demo.mybatisplus.dataobject.UserDO;
import com.will.demo.mybatisplus.mapper.UserMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Will.WT
 * @date 2022/10/23 00:48
 */
public class UserMapperTest extends BaseSpringBootTest{

    @Resource
    private UserMapper userMapper;


    @Test
    public void testGetUser(){
        UserDO userDO = userMapper.getUser(10002L);
        Assert.assertNotNull(userDO);
        Assert.assertEquals(userDO.getName(), "will");
    }

    @Test
    public void testQueryUser(){
        List<UserDO> userDOs = userMapper.queryUser(0, 10);
        Assert.assertNotNull(userDOs);
        Assert.assertEquals(userDOs.size(), 2);
    }

}
