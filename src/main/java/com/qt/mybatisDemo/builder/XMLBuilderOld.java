package com.qt.mybatisDemo.builder;

import com.qt.demo.BuildEntityStr;
import com.qt.mybatisDemo.entity.ColumnInfo;
import com.qt.mybatisDemo.entity.TableInfo;


import com.qt.mybatisDemo.enums.JDBCTypeEnum;
import com.qt.mybatisDemo.enums.MethodEnum;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/07/18 10:38
 * @version: V1.0
 */
public class XMLBuilderOld {
    private static String tableName;
    private static String bsaeMap;
    private static String baseSql;
    private static String mapperPath;
    private static String entityPath;
    private static List<ColumnInfo> columnInfoList;
    public static String buildXml(TableInfo tableInfo, Map<String,String> hasMethod){
        if(hasMethod==null) hasMethod =new HashMap<>();

        tableName = tableInfo.getTableName();
        columnInfoList =tableInfo.getColumnInfoList();
        bsaeMap = tableInfo.getBsaeMap();
        baseSql=tableInfo.getBaseSql();
        mapperPath=tableInfo.getMapperPath()+"."+BuildEntityStr.axaToAxA(tableName)+"Dao";
        entityPath = tableInfo.getEntityPath()+"."+BuildEntityStr.axaToAxA(tableName);


        StringBuffer buf = new StringBuffer();
        buf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        buf.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
        buf.append("<mapper namespace=\"").append(mapperPath).append("\">\n");


        resultMap(buf);
        sql(buf);
        if(!hasMethod.containsKey(MethodEnum.SELECT.getValue())||"true".equals(hasMethod.get(MethodEnum.SELECT.getValue()))) {
            select(buf);
        }
        if(!hasMethod.containsKey(MethodEnum.SELECTONE.getValue())||"true".equals(hasMethod.get(MethodEnum.SELECTONE.getValue()))) {
            selectone(buf);
        }
        if(!hasMethod.containsKey(MethodEnum.INSERT_SELECTIVE.getValue())||"true".equals(hasMethod.get(MethodEnum.INSERT_SELECTIVE.getValue()))) {
            insertSelective(buf);
        }
        if(!hasMethod.containsKey(MethodEnum.INSERT_SELECTIVE_LIST.getValue())||"true".equals(hasMethod.get(MethodEnum.INSERT_SELECTIVE_LIST.getValue()))) {
            insertSelectiveList(buf);
        }
//        if(!hasMethod.containsKey(MethodEnum.INSERT_METHOD.getValue())||"true".equals(hasMethod.get(MethodEnum.INSERT_METHOD.getValue()))) {
//            insert(buf);
//        }
//        if(!hasMethod.containsKey(MethodEnum.INSERT_LIST.getValue())||"true".equals(hasMethod.get(MethodEnum.INSERT_LIST.getValue()))) {
//            insertList(buf);
//        }
//        if(!hasMethod.containsKey(MethodEnum.UPDATE.getValue())||"true".equals(hasMethod.get(MethodEnum.UPDATE.getValue()))) {
//            update(buf);
//        }
        if(!hasMethod.containsKey(MethodEnum.UPDATE_SELECTIVE.getValue())||"true".equals(hasMethod.get(MethodEnum.UPDATE_SELECTIVE.getValue()))) {
            updateSelective(buf);
        }
        if(!hasMethod.containsKey(MethodEnum.UPDATELIST.getValue())||"true".equals(hasMethod.get(MethodEnum.UPDATELIST.getValue()))) {
            updateList(buf);
        }
        if(!hasMethod.containsKey(MethodEnum.UPDATE_SELECTIVE_LIST.getValue())||"true".equals(hasMethod.get(MethodEnum.UPDATELIST.getValue()))) {
            updateSelectiveList(buf);
        }




        buf.append("</mapper>\n");

        return buf.toString();

    }

