package com.will.demo.start.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Will.WT
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "success";
    }

    @GetMapping("/check_health")
    public String checkHealth(){
        return "success";
    }

}
