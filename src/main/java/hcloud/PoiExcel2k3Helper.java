package hcloud;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Copyright (C), 2015-2019, 上海海典软件有限公司
 *
 * @author: lxf
 * @date: 2019/11/12 16:12
 * @decription:
 */
public class PoiExcel2k3Helper extends PoiExcelHelper {
    /**
     * 获取sheet列表
     */
    @Override
    public ArrayList<String> getSheetList(String filePath) {
        ArrayList<String> sheetList = new ArrayList<String>(0);
        try {
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(filePath));
            int i = 0;
            while (true) {
                try {
                    String name = wb.getSheetName(i);
                    sheetList.add(name);
                    i++;
                } catch (Exception e) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sheetList;
    }

    /**
     * 读取Excel文件内容
     */
    @Override
    public ArrayList<ArrayList<String>> readExcel(InputStream inputStream, int sheetIndex, String rows, String columns) {
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();
        try {
            HSSFWorkbook wb = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = wb.getSheetAt(sheetIndex);

            dataList = readExcel(sheet, rows, getColumnNumber(sheet, columns));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    /**
     * 读取Excel文件内容
     */
    @Override
    public ArrayList<ArrayList<String>> readExcel(InputStream inputStream, int sheetIndex, String rows, int[] cols) {
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();
        try {
            HSSFWorkbook wb = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = wb.getSheetAt(sheetIndex);

            dataList = readExcel(sheet, rows, cols);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }
}
