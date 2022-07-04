package com.qt.mybatisDemo.operate;

import com.qt.demo.BuildEntityStr;
import com.qt.demo.Demo;
import com.qt.jdbc.MsqlDBConnection;
import com.qt.mybatisDemo.builder.*;
import com.qt.mybatisDemo.data.GetDataByDB;
import com.qt.mybatisDemo.entity.TableInfo;
import com.qt.mybatisDemo.resource.Resource;
import com.qt.mybatisDemo.resource.SourceEntity;
import com.qt.util.StringUtil;

import java.util.List;
import java.util.Map;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/07/23 14:21
 * @version: V1.0
 */
public class Operate {
    private static final String P = "/";
    private static final String J = ".";
    private static final String basePath ="com/perfma/cmdb/";

    public static void doOperate(String targetFilePath,String dbshcame) throws Exception{
        List<SourceEntity> sourceEntityList = Resource.getResource();
        sourceEntityList.stream().forEach(sourceEntity -> {
            String h3module = sourceEntity.getH3Module();
            String module = sourceEntity.getModule();

            String xmlPath = targetFilePath +"src/main/resources/mapper/";
            String entityPoPath = targetFilePath +"src/main/java/"+basePath+h3module+P+(StringUtil.isNullTrim(module)?"":(module + P))+"domain/";
            String entityResultPath = targetFilePath +"src/main/java/"+basePath+h3module+P+(StringUtil.isNullTrim(module)?"":(module + P))+"domain/result/";
//            String entityPoPath = targetFilePath +"src/main/java/"+basePath+h3module+P+(StringUtil.isNullTrim(module)?"":(module + P))+"entity/po/";
            String daoPath = targetFilePath +"src/main/java/"+basePath+h3module+P+(StringUtil.isNullTrim(module)?"":(module + P))+"mapper/";
            String serviceFilePath = targetFilePath +"src/main/java/"+basePath+h3module+P+(StringUtil.isNullTrim(module)?"":(module + P))+"service/";
            String serviceImplFilePath = targetFilePath +"src/main/java/"+basePath+h3module+P+(StringUtil.isNullTrim(module)?"":(module + P))+"service"+P+"impl/";
            String managerFilePath = targetFilePath +"src/main/java/"+basePath+h3module+P+(StringUtil.isNullTrim(module)?"":(module + P))+"check/";
            String controllerFilePath = targetFilePath +"src/main/java/"+basePath+h3module+P+(StringUtil.isNullTrim(module)?"":(module + P))+"controller/";
            String utilFilePath = targetFilePath +"src/main/java/"+basePath+h3module+P+"common/util/";
            String groupFilePath = targetFilePath +"src/main/java/"+basePath+h3module+P+(StringUtil.isNullTrim(module)?"":(module + P))+"domain/group/";
            String voFilePath = targetFilePath +"src/main/java/"+basePath+h3module+P+(StringUtil.isNullTrim(module)?"":(module + P))+"domain/vo/";

            List<String> tableNames = sourceEntity.getTableNames();

            List<TableInfo> tableInfoList = GetDataByDB.getData(tableNames,dbshcame);
            Map<String, Map<String,String>> tableNameHasMethod = sourceEntity.getTableNameHasMethod();

            String mapperPath = basePath.replaceAll(P, J) + h3module + J + (StringUtil.isNullTrim(module) ? "" : (module + J)) + "mapper";
            String entityPath = basePath.replaceAll(P, J) + h3module + J + (StringUtil.isNullTrim(module) ? "" : (module + J)) + "domain";
            String entityRePath = basePath.replaceAll(P, J) + h3module + J + (StringUtil.isNullTrim(module) ? "" : (module + J)) + "domain"+ J +"result";
            String servicePath = basePath.replaceAll(P, J) + h3module + J + (StringUtil.isNullTrim(module) ? "" : (module + J)) + "service";
            String serviceImplPath = basePath.replaceAll(P, J) + h3module + J + (StringUtil.isNullTrim(module) ? "" : (module + J)) + "service"+J+"impl";
            String managerPath = basePath.replaceAll(P, J) + h3module + J + (StringUtil.isNullTrim(module) ? "" : (module + J)) + "check";
            String controllerPath = basePath.replaceAll(P, J) + h3module + J + (StringUtil.isNullTrim(module) ? "" : (module + J)) + "controller";
            String utilPath = basePath.replaceAll(P, J) + h3module + J + "common.util";
            String groupPath = basePath.replaceAll(P, J) + h3module + J + (StringUtil.isNullTrim(module) ? "" : (module + J)) + "domain.group";
            String voPath = basePath.replaceAll(P, J) + h3module + J + (StringUtil.isNullTrim(module) ? "" : (module + J)) + "domain.vo";


            String managerName = "t_"+(StringUtil.isNullTrim(module)?h3module:module);

            System.out.println(" ----生成manager开始-----");
            String temp = "";
            TableInfo tableInfo1 = new TableInfo();
            tableInfo1.setMapperPath(mapperPath);
            tableInfo1.setEntityPath(entityPath);
            tableInfo1.setServicePath(servicePath);
            tableInfo1.setServiceImplPath(serviceImplPath);
            tableInfo1.setManagerPath(managerPath);
            tableInfo1.setControllerPath(controllerPath);
            tableInfo1.setUtilPath(utilPath);
            tableInfo1.setGroupPath(groupPath);
            tableInfo1.setVoPath(voPath);
            tableInfo1.setTableName(managerName);
            tableInfo1.setEntityRePath(entityRePath);
//            temp  = ManagerBuilder.buildManager(tableInfo1);
//            Demo.StringToFile(managerFilePath + BuildEntityStr.axaToAxA(managerName) +"Manager.java",temp);
//            System.out.println(" ----生成manager结束-----");
//
//            temp  = ControllerBuilder.buildController(tableInfo1,module,h3module);
//            Demo.StringToFile(controllerFilePath + BuildEntityStr.axaToAxA(managerName) +"Controller.java",temp);
//            System.out.println(" ----生成controller结束-----");
//
//            temp  = UtilBuilder.buildUtil(tableInfo1,h3module);
//            Demo.StringToFile(utilFilePath + BuildEntityStr.axaToAxA(h3module) +"Util.java",temp);
//            temp  = UtilBuilder.buildUtilEntity(tableInfo1,h3module);
//            Demo.StringToFile(utilFilePath +"LoginSession.java",temp);
//            System.out.println(" ----生成util结束-----");
//
//            temp  = GroupBuilder.buildGroup(tableInfo1,module,h3module);
//            Demo.StringToFile(groupFilePath + BuildEntityStr.axaToAxA(managerName) +"Group.java",temp);
//            System.out.println(" ----生成group结束-----");

//            temp  = VoBuilder.buildVO(tableInfo1,module,h3module);
//            Demo.StringToFile(voFilePath + BuildEntityStr.axaToAxA(managerName) +"VO.java",temp);
//            System.out.println(" ----生成VO结束-----");

            for (TableInfo tableInfo:tableInfoList){
                tableInfo.setMapperPath(mapperPath);
                tableInfo.setEntityPath(entityPath);
                tableInfo.setServicePath(servicePath);
                tableInfo.setServiceImplPath(serviceImplPath);
                tableInfo.setManagerPath(managerPath);
                tableInfo.setVoPath(voPath);
                tableInfo.setEntityRePath(entityRePath);
                String tableName = tableInfo.getTableName();
                tableName = tableName.replace("_0","");
                tableInfo.setTableName(tableName);

                Map<String,String> map = tableNameHasMethod.get(tableName);

                String str = "";
////
                System.out.println("表:"+tableName+" ----生成XML开始-----");
                str = XMLBuilder.buildXml(tableInfo,map);
                Demo.StringToFile(xmlPath + BuildEntityStr.axaToAxA(tableInfo.getTableName()) +"Mapper.xml",str);
                System.out.println("表:"+tableName+" ----生成XML结束-----\n");

                System.out.println("表:"+tableName+" ----生成Dao开始-----");
                str = MapperBuillder.buildMapper(tableInfo,map);
                Demo.StringToFile(daoPath + BuildEntityStr.axaToAxA(tableInfo.getTableName()) +"Dao.java",str);
                System.out.println("表:"+tableName+" ----生成Dao结束-----\n");
//
//                System.out.println("表:"+tableName+" ----生成Entity开始-----");
//                str = EntityBuilder.buildEntity(tableInfo,map);
//                Demo.StringToFile( entityPoPath+ BuildEntityStr.axaToAxA(tableInfo.getTableName()) +".java",str);
//                System.out.println("表:"+tableName+" ----生成Entity结束-----");

                System.out.println("表:"+tableName+" ----生成无注解Entity开始-----");
                str = EntityBuild.buildEntity(tableInfo);
                Demo.StringToFile(entityPoPath + BuildEntityStr.axaToAxA(tableInfo.getTableName()) +".java",str);
                System.out.println("表:"+tableName+" ----生成无注解Entity结束-----");
//
                System.out.println("表:"+tableName+" ----生成Service开始-----");
                str = ServiceBuilder.buildService(tableInfo,map);
                Demo.StringToFile(serviceFilePath + BuildEntityStr.axaToAxA(tableInfo.getTableName()) +"Service.java",str);
                System.out.println("表:"+tableName+" ----生成Service结束-----");

                System.out.println("表:"+tableName+" ----生成ServiceImpl开始-----");
                str = ServiceImplBuilder.buildServiceImpl(tableInfo, map);
                Demo.StringToFile(serviceImplFilePath + BuildEntityStr.axaToAxA(tableInfo.getTableName()) +"ServiceImpl.java",str);
                System.out.println("表:"+tableName+" ----生成ServiceImpl结束-----");

//                System.out.println("表:"+tableName+" ----生成VO开始-----");
//                str = EntityBuilder.buildEntityNoColumn(tableInfo);
//                Demo.StringToFile(voFilePath + BuildEntityStr.axaToAxA(tableInfo.getTableName()) +"VO.java",str);
//                System.out.println("表:"+tableName+" ----生成VO结束-----");


                System.out.println("表:"+tableName+" ----完成.............\n\n");
            }
        });

        MsqlDBConnection.close();
    }

}
