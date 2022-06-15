package util;

import java.util.*;

public class ExcelInfoUtil {

	public static ExcelRow ObjectToExcelRow(HashMap<String, Object> map){
		ExcelRow excelRow = new ExcelRow();
		Iterator<?> iter = map.entrySet().iterator();
		while (iter.hasNext()) { 
		    @SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) iter.next();
		    String key = entry.getKey()==null?"":entry.getKey().toString();
		    String value = entry.getValue()==null?"":entry.getValue().toString();
		    excelRow.addValue(key, value);
		}
		return excelRow;
	}
	
	public static List<ExcelRow> listToRows(List<HashMap<String, Object>> list){
		List<ExcelRow> excelRows = new ArrayList<ExcelRow>();
		for(int i = 0; i<list.size(); i++){
			ExcelRow excelRow = new ExcelRow();
			excelRow = ObjectToExcelRow(list.get(i));
			excelRows.add(excelRow);
		}
		return excelRows;
	}
	
	public static ExcelHead arrayToExcelHead(String[] titles, String[] displayNames){
		ExcelHead excelHead = new ExcelHead();
		if(titles.length == displayNames.length){
			for(int i = 0; i < titles.length; i++){
				ExcelHeadCell cell = new ExcelHeadCell();
				cell.setTitle(titles[i]);
				cell.setDisplayName(displayNames[i]);
				excelHead.addCell(cell);
			}
		}
		return excelHead;
	}
}
