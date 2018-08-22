package com.qfedu.shop.service;

import com.qfedu.shop.bean.ShoppingCar;

public interface CarService {
    //加入购物车
    int addToCar(Long uid,Long pid);
    //移除购物车
    int removeToCar(Long uid,Long pid);

    //编辑购物车
    int editToCar(Long uid,Long [] pid,int [] count);

    //查看购物车
    ShoppingCar showCar(Long uid);

    //清空购物车
    int removeAllToCar(Long uid);
}
