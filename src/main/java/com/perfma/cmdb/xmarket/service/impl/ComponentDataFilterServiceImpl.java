package com.perfma.cmdb.xmarket.service.impl;

import com.perfma.cmdb.xmarket.mapper.ComponentDataFilterDao;
import com.perfma.cmdb.xmarket.service.ComponentDataFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
/**
 * @ClassName:  ComponentDataFilterServiceImpl
 * @Description: ComponentDataFilter服务
 * @author: QianTao
 * @date:  2022/05/18 10:20:22
 * @version: V1.0
 */
@Service
@Slf4j
public class ComponentDataFilterServiceImpl implements ComponentDataFilterService {
    @Autowired
    private ComponentDataFilterDao componentDataFilterDao;


}