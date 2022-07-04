package com.qt.util;

public class TableColumn {
    String columnName = "";
    String columnValue= "";
    String columnType="";
    String canNull="";
    String defultValue="";
    String length="";
    String pointLenth="";
    String promoKey="";
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getLength() {
        switch (columnType.toLowerCase()){
            case "bigint":return "(19)";
            case "int":return "(11)";
            case "tinyint":return "(2)";
            case "timestamp":return "";
            case "datetime":return "";
            case "date":return "";
            case "double":return "("+length+","+pointLenth+")";
            default:
                if("varchar".equals(columnType.toLowerCase())){
                  return "(100)";
                }
                return "("+length+")";

        }
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getPointLenth() {
        return pointLenth;
    }

    public void setPointLenth(String pointLenth) {
        this.pointLenth = pointLenth;
    }

    public String getDefultValue() {
        if(!"".equals(defultValue)){
            return "'"+defultValue+"'";
        }
        switch (columnType.toLowerCase()){
            case "bigint":return "'0'";
            case "int":return "'0'";
            case "tinyint":return "'0'";
            case "timestamp":return "CURRENT_TIMESTAMP";
            case "datetime":return "";
            case "string":return "''";
            case "double":
                if(this.pointLenth == null) {
                    return "'0.00'";
                }else if("4".equals(this.pointLenth)){
                    return "'0.0000'";
                }else if("6".equals(this.pointLenth)){
                    return "'0.000000'";
                }
                return "0.00";
            default:return "'"+defultValue+"'";

        }
    }

    public void setDefultValue(String defultValue) {
        this.defultValue = defultValue;
    }

    public String getCanNull() {
        return canNull;
    }

    public void setCanNull(String canNull) {
        this.canNull = canNull;
    }

    public String getPromoKey() {
        return promoKey;
    }

    public void setPromoKey(String promoKey) {
        this.promoKey = promoKey;
    }
}
