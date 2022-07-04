package com.qt.mybatisDemo.builder;

import com.qt.demo.BuildEntityStr;
import com.qt.mybatisDemo.entity.TableInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/09/06 11:01
 * @version: V1.0
 */
public class ServiceImplBuilder {
    private static String dateStr = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

    public static String buildServiceImpl(TableInfo tableInfo, Map<String, String> hasMethod){
        if(hasMethod == null) hasMethod = new HashMap<>();

        String tableName = tableInfo.getTableName();
        String mapperPath=tableInfo.getMapperPath();
        String servicePath = tableInfo.getServicePath();
        String serviceImplPath = tableInfo.getServiceImplPath();
        StringBuffer buf = new StringBuffer();


        buf.append("package ").append(serviceImplPath).append(";\n");
        buf.append("\n");
//        buf.append("import com.hydee.h3.common.log.LoggerBuilder;\n");
        buf.append("import ").append(mapperPath).append(".").append(BuildEntityStr.axaToAxA(tableName)).append("Dao;\n");
        buf.append("import ").append(servicePath).append(".").append(BuildEntityStr.axaToAxA(tableName)).append("Service;\n");
//        buf.append("import org.slf4j.Logger;\n");
        buf.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        buf.append("import org.springframework.stereotype.Service;\n");
        buf.append("import lombok.extern.slf4j.Slf4j;\n");

        buf.append("/**\n" +
                " * @ClassName:  ").append(BuildEntityStr.axaToAxA(tableName)).append("ServiceImpl\n" +
                " * @Description: ").append(BuildEntityStr.axaToAxA(tableInfo.getTableName())).append("服务\n" +
                " * @author: Muyang\n" +
                " * @date:  ").append(dateStr).append("\n" +
                " * @version: V1.0\n" +
                " */\n");
        buf.append("@Service\n");
        buf.append("@Slf4j\n");
        buf.append("public class ").append(BuildEntityStr.axaToAxA(tableName)).append("ServiceImpl implements ").append(BuildEntityStr.axaToAxA(tableName)).append("Service {\n");
//        buf.append("    private Logger log = LoggerBuilder.getLogger(this.getClass());\n" +
        buf.append("    @Autowired\n" +
                "    private ").append(BuildEntityStr.axaToAxA(tableName)).append("Dao ").append(BuildEntityStr.axaToaxA(tableName)).append("Dao;\n");

        buf.append("\n\n}");


        return buf.toString();
    }
}
