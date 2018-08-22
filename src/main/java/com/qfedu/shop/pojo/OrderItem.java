package com.qfedu.shop.pojo;

public class OrderItem extends Car{
    private String orderid;//这个订单项是隶属于哪个订单的。

    public String getOrderid() {
        return orderid;
    }

    public OrderItem setOrderid(String orderid) {
        this.orderid = orderid;
        return this;
    }

    //设置数据
    public void setData(Car car){
        this.setCount(car.getCount());
        this.setPid(car.getPid());
        this.setUid(car.getUid());
        this.setCategory(car.getCategory());
        this.setDescription(car.getDescription());
        this.setId(car.getId());
        this.setImg_url(car.getImg_url());
        this.setName(car.getName());
        this.setPnum(car.getPnum());
        this.setPrice(car.getPrice());
    }
    @Override
    public String toString() {
        return "OrderItem{" +
                "orderid='" + orderid + '\'' +
                '}'+super.toString();
    }
}
