package com.will.demo.multienv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Will.WT
 * @date 2023/02/25 23:39
 */
@RestController
public class IndexController {

    private final static Logger logger = LoggerFactory.getLogger("appLog");


    @GetMapping("/")
    public String index(){
        return "OK";
    }

    @GetMapping("/check_health")
    public String checkHealth(){
        return "success";
    }

    @GetMapping("/test_multi_env")
    public String testMultiEnv(){
        logger.error("test multi env ------------>");



        return "ok";
    }


}
