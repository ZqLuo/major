package com.example.controller;

import com.example.constant.AjaxJson;
import com.example.constant.MenuTypeEnum;
import com.example.constant.SysCodeEnum;
import com.example.entity.Product;
import com.example.entity.SysCode;
import com.example.entity.SysMenu;
import com.example.service.ProductService;
import com.example.service.SysMenuService;
import com.example.util.DateUtils;
import com.example.util.PageReturn;
import com.example.util.StringUtil;
import com.example.vo.ProductQueryVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品controller
 * Created by zqLuo
 */
@Controller
@RequestMapping("productController")
public class ProductController extends BaseController{

    private Logger logger = Logger.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;
    @Autowired
    private SysMenuService sysMenuService;

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

    @RequestMapping(value = "editProductPage")
    public String goAddProductPage(String id,Model model){
        Product product = new Product();
        SysMenu sysMenu = sysMenuService.getSysMenuByType(MenuTypeEnum.PRODUCT_MENU.getMenyType());
        if(StringUtil.isNotEmpty(id)){
            product = productService.getProductById(Integer.parseInt(id));
            if(StringUtil.isNotEmpty(product.getPurchaseDate())){
                product.setPurchaseDateStr(DateUtils.formatDate(product.getPurchaseDate(),"yyyy-MM-dd HH:mm:ss"));
            }
        }
        //计量单位
        List<SysCode> measurementUnits = this.findSysCodesByType(SysCodeEnum.MEASUREMENT_UNIT);
        //商品类型
        List<SysCode> productTypes = this.findSysCodesByType(SysCodeEnum.PRODUCT_TYPE);
        model.addAttribute("product",product);
        model.addAttribute("measurementUnits",measurementUnits);
        model.addAttribute("productTypes",productTypes);
        model.addAttribute("preMenuUrl",sysMenu.getMenuUrl());
        return "product/editProduct";
    }

    @ResponseBody
    @RequestMapping(value = "editProduct")
    public AjaxJson editProduct(Product product){
        Product p = productService.getProductByNoAndProductType(product.getProductNo(),product.getProductType());
        AjaxJson ajaxJson = new AjaxJson();
        if(p != null){
            if(!"0".equals(p.getDeleteTag()) && !p.getId().equals(product.getId())){
                ajaxJson.setSuccess(false);
                ajaxJson.setMsg("当前商品类型下已存在相同商品编号的商品");
                return ajaxJson;
            }
        }
        try {
            productService.createOrUpdateProduct(product);
        } catch (Exception e){
            logger.error("添加商品信息错误",e);
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("发生错误");
        }
        return ajaxJson;
    }

    @ResponseBody
    @RequestMapping(value = "delProduct")
    public AjaxJson delProduct(String id){
        AjaxJson ajaxJson = new AjaxJson();
        productService.delProduct(id);
        return ajaxJson;
    }

    /**
     * 根据商品类型获取商品编号
     * @param productType
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getProductNo")
    public Map<String,Object> getProductNoByProductype(String productType){
        List<Map<String,Object>> list = productService.getProductNoByProductype(productType);
        Map<String,Object> map = new HashMap<String,Object>();
        if(list != null && list.size()>0){
            for(Map<String,Object> m : list){
                map.put("procuctId"+m.get("PRODUCTID"),m.get("PRODUCTNO"));
            }
        }
        return map;
    }
}
