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
 * @date: 2019/09/06 10:59
 * @version: V1.0
 */
public class ServiceBuilder {
    private static String dateStr = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

    public static String buildService(TableInfo tableInfo, Map<String, String> hasMethod){
        if(hasMethod == null) hasMethod = new HashMap<>();

        String tableName = tableInfo.getTableName();
        String mapperPath=tableInfo.getMapperPath();
        String servicePath = tableInfo.getServicePath();
        String serviceImplPath = tableInfo.getServiceImplPath();
        StringBuffer buf = new StringBuffer();


        buf.append("package ").append(servicePath).append(";\n");
        buf.append("\n");

        buf.append("/**\n" +
                " * @ClassName:  ").append(BuildEntityStr.axaToAxA(tableName)).append("Service\n" +
                " * @Description: ").append(BuildEntityStr.axaToAxA(tableInfo.getTableName())).append("接口\n" +
                " * @author: Muyang\n" +
                " * @date:  ").append(dateStr).append("\n" +
                " * @version: V1.0\n" +
                " */\n");
        buf.append("public interface ").append(BuildEntityStr.axaToAxA(tableName)).append("Service {\n");


        buf.append("}");


        return buf.toString();
    }
}
