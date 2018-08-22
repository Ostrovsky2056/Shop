package com.qfedu.shop.pojo;

public class Car extends Product{
    private Long uid;
    private Long pid;
    private int count;


    public Long getUid() {
        return uid;
    }

    public Car setUid(Long uid) {
        this.uid = uid;
        return this;
    }

    public Long getPid() {
        return pid;
    }

    public Car setPid(Long pid) {
        this.pid = pid;
        return this;
    }

    public int getCount() {
        return count;
    }

    public Car setCount(int count) {
        this.count = count;
        return this;
    }

    @Override
    public String toString() {
        return "Car{" +
                "uid=" + uid +
                ", pid=" + pid +
                ", count=" + count +
                '}'+super.toString();
    }
}
