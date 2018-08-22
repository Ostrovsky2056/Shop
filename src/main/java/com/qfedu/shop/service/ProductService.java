package com.qfedu.shop.service;

import com.qfedu.shop.bean.PageInfo;
import com.qfedu.shop.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    int addProduct(Product product);
    int editProduct(Product product);
    int deleteProduct( Long id);
    List<Product> selectAll(
            Long id,
            String name,
            String category,
            BigDecimal minPrice,
            BigDecimal maxPrice);

    Product selectOneById(Long id);

    PageInfo<Product> findProductByPage(int pageNo, int pageSize);
}
