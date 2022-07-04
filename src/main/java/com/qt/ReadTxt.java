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
//            File target = new File("/Users/qiantao/Desktop/xocean/b.sql");
//            if (!target.exists()) {
//                target.createNewFile();
//            }
            reader = new FileReader(origin);//定义一个fileReader对象，用来初始化BufferedReader
            bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
            StringBuilder sb = new StringBuilder();
//            out = new FileOutputStream(target);
            String s = "";
            String sql = "INSERT INTO `xportal-dev`.`x_auth_purview` ( `path`, `method`, `type`, `parent_id`, `description`, `icon`, `abandon_flag`) \n" +
                    "VALUES (\"%s\", \"%s\", 'API', 1, \"%s\", '1', 1);\n";
            StringBuilder buf = new StringBuilder();
            Map<String,String> map = new HashMap<>();
            Map<String,String> map1 = new HashMap<>();
            boolean flag = true;
            while ((s = bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
                if(StringUtils.isEmpty(s)){
                    continue;
                }
                if(flag && s.contains("---")){
                    flag = false;
                    continue;
                }
                s = s.replaceAll("> ","");
                s = s.replaceAll("< ","");
                String[] s1 = s.split("  ");
                if(flag){
                    map.put(s1[0],s1[1]);
                }else{
                    map1.put(s1[0],s1[1]);
                }
            }
            for (String key : map.keySet()) {
                if(map1.containsKey(key)){
                    buf.append("一致    md5:").append(key).append(" 本地scp上去的:").append(map.get(key)).append(" master上的:").append(map1.get(key)).append("\n");
                }else {
                    buf.append("不一致  md5:").append(key).append(" 本地scp上去的:").append(map.get(key)).append(" master上的:").append(map1.get(key)).append("\n");
                }
            }


            Demo.StringToFile("/Users/qiantao/Desktop/xocean/b.txt",buf.toString());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
//                out.close();
                bReader.close();
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void a(){
        String s ="{'path': '/api/redoc', 'name': 'redoc_html', 'methods': {'HEAD', 'GET'}}";

    }
}
