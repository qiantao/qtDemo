package com.qt.export;

import lombok.Data;

/**
 * @Author MuYang 
 * @Date 2022/6/22 15:09
 * @version: V1.0
 */
@Data
public class ExportDBEntity {
    /**
     * 表注释
     */
    private String tableComment;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 字段名称
     */
    private String columnName;
    /**
     * 是否必填
     */
    private String isNullable;
    /**
     * 字段默认值
     */
    private String columnDefault;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 字段长度
     */
    private String length;
    /**
     * 字段描述
     */
    private String columnComment;

}