package com.qfedu.shop.pojo;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private String id;//订单号当前毫秒数+uid
    private BigDecimal price;//订单总价值
    private String receiverAddress;//收货地址
    private String receiverName;//收件人
    private String receiverPhone;//收件人电话
    private int paystate;//支付状态：0代表未支付，1代表已支付
    private String orderTime;//订单创建时间
    private Long uid;//订单归属的用户
    private List<OrderItem> items;//订单里面的商品

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", paystate=" + paystate +
                ", orderTime='" + orderTime + '\'' +
                ", uid=" + uid +
                ", items=" + items +
                '}';
    }

    public String getId() {
        return id;
    }

    public Order setId(String id) {
        this.id = id;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Order setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public Order setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
        return this;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public Order setReceiverName(String receiverName) {
        this.receiverName = receiverName;
        return this;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public Order setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
        return this;
    }

    public int getPaystate() {
        return paystate;
    }

    public Order setPaystate(int paystate) {
        this.paystate = paystate;
        return this;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public Order setOrderTime(String orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public Long getUid() {
        return uid;
    }

    public Order setUid(Long uid) {
        this.uid = uid;
        return this;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Order setItems(List<OrderItem> items) {
        this.items = items;
        return this;
    }
}
