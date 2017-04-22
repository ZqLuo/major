package com.example.dao;

import com.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by zqLuo
 */
public interface ProductRepository extends JpaRepository<Product,Integer>,JpaSpecificationExecutor<Product> {
}
