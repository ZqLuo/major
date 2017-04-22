package com.example.controller;

import com.example.entity.Product;
import com.example.service.ProductService;
import com.example.util.PageForSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品controller
 * Created by zqLuo
 */
@Controller
@RequestMapping("productController")
public class ProductController extends BaseController{

    @Autowired
    private ProductService productService;

    @RequestMapping("productList")
    public String productList(Model model){
        PageForSql pageForSql = productService.getProductList(null,getPage(),getSize());
        return "product/productList";
    }
}
