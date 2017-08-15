package com.example.service;

import com.example.entity.MarketFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by zqLuo
 */
public interface MarketFileService {

    /**
     * 根据销售编号获取相关文件
     * @param marketId
     * @return
     */
    List<MarketFile> getMarketFilesByMarketId(String marketId);

    /**
     * 上传文件
     * @param file
     * @param marketId
     */
    MarketFile uploadMarketFile(MultipartFile file, String marketId) throws IOException;
}
