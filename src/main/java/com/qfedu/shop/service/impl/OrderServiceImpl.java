package com.qfedu.shop.service.impl;

import com.qfedu.shop.mapper.CarDao;
import com.qfedu.shop.mapper.OrderDao;
import com.qfedu.shop.pojo.Car;
import com.qfedu.shop.pojo.Order;
import com.qfedu.shop.pojo.OrderItem;
import com.qfedu.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CarDao carDao;


    @Override
    public int createOrder(Long uid,String receiverAddress,
                           String receiverName,
                           String receiverPhone,String id) {
        //把这个用户的购物车查到
        List<Car> cars=carDao.selectCarByUid(uid);
        //购物车中的商品构建成一个订单
        Order order=new Order();
        order.setReceiverAddress(receiverAddress);
        order.setReceiverName(receiverName);
        order.setReceiverPhone(receiverPhone);
        order.setUid(uid);
        order.setId(id);
        //订单总价值？
        List<OrderItem> items=new ArrayList<>();
        BigDecimal sum=new BigDecimal(0);
        for (int i = 0; i < cars.size(); i++) {
            OrderItem item=new OrderItem();
            item.setOrderid(id);
            item.setData(cars.get(i));
            sum=sum.add(item.getPrice().multiply(new BigDecimal(item.getCount())));
            items.add(item);
        }
        //我们有sum和items
        order.setPrice(sum);
        order.setItems(items);
        
        //业务计算  end
        
        //开始持久化处理
        int result=0;
        result=result+orderDao.insertToOrder(order);
        for (int i = 0; i < items.size(); i++) {
            result=result+orderDao.insertToOrderItem(items.get(i));
        }
        result=result+carDao.clearShoppingCar(uid);
        return result;
    }

    @Override
    public Order findOrderByOrderid(String orderId) {
        Order order=orderDao.selectOneByOrderId(orderId);
        List<OrderItem> items= orderDao.selectOneItemByOrderId(orderId);
        order.setItems(items);
        return order;
    }

    @Override
    public int payOrder(String orderId) {
        return orderDao.updatePayState(orderId,1);
    }
}
