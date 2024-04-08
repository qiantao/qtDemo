package com.qt;

import com.qt.jdbc.H3Dao;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @auther: qiantao
 * @DATE: 2018/12/25 11:31
 * @DESC:
 */
public class DemoExcel {

    public static void main(String[] args) {
        String filePath = "D:/aaaa/ware3.xlsx";
        HashMap<String,String> n = new HashMap<String, String>();
        n.put("t_ware_platform_base_info","平台");
        n.put("t_ware_group_base_info","集团");
        n.put("t_ware_company_base_info","企业");
        exportDBToExcel(filePath,n);
    }

    private static void exportDBToExcel(String filePath,HashMap<String,String> n){
        XSSFWorkbook wb = new XSSFWorkbook();
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            List<String> tablesNames = new ArrayList<String>();

            for (String key : n.keySet()){
                tablesNames.add(key);
            }
            for (String tableName :tablesNames) {
                System.out.println(n.get(tableName)+" 开始："+tableName);
                HashMap<String, Object> map = H3Dao.queryColums(tableName);
                System.out.println("创建excel表单  共"+map.size()+"个字段");
                //得到Excel工作表对象
                XSSFSheet sheet = wb.createSheet(n.get(tableName));
                int i = 0;
                int j = 0;
                System.out.println("开始生成行。。。。。");
                XSSFRow row1 = sheet.createRow(i++);
                //得到Excel工作表指定行的单元格
                XSSFCell cell11 = row1.createCell(j);
                cell11.setCellValue("数据库字段");
                XSSFCell cell22 = row1.createCell(j + 1);
                cell22.setCellValue("注释");
                XSSFCell cell33 = row1.createCell(j + 2);
                cell33.setCellValue(tableName);
                for (String key : map.keySet()) {
                    //得到Excel工作表的行
                    XSSFRow row = sheet.createRow(i++);
                    //得到Excel工作表指定行的单元格
                    XSSFCell cell1 = row.createCell(j);
                    cell1.setCellValue(key.toLowerCase());
                    XSSFCell cell2 = row.createCell(j + 1);
                    cell2.setCellValue(map.get(key).toString());
                }

                System.out.println(n.get(tableName)+" 完成："+tableName);
                System.out.println();
            }
            System.out.println("写入excel表格。。。");
            wb.write(out);
            System.out.println("完成");
        }catch (Exception e){
            e.printStackTrace();
            try {
                wb.write(out);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        try {
            H3Dao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
