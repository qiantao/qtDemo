package com.perfma.cmdb.xmarket.service.impl;

import com.perfma.cmdb.xmarket.mapper.ComponentVarsRelaDao;
import com.perfma.cmdb.xmarket.service.ComponentVarsRelaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
/**
 * @ClassName:  ComponentVarsRelaServiceImpl
 * @Description: ComponentVarsRela服务
 * @author: QianTao
 * @date:  2022/05/18 10:20:22
 * @version: V1.0
 */
@Service
@Slf4j
public class ComponentVarsRelaServiceImpl implements ComponentVarsRelaService {
    @Autowired
    private ComponentVarsRelaDao componentVarsRelaDao;


}