    /**
     * insertSelective
     * @param buf
     */
    private static void insertSelective(StringBuffer buf){
        //-----------insertSelective---开始
        buf.append("    <!-- 插入非空数据 -->\n");
        buf.append("    <insert id=\"").append(MethodEnum.INSERT_SELECTIVE.getValue()).append("\" useGeneratedKeys=\"true\" keyProperty=\"id\" parameterType=\"").append(entityPath).append("\">\n");
        buf.append("        insert into \n");
        buf.append("        <include refid=\"TableName\"/>\n");
        buf.append("        <trim  prefix=\"(\" suffix=\")\" suffixOverrides=\",\"> \n" );

        for (ColumnInfo columnInfo:columnInfoList){
            if("create_time".equals(columnInfo.getColumnName())||"modify_time".equals(columnInfo.getColumnName())) continue;
            buf.append("            <if test=\"").append(BuildEntityStr.axaToaxA(columnInfo.getColumnName())).append(" != null\">\n");
            buf.append("                ").append(columnInfo.getColumnName()).append(",\n");
            buf.append("            </if>\n");
        }
        buf.append("        </trim>\n");
        buf.append("        <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n");

        for (ColumnInfo columnInfo:columnInfoList){
            if("create_time".equals(columnInfo.getColumnName())||"modify_time".equals(columnInfo.getColumnName())) continue;
            buf.append("            <if test=\"").append(BuildEntityStr.axaToaxA(columnInfo.getColumnName())).append(" != null\">\n");
            buf.append("                #{").append(BuildEntityStr.axaToaxA(columnInfo.getColumnName())).append(",jdbcType=");
            buf.append(JDBCTypeEnum.getJDBCTypeByDBType(columnInfo.getDataType())).append("},\n");
            buf.append("            </if>\n");
        }
        buf.append("        </trim>\n");
        buf.append("\n    </insert>\n\n");
        //-----------insertSelective---结束
    }
    /**
     * insertSelectiveList
     * @param buf
     */
    private static void insertSelectiveList(StringBuffer buf){
        //-----------insertSelective---开始
        buf.append("    <!-- 插入非空数据集合 -->\n");
        buf.append("    <insert id=\"").append(MethodEnum.INSERT_SELECTIVE_LIST.getValue()).append("\" useGeneratedKeys=\"true\" keyProperty=\"id\">\n");
        buf.append("        <foreach collection=\"list\" item=\"item\" separator=\";\">\n");
        buf.append("            insert into \n");
        buf.append("            <include refid=\"TableName\"/>\n");
        buf.append("            <trim  prefix=\"(\" suffix=\")\" suffixOverrides=\",\"> \n" );

        for (ColumnInfo columnInfo:columnInfoList){
            if("create_time".equals(columnInfo.getColumnName())||"modify_time".equals(columnInfo.getColumnName())) continue;
            buf.append("                <if test=\"item.").append(BuildEntityStr.axaToaxA(columnInfo.getColumnName())).append(" != null\">\n");
            buf.append("                    ").append(columnInfo.getColumnName()).append(",\n");
            buf.append("                </if>\n");
        }
        buf.append("            </trim>\n");
        buf.append("            <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n");

        for (ColumnInfo columnInfo:columnInfoList){
            if("create_time".equals(columnInfo.getColumnName())||"modify_time".equals(columnInfo.getColumnName())) continue;
            buf.append("                <if test=\"item.").append(BuildEntityStr.axaToaxA(columnInfo.getColumnName())).append(" != null\">\n");
            buf.append("                    #{item.").append(BuildEntityStr.axaToaxA(columnInfo.getColumnName())).append(",jdbcType=");
            buf.append(JDBCTypeEnum.getJDBCTypeByDBType(columnInfo.getDataType())).append("},\n");
            buf.append("                </if>\n");
        }
        buf.append("            </trim>\n");
        buf.append("        </foreach>\n");
        buf.append("\n    </insert>\n\n");
        //-----------insertSelective---结束
    }
    /**
     * resultMap
     * @param buf
     */
    private static void resultMap(StringBuffer buf){
        //-----------resultMap---开始
        buf.append("   <resultMap id=\"").append(bsaeMap).append("\" type=\"").append(entityPath).append("\">\n");

        for (ColumnInfo columnInfo:columnInfoList){

            if("id".equals(columnInfo.getColumnName())){
                buf.append("        <id column=\"id\" jdbcType=\"").append(JDBCTypeEnum.getJDBCTypeByDBType(columnInfo.getDataType())).append("\" property=\"id\" />\n");
            }else {
                buf.append("        <result column=\"").append(columnInfo.getColumnName())
                        .append("\" jdbcType=\"").append(JDBCTypeEnum.getJDBCTypeByDBType(columnInfo.getDataType()))
                        .append("\" property=\"").append(BuildEntityStr.axaToaxA(columnInfo.getColumnName())).append("\" />\n");
            }
        }
        buf.append("   </resultMap>\n\n");
        //-----------resultMap---结束
    }

