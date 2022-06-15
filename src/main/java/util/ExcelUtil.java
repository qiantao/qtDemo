package util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExcelUtil {
	
	public static String memberType="member";
	
	public static void main(String[] args) throws IOException {
		
		HashMap<String,Object> titleMap =getProductApplicationTitleMap();
		
		List<HashMap<String, Object>> dataList = excelProductToListByHttpUrl("http://ydjcs.hydee.cn/hwimg/static/upload/xls/2018-04-11/1523428437941.xls", titleMap );
	
		System.err.println(dataList);
	}

	private static String getValue(Cell hssfCell) {
		/*if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {  
			// 返回布尔类型的值  
			return String.valueOf(hssfCell.getBooleanCellValue());  
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {  
			// 返回数值类型的值  
			return String.valueOf(hssfCell.getNumericCellValue());  
		} else {  */
		// 返回字符串类型的值
		if(hssfCell==null)return "";

		hssfCell.setCellType(Cell.CELL_TYPE_STRING);
		return hssfCell.getStringCellValue().toString().trim();  
		/*}  */
	}
	
	/**
	 * 
	 * @param httpUrl web文件url地址
	 * @param type 文件解析类型
	 * @return List<HashMap<String,Object>> 解析结果集
	 * @throws IOException
	 */
	
	public static List<HashMap<String,Object>> excelProductToListByHttpUrl(String httpUrl, String type) throws IOException {
		
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> map=new HashMap<String, Object>();
		if(type==null || type.length()<1){
			return list;
		}
		if(type.equals("merchanProduct")){
			map= getMerchantProductMap();
		}
		if(type.equals("baseProduct")){
			map= getBaseProductMap();
		}
		if(type.equals("product")){
			map= getProductMap();
		}
		if(type.equals("sale")){
			map= getSaleMap();
		}
		if(type.equals("store")){
			map=getStoreMap();
		}
		if(type.equals("employee")){
			map=getEmployeeMap();
		}
		if(type.equals("member")){
			map=getMemberMap();
		}
		
		if(type.equals("b2cStoreGoods")){
			map=getB2cGoodsMap();
		}
		if(type.equals("matchCodeMerchantProduct")){
			map=getMatchCodeMerchantProductMap();
		}
		return excelProductToListByHttpUrl(httpUrl, map);
//		System.err.println(httpUrl);
//		if(httpUrl==null||httpUrl.length()<1)return new ArrayList<HashMap<String,Object>>();
//		String tempFilePath=".."+httpUrl.substring(httpUrl.lastIndexOf("/"), httpUrl.length());
//		List<String> namelist=new ArrayList<String>();
//		int nameListSize=0;
//		int columnsize=0;
//		URL url = new URL(httpUrl);
//	    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
//        long TotalSize = Long.parseLong(urlc.getHeaderField( "Content-Length" ));
//        System.out.println( " 下载文件大小为: " + TotalSize);
//        urlc.disconnect(); // 先断开，下面再连接，否则下面会报已经连接的错误 
//        urlc = (HttpURLConnection) url.openConnection();
//		Workbook hssfWorkbook = null;
//	    DataOutputStream dos = null ;
//	    BufferedInputStream bis = null ;
//	    FileOutputStream fos = null ;
//	    int len = 0 ;
//	    byte [] bt = new byte [ 1024 ];
//	    fos = new FileOutputStream(tempFilePath); // 没有下载完毕就将文件的扩展名命名.bak 
//        dos = new DataOutputStream(fos);
//        bis = new BufferedInputStream(urlc.getInputStream());        
//        System.out.println( " 正在接收文件… " );
//        while ((len = bis.read(bt)) > 0 ) // 循环获取文件 
//        {
//          dos.write(bt, 0 , len);
//        }        
//        if (bis != null )
//            bis.close();
//        if (dos != null )
//        dos.close();
//        if (fos != null )
//        fos.close();
//		FileInputStream is = null;
//		try {
//			is = new FileInputStream(new File(tempFilePath));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} 
//	   urlc.disconnect(); // 先断开，下面再连接，否则下面会报已经连接的错误 
//		try {
//			hssfWorkbook = new XSSFWorkbook(is);
//		} catch (Exception ex) {
//			try {
//				is = new FileInputStream(new File(tempFilePath));
//				hssfWorkbook = new HSSFWorkbook(is);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		File tempFile = new File(tempFilePath);
//		if(tempFile.exists()){
//			tempFile.delete();
//		}
//		if(is!=null){
//			is.close();
//		}
//	
//		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();  
//		// 循环工作表Sheet  
//		Sheet hssfSheet = hssfWorkbook.getSheetAt(0);
//		HashMap<String,Object> map=new HashMap<String, Object>();
//		if(type==null || type.length()<1){
//			return list;
//		}
//		if(type.equals("merchanProduct")){
//			map= getMerchantProductMap();
//		}
//		if(type.equals("baseProduct")){
//			map= getBaseProductMap();
//		}
//		if(type.equals("product")){
//			map= getProductMap();
//		}
//		if(type.equals("sale")){
//			map= getSaleMap();
//		}
//		if(type.equals("store")){
//			map=getStoreMap();
//		}
//		if(type.equals("employee")){
//			map=getEmployeeMap();
//		}
//		if(type.equals("member")){
//			map=getMemberMap();
//		}
//		
//		if(type.equals("b2cStoreGoods")){
//			map=getB2cGoodsMap();
//		}
//		if(type.equals("matchCodeMerchantProduct")){
//			map=getMatchCodeMerchantProductMap();
//		}
//
//		// 循环行Row  
//		//HSSFHeader head= hssfSheet.getHeader();
//		for (int rowNum = 0; rowNum <=hssfSheet.getLastRowNum(); rowNum++) {  
//			Row hssfRow = hssfSheet.getRow(rowNum);  
//			if(rowNum==0){
//				columnsize=hssfRow.getPhysicalNumberOfCells();
//				for (int cellNum = 0; cellNum <columnsize; cellNum++){
//					Cell cell = hssfRow.getCell(cellNum);
//					if (cell == null || cell.toString().length()<1) {  
//						continue;
//					}
//					namelist.add((String)map.get(getValue(cell)));
//					nameListSize=namelist.size();
//				}
//				continue;
//			}
//			if (hssfRow == null || hssfRow.toString().length()<1) {  
//				continue;  
//			}
//
//			HashMap<String,Object> row=new HashMap<String,Object>();
//			// 循环列Cell  
//			for (int cellNum = 0; cellNum <nameListSize; cellNum++) {
//				Cell cell = hssfRow.getCell(cellNum);
//				String keyName=namelist.get(cellNum);
//				if (cell == null) {  
//					if(keyName!=null)row.put(keyName,""); 
//					continue;
//				}
//				row.put(keyName, getValue(cell));
//			}
//			list.add(row);
//		}  
//		System.err.println(JsonUtil.objectToString(list));
//		return list;
	}
	
	/**
	 * 
	 * @param httpUrl web文件url路径
	 * @param titleMap 解析title对应集合
	 * @return List<HashMap<String,Object>> 
	 * @throws IOException
	 */
	public static List<HashMap<String,Object>> excelProductToListByHttpUrl(String httpUrl, HashMap<String,Object> titleMap) throws IOException {
		
		System.out.println("httpUrl" + httpUrl);
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		
		if(httpUrl == null || httpUrl.length() < 1){
			return list;
		}
		if( titleMap == null || titleMap.size() < 1){
			return list;
		}
		
		String tempFilePath = ".." + httpUrl.substring(httpUrl.lastIndexOf("/"), httpUrl.length());
		List<String> namelist=new ArrayList<String>();
		int nameListSize=0;
		int columnsize=0;
		URL url = new URL(httpUrl);
	    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
        long TotalSize = Long.parseLong(urlc.getHeaderField( "Content-Length" ));
        System.out.println( " 下载文件大小为: " + TotalSize);
        urlc.disconnect(); // 先断开，下面再连接，否则下面会报已经连接的错误 
        urlc = (HttpURLConnection) url.openConnection();
		Workbook hssfWorkbook = null;
	    DataOutputStream dos = null ;
	    BufferedInputStream bis = null ;
	    FileOutputStream fos = null ;
	    int len = 0 ;
	    byte [] bt = new byte [ 1024 ];
	    fos = new FileOutputStream(tempFilePath); // 没有下载完毕就将文件的扩展名命名.bak
        dos = new DataOutputStream(fos);
        bis = new BufferedInputStream(urlc.getInputStream());
        System.out.println( " 正在接收文件… " );
        while ((len = bis.read(bt)) > 0 ) // 循环获取文件 
        {
          dos.write(bt, 0 , len);
        }        
        if (bis != null )
            bis.close();
        if (dos != null )
        dos.close();
        if (fos != null )
        fos.close();
		FileInputStream is = null;
		try {
			is = new FileInputStream(new File(tempFilePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	   urlc.disconnect(); // 先断开，下面再连接，否则下面会报已经连接的错误 
		try {
			hssfWorkbook = new XSSFWorkbook(is);
		} catch (Exception ex) {
			try {
				is = new FileInputStream(new File(tempFilePath));
				hssfWorkbook = new HSSFWorkbook(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		File tempFile = new File(tempFilePath);
		if(tempFile.exists()){
			tempFile.delete();
		}
		if(is!=null){
			is.close();
		}
	
		 
		// 循环工作表Sheet  
		Sheet hssfSheet = hssfWorkbook.getSheetAt(0);

		// 循环行Row  
		//HSSFHeader head= hssfSheet.getHeader();
		for (int rowNum = 0; rowNum <=hssfSheet.getLastRowNum(); rowNum++) {  
			Row hssfRow = hssfSheet.getRow(rowNum);  
			if(rowNum==0){
				columnsize=hssfRow.getPhysicalNumberOfCells();
				for (int cellNum = 0; cellNum <columnsize; cellNum++){
					Cell cell = hssfRow.getCell(cellNum);
					if (cell == null || cell.toString().length()<1) {  
						continue;
					}
					namelist.add((String)titleMap.get(getValue(cell)));
					nameListSize=namelist.size();
				}
				continue;
			}
			if (hssfRow == null || hssfRow.toString().length()<1) {  
				continue;  
			}

			HashMap<String,Object> row=new HashMap<String,Object>();
			// 循环列Cell  
			for (int cellNum = 0; cellNum <nameListSize; cellNum++) {
				Cell cell = hssfRow.getCell(cellNum);
				String keyName=namelist.get(cellNum);
				if (cell == null) {  
					if(keyName!=null)row.put(keyName,""); 
					continue;
				}
				row.put(keyName, getValue(cell));
			}
			list.add(row);
		}  
		return list;
	}

	/**
	 * 
	 * @param localFilePath 本地文件路径
	 * @param excelMapType excel类型,(merchanProduct,baseProduct,product,sale,store,employee,member,b2cStoreGoods,matchCodeMerchantProduct)
	 * @return
	 */
	public static List<HashMap<String,Object>> excelProductToList(String localFilePath, String excelMapType){
		
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> map=new HashMap<String, Object>();
		if(excelMapType==null || excelMapType.length()<1){
			return list;
		}
		if(excelMapType.equals("merchanProduct")){
			map= getMerchantProductMap();
		}
		if(excelMapType.equals("baseProduct")){
			map= getBaseProductMap();
		}
		if(excelMapType.equals("product")){
			map= getProductMap();
		}
		if(excelMapType.equals("sale")){
			map= getSaleMap();
		}
		if(excelMapType.equals("store")){
			map=getStoreMap();
		}
		if(excelMapType.equals("employee")){
			map=getEmployeeMap();
		}
		if(excelMapType.equals("member")){
			map=getMemberMap();
		}
		if(excelMapType.equals("b2cStoreGoods")){
			map=getB2cGoodsMap();
		}
		if(excelMapType.equals("matchCodeMerchantProduct")){
			map=getMatchCodeMerchantProductMap();
		}
		
		return excelProductToList(localFilePath, map);
		
//		List<String> namelist=new ArrayList<String>();
//		int nameListSize=0;
//		int columnsize=0;
//		InputStream is = null;
//		try {
//			is = new FileInputStream(new File(fileUrl));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}  
//
//		Workbook hssfWorkbook = null;
//		try {
//			hssfWorkbook = new XSSFWorkbook(is);
//		} catch (Exception ex) {
//			try {
//				is = new FileInputStream(new File(fileUrl));
//				hssfWorkbook = new HSSFWorkbook(is);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();  
//		// 循环工作表Sheet  
//		Sheet hssfSheet = hssfWorkbook.getSheetAt(0);
//		HashMap<String,Object> map=new HashMap<String, Object>();
//		if(excelMapType==null || excelMapType.length()<1){
//			return list;
//		}
//		if(excelMapType.equals("merchanProduct")){
//			map= getMerchantProductMap();
//		}
//		if(excelMapType.equals("baseProduct")){
//			map= getBaseProductMap();
//		}
//		if(excelMapType.equals("product")){
//			map= getProductMap();
//		}
//		if(excelMapType.equals("sale")){
//			map= getSaleMap();
//		}
//		if(excelMapType.equals("store")){
//			map=getStoreMap();
//		}
//		if(excelMapType.equals("employee")){
//			map=getEmployeeMap();
//		}
//		if(excelMapType.equals("member")){
//			map=getMemberMap();
//		}
//		if(excelMapType.equals("b2cStoreGoods")){
//			map=getB2cGoodsMap();
//		}
//		if(excelMapType.equals("matchCodeMerchantProduct")){
//			map=getMatchCodeMerchantProductMap();
//		}
//
//		// 循环行Row  
//		//HSSFHeader head= hssfSheet.getHeader();
//		for (int rowNum = 0; rowNum <=hssfSheet.getLastRowNum(); rowNum++) {  
//			Row hssfRow = hssfSheet.getRow(rowNum);  
//			if(rowNum==0){
//				columnsize=hssfRow.getPhysicalNumberOfCells();
//				for (int cellNum = 0; cellNum <columnsize; cellNum++){
//					Cell cell = hssfRow.getCell(cellNum);
//					if (cell == null || cell.toString().length()<1) {  
//						continue;
//					}
//					namelist.add((String)map.get(getValue(cell)));
//					nameListSize=namelist.size();
//				}
//				continue;
//			}
//			if (hssfRow == null || hssfRow.toString().length()<1) {  
//				continue;  
//			}
//
//			HashMap<String,Object> row=new HashMap<String,Object>();
//			// 循环列Cell  
//			for (int cellNum = 0; cellNum <nameListSize; cellNum++) {
//				Cell cell = hssfRow.getCell(cellNum);
//				String keyName=namelist.get(cellNum);
//				if (cell == null) {  
//					if(keyName!=null)row.put(keyName,""); 
//					continue;
//				}
//				row.put(keyName, getValue(cell));
//			}
//			list.add(row);
//		}  
//		return list;
	}


	/**
	 * 
	 * @param localFilePath 本地文件路径
	 * @param type
	 * @return Excel 数据集合
	 */
	public static List<HashMap<String,Object>> excelDataToList(String localFilePath, String type){
		
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		
		HashMap<String,Object> titleMap=new HashMap<String, Object>();
		if(type==null || type.length()<1){
			return list;
		}else if(type.equals(ExcelUtil.memberType)) {
			titleMap = getMemberTagMap();
		}

		return excelProductToList(localFilePath, titleMap);
		
//		List<String> namelist=new ArrayList<String>();
//		int nameListSize=0;
//		int columnsize=0;
//		InputStream is = null;
//
//		try {
//			is = new FileInputStream(new File(localFilePath));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
//
//		Workbook hssfWorkbook = null;
//
//		try {
//			hssfWorkbook = new XSSFWorkbook(is);
//		} catch (Exception ex) {
//			try {
//				is = new FileInputStream(new File(localFilePath));
//				hssfWorkbook = new HSSFWorkbook(is);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//	
//		// 循环工作表Sheet  
//		Sheet hssfSheet = hssfWorkbook.getSheetAt(0);
//		HashMap<String,Object> map=new HashMap<String, Object>();
//	
//		if(type==null || type.length()<1){
//			return list;
//		}else if(type.equals(ExcelUtil.memberType)) {
//			map = getMemberTagMap();
//		}
//
//		// 循环行Row  
//		//HSSFHeader head= hssfSheet.getHeader();
//		for (int rowNum = 0; rowNum <=hssfSheet.getLastRowNum(); rowNum++) {  
//			Row hssfRow = hssfSheet.getRow(rowNum);  
//			
//			if(rowNum==0){
//				columnsize=hssfRow.getPhysicalNumberOfCells();
//			
//				for (int cellNum = 0; cellNum <columnsize; cellNum++){
//					Cell cell = hssfRow.getCell(cellNum);
//				
//					if (cell == null || cell.toString().length()<1) {  
//						continue;
//					}
//					
//					namelist.add((String)map.get(getValue(cell)));
//					nameListSize=namelist.size();
//				}
//				
//				continue;
//			}
//			
//			if (hssfRow == null || hssfRow.toString().length()<1) {  
//				continue;  
//			}
//
//			HashMap<String,Object> row=new HashMap<String,Object>();
//			// 循环列Cell  
//			for (int cellNum = 0; cellNum <nameListSize; cellNum++) {
//				Cell cell = hssfRow.getCell(cellNum);
//				String keyName=namelist.get(cellNum);
//			
//				if (cell == null) {  
//					if(keyName!=null)row.put(keyName,""); 
//					continue;
//				}
//				
//				row.put(keyName, getValue(cell));
//			}
//			
//			list.add(row);
//		}  
		
//		return list;
	}

	/**
	 * 
	 * @param localFilePath 本地文件路径
	 * @param titleMap 解析Excel的title对应关系
	 * @return Excel 数据集合
	 */
	public static List<HashMap<String,Object>> excelProductToList(String localFilePath, HashMap<String,Object> titleMap){

		List<String> namelist=new ArrayList<String>();
		int nameListSize=0;
		int columnsize=0;
		InputStream is = null;
		try {
			is = new FileInputStream(new File(localFilePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		Workbook hssfWorkbook = null;
		try {
			hssfWorkbook = new XSSFWorkbook(is);
		} catch (Exception ex) {
			try {
				is = new FileInputStream(new File(localFilePath));
				hssfWorkbook = new HSSFWorkbook(is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}  
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		// 循环工作表Sheet  
		Sheet hssfSheet = hssfWorkbook.getSheetAt(0);
		if(titleMap==null || titleMap.size()<1){
			return list;
		}

		// 循环行Row  
		//HSSFHeader head= hssfSheet.getHeader();
		for (int rowNum = 0; rowNum <=hssfSheet.getLastRowNum(); rowNum++) {  
			Row hssfRow = hssfSheet.getRow(rowNum);  
			if(rowNum==0){
				columnsize=hssfRow.getPhysicalNumberOfCells();
				for (int cellNum = 0; cellNum <columnsize; cellNum++){
					Cell cell = hssfRow.getCell(cellNum);
					if (cell == null || cell.toString().length()<1) {  
						continue;
					}
					namelist.add((String)titleMap.get(getValue(cell)));
					nameListSize=namelist.size();
				}
				continue;
			}
			if (hssfRow == null || hssfRow.toString().length()<1) {  
				continue;  
			}

			HashMap<String,Object> row=new HashMap<String,Object>();
			// 循环列Cell  
			for (int cellNum = 0; cellNum <nameListSize; cellNum++) {
				Cell cell = hssfRow.getCell(cellNum);
				String keyName=namelist.get(cellNum);
				if (cell == null) {  
					if(keyName!=null)row.put(keyName,""); 
					continue;
				}
				row.put(keyName, getValue(cell));
			}
			list.add(row);
		}  
		return list;
	}

	public static HashMap<String,Object> getEmployeeMap(){

		HashMap<String,Object> employeemap=new HashMap<String,Object>();

		employeemap.put("门店编码(下划线不可删除,请直接在第二栏填写,覆盖例子)", "store_code");
		employeemap.put("员工编码", "employee_code");
		employeemap.put("姓名", "employee_name");
		employeemap.put("登录账号(8到20位数字)", "phone_number");
		employeemap.put("类型(药师4,营业员1)", "employee_type");
		employeemap.put("性别(男1,女0)", "sex");
		return employeemap;
	} 

	public static HashMap<String,Object> getMemberMap(){

		HashMap<String,Object> map=new HashMap<String,Object>();

		map.put("erp中重新生成的卡号", "cardNumber");
		map.put("用户Id(操作人)", "userId");
		
		return map;
	} 
	
	public static HashMap<String,Object> getMemberTagMap(){

		HashMap<String,Object> map=new HashMap<String,Object>();

		map.put("商品编码", "goodsCode");
		map.put("标签", "tagName");
		map.put("平台码", "platformCode");

		return map;
	} 

	public static HashMap<String,Object> getProductMap(){
		HashMap<String,Object> productmap=new HashMap<String,Object>();
		productmap.put("名称", "name");
		productmap.put("药品通用名", "commonname");
		productmap.put("商品编码", "erpproductid");
		productmap.put("产品分类", "productcategory_id");
		productmap.put("品牌", "brand_id");
		productmap.put("单位", "unit");
		productmap.put("规格", "standard");
		productmap.put("剂型", "jixing");
		productmap.put("批准文号", "approvalnumber");
		productmap.put("生产企业", "manufacturingenterprise");
		productmap.put("市场价", "marketprice");
		productmap.put("单价", "price");
		productmap.put("成份", "ingredients");
		productmap.put("性状", "shape_properties");
		productmap.put("功能主治", "functions");
		productmap.put("适用人群", "_type");
		productmap.put("用法用量", "usage_dosage");
		productmap.put("使用方法", "syfs");
		productmap.put("不良反应", "untoward_effect");
		productmap.put("注意事项", "matters_attention");
		productmap.put("禁忌", "taboo");
		productmap.put("药物作用", "drug_action");
		productmap.put("贮藏", "store_up");
		productmap.put("包装", "baozhuang");
		productmap.put("有效期", "effective");
		productmap.put("是否国产", "jinkou");
		productmap.put("重量", "weiht");
		productmap.put("是否处方药", "otccategory");
		return productmap;
	}

	public static HashMap<String,Object> getBaseProductMap(){
		HashMap<String,Object> productmap=new HashMap<String,Object>();
		productmap.put("企业GUID编号", "companyguid");
		productmap.put("商品编码", "wareid");
		productmap.put("平台商品编码", "platwareid");
		productmap.put("药品(0)/非药品(1)", "type");
		productmap.put("通用名称", "genericname");
		productmap.put("生产企业", "producer");
		productmap.put("产地", "prodAdd");
		productmap.put("商品规格", "warespec");
		productmap.put("批准文号", "fileno");
		productmap.put("条形码", "barcode");
		productmap.put("单位", "wareunit");
		productmap.put("剂型", "dosageclass");
		productmap.put("品牌名", "brandname");
		productmap.put("长", "length");
		productmap.put("高", "height");
		productmap.put("宽", "wide");
		productmap.put("重量", "weight");
		productmap.put("成分", "element");
		productmap.put("形状", "properties");
		productmap.put("功能主治/功能", "functions");
		productmap.put("适用对象", "appropriateObj");
		productmap.put("不适宜对象", "inappropriateObj");
		productmap.put("用法用量", "usageQuantity");
		productmap.put("不良反应", "untowardeffect");
		productmap.put("禁忌", "contraindication");
		productmap.put("注意事项", "attention");
		productmap.put("药物相互作用", "drugInteractions");
		productmap.put("药理作用", "pharmacologicalAction");
		productmap.put("有效期/保质期", "invalidate");
		productmap.put("储藏条件", "storeconditions");
		productmap.put("执行标准", "implementstandards");
		productmap.put("是否含麻黄碱", "isephedrine");
		productmap.put("是否医保", "isinsurance");
		productmap.put("关键字字典", "keyword");
		productmap.put("是否处方/是否OTC甲类/OTC乙类", "isotc");
		productmap.put("图片集合", "data");
		return productmap;
	}

	public static HashMap<String,Object> getMerchantProductMap(){
		HashMap<String,Object> productmap=new HashMap<String,Object>();
		productmap.put("产品编码", "productCode");
		productmap.put("条形码", "barcode");
		productmap.put("商品名称", "goodsName");
		productmap.put("商品规格", "goodsStandard");
		productmap.put("生产厂家", "manufacturing_enterprise");

		productmap.put("批准文号", "fileno");
		productmap.put("转换类型", "type");
		productmap.put("转换比率", "converts");
		productmap.put("税率", "saletax");
		return productmap;
	}

	public static HashMap<String,Object> getSaleMap(){
		HashMap<String,Object> productmap=new HashMap<String,Object>();
		productmap.put("门店编码", "storecode");
		productmap.put("产品编码", "productcode");
		productmap.put("产品库存", "quantity");
		productmap.put("零售价格", "saleprice");
		return productmap;
	}

	public static HashMap<String,Object> getStoreMap(){

		HashMap<String,Object> productmap=new HashMap<String,Object>();

		productmap.put("编码(下划线不可删除,请直接在第二栏填写,覆盖例子)", "store_code");
		productmap.put("门店名称", "store_name");
		productmap.put("营业时间", "business_hours");
		productmap.put("详细地址", "address");
		productmap.put("联系电话", "phone");
		productmap.put("介绍", "introduce");
		productmap.put("门店编码(下划线不可删除,请直接在第二栏填写,覆盖例子)", "store_code");
		productmap.put("服务范围(米)", "service_scope");
		productmap.put("医疗保险(是否支持)", "health_insurance");
		productmap.put("扫码购(是否支持)", "scancode_buy");
		//		productmap.put("城市", "city");
		//		productmap.put("员工人数", "employee_number");
		//		productmap.put("门店图片", "pictures");
		//		productmap.put("经度", "longitude");
		//		productmap.put("纬度", "latitude");
		//		productmap.put("门店状态", "store_status");
		//		productmap.put("价格分组", "price_group");
		//		productmap.put("门店店长", "store_manager");


		return productmap;
	}
	
	public static HashMap<String,Object> getB2cGoodsMap(){
		HashMap<String,Object> b2cGoodsmap=new HashMap<String,Object>();
		b2cGoodsmap.put("产品编码", "productCode");
		b2cGoodsmap.put("商品库存", "goodsStock");
		b2cGoodsmap.put("商品价格", "goodsPrice");
		return b2cGoodsmap;
	}
	
	public static HashMap<String,Object> getProductApplicationTitleMap(){
		HashMap<String,Object> titleMap=new HashMap<String,Object>();
		titleMap.put("1", "1");
		titleMap.put("2", "2");
		titleMap.put("3", "3");
		titleMap.put("4", "4");
		titleMap.put("商品编码", "productCode");
		titleMap.put("商品名称", "goodsName");
		titleMap.put("品牌", "brandname");
		titleMap.put("规格", "packStandard");
		titleMap.put("生产企业", "manufacturingEnterprise");
		titleMap.put("条形码", "barcode");
		titleMap.put("批准文号", "approvalNumber");
		titleMap.put("关键字", "searchKeyword");
		titleMap.put("运输方式： 常温(0)/冷藏(1)/冰冻(2)", "freightType");
		titleMap.put("是否易碎", "breakabletype");
		titleMap.put("是否液体", "liquidtype");
		titleMap.put("完整说明书", "sInstructions");
		titleMap.put("功能主治/功能", "sFunctional");
		titleMap.put("用法用量", "dosage");
		titleMap.put("不良反应", "adverseReaction");
		titleMap.put("禁忌", "taboo");
		titleMap.put("注意事项", "attentionNote");
		titleMap.put("有效期/保质期", "drugTestingEffective");
		titleMap.put("通用名", "commenName");
		titleMap.put("剂型", "dosageFormsClassification");
		titleMap.put("是否含麻黄碱", "ephedrineType");
		titleMap.put("是否医保", "insuranceDrug");
		titleMap.put("药品类型：处方(1)/OTC甲类(0)/OTC乙类(2)", "prescriptionDrug");
		return titleMap;
	}
	
	public static HashMap<String,Object> getMatchCodeMerchantProductMap(){
		HashMap<String,Object> matchCodeProductmap=new HashMap<String,Object>();
		matchCodeProductmap.put("商品编码", "productCode");
		matchCodeProductmap.put("商品名称", "goodsName");
		matchCodeProductmap.put("规格", "goodsStandard");
		matchCodeProductmap.put("生产企业", "manufacturing_enterprise");
		matchCodeProductmap.put("批准文号", "fileno");
		matchCodeProductmap.put("条形码", "barcode");
		
		return matchCodeProductmap;
	}
}
