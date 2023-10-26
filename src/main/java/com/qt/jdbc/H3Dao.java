package com.qt.jdbc;

import com.qt.export.ExportDBEntity;
import com.qt.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/01/29 16:45
 * @version: V1.0
 */
public class H3Dao extends MsqlDBConnection{

    public static String query(){
        HashMap<String,Object> map = new HashMap<String, Object>();
        PreparedStatement pstmt =null;
        ResultSet rs =null;
        try {
            con = getConnection();
            String sql = "SELECT * FROM t_column_schema_info WHERE id =413";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){
                map.put(rs.getString("name"),rs.getString("name"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(con!=null) con.close();
                if(pstmt!=null) pstmt.close();
                if(rs!=null) rs.close();
            }catch (Exception e){

            }
        }

        return "";
    }
    public static String queryTableIdByName(String tableName){
        String rsstr = "";
        PreparedStatement pstmt =null;
        ResultSet rs =null;
        int count = 0;
        try {
            con = getConnection();
            String sql = "SELECT * FROM t_table_schema_info WHERE `name` = '"+ tableName+"' order by id desc";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){
                count++;
                if(count>1) {
//                    System.out.println(tableName+"在数据库中存在多条记录");
                    return rsstr;
                }
                rsstr = rs.getObject("id")==null?"":rs.getInt("id")+"";

            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(con!=null) con.close();
                if(pstmt!=null) pstmt.close();
                if(rs!=null) rs.close();
            }catch (Exception e){

            }
        }

        return rsstr;
    }
    public static String queryColumnIdByName(String tableId,String columnName){
        String rsstr = "";
        PreparedStatement pstmt =null;
        ResultSet rs =null;
        int count = 0;
        try {
            con = getConnection();
            String sql = "SELECT * FROM t_column_schema_info WHERE table_id ='"+tableId+"' and `name` ='" +columnName+"'";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){
                if(count>0){
                    System.out.println("表 "+tableId+" 字段存在多条记录："+columnName);
//                    return null;
                }
                rsstr = rs.getObject("id")==null?"":rs.getInt("id")+"";
                count++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
//                if(con!=null) con.close();
                if(pstmt!=null) pstmt.close();
                if(rs!=null) rs.close();
            }catch (Exception e){

            }
        }