    /**
     * sql
     * @param buf
     */
    private static void sql(StringBuffer buf){
        StringBuffer sqlBuf = new StringBuffer();
        for (ColumnInfo columnInfo:columnInfoList) {

            sqlBuf.append(columnInfo.getColumnName()).append(",");
        }
        sqlBuf.setLength(sqlBuf.length()-1);
        //--SQL 代码块开始---
        buf.append("    <sql id=\"").append(baseSql).append("\">\n        ");
        buf.append(sqlBuf).append("\n");
        buf.append("    </sql>\n\n");
        buf.append("    <sql id=\"TableName\">\n");
        buf.append("        ").append(tableName).append("\n");
        buf.append("    </sql>\n\n");
        //--SQL 代码块开始---
    }

    /**
     * insert
     * @param buf
     */
    private static void insert(StringBuffer buf){
        //-----------insert---开始
        buf.append("    <!-- 插入一条-->\n");
        buf.append("    <insert id=\"").append(MethodEnum.INSERT_METHOD.getValue()).append("\" useGeneratedKeys=\"true\" keyProperty=\"id\" parameterType=\"").append(entityPath).append("\">\n");
        buf.append("        insert into \n");
        buf.append("        <include refid=\"TableName\" />\n");
        buf.append("        (\n");
        buf.append("        <include refid=\"").append(baseSql).append("\" />\n");
        buf.append("        )\n        values (");
        int spilt = 0;
        int index = 0;
        for (ColumnInfo columnInfo:columnInfoList){
            index ++;
            buf.append("#{").append(BuildEntityStr.axaToaxA(columnInfo.getColumnName())).append(",jdbcType=").append(JDBCTypeEnum.getJDBCTypeByDBType(columnInfo.getDataType())).append("}, ");
            if(index == columnInfoList.size()){
                buf.setLength(buf.length()-2);
                break;
            }
            if(spilt >2) {
                buf.append(" \n        ");
                spilt = 0;
            }
            spilt++;
        }
//        if(spilt==1){
//            buf.setLength(buf.length()-" \n        ".length());
//        }else{
//            buf.setLength(buf.length()-2);
//        }
        buf.append(")\n    </insert>\n\n");
        //-----------insert---结束
    }
    /**
     * insertList
     * @param buf
     */
    private static void insertList(StringBuffer buf){
        //-----------insert---开始
        buf.append("    <!-- 批量插入-->\n");
        buf.append("    <insert id=\"").append(MethodEnum.INSERT_LIST.getValue()).append("\" parameterType=\"java.util.List\">\n");
        buf.append("        insert into \n");
        buf.append("        <include refid=\"TableName\" />\n");
        buf.append("        (\n");
        buf.append("        <include refid=\"").append(baseSql).append("\" />\n");
        buf.append("        )\n        values \n");
        buf.append("        <foreach collection=\"").append(BuildEntityStr.axaToaxA(tableName)).append("List\" item=\"item\" index=\"index\" separator=\",\">\n");
        buf.append("            (");
        int spilt = 0;
        for (ColumnInfo columnInfo:columnInfoList){
            buf.append("\n            #{item.").append(BuildEntityStr.axaToaxA(columnInfo.getColumnName())).append(",jdbcType=").append(JDBCTypeEnum.getJDBCTypeByDBType(columnInfo.getDataType())).append("},");
        }
        buf.setLength(buf.length()-1);
        buf.append("\n            )\n");
        buf.append("        </foreach>\n");
        buf.append("    </insert>\n\n");
        //-----------insert---结束
    }
    /**
     * update
     * @param buf
     */
    private static void update(StringBuffer buf){
        //update --开始
        buf.append("    <!-- 更新一条记录-->\n");
        buf.append("    <update id=\"").append(MethodEnum.UPDATE.getValue()).append("\" parameterType=\"").append(entityPath).append("\">\n");
        buf.append("        update \n");
        buf.append("        <include refid=\"TableName\" />\n");
        buf.append("        set \n");
        ColumnInfo temp = null;
        for (ColumnInfo columnInfo:columnInfoList){
            if("id".equals(columnInfo.getColumnName())) {
                temp = columnInfo;
                continue;
            }
            if("create_time".equals(columnInfo.getColumnName())||"modify_time".equals(columnInfo.getColumnName())) continue;
            buf.append("        ").append(columnInfo.getColumnName()).append(" = #{").append(BuildEntityStr.axaToaxA(columnInfo.getColumnName()))
                    .append(",jdbcType=").append(JDBCTypeEnum.getJDBCTypeByDBType(columnInfo.getDataType())).append("},\n");
        }
        buf.setLength(buf.length()-2);
        buf.append("\n        where id = #{id,jdbcType=").append(JDBCTypeEnum.getJDBCTypeByDBType(temp.getDataType())).append("}\n" );
        buf.append("    </update>\n\n");
        //update ---结束
    }
    /**
     * update
     * @param buf
     */
    private static void updateList(StringBuffer buf){
        //update --开始
        buf.append("    <!-- 批量更新-->\n");
        buf.append("    <update id=\"").append(MethodEnum.UPDATELIST.getValue()).append("\" parameterType=\"java.util.List\">\n");
        buf.append("        <foreach collection=\"list\" item=\"item\" separator=\";\">\n");
        buf.append("           update \n");
        buf.append("           <include refid=\"TableName\" />\n");
        buf.append("           set \n");
        ColumnInfo temp = null;
        for (ColumnInfo columnInfo:columnInfoList){
            if("id".equals(columnInfo.getColumnName())) {
                temp = columnInfo;
                continue;
            }
            if("create_time".equals(columnInfo.getColumnName())||"create_user".equals(columnInfo.getColumnName())||"modify_time".equals(columnInfo.getColumnName())) continue;
            buf.append("           ").append(columnInfo.getColumnName()).append(" = #{item.").append(BuildEntityStr.axaToaxA(columnInfo.getColumnName()))
                    .append(",jdbcType=").append(JDBCTypeEnum.getJDBCTypeByDBType(columnInfo.getDataType())).append("},\n");
        }
        buf.setLength(buf.length()-2);
        buf.append("\n           where id = #{item.id,jdbcType=").append(JDBCTypeEnum.getJDBCTypeByDBType(temp.getDataType())).append("}\n" );
        buf.append("        </foreach>\n");
        buf.append("    </update>\n\n");
        //update ---结束
    }
    /**
     * updateSelective
     * @param buf
     */
    private static void updateSelective(StringBuffer buf){
        buf.append("    <!-- 选择更新-->\n");
        buf.append("    <update id=\"").append(MethodEnum.UPDATE_SELECTIVE.getValue()).append("\" parameterType=\"").append(entityPath).append("\">\n");
        buf.append("        update \n");
        buf.append("        <include refid=\"TableName\" />\n");
        buf.append("        <trim prefix=\"set\" suffixOverrides=\",\"> \n" );
        ColumnInfo temp = null;
        for (ColumnInfo columnInfo:columnInfoList){
            if("id".equals(columnInfo.getColumnName()) || !"".equals(columnInfo.getPrimaryKey())) {
                temp = columnInfo;
                continue;
            }
            if("create_time".equals(columnInfo.getColumnName())||"create_user".equals(columnInfo.getColumnName())||"modify_time".equals(columnInfo.getColumnName())) continue;
            buf.append("            <if test=\"").append(BuildEntityStr.axaToaxA(columnInfo.getColumnName())).append(" != null\">\n");
            buf.append("                ").append(columnInfo.getColumnName()).append(" = #{").append(BuildEntityStr.axaToaxA(columnInfo.getColumnName()));
            buf.append(",jdbcType=").append(JDBCTypeEnum.getJDBCTypeByDBType(columnInfo.getDataType())).append("},\n").append("            </if>\n");

        }
        buf.append("        </trim>\n");
        buf.append("        where "+temp.getColumnName()+" = #{"+BuildEntityStr.axaToaxA(temp.getColumnName())+",jdbcType=").append(JDBCTypeEnum.getJDBCTypeByDBType(temp.getDataType())).append("}\n" );
        buf.append(       "    </update>\n\n");
        //updateSelective ---结束
    }

