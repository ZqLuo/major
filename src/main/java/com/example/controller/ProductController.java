package com.example.controller;

import com.example.constant.SysCodeEnum;
import com.example.entity.SysCode;
import com.example.service.ProductService;
import com.example.util.PageReturn;
import com.example.vo.ProductQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品controller
 * Created by zqLuo
 */
@Controller
@RequestMapping("productController")
public class ProductController extends BaseController{

    @Autowired
    private ProductService productService;

    /**
     * 商品管理页面跳转
     * @param model
     * @return
     */
    @RequestMapping("productListPage")
    public String productListPage(Model model){
        List<SysCode> productTypes = this.findSysCodesByType(SysCodeEnum.PRODUCT_TYPE);
        model.addAttribute("productTypes",productTypes);
        return "product/productList";
    }

    /**
     * 获取商品列表
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "productList",method = RequestMethod.POST)
    public PageReturn productList(ProductQueryVo productQueryVo){
        PageReturn prageReturn = productService.getProductList(productQueryVo,getPage(),getSize());
        return prageReturn;
    }
}
