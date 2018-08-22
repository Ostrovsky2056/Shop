package com.qfedu.shop.service;

import com.qfedu.shop.pojo.Order;

public interface OrderService {
    //创建订单
    int createOrder(Long uid,  String receiverAddress,
             String receiverName,
             String receiverPhone,String id);
    //查询订单
    Order findOrderByOrderid(String orderId);
    //支付订单
    int payOrder(String orderId);
}
