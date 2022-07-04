package com.qt.excel;

import com.qt.demo.Demo;
import com.qt.util.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2020/08/26 10:11
 * @version: V1.0
 */
public class ReadExcel {
    public static void main(String[] args) {
        Workbook hssfWorkbook = getWorkBook("/Users/qiantao/Desktop/副本门禁人员编号对应表.xlsx");
        Sheet hssfSheet = hssfWorkbook.getSheetAt(0);

        int maxRow = hssfSheet.getLastRowNum();

        StringBuffer buf = new StringBuffer("[");
        for (int i = 0; i <= maxRow; i++) {
            if(i==0) continue;
            Row hssfRow = hssfSheet.getRow(i);
            if(hssfRow == null) continue;
            String personName = getValue(hssfRow.getCell(1));
            String tscName = getValue(hssfRow.getCell(2));
            if(StringUtil.isNullTrim(personName)){
                continue;
            }
            buf.append("\n    {\n" +
                    "    \"personName\":\""+personName+"\",\n" +
                    "    \"tscName\":\""+tscName+"\"\n" +
                    "    }").append(",");
        }
        buf.setLength(buf.length()-1);
        buf.append("\n]");
        System.out.println(buf.toString());
        Demo.StringToFile("/Users/qiantao/Desktop/json.txt",buf.toString());
    }

    public static void buildStr(Row row,StringBuffer buf,StringBuffer buf1){
        buf1.append(getValue(row.getCell(2))).append(",");


    }
    private static String getValue(Cell hssfCell) {
        // 返回字符串类型的值
        if(hssfCell==null)return "";

        hssfCell.setCellType(Cell.CELL_TYPE_STRING);
        return hssfCell.getStringCellValue().trim();
    }
    /**
     * 构建excel操作对象
     * @param downLoadFilePath
     * @return
     */
    public static Workbook getWorkBook(String downLoadFilePath){
        //exlce 文件操作对象
        Workbook hssfWorkbook = null;
        //创建文件输入流
//        FileInputStream is = null;
        InputStream is = null;
        try {
            File f = new File(downLoadFilePath);
//            if(!AliOssUtil.downLoadFile(tempFilePath,f)){
//                return null;
//            }

            is = new FileInputStream(f);
            hssfWorkbook = new XSSFWorkbook(is);

        } catch (Exception e) {
            try {
//                is = new FileInputStream(new File(tempFilePath));
                hssfWorkbook = new HSSFWorkbook(is);
            } catch (Exception e1) {
//                log.error("输入流创建失败！",e);
            }

        }
        closeStream(is);
        return hssfWorkbook;
    }
    /**
     * 关闭文件流
     * @param is
     */
    public static void closeStream(InputStream is){
        if (is != null) {
            try {
                //关闭文件输入流
                is.close();
            } catch (IOException e) {
            }
        }
    }
}