    /**
     * updateSelectiveList
     * @param buf
     */
    private static void updateSelectiveList(StringBuffer buf){
        buf.append("    <!-- 选择更新-->\n");
        buf.append("    <update id=\"").append(MethodEnum.UPDATE_SELECTIVE.getValue()).append("\" parameterType=\"").append(entityPath).append("\">\n");
        buf.append("        <foreach collection=\"list\" item=\"item\" separator=\";\">\n");
        buf.append("        update \n");
        buf.append("            <include refid=\"TableName\" />\n");
        buf.append("            <trim prefix=\"set\" suffixOverrides=\",\"> \n" );
        ColumnInfo temp = null;
        for (ColumnInfo columnInfo:columnInfoList){
            if("id".equals(columnInfo.getColumnName()) || !"".equals(columnInfo.getPrimaryKey())) {
                temp = columnInfo;
                continue;
            }
            if("create_time".equals(columnInfo.getColumnName())||"create_user".equals(columnInfo.getColumnName())||"modify_time".equals(columnInfo.getColumnName())) continue;
            buf.append("                <if test=\"").append(BuildEntityStr.axaToaxA(columnInfo.getColumnName())).append(" != null\">\n");
            buf.append("                    ").append(columnInfo.getColumnName()).append(" = #{").append(BuildEntityStr.axaToaxA(columnInfo.getColumnName()));
            buf.append(",jdbcType=").append(JDBCTypeEnum.getJDBCTypeByDBType(columnInfo.getDataType())).append("},\n").append("            </if>\n");

        }
        buf.append("            </trim>\n");
        buf.append("            where "+temp.getColumnName()+" = #{"+BuildEntityStr.axaToaxA(temp.getColumnName())+",jdbcType=").append(JDBCTypeEnum.getJDBCTypeByDBType(temp.getDataType())).append("}\n" );
        buf.append("        </foreach>\n");
        buf.append("    </update>\n\n");
        //updateSelective ---结束
    }

