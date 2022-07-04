package com.qt.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/01/29 17:11
 * @version: V1.0
 */
public class H3ElsEntity {
    private String tableId = "";
    private String tableName = "";
    private String columnTitle = "";
    private String columnId = "";
    private String columnKey = "";
    private String columnHidden = "";
    private String order = "";
    private String entityColumnType = "";
    private String columnTypeDisabled = "";
    private String entityColumnTypeData = "";
    //公共使用
    private String columnTypeName = "";
    private String columnTypePlaceholder = "";
    //普通列
    //可搜索字段
    private DownDate downDate;

    //枚举
        //搜索枚举
    private List<SearchEnums> searchEnumsList= new ArrayList<SearchEnums>();

        //展示枚举
    private List<ShowEnums> showEnumsList= new ArrayList<ShowEnums>();

    //范围查找
    private String columnTypeExtend = "";//扩展字段（3，范围搜索对应值true）

    //时间
    private String relationTableName = "";
    private String relationTableId = "";
    private String relationColumnName="";
    private String relationColumnId="";
    private String relationShowColumnName="";//展示字段名
    private String relationShowColumnId="";//展示字段ID
    List<RelaTable> relaTableList = new ArrayList<RelaTable>();
    List<RelaEntity> relaEntityList = new ArrayList<RelaEntity>();
//    private String entityColumnDataType = "";
//    private String entityColumnDataType = "";
//    private String entityColumnDataType = "";
//    private String entityColumnDataType = "";
//    private String entityColumnDataType = "";
//    private String entityColumnDataType = "";
//    private String entityColumnDataType = "";
    private String tableIds = "";
    private String tableNames = "";

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnTitle() {
        return columnTitle;
    }

    public void setColumnTitle(String columnTitle) {
        this.columnTitle = columnTitle;
    }

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getColumnHidden() {
        if(columnHidden==null||columnHidden.length()<1){
            return "0";
        }
        return columnHidden;
    }

    public void setColumnHidden(String columnHidden) {
        this.columnHidden = columnHidden;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getEntityColumnType() {
        return entityColumnType;
    }

    public void setEntityColumnType(String entityColumnType) {
        this.entityColumnType = entityColumnType;
    }

    public String getColumnTypeDisabled() {
        if(columnTypeDisabled==null || columnTypeDisabled.length()<1){
            return "0";
        }
        return columnTypeDisabled;
    }

    public void setColumnTypeDisabled(String columnTypeDisabled) {
        this.columnTypeDisabled = columnTypeDisabled;
    }

    public String getEntityColumnTypeData() {
        return entityColumnTypeData;
    }

    public void setEntityColumnTypeData(String entityColumnTypeData) {
        this.entityColumnTypeData = entityColumnTypeData;
    }

    public String getColumnTypeName() {
        return columnTypeName;
    }

    public void setColumnTypeName(String columnTypeName) {
        this.columnTypeName = columnTypeName;
    }

    public String getColumnTypePlaceholder() {
        return columnTypePlaceholder;
    }

    public void setColumnTypePlaceholder(String columnTypePlaceholder) {
        this.columnTypePlaceholder = columnTypePlaceholder;
    }

    public DownDate getDownDate() {
        return downDate;
    }

    public void setDownDate(DownDate downDate) {
        this.downDate = downDate;
    }

    public List<SearchEnums> getSearchEnumsList() {
        return searchEnumsList;
    }

    public void setSearchEnumsList(List<SearchEnums> searchEnumsList) {
        this.searchEnumsList = searchEnumsList;
    }

    public List<ShowEnums> getShowEnumsList() {
        return showEnumsList;
    }

    public void setShowEnumsList(List<ShowEnums> showEnumsList) {
        this.showEnumsList = showEnumsList;
    }

    public String getColumnTypeExtend() {
        return columnTypeExtend;
    }

    public void setColumnTypeExtend(String columnTypeExtend) {
        this.columnTypeExtend = columnTypeExtend;
    }

    public String getRelationTableName() {
        return relationTableName;
    }

    public void setRelationTableName(String relationTableName) {
        this.relationTableName = relationTableName;
    }

    public String getRelationTableId() {
        return relationTableId;
    }

    public void setRelationTableId(String relationTableId) {
        this.relationTableId = relationTableId;
    }

    public String getRelationColumnName() {
        return relationColumnName;
    }

    public void setRelationColumnName(String relationColumnName) {
        this.relationColumnName = relationColumnName;
    }

    public String getRelationColumnId() {
        return relationColumnId;
    }

    public void setRelationColumnId(String relationColumnId) {
        this.relationColumnId = relationColumnId;
    }

    public String getRelationShowColumnName() {
        return relationShowColumnName;
    }

    public void setRelationShowColumnName(String relationShowColumnName) {
        this.relationShowColumnName = relationShowColumnName;
    }

    public String getRelationShowColumnId() {
        return relationShowColumnId;
    }

    public void setRelationShowColumnId(String relationShowColumnId) {
        this.relationShowColumnId = relationShowColumnId;
    }

    public List<RelaTable> getRelaTableList() {
        return relaTableList;
    }

    public void setRelaTableList(List<RelaTable> relaTableList) {
        this.relaTableList = relaTableList;
    }

    public String getTableIds() {
        return tableIds;
    }

    public void setTableIds(String tableIds) {
        this.tableIds = tableIds;
    }

    public String getTableNames() {
        return tableNames;
    }

    public void setTableNames(String tableNames) {
        this.tableNames = tableNames;
    }

    public List<RelaEntity> getRelaEntityList() {
        return relaEntityList;
    }

    public void setRelaEntityList(List<RelaEntity> relaEntityList) {
        this.relaEntityList = relaEntityList;
    }
}
