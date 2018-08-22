package com.qfedu.shop.mapper;

import com.qfedu.shop.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao {
    int insertProduct(Product product);

    List<Product> selectAll(
            @Param("id")
                    Long id,
            @Param("name") String name,
            @Param("category") String category,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice);

    Product selectOneById(@Param("id")  Long id);

    int updateProduct(Product product);

    int deleteProduct(@Param("id")  Long id);

    List<Product> findProductPage(@Param("start") int start,@Param("size") int size);
    Long findProductCount();

}
