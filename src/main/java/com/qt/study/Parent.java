package com.qt.study;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author MuYang
 * @Date 2023/2/26 20:31
 * @version: V1.0
 */
@Getter
@Setter
@ToString
public class Parent {
    /**
     * 属性(成员)
     */
    private String name;
    /**
     * 属性
     */
    private int age;

    public String operate;

    /**
     * 无参数构造方法
     */
    public Parent(){

    }

    /**
     * 有参数的构造方法
     * @param age
     * @param name
     */
    public Parent(int age,String name){
        this.age = age;
        this.name = name;
    }

    public Parent(OParent o){
        this.age = o.getAge();
        this.name = o.getName();
        this.operate = o.getOperate();
    }


    /**
     * 类的方法
     *
     * @return
     */
    public String doSomething(String... args){

        return age+"岁的"+name+"在"+operate;
    }

}
