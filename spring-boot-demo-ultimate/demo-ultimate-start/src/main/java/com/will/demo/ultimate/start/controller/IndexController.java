package com.will.demo.ultimate.start.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Will.WT
 */
@RestController
public class IndexController {

    @Value("${log.basedir}")
    private String logBasedir;

    @GetMapping("/")
    public String index(){
        return "OK";
    }

    @GetMapping("/check_health")
    public String checkHealth(){
        return "success";
    }

    @GetMapping("/log_basedir")
    public String getLogBasedir(){
        return logBasedir;
    }

}
