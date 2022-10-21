package com.will.demo.controller;

import com.will.demo.dataobject.UserDO;
import com.will.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Will.WT
 * @date 2022/10/21 23:01
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserMapper userMapper;


    @GetMapping("/get")
    public UserDO getUser(@RequestParam Long userId){
        return userMapper.getUser(userId);
    }

    @GetMapping("/query")
    public List<UserDO> queryUser(@RequestParam int offset, @RequestParam int pageSize){
        return userMapper.queryUser(offset, pageSize);
    }

}
