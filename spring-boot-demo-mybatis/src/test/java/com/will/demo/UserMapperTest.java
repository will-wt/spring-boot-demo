package com.will.demo;

import com.will.demo.dataobject.UserDO;
import com.will.demo.mapper.UserMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Will.WT
 * @date 2022/10/22 23:27
 */
public class UserMapperTest extends BaseSpringBootTest {

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
