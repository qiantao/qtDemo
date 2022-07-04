package com.qt.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/07/18 17:40
 * @version: V1.0
 */
public class StudyDao extends MsqlDBConnection{
    public static void query(){
        PreparedStatement pstmt =null;
        ResultSet rs =null;
        try {
            con = getConnection();
            String sql = "SELECT * FROM (select B.TABLE_COMMENT ,A.TABLE_NAME,A.COLUMN_NAME,A.IS_NULLABLE,A.COLUMN_DEFAULT,A.DATA_TYPE,A.CHARACTER_MAXIMUM_LENGTH,A.NUMERIC_PRECISION,A.NUMERIC_SCALE,\n" +
                    "A.DATETIME_PRECISION,A.COLUMN_COMMENT from information_schema.`COLUMNS` A left join \n" +
                    "information_schema.`TABLES` B ON A.TABLE_NAME = B.TABLE_NAME\n" +
                    "WHERE A.TABLE_NAME = ? ) C order by C.TABLE_NAME; " ;
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                close(con,pstmt,rs);
            }catch (SQLException e){

            }
        }
    }
}
