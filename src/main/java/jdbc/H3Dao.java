package jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

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
            String sql = "SELECT * FROM (select B.TABLE_COMMENT ,A.TABLE_NAME,A.COLUMN_NAME,A.IS_NULLABLE,A.COLUMN_DEFAULT,A.DATA_TYPE,A.CHARACTER_MAXIMUM_LENGTH,A.NUMERIC_PRECISION,A.NUMERIC_SCALE,\n" +
                    "A.DATETIME_PRECISION,A.COLUMN_COMMENT,C.CONSTRAINT_NAME,C.COLUMN_NAME KEYCOLUMN \n " +
                            "from information_schema.`COLUMNS` A left join \n " +
                            "information_schema.`TABLES` B ON A.TABLE_NAME = B.TABLE_NAME\n " +
                            "left join information_schema.KEY_COLUMN_USAGE C \n" +
                            "ON A.TABLE_NAME = C.TABLE_NAME and A.COLUMN_NAME = C.COLUMN_NAME AND C.CONSTRAINT_NAME='PRIMARY' \n " +
                            "WHERE A.TABLE_NAME = '"+tableName+"' ) C order by C.TABLE_NAME ";
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

}
