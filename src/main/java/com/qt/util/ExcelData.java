package com.qt.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author head excel表头
 * @author rows excel数据集
 */
public class ExcelData {
	
	private ExcelHead head;
	private List<ExcelRow> rows;

	public ExcelData(){
		rows=new ArrayList<ExcelRow>();
	}
	
	public void addRow(ExcelRow row){
		this.rows.add(row);
	}

	public ExcelHead getHead() {
		return head;
	}

	public void setHead(ExcelHead head) {
		this.head = head;
	}

	public List<ExcelRow> getRows() {
		return rows;
	}

	public void setRows(List<ExcelRow> rows) {
		this.rows = rows;
	}

}
