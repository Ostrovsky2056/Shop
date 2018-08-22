package com.qfedu.shop.mapper;

import com.qfedu.shop.pojo.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarDao {

    //添加到购物车-移除购物车
    //uid是用户id
    //pid是商品id
    int insertToCar(@Param("uid") Long uid, @Param("pid")Long pid);
    int insertToCarCount(@Param("uid") Long uid, @Param("pid")Long pid,@Param("count") int count);
    //删除一个条目
    int deleteToCar(@Param("uid") Long uid, @Param("pid")Long pid);
    //更新一个条目
    //count是数量，count不可为0
    int updateToCar(@Param("uid") Long uid, @Param("pid")Long pid,@Param("count")int count);

    //清空购物车
    //把这个car表中所有uid为指定的uid的条目都删除
    int clearShoppingCar(@Param("uid")Long uid);

    //查询
    //查询单个条目
    Car selectOneItem(@Param("uid") Long uid, @Param("pid")Long pid);

    //查询一个人的购物车
    List<Car> selectCarByUid(@Param("uid") Long uid);

}
