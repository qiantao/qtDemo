package com.qt.mybatisDemo.enums;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/07/18 11:28
 * @version: V1.0
 */
public enum  MethodEnum {
    INSERT_METHOD("insert"),
    INSERT_SELECTIVE("insertSelective"),
    INSERT_SELECTIVE_LIST("insertSelectiveList"),
    UPDATE_SELECTIVE("updateByEntitySelective"),
    UPDATE("updateById"),
    UPDATELIST("updateListById"),
    SELECT("selectListByEntity"),
    SELECTONE("selectByEntity"),
    INSERT_LIST("insertList"),
    UPDATE_SELECTIVE_LIST("updateSelectiveList");
    private String value;
    MethodEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
