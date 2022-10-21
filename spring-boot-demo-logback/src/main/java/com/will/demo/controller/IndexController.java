package com.will.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Will.WT
 */
@RestController
public class IndexController {

    private final static Logger logger = LoggerFactory.getLogger("appLog");
    private final static Logger defaultLogger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
    public String index(){
        return "OK";
    }

    @GetMapping("/check_health")
    public String checkHealth(){
        return "success";
    }

    @GetMapping("/test_log")
    public String testLog(){
        defaultLogger.info("=============> start print test log.");

        for (int i=0; i<10; i++){
            logger.info("this is text for test log, index={}", i);
        }

        logger.error("test for print error log ------------>");
        return "over";
    }

}
