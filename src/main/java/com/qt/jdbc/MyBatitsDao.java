package com.qt.jdbc;


import com.qt.mybatisDemo.entity.ColumnInfo;
import com.qt.mybatisDemo.entity.TableInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/07/18 10:03
 * @version: V1.0
 */
public class MyBatitsDao extends MsqlDBConnection {

    public static List<TableInfo> queryColumnInfoByTableName(List<String> tableNames,String dbSchema){
        List<ColumnInfo> columnInfos = new ArrayList<>();
        List<TableInfo> tableInfoList = new ArrayList<>();
        PreparedStatement pstmt =null;
        ResultSet rs =null;
        try {
            String str = "'";
            for (String tableName : tableNames) {
                str += tableName+"','";
            }
            str = str.substring(0,str.length()-2);
            con = getConnection();
            String sql = "SELECT * FROM (select A.TABLE_SCHEMA,B.TABLE_COMMENT ,A.TABLE_NAME,A.COLUMN_NAME,A.IS_NULLABLE,A.COLUMN_DEFAULT,A.DATA_TYPE,A.CHARACTER_MAXIMUM_LENGTH,A.NUMERIC_PRECISION,A.NUMERIC_SCALE,\n" +
                    "A.DATETIME_PRECISION,A.COLUMN_COMMENT,C.CONSTRAINT_NAME,C.COLUMN_NAME KEYCOLUMN \n " +
                    "from information_schema.`COLUMNS` A left join \n " +
                    "information_schema.`TABLES` B ON A.TABLE_NAME = B.TABLE_NAME\n " +
                    "left join information_schema.KEY_COLUMN_USAGE C \n" +
                    "ON A.TABLE_NAME = C.TABLE_NAME and A.COLUMN_NAME = C.COLUMN_NAME AND C.CONSTRAINT_NAME='PRIMARY' \n " +
                    "WHERE A.TABLE_NAME in ("+str+")";
            if(!"".equals(dbSchema)){
                sql += " and A.TABLE_SCHEMA = '"+dbSchema+"'";
            }
            sql +=") C order by C.TABLE_NAME " ;
            pstmt = con.prepareStatement(sql);
            int index = 1;

//            pstmt.setString(index, str);
            rs = pstmt.executeQuery();

            TableInfo tableInfo = new TableInfo();
            List<String> list = new ArrayList<>();
            while (rs.next()){
                String tableNameColumnName = rs.getString("COLUMN_NAME") +"."+ rs.getString("TABLE_NAME");
                if(list.contains(tableNameColumnName)){
                    continue;
                }
                list.add(tableNameColumnName);
                if(tableInfo.getTableName()==null){
                    String tableName = rs.getString("TABLE_NAME");
                    tableInfo.setTableName(tableName);
                    tableInfo.setTableComent(rs.getString("TABLE_COMMENT"));
                }
                if(tableInfo.getTableName()!=null&&!tableInfo.getTableName().equals(rs.getString("TABLE_NAME"))){
                    tableInfoList.add(tableInfo);
                    tableInfo.setColumnInfoList(columnInfos);
                    tableInfo = new TableInfo();
                    tableInfo.setTableName(rs.getString("TABLE_NAME"));
                    tableInfo.setTableComent(rs.getString("TABLE_COMMENT"));
                    columnInfos = new ArrayList<>();
                }
                ColumnInfo columnInfo = new ColumnInfo();
                columnInfo.setTableName(rs.getString("TABLE_NAME"));
                columnInfo.setColumnName(rs.getString("COLUMN_NAME"));
                columnInfo.setIsNull(rs.getString("IS_NULLABLE"));
                columnInfo.setColumnDefault(rs.getString("COLUMN_DEFAULT"));
                columnInfo.setDataType(rs.getString("DATA_TYPE"));
                columnInfo.setCharacterMaximumLength(rs.getString("CHARACTER_MAXIMUM_LENGTH"));
                columnInfo.setNumLenth(rs.getString("NUMERIC_PRECISION"));
                columnInfo.setNumScale(rs.getString("NUMERIC_SCALE"));
                columnInfo.setColumnComment(rs.getString("COLUMN_COMMENT"));
//                columnInfo.setPrimaryKey(rs.getString("KEYCOLUMN")==null?"":"keyStrategy=GenerationType.AUTO_INCREMENT");
                columnInfo.setPrimaryKey(rs.getString("KEYCOLUMN")==null?"":"columnDefine= ColumnDefineTypeEnum.AUTOCODE");
                columnInfos.add(columnInfo);
            }
            if(columnInfos.size()>0){
                tableInfoList.add(tableInfo);
                tableInfo.setColumnInfoList(columnInfos);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                close(null,pstmt,rs);
            }catch (SQLException e){

            }
        }
        return tableInfoList;


    }


}
