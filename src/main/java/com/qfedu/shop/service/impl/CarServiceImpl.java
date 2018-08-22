package com.qfedu.shop.service.impl;

import com.qfedu.shop.bean.ShoppingCar;
import com.qfedu.shop.mapper.CarDao;
import com.qfedu.shop.pojo.Car;
import com.qfedu.shop.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;


    @Override
    public int addToCar(Long uid, Long pid) {
        //是insert还是update不一定
        Car car= carDao.selectOneItem(uid,pid);
        int result=0;
        if(car==null){
            //insert
            result=carDao.insertToCar(uid,pid);
        }else{
            //update
            result=carDao.updateToCar(uid,pid,car.getCount()+1);
        }
        return result;
    }

    @Override
    public int removeToCar(Long uid, Long pid) {
       return carDao.deleteToCar(uid,pid);

    }

    @Override
    public int editToCar(Long uid, Long[] pid, int[] count) {
        //先清空，再加入
        carDao.clearShoppingCar(uid);
        int result=0;
        for (int i=0;i<pid.length;i++) {
            //编辑购物车的时候若是count是0，就移除
            if (count[i] == 0) {
                result = result + carDao.deleteToCar(uid, pid[i]);
            } else {
                result = result + carDao.insertToCarCount(uid, pid[i], count[i]);
            }
        }
        return result;
    }

    @Override
    public ShoppingCar showCar(Long uid) {
        List<Car> items=carDao.selectCarByUid(uid);
        ShoppingCar shoppingCar=new ShoppingCar();
        shoppingCar.setItems(items);
        shoppingCar.setUid(uid);

        return shoppingCar;
    }

    @Override
    public int removeAllToCar(Long uid) {
        return carDao.clearShoppingCar(uid);
    }
}