    /**
     * 查询方法 select
     * @param buf
     */
    private static void select(StringBuffer buf){
        buf.append("    <!-- 查询多条记录-->\n");
        buf.append("    <select id=\"").append(MethodEnum.SELECT.getValue()).append("\" parameterType=\"").append(entityPath).append("\" resultMap=\"").append(bsaeMap).append("\">\n" +
                "        select \n" +
                "        <include refid=\"").append(baseSql).append("\" />\n" +
                "        from \n");
        buf.append("        <include refid=\"TableName\"/>\n");
        buf.append("        <trim prefix=\"WHERE\" prefixOverrides=\"AND |OR \"> \n");

        for (ColumnInfo columnInfo:columnInfoList) {
            buf.append("            <if test=\"").append(BuildEntityStr.axaToaxA(columnInfo.getColumnName())).append(" != null\">\n");
            buf.append("               AND ").append(columnInfo.getColumnName()).append(" = #{").append(BuildEntityStr.axaToaxA(columnInfo.getColumnName()));
            buf.append(",jdbcType=").append(JDBCTypeEnum.getJDBCTypeByDBType(columnInfo.getDataType())).append("} \n").append("            </if>\n");
        }
        buf.append("        </trim >\n    </select>\n\n");
    }
    /**
     * 查询方法 selectone
     * @param buf
     */
    private static void selectone(StringBuffer buf){
        buf.append("    <!-- 查询一条记录-->\n");
        buf.append("    <select id=\"").append(MethodEnum.SELECTONE.getValue()).append("\" parameterType=\"").append(entityPath).append("\" resultMap=\"").append(bsaeMap).append("\">\n" +
                "        select \n" +
                "        <include refid=\"").append(baseSql).append("\" />\n" +
                "        from \n");
        buf.append("        <include refid=\"TableName\"/>\n");
        buf.append("        <trim prefix=\"WHERE\" prefixOverrides=\"AND |OR \"> \n");

        for (ColumnInfo columnInfo:columnInfoList) {
            buf.append("            <if test=\"").append(BuildEntityStr.axaToaxA(columnInfo.getColumnName())).append(" != null\">\n");
            buf.append("                AND ").append(columnInfo.getColumnName()).append(" = #{").append(BuildEntityStr.axaToaxA(columnInfo.getColumnName()));
            buf.append(",jdbcType=").append(JDBCTypeEnum.getJDBCTypeByDBType(columnInfo.getDataType())).append("} \n").append("            </if>\n");
        }
        buf.append("        </trim >\n    </select>\n\n");
    }
}
