package com.qt.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2020/08/26 10:11
 * @version: V1.0
 */
public class ReadExcel {
    public static void main(String[] args){
        InputStream is = null;
        FileOutputStream fos = null;

        String filePath = "/Users/qiantao/Desktop/11.xlsx";
        try {
            File f = new File(filePath);
            is = new FileInputStream(f);
            Workbook hssfWorkbook = new XSSFWorkbook(is);
            Sheet hssfSheet = hssfWorkbook.getSheetAt(0);
            int maxRow = hssfSheet.getLastRowNum();

            if(is != null) is .close();
            fos = new FileOutputStream(filePath);

            //追加内容
            Row row = hssfSheet.createRow(maxRow+1);
            Cell cell = row.createCell(0);
            cell.setCellType(CellType.STRING);
            cell.setCellValue("aaaa");
            Cell cell1 = row.createCell(1);
            cell1.setCellType(CellType.STRING);
            cell1.setCellValue("aaaa");

            hssfWorkbook.write(fos);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(is != null) {
                try {
                    is .close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if(fos != null) {
                try {
                    fos .close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void buildStr(Row row,StringBuffer buf,StringBuffer buf1){
        buf1.append(getValue(row.getCell(2))).append(",");


    }
    private static String getValue(Cell hssfCell) {
        // 返回字符串类型的值
        if(hssfCell==null)return "";

        hssfCell.setCellType(CellType.STRING);
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
