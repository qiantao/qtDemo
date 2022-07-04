package com.qt.util;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/01/31 9:38
 * @version: V1.0
 */
public class RelaTable {
    private String columnTypeName="";
    private String columnTypePlaceholder="";
    private String columnTypeDisabled="";
    private String columnId="";
    private String columnKey="";
    private String order="";

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

    public String getColumnTypeDisabled() {
        return columnTypeDisabled;
    }

    public void setColumnTypeDisabled(String columnTypeDisabled) {
        this.columnTypeDisabled = columnTypeDisabled;
    }

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }
}
