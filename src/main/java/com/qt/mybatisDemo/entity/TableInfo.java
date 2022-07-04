package com.qt.mybatisDemo.entity;

import com.qt.demo.BuildEntityStr;

import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/07/18 10:47
 * @version: V1.0
 */
public class TableInfo {
    private String tableName ;
    private List<ColumnInfo> columnInfoList ;
    private String bsaeMap;//定义mapResult命明
    private String baseSql;//定义字段命名
    private String mapperPath;//daoMapper路径  com.hydee.h3.com.qt.demo.generator.entity
    private String entityPath ;//实体路径  com.hydee.h3.com.qt.demo.generator.mapper
    private String entityRePath ;//实体路径  com.hydee.h3.com.qt.demo.generator.mapper.result
    private String tableComent;
    private String servicePath;//接口路径
    private String serviceImplPath;//服务路径
    private String managerPath;//manager路径
    private String controllerPath;//controller路径
    private String utilPath;//util路径
    private String groupPath;//group路径
    private String voPath;//vo路径
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ColumnInfo> getColumnInfoList() {
        return columnInfoList;
    }

    public void setColumnInfoList(List<ColumnInfo> columnInfoList) {
        this.columnInfoList = columnInfoList;
    }

    public String getBsaeMap() {
        if(bsaeMap==null || bsaeMap.length()<1) return BuildEntityStr.axaToAxA(tableName)+"BaseMap";
        return bsaeMap;
    }

    public void setBsaeMap(String bsaeMap) {
        this.bsaeMap = bsaeMap;
    }

    public String getBaseSql() {
        if(baseSql==null || baseSql.length()<1) return BuildEntityStr.axaToAxA(tableName)+"BaseSql";
        return baseSql;
    }

    public void setBaseSql(String baseSql) {
        this.baseSql = baseSql;
    }

    public String getMapperPath() {
        return mapperPath;
    }

    public void setMapperPath(String mapperPath) {
        this.mapperPath = mapperPath;
    }

    public String getEntityPath() {
        return entityPath;
    }

    public void setEntityPath(String entityPath) {
        this.entityPath = entityPath;
    }

    public String getTableComent() {
        return tableComent;
    }

    public void setTableComent(String tableComent) {
        this.tableComent = tableComent;
    }

    public String getServicePath() {
        return servicePath;
    }

    public void setServicePath(String servicePath) {
        this.servicePath = servicePath;
    }

    public String getServiceImplPath() {
        return serviceImplPath;
    }

    public void setServiceImplPath(String serviceImplPath) {
        this.serviceImplPath = serviceImplPath;
    }

    public String getManagerPath() {
        return managerPath;
    }

    public void setManagerPath(String managerPath) {
        this.managerPath = managerPath;
    }

    public String getControllerPath() {
        return controllerPath;
    }

    public void setControllerPath(String controllerPath) {
        this.controllerPath = controllerPath;
    }

    public String getUtilPath() {
        return utilPath;
    }

    public void setUtilPath(String utilPath) {
        this.utilPath = utilPath;
    }

    public String getGroupPath() {
        return groupPath;
    }

    public void setGroupPath(String groupPath) {
        this.groupPath = groupPath;
    }

    public String getVoPath() {
        return voPath;
    }

    public void setVoPath(String voPath) {
        this.voPath = voPath;
    }

    public String getEntityRePath() {
        return entityRePath;
    }

    public void setEntityRePath(String entityRePath) {
        this.entityRePath = entityRePath;
    }
}
