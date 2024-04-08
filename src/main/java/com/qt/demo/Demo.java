package com.qt.demo;

import com.qt.util.TableColumn;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Demo {

    private final static int CVALUE=0;
    private final static int CNAME=1;
    private final static int CTYPE=4;
    private final static int CNULL=5;
    private final static int CDEFULT=2;
    private final static int CLENGTH=3;
    private final static int CPOINT=6;
    private static String sql="";

//
//    public static void main(String[] args) {
//
////        String tempFilePath="D:/aaaa/pos.xlsx";//源文件路径
//        String tempFilePath="D:/aaaa/ware.xlsx";//源文件路径
////        String tempFilePath="D:/aaaa/goods.xlsx";//源文件路径
//        String targetFilePath = "D:/aaaa/ware";//目标文件夹
////        String targetFilePath = "D:/svncode/h3/h3svn/trunk/h3-ware/h3-ware-center/src/main/java/com/hydee/h3/ware/cpware";//目标文件夹
//
////        int index = 0;
////        createStarted(tempFilePath,targetFilePath,index);
//
//        int totleCount = 10;
//        for (int i = 0; i < totleCount ; i++) {
//            try {
//                createStarted(tempFilePath,targetFilePath,i);
//            }catch (Exception e){
//                System.out.println("执行表下标 "+i+" 不存在，结束");
//                break;
//            }
//        }
//        String serviceImplStr= targetFilePath + "/sql/ware.sql";
//        StringToFile(serviceImplStr,sql);
//    }

    public static void createStarted(String tempFilePath,String targetFilePath,int index){
//    }
//    public static void main(String[] args) {
//        String tempFilePath="E:/x.xlsx";//源文件路径
//        String targetFilePath = "E:/";//目标文件夹
        String tableComment = "";
        String tableName = "";
//        String targetFilePath = "E:/target.xls";//目标文件路径
//		BufferedInputStream fis =  new FileInputStream(new File(tempFilePath));
        //exlce 文件操作对象
        Workbook hssfWorkbook = null;
        //创建文件输入流
        FileInputStream is = null;
        try {
            is = new FileInputStream(new File(tempFilePath));
        } catch (FileNotFoundException e) {
            System.out.println("输入流创建失败！");
            e.printStackTrace();
        }
        try {
            hssfWorkbook = new XSSFWorkbook(is);
        } catch (Exception ex) {
            try {
                is = new FileInputStream(new File(tempFilePath));
                hssfWorkbook = new HSSFWorkbook(is);
            } catch (IOException e) {
                System.err.println("excel对象创建失败！");
                e.printStackTrace();
            }
        }
        //虚拟机运行结束删除文件
//	    new File(tempFilePath).deleteOnExit();
        if(is!=null){
            try {
                //关闭文件输入流
                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        // 循环工作表Sheet
        Sheet hssfSheet = hssfWorkbook.getSheetAt(index);
        int columnsize=0;//列总数
//        List<String> namelist=new ArrayList<String>();
        List<TableColumn> columnList = new ArrayList<TableColumn>();
        int nameListSize=0;
        // 循环行Row
        //HSSFHeader head= hssfSheet.getHeader();
        //读取excel中的内容到list中
        for (int rowNum = 0; rowNum <=hssfSheet.getLastRowNum(); rowNum++) {
            Row hssfRow = hssfSheet.getRow(rowNum);
            if(hssfRow==null){
                System.out.println(rowNum +"为空");
                break;
            }
            columnsize=hssfRow.getPhysicalNumberOfCells();
            if(rowNum==0){
                tableComment = getValue(hssfRow.getCell(0)).trim();
                tableName = getValue(hssfRow.getCell(1)).trim();
                continue;
            }
            TableColumn column = new TableColumn();
            if(rowNum ==1){
                column.setPromoKey("true");
            }
            if(getValue(hssfRow.getCell(CVALUE)).length()==0){
                continue;
            }
            column.setColumnValue(getValue(hssfRow.getCell(CVALUE)));
            column.setColumnName(getValue(hssfRow.getCell(CNAME)));
            column.setColumnType(getValue(hssfRow.getCell(CTYPE)));
            column.setCanNull(getValue(hssfRow.getCell(CNULL)));
            column.setDefultValue(getValue(hssfRow.getCell(CDEFULT)));
            column.setLength(getValue(hssfRow.getCell(CLENGTH)));
            column.setPointLenth(getValue(hssfRow.getCell(CPOINT)));
            columnList.add(column);
        }
        System.out.println("表开始"+tableName);
        String str = "";
//        System.out.println("实体开始"+tableName);
//        str = BuildEntityStr.buildEntityStr(columnList,tableComment,tableName);
//        String entityStr= targetFilePath + "/entity/"+ BuildEntityStr.axaToAxA(tableName)+".java";
//        StringToFile(entityStr,str);
//        System.out.println("Dao开始"+tableName);
//        str = BuildEntityStr.buildDaoStr(columnList,tableComment,tableName);
//        String daoStr= targetFilePath + "/dao/"+BuildEntityStr.axaToAxA(tableName)+"Dao.java";
//        StringToFile(daoStr,str);
//        System.out.println("Service开始"+tableName);
//        str = BuildEntityStr.buildServiceStr(columnList,tableComment,tableName);
//        String serviceStr= targetFilePath + "/service/"+BuildEntityStr.axaToAxA(tableName)+"Service.java";
//        StringToFile(serviceStr,str);
//        System.out.println("ServiceImpl开始"+tableName);
//        str = BuildEntityStr.buildServiceImplStr(columnList,tableComment,tableName);
//        String serviceImplStr= targetFilePath + "/service/Impl/"+BuildEntityStr.axaToAxA(tableName)+"ServiceImpl.java";
//        StringToFile(serviceImplStr,str);


        ////----pos
//        System.out.println("DaoService开始"+tableName);
//        str = BuildEntityStr.buildDaoServiceStr(columnList,tableComment,tableName);
//        String daoServiceStr= targetFilePath + "/dao/"+ BuildEntityStr.axaToAxA(tableName)+"Dao.java";
//        StringToFile(daoServiceStr,str);
//        System.out.println("DaoServiceImpl开始"+tableName);
//        str = BuildEntityStr.buildDaoImplStr(columnList,tableComment,tableName);
//        String daoServiceImplStr= targetFilePath + "/dao/Impl/"+ BuildEntityStr.axaToAxA(tableName)+"DaoImpl.java";
//        StringToFile(daoServiceImplStr,str);
//        System.out.println("posService开始"+tableName);
//        str = BuildEntityStr.buildPosServiceStr(columnList,tableComment,tableName);
//        String posServiceStr= targetFilePath + "/service/"+ BuildEntityStr.axaToAxA(tableName)+"Service.java";
//        StringToFile(posServiceStr,str);
//        System.out.println("posServiceImpl开始"+tableName);
//        str = BuildEntityStr.buildPosServiceImplStr(columnList,tableComment,tableName);
//        String serviceImplStr= targetFilePath + "/service/Impl/"+ BuildEntityStr.axaToAxA(tableName)+"ServiceImpl.java";
//        StringToFile(serviceImplStr,str);


//        BuildEntityStr.buildQuerySql(columnList,tableName);
//        BuildEntityStr.buildInsertSql(columnList,tableName);
//        BuildEntityStr.buildUpdateSql(columnList,tableName);
//        BuildEntityStr.buildSetParam(columnList,tableName);
//        BuildEntityStr.buildWhereSql(columnList);
//        BuildEntityStr.buildQueryResult(columnList,tableName);
        sql += "\n";
        sql += BuildEntityStr.buildCreateTableSql(columnList,tableName,tableComment);
//        String serviceImplStr= targetFilePath + "/sql/"+ BuildEntityStr.axaToAxA(tableName)+".sql";
//        StringToFile(serviceImplStr,str);
//        BuildEntityStr.buildMapToEntity(columnList,tableName);
//        BuildEntityStr.buildEntityToMap(columnList,tableName);
//        BuildEntityStr.buildApiStr(columnList);
//        BuildEntityStr.buildEntityJson(columnList,false);
////        BuildEntityStr.buildInsertDBSql(columnList,tableName);
        sql += "\n\n\n\n";
        System.out.println("完成\n\n");
    }


    public static void main(String[] args) {
        //1.
        //2.调用一个方法

//        String str1 = "文件路径";
//        String str2 = "需要写入的内容";
////        Demo demo = new Demo();
//        Demo.StringToFile(str1,str2);
//
        int a = 5;
        int b = 6;
        int add = add(a, b);
        int c = add - 10;
        System.out.println(c);

    }

    public static int add(int a ,int b ){
        return a+b;
    }
    public static boolean StringToFile(String targetFilePath, String str){

       FileOutputStream out=null;
       BufferedReader bReader=null;
       FileReader reader =null;
       try{
//			File f = new File("E:/3.txt");
           File target = new File(targetFilePath);
           if(!target.getParentFile().exists()){
               target.getParentFile().mkdirs();
           }
           if(!target.exists())
           {
               target.createNewFile();
           }else{
               target.delete();
               target.createNewFile();
           }
//			reader = new FileReader(f);//定义一个fileReader对象，用来初始化BufferedReader
//	        bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
           StringBuilder sb = new StringBuilder();
           out=new FileOutputStream(target);
           out.write(str.getBytes());
       }catch(Exception e){
           e.printStackTrace();
           return false;
       }finally{
           try {
               out.close();
//				bReader.close();
//				reader.close();
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       return true;
   }

    public static String getValue(Cell hssfCell) {
		/*if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			// 返回布尔类型的值
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			// 返回数值类型的值
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {  */
        // 返回字符串类型的值
        if(hssfCell==null)return "";

        hssfCell.setCellType(CellType.STRING);
        String value = hssfCell.getStringCellValue().trim();
        if (value.startsWith("`")){
            value = value.replaceAll("`","");
        }
        return value;
        /*}  */
    }
}
