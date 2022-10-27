package com.will.demo.multidruid.controller;

import com.will.demo.multidruid.dataobject.OrderDO;
import com.will.demo.multidruid.mapper.trade.OrderMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Will.WT
 * @date 2022/10/27 22:03
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderMapper orderMapper;


    @GetMapping("/get")
    public OrderDO getOrder(@RequestParam Long orderId){
        return orderMapper.getOrder(orderId);
    }

    @GetMapping("/list")
    public List<OrderDO> listOrder(@RequestParam Long userId){
        return orderMapper.queryOrder(userId);
    }

}
