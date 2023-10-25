package com.qt.mybatisDemo;


import cn.hutool.json.JSONUtil;
import com.qt.mybatisDemo.operate.Operate;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * @ClassName:
 * @Description: 11111111111
 * @author: QianTao
 * @date: 2019/07/18 9:58
 * @version: V1.0
 */
public class MybatisDemo {

    public static void main(String[] args) throws Exception{
//        String targetFilePath = "/Users/qiantao/Desktop/git/qt/github/qtdemo/";//目标文件夹
//        String dbshcame = "perfma_xark";
//        Operate.doOperate(targetFilePath,dbshcame);
//        System.out.println(System.currentTimeMillis());
        String s = "{\"d\":1697616715583}";
        LocalDateTime localDateTime = LocalDateTime.now();
        Pl p = new Pl();
        p.setD(LocalDateTime.now());
        System.out.println(JSONUtil.toBean(s, Pl.class));
//        byte[] bytes = s.getBytes();
//        for (byte aByte : bytes) {
//            System.out.println(aByte);
//        }
//        String msg = new String(bytes);
//        System.out.println(msg);


//        System.out.println(6 << 2);
    }

}
