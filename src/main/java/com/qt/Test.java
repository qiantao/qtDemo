package com.qt;

import cn.hutool.json.JSONUtil;
import com.TestEntity;
import com.google.gson.Gson;
import com.qt.ocr.GsonUtils;
import com.qt.util.TableColumn;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author MuYang
 * @Date 2023/5/12 16:19
 * @version: V1.0
 */
public class Test {

    public static void main(String[] args) {

        Map<String,Object> map = new HashMap<>();
        map.put("xxx","1");
        String jsonStr = JSONUtil.toJsonStr(map);
        byte[] bytes = jsonStr.getBytes();
//
    }

    public static int a(Long a){
        a = 3L;
        return 0;
    }
}
