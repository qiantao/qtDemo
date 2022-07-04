package com.qt.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author excel导出时对应头部数据
 * @author cells 头部显示对象
 *  @author map 头部显示中英文对照集
 *
 */
public class ExcelHead {
	
	private HashMap<String,String> map;
	private List<ExcelHeadCell> cells;

	public ExcelHead(){
		cells=new ArrayList<ExcelHeadCell>();
		map=new HashMap<String, String>();
	}
	
	public List<ExcelHeadCell> getCells() {
		return cells;
	}

	public void setCells(List<ExcelHeadCell> cells) {
		this.cells = cells;
	}
	
	public void addCell(ExcelHeadCell cell){
		this.cells.add(cell);
		map.put(cell.getTitle(), cell.getDisplayName());
	}
	
}
