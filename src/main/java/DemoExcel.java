import jdbc.H3Dao;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @auther: qiantao
 * @DATE: 2018/12/25 11:31
 * @DESC:
 */
public class DemoExcel {

    public static void main(String[] args) {
        String filePath = "D:/aaaa/ware3.xlsx";
        HashMap<String,String> n = new HashMap<String, String>();
        n.put("t_ware_platform_base_info","平台");
        n.put("t_ware_group_base_info","集团");
        n.put("t_ware_company_base_info","企业");
//        n.put("t_ware","商品信息表");
//        n.put("t_ware_class_base","商品类别表");
//        n.put("t_class_base","商品类别设置");
//        n.put("t_class","企业商品类别设置");
//        n.put("t_ware_stall","商品货位表");
//        n.put("t_ware_ext","商品扩展信息表");
//        n.put("t_ware_ext_ini","商品扩展信息配置表");
//        n.put("t_ware_base_snapshot","商品基本信息历史快照表");
//        n.put("t_ware_certificate_image","商品证照扫描件");
//        n.put("t_ware_ext_snapshot","商品扩展信息历史快照表");
//        n.put("t_ware_snapshot","商品信息历史快照表");
//        n.put("t_area","产地档案");
//        n.put("t_factory","生产企业档案");
//        n.put("t_comp_ware","企业与商品关联表");
//        n.put("t_chk_vendor","首营供应商");
//        n.put("t_chk_ware","首营商品档案");
//        n.put("t_pst_info","赠品基本信息");
        exportDBToExcel(filePath,n);
    }

    private static void exportDBToExcel(String filePath,HashMap<String,String> n){
        XSSFWorkbook wb = new XSSFWorkbook();
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            List<String> tablesNames = new ArrayList<String>();

            for (String key : n.keySet()){
                tablesNames.add(key);
            }
//            HSSFWorkbook wb =  ExcelUtils.fillExcel("awxw",excelData);
//            POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream("E:/aaaa/test.xlsx"));
            //得到Excel工作簿对象
//            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("E:/aaaa/test.xlsx"));

            //得到Excel工作表对象
//            XSSFSheet sheet = wb.getSheetAt(0);

//            List<HashMap<String,Object>> mapList = H2Dao.queryColums(tablesNames);
            for (String tableName :tablesNames) {
                System.out.println(n.get(tableName)+" 开始："+tableName);
//                HashMap<String, Object> map = H2Dao.queryColums(tableName.toUpperCase());
                HashMap<String, Object> map = H3Dao.queryColums(tableName);
                System.out.println("创建excel表单  共"+map.size()+"个字段");
                //得到Excel工作表对象
                XSSFSheet sheet = wb.createSheet(n.get(tableName));
                int i = 0;
                int j = 0;
                System.out.println("开始生成行。。。。。");
                XSSFRow row1 = sheet.createRow(i++);
                //得到Excel工作表指定行的单元格
                XSSFCell cell11 = row1.createCell(j);
                cell11.setCellValue("数据库字段");
                XSSFCell cell22 = row1.createCell(j + 1);
                cell22.setCellValue("注释");
                XSSFCell cell33 = row1.createCell(j + 2);
                cell33.setCellValue(tableName);
                for (String key : map.keySet()) {
//                    if(map.get(key).toString().startsWith("商品扩展")) continue;
                    //得到Excel工作表的行
                    XSSFRow row = sheet.createRow(i++);
                    //得到Excel工作表指定行的单元格
                    XSSFCell cell1 = row.createCell(j);
                    cell1.setCellValue(key.toLowerCase());
                    XSSFCell cell2 = row.createCell(j + 1);
                    cell2.setCellValue(map.get(key).toString());
                }
//            cell.setCellValue();setEncoding((short) 1);

                System.out.println(n.get(tableName)+" 完成："+tableName);
                System.out.println();
            }
            System.out.println("写入excel表格。。。");
            wb.write(out);
            System.out.println("完成");
        }catch (Exception e){
            e.printStackTrace();
            try {
                wb.write(out);
//                System.out.println("2222222222222");
            }catch (Exception ex){
//                System.out.println("221121111");
                ex.printStackTrace();
            }
        }
        try {
            H3Dao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
