package com.qt.export;

import com.qt.jdbc.H3Dao;
import com.qt.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @auther: qiantao
 * @DATE: 2018/12/25 11:31
 * @DESC:
 */
public class DemoExportExcel {

    public static void main(String[] args) {
        String filePath = "/Users/qiantao/Desktop/aaa.xlsx";
        String dbSchema = "tocean";
        exportDBToExcel(filePath,dbSchema);
    }

    private static void exportDBToExcel(String filePath,String dbSchema){
        XSSFWorkbook wb = new XSSFWorkbook();
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath);

            List<ExportDBEntity> exportDBEntities = H3Dao.queryAllColumn(dbSchema);
            Map<String,String> tableMap = new HashMap<>();
            exportDBEntities.forEach(e->{
                tableMap.put(e.getTableName(),e.getTableComment());
            });
            XSSFSheet sheetTotal = wb.createSheet("表结构汇总");
            int ite = 0;
            int jTemp = 0;
            System.out.println("开始生成行。。。。。");
            XSSFRow rowTotal = sheetTotal.createRow(ite++);
            //得到Excel工作表指定行的单元格
            rowTotal.createCell(jTemp).setCellValue("表名");
            rowTotal.createCell(jTemp+1).setCellValue("表备注");
            for (String key : tableMap.keySet()) {
                XSSFRow row = sheetTotal.createRow(ite++);
                row.createCell(jTemp).setCellValue(key);
                row.createCell(jTemp+1).setCellValue(tableMap.get(key));
            }

            Map<String, List<ExportDBEntity>> collect = exportDBEntities.stream().collect(Collectors.groupingBy(ExportDBEntity::getTableName));
            System.out.println("创建excel表单  共"+collect.size()+"个字段");
            for (String tableName :collect.keySet()) {
                System.out.println(tableName+" 开始："+ tableName);
                List<ExportDBEntity> data = collect.get(tableName);

                //得到Excel工作表对象
                XSSFSheet sheet = wb.createSheet(tableName);
                int i =0;
                int j = 0;
                System.out.println("开始生成行。。。。。");
                XSSFRow row1 = sheet.createRow(i++);
                //得到Excel工作表指定行的单元格
                XSSFCell cell11 = row1.createCell(j);
                cell11.setCellValue("数据库字段");
                XSSFCell cell22 = row1.createCell(j + 1);
                cell22.setCellValue("数据类型");
                XSSFCell cell33 = row1.createCell(j + 2);
                cell33.setCellValue("长度");
                XSSFCell cell44 = row1.createCell(j + 3);
                cell44.setCellValue("是否必填");
                XSSFCell cell55 = row1.createCell(j + 4);
                cell55.setCellValue("字段默认值");
                XSSFCell cell66 = row1.createCell(j + 5);
                cell66.setCellValue("字段备注");
                for (ExportDBEntity e: data) {
                    //得到Excel工作表的行
                    XSSFRow row = sheet.createRow(i++);
                    //得到Excel工作表指定行的单元格
                    row.createCell(j).setCellValue(e.getColumnName());
                    row.createCell(j + 1).setCellValue(e.getDataType());
                    row.createCell(j+2).setCellValue(e.getLength());
                    row.createCell(j+3).setCellValue(e.getIsNullable());
                    row.createCell(j+4).setCellValue(e.getColumnDefault());
                    row.createCell(j+5).setCellValue(StringUtil.isNullTrim(e.getColumnComment())?e.getColumnName():e.getColumnComment());
                }

                System.out.println(tableName+" 完成："+tableName);
                System.out.println();
            }
            System.out.println("写入excel表格。。。");
            wb.write(out);
            System.out.println("完成");
        }catch (Exception e){
            e.printStackTrace();
            try {
                wb.write(out);
//                System.out.println("2222222222222");
            }catch (Exception ex){
//                System.out.println("221121111");
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
