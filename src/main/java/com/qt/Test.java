package com.qt;

import cn.hutool.json.JSONUtil;
import com.TestEntity;
import com.google.gson.Gson;
import com.qt.ocr.GsonUtils;
import com.qt.util.TableColumn;

import java.util.Objects;

/**
 * @Author MuYang
 * @Date 2023/5/12 16:19
 * @version: V1.0
 */
public class Test {

    public static void main(String[] args) {
        TestEntity t = new TestEntity();

        System.out.println(Objects.equals(t.getFlag(),true));
        String s = "{\"respository\":{\"_id\":\"64d33d4451494f4878b3ea1c\",\"tenant\":\"64d1f7f7b4569130e5d64157\",\"tenantType\":\"organization\",\"name\":\"111\",\"note\":\"11\",\"_creatorId\":\"64be3a7c4fc0179e31623862\",\"isDeleted\":false,\"created\":\"2023-08-09T07:16:20.507Z\",\"updated\":\"2023-08-09T07:16:20.507Z\"},\"versionId\":\"64e30262971feb81762c916e\",\"repositoryId\":\"64d33d4451494f4878b3ea1c\"}";
//
    }

    public static int a(Long a){
        a = 3L;
        return 0;
    }
}
