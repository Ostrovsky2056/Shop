package com.qfedu.shop.pojo;

import java.math.BigDecimal;

//商品类
public class Product {
    private Long id;//商品id
    private String name;//商品名称
    private BigDecimal price;//商品价格
    private int pnum;//库存数量
    private String category;//商品类型
    private String description;//商品描述
    private String img_url;//图片的url

    public Product(Long id, String name, String price, String pnum, String category, String description, String img_url) {
        this(name, price, pnum, category, description, img_url);
        this.id = id;
    }

    public Product(String name, String price, String pnum, String category, String description, String img_url) {
        this.name = name;
        this.price = new BigDecimal(price);
        this.pnum = Integer.parseInt(pnum);
        this.category = category;
        this.description = description;
        this.img_url = img_url;
    }

    public Long getId() {
        return id;
    }

    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public int getPnum() {
        return pnum;
    }

    public Product setPnum(int pnum) {
        this.pnum = pnum;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Product setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImg_url() {
        return img_url;
    }

    public Product setImg_url(String img_url) {
        this.img_url = img_url;
        return this;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", pnum=" + pnum +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", img_url='" + img_url + '\'' +
                '}';
    }

    public Product() {
    }
}
