package com.will.demo.multidruid.mapper.trade;

import com.will.demo.multidruid.dataobject.OrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Will.WT
 * @date 2022/10/27 15:38
 */
@Mapper
public interface OrderMapper {

    /**
     * 查询某个订单
     * @param orderId
     * @return
     */
    @Select("select * from trade_order where order_id=#{orderId}")
    OrderDO getOrder(@Param("orderId") Long orderId);

    /**
     * 查询某个用户的订单
     * @param userId
     * @return
     */
    @Select("select * from trade_order where user_id=#{userId} limit 10")
    List<OrderDO> queryOrder(@Param("userId") Long userId);

}
