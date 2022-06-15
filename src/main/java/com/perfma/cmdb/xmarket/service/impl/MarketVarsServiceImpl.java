package com.perfma.cmdb.xmarket.service.impl;

import com.perfma.cmdb.xmarket.mapper.MarketVarsDao;
import com.perfma.cmdb.xmarket.service.MarketVarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
/**
 * @ClassName:  MarketVarsServiceImpl
 * @Description: MarketVars服务
 * @author: QianTao
 * @date:  2022/05/18 10:20:22
 * @version: V1.0
 */
@Service
@Slf4j
public class MarketVarsServiceImpl implements MarketVarsService {
    @Autowired
    private MarketVarsDao marketVarsDao;


}