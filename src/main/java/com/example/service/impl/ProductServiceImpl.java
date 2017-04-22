package com.example.service.impl;

import com.example.dao.ProductRepository;
import com.example.entity.Product;
import com.example.service.ProductService;
import com.example.util.JdbcUtil;
import com.example.util.PageForSql;
import com.example.vo.ProductQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zqLuo
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private JdbcUtil<Product> jdbcUtil;


    @Override
    public PageForSql getProductList(ProductQueryVo productQuerty, int page, int size) {
        StringBuffer sql = new StringBuffer();
        sql.append("select p.id," +
                "product_no productNo," +
                "product_type productType," +
                "pt.name productTypeName," +
                "measurement_unit measurementUnit," +
                "mu.name measurementUnitName," +
                "purchase_date purchaseDate," +
                "quantity," +
                "price," +
                "remark " +
                "from product p left join sys_code pt " +
                "on (p.product_type = pt.code and pt.type = 'productType') " +
                "left join sys_code mu " +
                "on (p.measurement_unit = mu.code and mu.type = 'measurementUnit')");
        return jdbcUtil.queryForPage(sql.toString(),page,size, Product.class,null);
    }
}
