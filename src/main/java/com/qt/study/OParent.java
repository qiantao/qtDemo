package com.qt.study;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author MuYang
 * @Date 2023/2/26 20:44
 * @version: V1.0
 */
@Data
//@AllArgsConstructor
public class OParent {

    public OParent(int age,String name,String operate){
        //1
        this.age = age;
        //2
        this.name = name;
        //3
        this.operate = operate;
    }
    /**
     * 属性
     */
    private int age;

    /**
     * 属性(成员)
     */
    private String name;


    private String operate;
}
