package com.perfma.cmdb.xmarket.service.impl;

import com.perfma.cmdb.xmarket.mapper.ComponentJsonDao;
import com.perfma.cmdb.xmarket.service.ComponentJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
/**
 * @ClassName:  ComponentJsonServiceImpl
 * @Description: ComponentJson服务
 * @author: QianTao
 * @date:  2022/05/18 10:20:22
 * @version: V1.0
 */
@Service
@Slf4j
public class ComponentJsonServiceImpl implements ComponentJsonService {
    @Autowired
    private ComponentJsonDao componentJsonDao;


}