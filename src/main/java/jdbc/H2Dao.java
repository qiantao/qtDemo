package jdbc;

import util.ExcelData;
import util.ExcelHead;
import util.ExcelInfoUtil;
import util.ExcelRow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @auther: qiantao
 * @DATE: 2018/12/25 10:06
 * @DESC:
 */
public class H2Dao extends DBConnection{
    private static String COLUMN_NAME ="COLUMN_NAME";
    private static String COMMENTS ="COMMENTS";

    public static HashMap<String,Object> queryColums(String tableName){
        HashMap<String,Object> map = new HashMap<String, Object>();
        PreparedStatement pstmt =null;
        ResultSet rs =null;
        try {
            con = getConnection();
            String sql = "SELECT * FROM user_col_comments WHERE Table_Name='"+tableName+"'";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){
                map.put(rs.getString(COLUMN_NAME),rs.getObject(COMMENTS)==null?"":rs.getObject(COMMENTS));
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

        return map;
    }
    public static List<HashMap<String,Object>> queryColums(List<String> tableNames){
        List<HashMap<String,Object>> map = new ArrayList<HashMap<String,Object>>();
        for (String tableName : tableNames){
            String upTableName = tableName.toUpperCase();
            HashMap<String,Object> m = queryColums(upTableName);
            map.add(m);
        }
        return map;
    }
    public static ExcelData queryColumsExcel(){
        ExcelData excelData = new ExcelData();

        String titles[] = {"comments"};
        String displayNames[] = {"H2字段名称"};
        ExcelHead excelHead = ExcelInfoUtil.arrayToExcelHead(titles, displayNames);
        excelData.setHead(excelHead);
        try {
            con = getConnection();
            String sql = "SELECT * FROM user_col_comments WHERE Table_Name='T_WARE_BASE'";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
//                map.put(rs.getString(COLUMN_NAME),rs.getObject(COMMENTS));
                ExcelRow excelRow = new ExcelRow();
                excelRow.addValue("comments",rs.getObject(COMMENTS));
                excelData.getRows().add(excelRow);
            }
        }catch(Exception e){

        }

        return excelData;
    }
}
