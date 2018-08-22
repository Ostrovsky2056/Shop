package com.qfedu.shop.service.impl;

import com.qfedu.shop.bean.PageInfo;
import com.qfedu.shop.mapper.ProductDao;
import com.qfedu.shop.pojo.Product;
import com.qfedu.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Override
    public int addProduct(Product product) {
        int result=productDao.insertProduct(product);
        return result;
    }

    @Override
    public List<Product> selectAll(Long id, String name, String category, BigDecimal minPrice, BigDecimal maxPrice) {
        if(name!=null){
            name="%"+name+"%";
        }

        if(category!=null){
            category="%"+category+"%";
        }
        return productDao.selectAll(id,name,category,minPrice,maxPrice);

    }

    @Override
    public Product selectOneById(Long id) {
        return productDao.selectOneById(id);
    }

    @Override
    public int editProduct(Product product) {
        return productDao.updateProduct(product);
    }

    @Override
    public int deleteProduct(Long id) {
        return productDao.deleteProduct(id);
    }

    @Override
    public PageInfo<Product> findProductByPage(int pageNo, int pageSize) {
        PageInfo<Product> pageInfo=new PageInfo<>();

        int start=(pageNo-1)*pageSize;
        List<Product> items=productDao.findProductPage(start,pageSize);
        long count=productDao.findProductCount();
        int pageCount= (int) ((count/pageSize)+(count%pageSize==0?0:1));
        pageInfo.setItems(items);
        pageInfo.setPageCount(pageCount);
        pageInfo.setPageNo(pageNo);
        pageInfo.setPageSize(pageSize);

        return pageInfo;
    }
}
