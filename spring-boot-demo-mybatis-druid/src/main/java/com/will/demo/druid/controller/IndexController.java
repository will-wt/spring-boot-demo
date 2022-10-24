package com.will.demo.druid.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Will.WT
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "OK";
    }

    @GetMapping("/check_health")
    public String checkHealth(){
        return "success";
    }

}
