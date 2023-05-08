package com.qt.demo;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.util.ResourceUtils;

import java.util.Map;

/**
 * @Author MuYang
 * @Date 2022/9/21 14:32
 * @version: V1.0
 */
public class JsonDemo {
    public static void main(String[] args) {
        String sql = "SELECT * FROM (select B.TABLE_COMMENT ,A.TABLE_NAME,A.COLUMN_NAME,A.IS_NULLABLE,A.COLUMN_DEFAULT,A.DATA_TYPE,A.CHARACTER_MAXIMUM_LENGTH,A.NUMERIC_PRECISION,A.NUMERIC_SCALE,\n" +
                "A.DATETIME_PRECISION,A.COLUMN_COMMENT,C.CONSTRAINT_NAME,C.COLUMN_NAME KEYCOLUMN \n " +
                "from information_schema.`COLUMNS` A left join \n " +
                "information_schema.`TABLES` B ON A.TABLE_NAME = B.TABLE_NAME\n " +
                "left join information_schema.KEY_COLUMN_USAGE C \n" +
                "ON A.TABLE_NAME = C.TABLE_NAME and A.COLUMN_NAME = C.COLUMN_NAME AND C.CONSTRAINT_NAME='PRIMARY' \n " +
                "WHERE A.TABLE_NAME = '"+12+"' ) C order by C.TABLE_NAME ";
        System.out.println(sql);
    }

    public static String buildMap(String className,Map<String,Object> map,StringBuffer buf){
        buf.append("/**\n" +
                " * @Author MuYang\n" +
                " * @Date 2022/9/21 14:26\n" +
                " * @version: V1.0\n" +
                " */\n" +
                "@Data\n" +
                "public class "+BuildEntityStr.axaToAxA(className)+" {");

        for (String key : map.keySet()) {

            Object o = map.get(key);

            System.out.println(o instanceof String);
            System.out.println(o instanceof Integer);
            System.out.println(o instanceof Long);
            System.out.println(o instanceof JSONObject);


            System.out.println(key+":"+map.get(key).toString());

        }
        return "";
    }

}
