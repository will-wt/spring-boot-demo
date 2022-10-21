package com.will.demo.ultimate.start.controller;

import com.will.demo.ultimate.biz.manager.UserManager;
import com.will.demo.ultimate.common.Result;
import com.will.demo.ultimate.dal.dataobject.UserDO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Will.WT
 * @date 2022/10/21 00:07
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserManager userManager;


    @GetMapping("/get")
    public Result<UserDO> getUser(@RequestParam Long userId){
        UserDO userDO = userManager.getUser(userId);
        return new Result<>(userDO);
    }

    @GetMapping("/query")
    public Result<List<UserDO>> queryUser(@RequestParam int offset, @RequestParam int pageSize){
        List<UserDO> userDOs = userManager.queryUser(offset, pageSize);
        return new Result<>(userDOs);
    }

}
