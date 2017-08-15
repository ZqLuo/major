package com.example.dao;

import com.example.entity.MarketFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by zqLuo
 */
public interface MarketFileRepository extends JpaRepository<MarketFile,Integer> {

    /**
     * 根据销售编号获取相关文件
     * @param marketId
     * @return
     */
    @Query("select o from MarketFile o where o.market.id = ?1")
    List<MarketFile> getMarketFilesByMarketId(Integer marketId);

    /**
     * 根据文件名和销售编号删除文件
     * @param fileName
     * @param marketId
     */
    @Modifying
    @Query("delete from MarketFile m where m.fileName = ?1 and m.market.id = ?2")
    void deleteByFileNameAndMarketId(String fileName, Integer marketId);
}
