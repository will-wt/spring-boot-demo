package com.will.demo.ultimate.biz.manager;

import com.will.demo.ultimate.dal.dataobject.UserDO;
import com.will.demo.ultimate.dal.mapper.UserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Will.WT
 * @date 2022/10/21 00:08
 */
@Component
public class UserManager {

    @Resource
    private UserMapper userMapper;


    public UserDO getUser(Long userId){
        //todo 增加缓存
        return userMapper.getUser(userId);
    }

    public List<UserDO> queryUser(int offset, int pageSize){
        return userMapper.queryUser(offset, pageSize);
    }

}
