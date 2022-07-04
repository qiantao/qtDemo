package com.qt.yapi;

import java.io.Serializable;
import java.util.List;

public class Parameter implements Serializable {
    String name;
    String type;
    int required;
    String desc;
    int level;
    int levelid;
    int parentid;
    String arrayItemsType;
    String arrayItemsDesc;
    String enumDesc;
    String min;
    String max;
    List<Parameter> sonParameterList;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        String ddd="object";
        if("string".equals(type)||"String".equals(type)){
            ddd="string";
        }
        if("integer".equals(type)||"int".equals(type)||"long".equals(type)||"double".equals(type)||"number".equals(type)){
            ddd="integer";
        }
        if("boolean".equals(type)||"Boolean".equals(type)){
            ddd ="boolean";
        }
        if("array".equals(type)){
            ddd = type;
        }
        return ddd;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRequired() {
        return required;
    }

    public void setRequired(int required) {
        this.required = required;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevelid() {
        return levelid;
    }

    public void setLevelid(int levelid) {
        this.levelid = levelid;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public List<Parameter> getSonParameterList() {
        return sonParameterList;
    }

    public void setSonParameterList(List<Parameter> sonParameterList) {
        this.sonParameterList = sonParameterList;
    }

    public String getArrayItemsType() {
        return arrayItemsType;
    }

    public void setArrayItemsType(String arrayItemsType) {
        this.arrayItemsType = arrayItemsType;
    }

    public String getArrayItemsDesc() {
        return arrayItemsDesc;
    }

    public void setArrayItemsDesc(String arrayItemsDesc) {
        this.arrayItemsDesc = arrayItemsDesc;
    }

    public String getEnumDesc() {
        return enumDesc;
    }

    public void setEnumDesc(String enumDesc) {
        this.enumDesc = enumDesc;
    }

    public String getMin() {
        if(getType().equals("string")||getType().equals("integer")){
            min = "1";
        }
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        if(getType().equals("string")){
            max = "10";
        }else if(getType().equals("integer")){
            max = "10000";
        }
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }
}
