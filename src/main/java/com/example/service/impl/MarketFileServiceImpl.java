package com.example.service.impl;

import com.example.config.SystemProperties;
import com.example.dao.MarketFileRepository;
import com.example.entity.Market;
import com.example.entity.MarketFile;
import com.example.service.MarketFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by zqLuo
 */
@Service
@Transactional(readOnly = true)
public class MarketFileServiceImpl implements MarketFileService {

    @Autowired
    private MarketFileRepository marketFileRepository;
    @Autowired
    private SystemProperties systemProperties;

    @Override
    public List<MarketFile> getMarketFilesByMarketId(String marketId) {
        return marketFileRepository.getMarketFilesByMarketId(Integer.parseInt(marketId));
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public MarketFile uploadMarketFile(MultipartFile file, String marketId) throws IOException {
        //保存文件
        String path = systemProperties.getFileRootPath();
        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        File targetFile = new File(path, fileName);
        if(targetFile.exists()){
            targetFile.delete();
            //删除文件纪录
            marketFileRepository.deleteByFileNameAndMarketId(fileName,Integer.parseInt(marketId));
        }
        // 保存
        file.transferTo(targetFile);

        MarketFile marketFile = new MarketFile();
        Market market = new Market();
        market.setId(Integer.parseInt(marketId));
        marketFile.setMarket(market);
        marketFile.setFileName(fileName);
        marketFile.setFilePath(targetFile.getPath());
        marketFileRepository.save(marketFile);
        return marketFile;
    }
}
