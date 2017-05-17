package com.example.service.impl;

import com.example.dao.ProductHistoryRepository;
import com.example.dao.ProductRepository;
import com.example.entity.Product;
import com.example.entity.ProductHistory;
import com.example.service.ProductService;
import com.example.util.DateUtils;
import com.example.util.JdbcUtil;
import com.example.util.PageReturn;
import com.example.util.StringUtil;
import com.example.vo.ProductQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by zqLuo
 */
@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private JdbcUtil<Product> jdbcUtil;
    @Autowired
    private JdbcUtil<ProductHistory> jdbcUtilHistory;
    @Autowired
    private ProductHistoryRepository productHistoryRepository;


    @Override
    public PageReturn getProductList(ProductQueryVo productQuerty, int page, int size) {
        StringBuffer sql = new StringBuffer();
        List<Object> params = new ArrayList<Object>();
        sql.append("select p.id," +
                "product_no productNo," +
                "product_type productType," +
                "pt.name productTypeName," +
                "measurement_unit measurementUnit," +
                "mu.name measurementUnitName," +
                "purchase_date purchaseDate," +
                "quantity," +
                "price," +
                "remark," +
                "total_quatity totalQuantity,"+
                "total_price totalPrice "+
                "from product p left join sys_code pt " +
                "on (p.product_type = pt.code and pt.type = 'productType') " +
                "left join sys_code mu " +
                "on (p.measurement_unit = mu.code and mu.type = 'measurementUnit') where delete_tag = '1' ");
        if(productQuerty != null){
            if(StringUtil.isNotEmpty(productQuerty.getProductType())){
                sql.append(" and product_type = ? ");
                params.add(productQuerty.getProductType());
            }
            if(StringUtil.isNotEmpty(productQuerty.getPurchaseDateBegin())){
                sql.append(" and purchase_date >= str_to_date(?,'%Y-%m-%d %H:%i:%s')");
                params.add(productQuerty.getPurchaseDateBegin()+" 00:00:00");
            }
            if(StringUtil.isNotEmpty(productQuerty.getPurchaseDateEnd())){
                sql.append(" and purchase_date <= str_to_date(?,'%Y-%m-%d %H:%i:%s')");
                params.add(productQuerty.getPurchaseDateEnd()+" 23:59:59");
            }
            if(StringUtil.isNotEmpty(productQuerty.getProductNo())){
                sql.append(" and product_no like ? ");
                params.add("%" + productQuerty.getProductNo() + "%");
            }
        }
        sql.append(" order by purchase_date desc");
        return jdbcUtil.queryForPage(sql.toString(),page,size, Product.class,params.toArray());
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void createOrUpdateProduct(Product product) {
        if(StringUtil.isNotEmpty(product.getPurchaseDateStr())){
            product.setPurchaseDate(DateUtils.parse(product.getPurchaseDateStr(),"yyyy-MM-dd HH:mm:ss"));
        }else {
            product.setPurchaseDate(new Date());
        }
        productRepository.save(product);
    }

    @Override
    public Product getProductByNoAndProductType(String productNo, String productType) {
        return productRepository.getProductByNoAndProductType(productNo,productType);
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void delProduct(String id) {
        Product product = productRepository.findOne(Integer.parseInt(id));
        product.setDeleteTag("0");
        productRepository.save(product);
    }

    @Override
    public List<Map<String,Object>> getProductNoByProductype(String productType) {
        String sql = "select product_no PRODUCTNO,id PRODUCTID  from product where product_type = ?";
        return jdbcUtil.queryForListMap(sql,productType);
    }

    @Override
    public PageReturn getProductHistoryList(String productId,int page,int size) {
        StringBuffer sql = new StringBuffer();
        sql.append("select id,in_date inDate,price, quantity,total_price totalPrice,remark from product_history where product_id = ? ");
        List<Object> params = new ArrayList<Object>();
        params.add(productId);
        return jdbcUtilHistory.queryForPage(sql.toString(),page,size, ProductHistory.class,params.toArray());
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public ProductHistory saveOrUpdateProductHistory(ProductHistory productHistory) throws Exception {
        if(StringUtil.isNotEmpty(productHistory.getInDateValue())){
            productHistory.setInDate(DateUtils.parseDate(productHistory.getInDateValue(),"yyyy-MM-dd"));
        }else {
            productHistory.setInDate(new Date());
        }
        productHistoryRepository.save(productHistory);
        return productHistory;
    }

    @Override
    public ProductHistory getProductHistoryById(String id) {
        return productHistoryRepository.findOne(Integer.parseInt(id));
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void updateProductPriceAndQuantity(Integer productId){
        productHistoryRepository.updateProductPriceAndQuantity(productId);
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void removeProductHistory(String id) {
        productHistoryRepository.delete(Integer.parseInt(id));
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void updateProductResidueQuantity(String id) {
        productRepository.updateProductResidueQuantity(Integer.parseInt(id));
    }
}
