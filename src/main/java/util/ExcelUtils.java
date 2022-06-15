package util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.Region;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("deprecation")
public class ExcelUtils {

	private HSSFWorkbook wb = null;

	private HSSFSheet sheet = null;

	/**
	 * @param wb
	 * @param sheet
	 */
	public ExcelUtils(HSSFWorkbook wb, HSSFSheet sheet) {
		this.wb = wb;
		this.sheet = sheet;
	}

	/**
	 * 合并单元格后给合并后的单元格加边框
	 * 
	 * @param region
	 * @param cs
	 */
	public void setRegionStyle(CellRangeAddress region, HSSFCellStyle cs) {

		int toprowNum = region.getFirstRow();
		for (int i = toprowNum; i <= region.getLastRow(); i++) {
			HSSFRow row = sheet.getRow(i);
			for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
				HSSFCell cell = row.getCell(j);// XSSFCellUtil.getCell(row,
				if(cell==null)continue;								// (short) j);
				cell.setCellStyle(cs);
			}
		}
	}

	/**
	 * 设置表头的单元格样式
	 * 
	 * @return
	 */
	public HSSFCellStyle getHeadStyle() {
		// 创建单元格样式
		HSSFCellStyle cellStyle = wb.createCellStyle();
		// 设置单元格的背景颜色为淡蓝色
		cellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		// 设置单元格居中对齐
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置单元格垂直居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 创建单元格内容显示不下时自动换行
		cellStyle.setWrapText(true);
		// 设置单元格字体样式
		HSSFFont font = wb.createFont();
		// 设置字体加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 200);
		cellStyle.setFont(font);
		// 设置单元格边框为细线条
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		return cellStyle;
	}

	/**
	 * 设置表体的单元格样式
	 * 
	 * @return
	 */
	public HSSFCellStyle getBodyStyle() {
		// 创建单元格样式
		HSSFCellStyle cellStyle = wb.createCellStyle();
		// 设置单元格居中对齐
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 设置单元格垂直居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 创建单元格内容显示不下时自动换行
		cellStyle.setWrapText(true);
		// 设置单元格字体样式
		HSSFFont font = wb.createFont();
		// 设置字体加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 200);
		cellStyle.setFont(font);
		// 设置单元格边框为细线条
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		return cellStyle;
	}

	
	private static final int sheetSize=60001;
	/**
	 * 填充Excel
	 * 
	 * @param xlsName
	 * @param excelInfo
	 * @param outputStream
	 * @author DJ
	 */
	public static HSSFWorkbook fillExcel(String xlsName, ExcelData excelData) throws Exception{
		// 创建一个workbook 对应一个excel应用文件
		HSSFWorkbook workBook = new HSSFWorkbook();
		// 在workbook中添加一个sheet,对应Excel文件中的sheet
		
		List<ExcelHeadCell> titleList = excelData.getHead().getCells();
		List<ExcelRow> dataList = excelData.getRows();
		int dataIndex=dataList.size();
		HSSFSheet sheet = null;//workBook.createSheet(xlsName);
		HSSFCell cell = null;
		HSSFCellStyle bodyStyle = null;
		int j = 0;
		
		if(dataIndex==0){
			sheet = workBook.createSheet(String.valueOf(0));
			ExcelUtils exportUtil = new ExcelUtils(workBook, sheet);
			HSSFCellStyle headStyle = exportUtil.getHeadStyle();
			bodyStyle = exportUtil.getBodyStyle();
			HSSFRow headRow = sheet.createRow(0);
			for (int i = 0; i < titleList.size(); i++) {
				cell = headRow.createCell(i);
				cell.setCellStyle(headStyle);
				cell.setCellValue(titleList.get(i).getDisplayName());
			}
			return workBook;
		}
		for(int n=0;n<dataIndex;n++){
			if(n%sheetSize==0){
				sheet = workBook.createSheet(String.valueOf(n));
				ExcelUtils exportUtil = new ExcelUtils(workBook, sheet);
				HSSFCellStyle headStyle = exportUtil.getHeadStyle();
				bodyStyle = exportUtil.getBodyStyle();
				HSSFRow headRow = sheet.createRow(0);
				for (int i = 0; i < titleList.size(); i++) {
					cell = headRow.createCell(i);
					cell.setCellStyle(headStyle);
					cell.setCellValue(titleList.get(i).getDisplayName());
				}
				j=1;
			}
		
			ExcelRow value=dataList.get(n);
				HSSFRow bodyRow = sheet.createRow(j);
				for (int k = 0; k < titleList.size(); k++) {
					cell = bodyRow.createCell(k);
					cell.setCellStyle(bodyStyle);
					cell.setCellValue(value.getValue(titleList.get(k).getTitle())==null?"":value.getValue(titleList.get(k).getTitle()).toString());
				}
			j++;
		}
		
		return workBook;
	}
	
	//方法目前适用于非合并列位于最后
	public static HSSFWorkbook fillMergeExcel(String xlsName, ExcelData excelData) {
		// 创建一个workbook 对应一个excel应用文件
		HSSFWorkbook workBook = new HSSFWorkbook();
		// 在workbook中添加一个sheet,对应Excel文件中的sheet
		
		List<ExcelHeadCell> titleList = excelData.getHead().getCells();
		List<ExcelRow> dataList = excelData.getRows();
		int dataIndex=dataList.size();
		HSSFSheet sheet = null;//workBook.createSheet(xlsName);
		HSSFCell cell = null;
		HSSFCellStyle bodyStyle = null;
		ExcelUtils exportUtil = new ExcelUtils(workBook, sheet);
		HSSFCellStyle headStyle = exportUtil.getHeadStyle();
		bodyStyle = exportUtil.getBodyStyle();
		int j = sheetSize;
		for(int n=0;n<dataIndex;n++){
			if(j>=sheetSize){//数据大于sheetSize时，生成新的sheet
				sheet = workBook.createSheet(String.valueOf(n));
			
				HSSFRow headRow = sheet.createRow(0);
				for (int i = 0; i < titleList.size(); i++) {
					cell = headRow.createCell(i);
					cell.setCellStyle(headStyle);
					cell.setCellValue(titleList.get(i).getDisplayName());
				}
				j=1;
			}
		
			ExcelRow value=dataList.get(n);
			HSSFRow bodyRow = sheet.createRow(j);
			int rowSize=0;//合并行数
			HashMap<Integer,Integer> rowMap = new HashMap<Integer, Integer>();
			List<Integer> mergeCell=new ArrayList<Integer>();
			for (int k = 0; k < titleList.size(); k++) {

				cell = bodyRow.createCell(k);
				cell.setCellStyle(bodyStyle);
				
				String[] titles=titleList.get(k).getTitles();
				if(titles.length<1){
					mergeCell.add(k);
					continue;
				}
				Object values=value.getValue(titles[0]);
				if(values instanceof List &&titles.length>1){
					List<HashMap<String, Object>> details=(List<HashMap<String, Object>>) values;
					if(details == null || details.size()==0){
						mergeCell.add(k);
						cell.setCellValue("");
						continue;
					}
					int rsize=details.size();
					
					if(rowSize<rsize){
						rowSize=rsize;
					}
					rowMap.put(k, rsize);
					mergeCell.add(k);
					int startIndex=j+(rowSize-rsize);
					cell.setCellValue(details.get(0).get(titles[1]).toString());
					for(int index=1; index<rsize;index++){
						HashMap<String, Object> detail=details.get(index);
						HSSFRow bodyRows = sheet.getRow(++startIndex);
						if(bodyRows==null)bodyRows=sheet.createRow(startIndex);
						cell = bodyRows.createCell(k);
						cell.setCellStyle(bodyStyle);
						cell.setCellValue(detail.get(titles[1]).toString());
					}
				}else{
					mergeCell.add(k);
					cell.setCellValue(values.toString());
				}
			}
			exportUtil.sheet=sheet;
			if(rowSize>1){
				rowSize--;
				for (int k:mergeCell) {
//				for (int k =1 ; k<=titleList.size();k++) {
//					Region region0=new Region(j, (short)k,(j+rowSize),  (short)k);
//					sheet.addMergedRegion(region0);
//					setRegionStyle(sheet,region0, bodyStyle);
					if(rowMap.get(k)!=null){
						int rsize =rowMap.get(k)-1;
						if(rsize<0)continue;
						if(rsize == rowSize){
							continue;
						}
						CellRangeAddress cellRangeAddress=new CellRangeAddress(j, j+(rowSize-rsize), k, k);
						sheet.addMergedRegion(cellRangeAddress);
						setRegionStyle(sheet,cellRangeAddress, bodyStyle);
						continue;
					}
					CellRangeAddress cellRangeAddress=new CellRangeAddress(j, j+rowSize, k, k);
					sheet.addMergedRegion(cellRangeAddress);
					setRegionStyle(sheet,cellRangeAddress, bodyStyle);
				}
				j=j+rowSize;
			}
			j++;
		}
		
		return workBook;
	}
	
    public static void setRegionStyle(HSSFSheet sheet, Region region,
            HSSFCellStyle cs) {
        for (int i = region.getRowFrom(); i <= region.getRowTo(); i++) {
            HSSFRow row = HSSFCellUtil.getRow(i, sheet);
            for (int j = region.getColumnFrom(); j <= region.getColumnTo(); j++) {
                HSSFCell cell = HSSFCellUtil.getCell(row, (short) j);
                cell.setCellStyle(cs);
            }
        }
    }
    
    public static void setRegionStyle(HSSFSheet sheet,CellRangeAddress region, HSSFCellStyle cs) {

		int toprowNum = region.getFirstRow();
		for (int i = toprowNum; i <= region.getLastRow(); i++) {
			HSSFRow row = sheet.getRow(i);
			for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
				HSSFCell cell = HSSFCellUtil.getCell(row,j);// XSSFCellUtil.getCell(row,
				if(cell==null)continue;								// (short) j);
				cell.setCellStyle(cs);
			}
		}
	}

}
