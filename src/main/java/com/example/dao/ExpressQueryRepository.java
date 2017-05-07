package com.example.dao;

import com.example.entity.ExpressQuery;
import com.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 快递查询
 * Created by zqLuo
 */
public interface ExpressQueryRepository extends JpaRepository<ExpressQuery,Integer>{

    /**
     * 根据快递单号查询快递查询信息
     * @param expressNo
     * @return
     */
    @Query("select o from ExpressQuery o where o.expressNo = ?1")
    ExpressQuery getExpressQueryByNo(String expressNo);
}
