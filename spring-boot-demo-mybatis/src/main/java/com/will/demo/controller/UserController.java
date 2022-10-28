package com.will.demo.controller;

import com.google.gson.Gson;
import com.will.demo.dataobject.UserDO;
import com.will.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

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
        UserDO userDO = userMapper.getUser(userId);
        System.out.println(new Gson().toJson(userDO));
        return userDO;
    }

    @GetMapping("/query")
    public List<UserDO> queryUser(@RequestParam int offset, @RequestParam int pageSize){
        return userMapper.queryUser(offset, pageSize);
    }

    @GetMapping("/add")
    public String addUser(@RequestParam String name, @RequestParam Integer age, @RequestParam String address){
        UserDO userDO = new UserDO();
        userDO.setName(name);
        userDO.setAge(age);
        userDO.setAddress(address);

        String msg = "success";
        try {
            userMapper.addUser(userDO);
        } catch (Exception e) {
            e.printStackTrace();
            msg = "failed, error msg:"+ e.getMessage();
        }

        return msg;
    }

}
