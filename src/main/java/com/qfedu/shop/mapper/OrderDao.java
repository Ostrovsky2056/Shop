package com.qfedu.shop.mapper;

import com.qfedu.shop.pojo.Order;
import com.qfedu.shop.pojo.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {
    //创建订单的方法
    int insertToOrder(Order order);
    int insertToOrderItem(OrderItem orderItem);
    //订单支付的方法
    int updatePayState(@Param("orderId") String orderId,@Param("state")  int state);
    //查询
    Order selectOneByOrderId(@Param("orderId") String orderId);
    //查询
    //List<Order> selectByOrderUId(@Param("uid") Long uid);
    //查询
    List<OrderItem> selectOneItemByOrderId(@Param("orderId") String orderId);
}
