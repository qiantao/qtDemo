package com.qt.jdbc;

import cn.hutool.json.JSONUtil;

import java.util.LinkedHashMap;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/06/20 18:10
 * @version: V1.0
 */
public class DemoAAA {
    public static void main(String[] args) {
//        H3Dao.query();
//        Double d = 3D;
//        System.out.println(d);
        System.out.println("\\n");
        LinkedHashMap<String,String> map  = new LinkedHashMap();
        map.put("n","n1");
        map.put("d","d2");
        System.out.println(JSONUtil.toJsonStr(map));
    }
}
