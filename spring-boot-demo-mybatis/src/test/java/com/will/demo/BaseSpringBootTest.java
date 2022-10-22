package com.will.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Will.WT
 * @date 2022/10/22 23:25
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BaseSpringBootTest {

    @Test
    public void init(){
        System.out.println("==================== test start");
    }

}
