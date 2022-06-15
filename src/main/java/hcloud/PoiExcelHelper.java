package hcloud;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Copyright (C), 2015-2019, 上海海典软件有限公司
 *
 * @author: lxf
 * @date: 2019/11/12 16:11
 * @decription:
 */
public abstract class PoiExcelHelper {
    public static final String SEPARATOR = ",";
    public static final String CONNECTOR = "-";

    /**
     * 获取sheet列表，子类必须实现
     */
    public abstract ArrayList<String> getSheetList(String filePath);

    /**
     * 读取Excel文件数据
     */
    public ArrayList<ArrayList<String>> readExcel(InputStream inputStream, int sheetIndex) {
        return readExcel(inputStream, sheetIndex, "1-", "1-");
    }

    /**
     * 读取Excel文件数据
     */
    public ArrayList<ArrayList<String>> readExcel(InputStream inputStream, int sheetIndex, String rows) {
        return readExcel(inputStream, sheetIndex, rows, "1-");
    }

    /**
     * 读取Excel文件数据
     */
    public ArrayList<ArrayList<String>> readExcel(InputStream inputStream, int sheetIndex, String[] columns) {
        return readExcel(inputStream, sheetIndex, "1-", columns);
    }

    /**
     * 读取Excel文件数据，子类必须实现
     */
    public abstract ArrayList<ArrayList<String>> readExcel(InputStream inputStream, int sheetIndex, String rows, String columns);

    /**
     * 读取Excel文件数据
     */
    public ArrayList<ArrayList<String>> readExcel(InputStream inputStream, int sheetIndex, String rows, String[] columns) {
        int[] cols = getColumnNumber(columns);

        return readExcel(inputStream, sheetIndex, rows, cols);
    }

    /**
     * 读取Excel文件数据，子类必须实现
     */
    public abstract ArrayList<ArrayList<String>> readExcel(InputStream inputStream, int sheetIndex, String rows, int[] cols);

    /**
     * 读取Excel文件内容
     */
    protected ArrayList<ArrayList<String>> readExcel(Sheet sheet, String rows, int[] cols) {
        ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();
        // 处理行信息，并逐行列块读取数据
        String[] rowList = rows.split(SEPARATOR);
        for (String rowStr : rowList) {
            if (rowStr.contains(CONNECTOR)) {
                String[] rowArr = rowStr.trim().split(CONNECTOR);
                int start = Integer.parseInt(rowArr[0]) - 1;
                int end;
                if (rowArr.length == 1) {
                    end = sheet.getLastRowNum();
                } else {
                    end = Integer.parseInt(rowArr[1].trim()) - 1;
                }
                dataList.addAll(getRowsValue(sheet, start, end, cols));
            } else {
                dataList.add(getRowValue(sheet, Integer.parseInt(rowStr) - 1, cols));
            }
        }
        return dataList;
    }

    /**
     * 获取连续行、列数据
     */
    protected ArrayList<ArrayList<String>> getRowsValue(Sheet sheet, int startRow, int endRow,
                                                        int startCol, int endCol) {
        if (endRow < startRow || endCol < startCol) {
            return null;
        }

        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        for (int i = startRow; i <= endRow; i++) {
            data.add(getRowValue(sheet, i, startCol, endCol));
        }
        return data;
    }

    /**
     * 获取连续行、不连续列数据
     */
    private ArrayList<ArrayList<String>> getRowsValue(Sheet sheet, int startRow, int endRow, int[] cols) {
        if (endRow < startRow) {
            return null;
        }

        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        for (int i = startRow; i <= endRow; i++) {
            data.add(getRowValue(sheet, i, cols));
        }
        return data;
    }

    /**
     * 获取行连续列数据
     */
    private ArrayList<String> getRowValue(Sheet sheet, int rowIndex, int startCol, int endCol) {
        if (endCol < startCol) {
            return null;
        }

        Row row = sheet.getRow(rowIndex);
        ArrayList<String> rowData = new ArrayList<String>();
        for (int i = startCol; i <= endCol; i++) {
            rowData.add(getCellValue(row, i));
        }
        return rowData;
    }