        return rsstr;
    }
    public static HashMap<String,Object> queryColums(String tableName){
        HashMap<String,Object> map = new HashMap<String, Object>();
        PreparedStatement pstmt =null;
        ResultSet rs =null;
        try {
            con = getConnection();
//            String sql = "SELECT * FROM (select B.TABLE_COMMENT ,A.TABLE_NAME,A.COLUMN_NAME,A.IS_NULLABLE,A.COLUMN_DEFAULT,A.DATA_TYPE,A.CHARACTER_MAXIMUM_LENGTH,A.NUMERIC_PRECISION,A.NUMERIC_SCALE,\n" +
//                    "A.DATETIME_PRECISION,A.COLUMN_COMMENT,C.CONSTRAINT_NAME,C.COLUMN_NAME KEYCOLUMN \n " +
//                            "from information_schema.`COLUMNS` A left join \n " +
//                            "information_schema.`TABLES` B ON A.TABLE_NAME = B.TABLE_NAME\n " +
//                            "left join information_schema.KEY_COLUMN_USAGE C \n" +
//                            "ON A.TABLE_NAME = C.TABLE_NAME and A.COLUMN_NAME = C.COLUMN_NAME AND C.CONSTRAINT_NAME='PRIMARY' \n " +
//                            "WHERE A.TABLE_NAME = '"+tableName+"' ) C order by C.TABLE_NAME ";
            String sql = "";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){
                map.put(rs.getString("COLUMN_NAME"),rs.getObject("COLUMN_COMMENT")==null?"":rs.getObject("COLUMN_COMMENT"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
//                if(con!=null) con.close();
                if(pstmt!=null) pstmt.close();
                if(rs!=null) rs.close();
            }catch (Exception e){

            }
        }

        return map;
    }

//    private static List<String> s = Lists.newArrayList("file_store");
    public static List<ExportDBEntity> queryAllColumn(String dbSchema){

        List<ExportDBEntity> map = new ArrayList<>();
        PreparedStatement pstmt =null;
        ResultSet rs =null;
        try {
            con = getConnection();
//            String sql = "SELECT * FROM (select B.TABLE_COMMENT ,A.TABLE_NAME,A.COLUMN_NAME,A.IS_NULLABLE,A.COLUMN_DEFAULT,A.DATA_TYPE,A.CHARACTER_MAXIMUM_LENGTH,A.NUMERIC_PRECISION,A.NUMERIC_SCALE,\n" +
//                    "A.DATETIME_PRECISION,A.COLUMN_COMMENT,C.CONSTRAINT_NAME,C.COLUMN_NAME KEYCOLUMN \n " +
//                            "from information_schema.`COLUMNS` A left join \n " +
//                            "information_schema.`TABLES` B ON A.TABLE_NAME = B.TABLE_NAME\n " +
//                            "left join information_schema.KEY_COLUMN_USAGE C \n" +
//                            "ON A.TABLE_NAME = C.TABLE_NAME and A.COLUMN_NAME = C.COLUMN_NAME AND C.CONSTRAINT_NAME='PRIMARY' \n " +
//                            "WHERE A.TABLE_NAME = '"+tableName+"' ) C order by C.TABLE_NAME ";
            String sql = "\n" +
                    "SELECT\n" +
                    "\t* \n" +
                    "FROM\n" +
                    "\t(\n" +
                    "\tSELECT\n" +
                    "\t\tB.TABLE_SCHEMA ,\n" +
                    "\t\tB.TABLE_COMMENT ,\n" +
                    "\t\tA.TABLE_NAME ,\n" +
                    "\t\tA.COLUMN_NAME ,\n" +
                    "\t\tA.IS_NULLABLE,\n" +
                    "\t\tA.COLUMN_DEFAULT,\n" +
                    "\t\tA.DATA_TYPE,\n" +
                    "\t\tA.CHARACTER_MAXIMUM_LENGTH,\n" +
                    "\t\tA.NUMERIC_PRECISION,\n" +
                    "\t\tA.NUMERIC_SCALE,\n" +
                    "\t\tA.DATETIME_PRECISION,\n" +
                    "\t\tA.COLUMN_COMMENT\n" +
                    "\tFROM\n" +
                    "\t\tinformation_schema.`COLUMNS` A\n" +
                    "\t\tLEFT JOIN information_schema.`TABLES` B ON A.TABLE_NAME = B.TABLE_NAME \n" +
                    "\t\twhere A.table_schema = '"+dbSchema+"' and B.TABLE_SCHEMA='"+dbSchema+"' \n" +
                    "\t) C \n" +
                    "where C.TABLE_SCHEMA is not null\n" +
                    "ORDER BY\n" +
                    "\tC.TABLE_NAME\n" +
                    "\t";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){
                ExportDBEntity exportDBEntity = new ExportDBEntity();
                exportDBEntity.setTableComment(rs.getString("TABLE_COMMENT")==null?"":rs.getString("TABLE_COMMENT"));
                exportDBEntity.setTableName(rs.getString("TABLE_NAME")==null?"":rs.getString("TABLE_NAME"));
//                if(!s.contains(exportDBEntity.getTableName())){
//                    continue;
//                }
                exportDBEntity.setColumnName(rs.getString("COLUMN_NAME"));
                exportDBEntity.setIsNullable(rs.getString("IS_NULLABLE")==null?"":rs.getString("IS_NULLABLE"));
                exportDBEntity.setColumnDefault(rs.getString("COLUMN_DEFAULT")==null?"":rs.getString("COLUMN_DEFAULT"));
                exportDBEntity.setDataType(rs.getString("DATA_TYPE")==null?"":rs.getString("DATA_TYPE"));
                exportDBEntity.setColumnComment(rs.getString("COLUMN_COMMENT")==null?"":rs.getString("COLUMN_COMMENT"));
                String stringLength = rs.getString("CHARACTER_MAXIMUM_LENGTH") == null ? "" : rs.getString("CHARACTER_MAXIMUM_LENGTH");
                if(StringUtil.isNullTrim(stringLength)){
                    stringLength = rs.getString("NUMERIC_PRECISION") == null ? "" : rs.getString("NUMERIC_PRECISION");
                }
                exportDBEntity.setLength(stringLength);
                map.add(exportDBEntity);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
//                if(con!=null) con.close();
                if(pstmt!=null) pstmt.close();
                if(rs!=null) rs.close();
            }catch (Exception e){

            }
        }

        return map;
    }



}
