package com.qt.export;

import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.qt.jdbc.H3Dao;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @Author MuYang
 * @Date 2023/10/25 10:27
 * @version: V1.0
 */
public class ExportWord {
    public static List<String> columnList = Lists.newArrayList("数据库字段","数据类型","长度","是否必填","字段默认值","字段备注");
    public static List<String> columnNameList = Lists.newArrayList("columnName","dataType","length","isNullable","columnDefault","columnComment");
    public static void main(String[] args) throws Exception {
        String filePath = "/Users/qiantao/Desktop/1.docx";
        String dbSchema = "tocean";
        exportDoc(filePath,dbSchema);
    }
    public static void exportDoc(String filePath,String dbSchema) throws Exception{

        List<ExportDBEntity> exportDBEntities = H3Dao.queryAllColumn(dbSchema);
        Map<String,String> tableMap = new HashMap<>();
        exportDBEntities.forEach(e->{
            tableMap.put(e.getTableName(),e.getTableComment());
        });
        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(filePath);
        int count = 1;
        Map<String, List<ExportDBEntity>> collect = exportDBEntities.stream().
                collect(Collectors.groupingBy(ExportDBEntity::getTableName));

        for (String tableName : tableMap.keySet()) {
            System.out.println("开始创建表 ---> "+tableName);
            List<ExportDBEntity> tableData = collect.get(tableName);

            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(count+". "+tableName+"\t"+tableMap.get(tableName) );

            XWPFTable table = document.createTable(tableData.size()+1, columnList.size());
            CTTblPr ctTblPr = table.getCTTbl().addNewTblPr();
            ctTblPr.addNewTblW().setW(BigInteger.valueOf(8000));
            for (int i = 0; i <columnList.size(); i++) {
                XWPFTableCell cell = table.getRow(0).getCell(i);
                cell.setText(columnList.get(i));
            }

            for (int row = 1; row < tableData.size()+1; row++) {
                ExportDBEntity exportDBEntity = tableData.get(row - 1);
                String jsonStr = JSONUtil.toJsonStr(exportDBEntity);
                Map map = JSONUtil.toBean(jsonStr, Map.class);
                for (int col = 0; col < columnList.size(); col++) {
                    XWPFTableCell cell = table.getRow(row).getCell(col);
                    cell.setText(Objects.isNull(map.get(columnNameList.get(col)))?"":map.get(columnNameList.get(col)).toString());
                }
            }
            XWPFParagraph paragraph1 = document.createParagraph();
            XWPFRun run1 = paragraph1.createRun();
            run1.setText("\n");
            System.out.println("创建表 ---> "+tableName+" 完成----");
            count++;
        }

        document.write(out);
    }


}
