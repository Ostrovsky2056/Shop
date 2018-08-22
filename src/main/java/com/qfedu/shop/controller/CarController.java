package com.qfedu.shop.controller;

import com.qfedu.shop.bean.ShoppingCar;
import com.qfedu.shop.pojo.User;
import com.qfedu.shop.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CarController {
    @Autowired
    private CarService carServcie;

    @RequestMapping(value="/goshopping",method=RequestMethod.POST)
    public String goshopping(
            @RequestParam("count") int [] count,
            @RequestParam("pid") Long [] pid,
            HttpServletRequest request

    ){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return "login";
        }else{
            Long uid=user.getUid();
            carServcie.editToCar(uid,pid,count);
            return "product_list";
        }

    }

    @RequestMapping(value="/goorder",method=RequestMethod.POST)
    public String goorder(
            @RequestParam("count") int [] count,
            @RequestParam("pid") Long [] pid,
            HttpServletRequest request

    ){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return "login";
        }else{
            Long uid=user.getUid();
            carServcie.editToCar(uid,pid,count);
            ShoppingCar shoppingCar=carServcie.showCar(uid);
            request.setAttribute("car",shoppingCar);
            return "order";
        }
    }

    @RequestMapping("/showcar")
    public String showcar(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return "login";
        }else{
            Long uid=user.getUid();
            ShoppingCar shoppingCar=carServcie.showCar(uid);
            request.setAttribute("car",shoppingCar);
            return "cart";
        }
    }


    @RequestMapping("addtocar")
    public String addToCar(
            @RequestParam("pid") Long pid,
            HttpServletRequest request
    ){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return "login";
        }else{
            Long uid=user.getUid();
            int result=carServcie.addToCar(uid,pid);
            if(result>0){
                ShoppingCar shoppingCar=carServcie.showCar(uid);
                request.setAttribute("car",shoppingCar);
                return "cart";
            }else{
                return "error";
            }
        }
    }
}