    /**
     * 获取行不连续列数据
     */
    private ArrayList<String> getRowValue(Sheet sheet, int rowIndex, int[] cols) {
        Row row = sheet.getRow(rowIndex);
        ArrayList<String> rowData = new ArrayList<String>();
        for (int colIndex : cols) {
            rowData.add(getCellValue(row, colIndex));
        }
        return rowData;
    }

    /**
     * 获取单元格内容
     *
     * @param row
     * @param column a excel column string like 'A', 'C' or "AA".
     * @return
     */
    protected String getCellValue(Row row, String column) {
        return getCellValue(row, getColumnNumber(column));
    }

    /**
     * 获取单元格内容
     *
     * @param row
     * @param col a excel column index from 0 to 65535
     * @return
     */
    private String getCellValue(Row row, int col) {
        if (row == null) {
            return "";
        }
        Cell cell = row.getCell(col);
        return getCellValue(cell);
    }

    /**
     * 获取单元格内容
     *
     * @param cell
     * @return
     */
    private String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        String cellValue = "";
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat simpleDateFormat = null;
                    short dataFormat = cell.getCellStyle().getDataFormat();
                    if (dataFormat == HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm")) {
                        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = cell.getDateCellValue();
                        cellValue = simpleDateFormat.format(date);
                    }

                } else {
                    double value = cell.getNumericCellValue();
                    CellStyle style = cell.getCellStyle();
                    DecimalFormat format = new DecimalFormat();
                    String temp = style.getDataFormatString();
                    // 单元格设置成常规
                    if ("General".equals(temp)) {
                        format.applyPattern("#");
                    }
                    cellValue = format.format(value);
                }
                break;
            case HSSFCell.CELL_TYPE_STRING:
                cellValue = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                cellValue = "";
        }
        String value = cellValue;
        try {
            // This step is used to prevent Integer string being output with
            // '.0'.
            Float.parseFloat(value);
            value = value.replaceAll("\\.0$", "");
            value = value.replaceAll("\\.0+$", "");
            return value;
        } catch (NumberFormatException ex) {
            return value;
        }
    }

    /**
     * Change excel column letter to integer number
     *
     * @param columns column letter of excel file, like A,B,AA,AB
     * @return
     */
    private int[] getColumnNumber(String[] columns) {
        int[] cols = new int[columns.length];
        for (int i = 0; i < columns.length; i++) {
            cols[i] = getColumnNumber(columns[i]);
        }
        return cols;
    }

    /**
     * Change excel column letter to integer number
     *
     * @param column column letter of excel file, like A,B,AA,AB
     * @return
     */
    private int getColumnNumber(String column) {
        int length = column.length();
        short result = 0;
        for (int i = 0; i < length; i++) {
            char letter = column.toUpperCase().charAt(i);
            int value = letter - 'A' + 1;
            result += value * Math.pow(26, length - i - 1);
        }
        return result - 1;
    }

    /**
     * Change excel column string to integer number array
     *
     * @param sheet   excel sheet
     * @param columns column letter of excel file, like A,B,AA,AB
     * @return
     */
    protected int[] getColumnNumber(Sheet sheet, String columns) {
        // 拆分后的列为动态，采用List暂存
        ArrayList<Integer> result = new ArrayList<Integer>();
        String[] colList = columns.split(SEPARATOR);
        for (String colStr : colList) {
            if (colStr.contains(CONNECTOR)) {
                String[] colArr = colStr.trim().split(CONNECTOR);
                int start = Integer.parseInt(colArr[0]) - 1;
                int end;
                if (colArr.length == 1) {
                    end = sheet.getRow(sheet.getFirstRowNum()).getLastCellNum() - 1;
                } else {
                    end = Integer.parseInt(colArr[1].trim()) - 1;
                }
                for (int i = start; i <= end; i++) {
                    result.add(i);
                }
            } else {
                result.add(Integer.parseInt(colStr) - 1);
            }
        }

        // 将List转换为数组
        int len = result.size();
        int[] cols = new int[len];
        for (int i = 0; i < len; i++) {
            cols[i] = result.get(i).intValue();
        }

        return cols;
    }
}