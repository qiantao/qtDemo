package com.qt;

import com.qt.demo.Demo;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ReadTxt {
    public static void main(String[] args) {
        FileOutputStream out=null;
        BufferedReader bReader=null;
        FileReader reader =null;
        try {
            File origin = new File("/Users/qiantao/Desktop/1.txt");
            reader = new FileReader(origin);//定义一个fileReader对象，用来初始化BufferedReader
            bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
            StringBuilder buf = new StringBuilder();
            String s = "";
            while ((s = bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
                if(StringUtils.isEmpty(s)){
                }
            }

            Demo.StringToFile("/Users/qiantao/Desktop/xocean/b.txt",buf.toString());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                bReader.close();
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
