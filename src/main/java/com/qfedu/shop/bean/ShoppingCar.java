package com.qfedu.shop.bean;

import com.qfedu.shop.pojo.Car;

import java.util.List;

public class ShoppingCar {
    private Long uid;
    private List<Car> items;

    @Override
    public String toString() {
        return "ShoppingCar{" +
                "uid=" + uid +
                ", items=" + items +
                '}';
    }

    public Long getUid() {
        return uid;
    }

    public ShoppingCar setUid(Long uid) {
        this.uid = uid;
        return this;
    }

    public List<Car> getItems() {
        return items;
    }

    public ShoppingCar setItems(List<Car> items) {
        this.items = items;
        return this;
    }
}
