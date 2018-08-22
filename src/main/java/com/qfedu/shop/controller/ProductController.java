package com.qfedu.shop.controller;

import com.google.gson.Gson;
import com.qfedu.shop.bean.PageInfo;
import com.qfedu.shop.pojo.Product;
import com.qfedu.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping("admin/showProductList")
    public String showProductList(

            @RequestParam(value = "id", required = false, defaultValue = "-1") Long id,
            @RequestParam(value = "name", required = false, defaultValue = "null") String name,
            @RequestParam(value = "category", required = false, defaultValue = "null") String category,
            @RequestParam(value = "minPrice", required = false, defaultValue = "0") BigDecimal minPrice,
            @RequestParam(value = "maxPrice", required = false, defaultValue = "999999") BigDecimal maxPrice,
            HttpServletRequest request
    ) {
        if (id == -1) {
            id = null;
        }
        if ("null".equals(name)) {
            name = null;
        }
        if ("null".equals(category)) {
            category = null;
        }
        if ("0".equals(minPrice)) {
            minPrice = null;
        }
        if ("999999".equals(maxPrice)) {
            maxPrice = null;
        }
        List<Product> list = productService.selectAll(id, name, category, minPrice, maxPrice);

        request.setAttribute("pros", list);

        return "products/list";


    }

    @RequestMapping("admin/addProduct")
    public String addProduct(
            @RequestParam("name") String name,
            @RequestParam("price") String price,
            @RequestParam("pnum") String pnum,
            @RequestParam("category") String category,
            @RequestParam("description") String description,
            @RequestParam("img_url") String img_url,
            HttpServletRequest request
    ) {
        Product product = new Product(name, price, pnum, category, description, img_url);

        int result = productService.addProduct(product);
        if (result > 0) {
            List<Product> list = productService.selectAll(null, null, null, null, null);

            request.setAttribute("pros", list);
            return "products/list";
        } else {
            return "products/error";
        }

    }


    //文件上传//
    //显示上传文件页面的方法
    //C:\Tomcat 8.5\ideaImages
    public static final String IMAGE_PATH = "C:" + File.separator + "Tomcat 8.5" + File.separator + "ideaImages";

    //接收文件的方法中
    @ResponseBody
    @RequestMapping("/fileupload")
    public String fileupload(
            HttpServletRequest request,
            @RequestParam("icon") MultipartFile file) throws Exception {
        System.out.println("接收到文件了" + file.toString());
        if (file != null && file.getOriginalFilename() != null && file.getOriginalFilename().length() > 0) {
            //保存文件的逻辑
            String origName = file.getOriginalFilename();

            //取后缀名
            String extendsName = origName.substring(origName.lastIndexOf("."));
            if (".png".equalsIgnoreCase(extendsName) ||
                    ".bmp".equalsIgnoreCase(extendsName) ||
                    ".jpg".equalsIgnoreCase(extendsName) ||
                    ".jpeg".equalsIgnoreCase(extendsName)) {
                //文件合法
                //防止文件重名
                //username+time
                //uuid

                String newFilename = UUID.randomUUID().toString() + extendsName;

                //创建一个文件
                File uploadFile = new File(IMAGE_PATH + File.separator + newFilename);
                //把文件保存进去
                file.transferTo(uploadFile);

                //生成水印文件
                //saveWaterMarkFile(request,uploadFile,newFilename);

                //返回文件名
                return "/images/" + newFilename;

            } else {
                //文件类型不正确
                return "error";
            }
        }


        return "error";
    }


    @RequestMapping("/admin/edit")
    public String edit(
            @RequestParam("id") Long id,
            HttpServletRequest request
    ) {
        //根据id查一个product
        //跳转到编辑页面
        Product product = productService.selectOneById(id);
        request.setAttribute("product", product);
        return "products/edit";
    }
    @RequestMapping("/showone")
    public String showone( @RequestParam("id") Long id,
                           HttpServletRequest request){
        //根据id查一个product
        //跳转到编辑页面
        Product product = productService.selectOneById(id);
        request.setAttribute("product", product);
        return "product_info";
    }


    @RequestMapping("/admin/doedit")
    public String doedit(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("price") String price,
            @RequestParam("pnum") String pnum,
            @RequestParam("category") String category,
            @RequestParam("description") String description,
            @RequestParam("img_url") String img_url,
            HttpServletRequest request
    ) {
        Product product = new Product(id, name, price, pnum, category, description, img_url);
        //使用上述参数依据id更新数据库
        int result= productService.editProduct(product);
        //查一个List<Product>

        //跳到list.jsp上
        if (result > 0) {
            List<Product> list = productService.selectAll(null, null, null, null, null);

            request.setAttribute("pros", list);
            return "products/list";
        } else {
            return "products/error";
        }
    }

    @RequestMapping("/admin/delete")
    public String delete(
            @RequestParam("id") Long id,
            HttpServletRequest request

    ){
       int result= productService.deleteProduct(id);
        if (result > 0) {
            List<Product> list = productService.selectAll(null, null, null, null, null);

            request.setAttribute("pros", list);
            return "products/list";
        } else {
            return "products/error";
        }

    }

    @RequestMapping("/showProductPage")
    public void showProductPage(
            @RequestParam("pageNo") int pageNo,
            @RequestParam("pageSize") int pageSize,
            HttpServletResponse response
    ) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PageInfo<Product> pageInfo=productService.findProductByPage(pageNo,pageSize);
        response.getWriter().print(new Gson().toJson(pageInfo));
    }



}
