package com.qfedu.shop.controller;

import com.qfedu.shop.pojo.Order;
import com.qfedu.shop.pojo.User;
import com.qfedu.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "/createorder",method = RequestMethod.POST)
    public String createOrder(
        @RequestParam("receiverAddress") String receiverAddress,
        @RequestParam("receiverName") String receiverName,
        @RequestParam("receiverPhone") String receiverPhone,
        HttpServletRequest request
    ){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return "login";
        }else {
            Long uid = user.getUid();
            String id=System.currentTimeMillis()+""+uid;
            int result=orderService.createOrder(uid,receiverAddress,receiverName,receiverPhone,id);
            if(result>0){
                Order order=orderService.findOrderByOrderid(id);
                request.setAttribute("order",order);
                return "orderInfo";
            }

        }
        return "error";
    }


    @RequestMapping("showorder")
    public String showorder(
            @RequestParam("orderid") String orderid,
            HttpServletRequest request
    ){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return "login";
        }else{
            Order order= orderService.findOrderByOrderid(orderid);
            request.setAttribute("order",order);
            return "orderInfo";
        }
    }



}
