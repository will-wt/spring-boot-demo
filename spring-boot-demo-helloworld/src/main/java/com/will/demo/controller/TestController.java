package com.will.demo.controller;

import com.will.demo.domain.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Will.WT
 */
@RestController
public class TestController {

    @GetMapping("/hello_world")
    public Result<String> testPrint(@RequestParam(required = false) String word){
        Result<String> result = new Result<>();
        result.setData(word);
        return result;
    }


}
