package com.qt.enums;


/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/07/30 9:47
 * @version: V1.0
 */
public enum  MysqlTypeEnum {
    POS(3,"pos");
    private int id ;
    private String value;
    MysqlTypeEnum(int id,String value){
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
