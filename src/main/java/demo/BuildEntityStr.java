package demo;

import util.TableColumn;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BuildEntityStr {
    private static String dateStr = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
    public static String buildEntityStr(List<TableColumn> list,String tableComment,String tableName) {
        if (list == null || list.size() == 0) return "";
        StringBuffer buf = new StringBuffer();
        String className = "";
        buf.append("package com.hydee;\n" +
                "\n" +
                "\n" +
                "import com.hydee.basedata.base.bean.annotation.Column;\n" +
                "import com.hydee.basedata.base.bean.annotation.Table;\n" +
                "import com.hydee.basedata.base.bean.serivce.meta.bean.GenerationType;\n"+
                "\n" +
                "import java.sql.Timestamp;\n"+
                "import java.io.Serializable;\n"+
                "import java.util.Date;\n" +
                "\n" +
                "/**   \n" +
                " * @ClassName:  "+axaToAxA(tableName)+"   \n" +
                " * @Description: "+tableComment+"("+tableName+")实体类   \n" +
                " * @author: QianTao \n" +
                " * @date:   "+dateStr+"\n" +
                " * @version: V1.0   \n" +
                " */\n"+
                "@Table(name = \"" + tableName + "\",comment = \"" + tableComment + "\")\n");
        if (tableName.contains("_")) {
            className = axaToAxA(tableName);
        }

        buf.append("public class " + className + " implements Serializable {\n");
        for (TableColumn tab : list) {
            buf.append("\n");
            String nullable = "";
            if("否".equals(tab.getCanNull())){
                nullable = ",nullable = false ";
            }
//            nullable = false,defaultValue = "",length = 0,scale = 0
            buf.append("    @Column(name = \"").append(tab.getColumnValue()).append("\", comment = \"").append(tab.getColumnName()).append("\"");
            if("Date".equals(tab.getColumnType())||"Timestamp".equals(tab.getColumnType())){
                buf.append(")\n");
                buf.append("    private ").append(tab.getColumnType()).append(" ").append(axaToaxA(tab.getColumnValue())).append(";\n");
                continue;
            }
            buf.append(nullable);
            if(!"id".equals(tab.getColumnValue())) {
                buf.append(",defaultValue=\"").append(tab.getDefultValue()).append("\"");
            }
            if(!"Date".equals(tab.getColumnType())&&!"Timestamp".equals(tab.getColumnType())) {
                buf.append(",length=").append(tab.getLength());
            }

            if(!"0".equals(tab.getPointLenth())){
                buf.append(",scale = ").append(tab.getPointLenth());
            }
            if (tab.getPromoKey().length() > 0) {
                buf.append(", primaryKey = ").append(tab.getPromoKey()).append(",keyStrategy= GenerationType.AUTO_INCREMENT )\n");
            } else {
                buf.append(" )\n");
            }
//            else if (tab.getDefultValue() != null) {
//                buf.append(",columnDefinition = \"").append(tab.getDefultValue()).append("\" )\n");
//            }

            String defultValue = "\"\"";
            if(tab.getColumnType().equals("int")||tab.getColumnType().equals("long")||tab.getColumnType().equals("double")){
                defultValue = tab.getDefultValue();
            }
            buf.append("    private ").append(tab.getColumnType()).append(" ").append(axaToaxA(tab.getColumnValue())).append(" = ").append(defultValue).append(";\n");

        }
        buf.append("\n");
        buf.append("\n");
        buf.append("\n");

        for (TableColumn tab : list) {

            buf.append("    /**\n");
            buf.append("    *   ").append(tab.getColumnName()).append("\n");
//            buf.append("    * @param ").append(axaToaxA(tab.getColumnValue())).append("\n");
            buf.append("    */\n");
            buf.append("    public ").append(tab.getColumnType()).append(" get").append(axaToAxA(tab.getColumnValue())).append(" () {return ").append(axaToaxA(tab.getColumnValue())).append("; }\n");
            buf.append("\n");
            buf.append("    /**\n");
//            buf.append("    *").append(tab.getColumnName()).append("\n");
            buf.append("    * @param ").append(axaToaxA(tab.getColumnValue())).append("  ").append(tab.getColumnName()).append("\n");
            buf.append("    */\n");
            buf.append("    public void set").append(axaToAxA(tab.getColumnValue())).append(" (").append(tab.getColumnType()).append(" ").append(axaToaxA(tab.getColumnValue())).append("){this.").append(axaToaxA(tab.getColumnValue())).append(" = ").append(axaToaxA(tab.getColumnValue())).append(";}\n");
            buf.append("\n");
        }
        buf.append("\n");
        buf.append("\n");
        buf.append("\n");
        buf.append("}");
//        buf.append("    @Override\n");
//        buf.append("    public String toString() {\n");
//        buf.append("     return \"Order[\" +\n" +
//                "                    \"orderId=\" + orderId +\n" +
//                "                    \", orderNum='\" + orderNum + '\\'' +\n" +
//                "                    \", tenantId=\" + tenantId +\n" +
//                "                    \", userId=\" + userId +\n" +
//                "                    \", totalAmount=\" + totalAmount +\n" +
//                "                    \", orderSource='\" + orderSource + '\\'' +\n" +
//                "                    \", createTime=\" + createTime +\n" +
//                "                    \", updateTime=\" + updateTime +\n" +
//                "                    ']';");



        return buf.toString();
    }

    public static String buildCreateTableSql(List<TableColumn> tableColumnList,String tableName,String tableComment){
        StringBuffer buf = new StringBuffer();
        buf.append("CREATE TABLE `").append(tableName).append("` (\n");
        String keyValue = "";
        for (TableColumn tab : tableColumnList){
            if(tab.getColumnValue().equals("id") || tab.getColumnName().equals("主键id")
                    || tab.getColumnName().equals("主键ID")
                    ||tab.getColumnName().equals("自增ID")
                    ||tab.getColumnName().equals("自增长id")
                    ||tab.getColumnName().equals("自增长ID")
                    ||tab.getColumnName().equals("自增id")){
                buf.append("  `").append(tab.getColumnValue()).append("` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '自增主键',\n") ;
                keyValue = tab.getColumnValue();
                continue;
            }
            String def = "";
            if(!"否".equals(tab.getCanNull())){
                if(!"'无'".equals(tab.getDefultValue())) {
                    def = "DEFAULT " + tab.getDefultValue()+ " ";
                }
            }
            if("create_user".equals(tab.getColumnValue()) || "create_user".equals(tab.getColumnValue())){
                buf.append("  `").append(tab.getColumnValue()).append("` ").append(tab.getColumnType()).append(tab.getLength());
                buf.append(" NOT NULL ").append("COMMENT '").append(tab.getColumnName()).append("',\n");
            }else if("create_time".equals(tab.getColumnValue())){
                buf.append("  `").append(tab.getColumnValue()).append("` ").append(tab.getColumnType()).append(tab.getLength());
                buf.append(" NOT NULL DEFAULT CURRENT_TIMESTAMP ").append("COMMENT '").append(tab.getColumnName()).append("',\n");
            }else if("modify_time".equals(tab.getColumnValue())){
                buf.append("  `").append(tab.getColumnValue()).append("` ").append(tab.getColumnType()).append("(3)");
                buf.append(" NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) ").append("COMMENT '").append(tab.getColumnName()).append("',\n");

            }else if(tab.getColumnType().equals("datetime")){
                buf.append("  `").append(tab.getColumnValue()).append("` ").append(tab.getColumnType()).append(tab.getLength());
                buf.append(" DEFAULT NULL ").append("COMMENT '").append(tab.getColumnName()).append("',\n");
            }else {
                buf.append("  `").append(tab.getColumnValue()).append("` ").append(tab.getColumnType()).append(tab.getLength());
                buf.append(" NOT NULL ").append(def).append("COMMENT '").append(tab.getColumnName()).append("',\n");
            }

        }
        if(!"".equals(keyValue)) {
            buf.append("  PRIMARY KEY (`"+keyValue+"`) USING BTREE\n");
        }
        buf.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='").append(tableComment).append("';");
        System.out.println(buf.toString());
        return buf.toString();
    }

    public static String buildWhereSql(List<TableColumn> list){
        StringBuffer buf = new StringBuffer();
        StringBuffer buf1 = new StringBuffer();
        buf1.append("/**\n");
        buf1.append(" *\n");
        buf1.append(" * @param queryCondition 查询条件 包含<br>\n");



        buf.append("/**\n");
        buf.append(" * 根据查询条件构建查询条件sql\n");
        buf.append(" * @param paramList 查询条件\n");
        buf.append(" * @return\n");
        buf.append(" */\n");
        buf.append("private String buildQueryWhereSql(List<String> paramList){\n");
        buf.append("    StringBuffer buf = new StringBuffer();\n");
        buf.append("    String whereSql = \"\";\n");
        buf.append("\n");
        buf.append("    for (String str :paramList){\n");
        int index = 0;
        for (TableColumn tab :list) {
            if(index ==0) {
                buf.append("       if(\"").append(axaToaxA(tab.getColumnValue())).append("\".equals(str)){\n");
            }else {
                buf.append("else if(\"").append(axaToaxA(tab.getColumnValue())).append("\".equals(str)){\n");
            }
            buf.append("            buf.append(\"").append(tab.getColumnValue()).append(" = ? AND \");\n");
            buf.append("        }");
            index++;
            buf1.append(" * ").append(axaToaxA(tab.getColumnValue())).append(" ").append(tab.getColumnName()).append("<br>\n");
        }

        buf1.append(" * pageIndex 第几页<br>\n");
        buf1.append(" * pageSize 每页大小<br>\n");
        buf1.append(" * @return\n");
        buf1.append(" */");
        buf1.append("\n\n\n");

        buf.append("\n   }\n");
        buf.append("   whereSql = buf.toString();\n");
        buf.append("   if(whereSql.endsWith(\"AND \")){\n");
        buf.append("\n");
        buf.append("       whereSql = whereSql.substring(0,whereSql.length()-\"AND \".length());\n");
        buf.append("   }\n");
        buf.append("\n");
        buf.append("   return whereSql;\n");
        buf.append("}");
        System.out.println(buf1.toString());
        System.out.println(buf.toString());
        return buf.toString();
    }
    public static String buildQuerySql(List<TableColumn> list,String tableName){
        StringBuffer buf = new StringBuffer();
        buf.append("select ");
        for (TableColumn tab : list){
            buf.append(tab.getColumnValue()).append(",");
        }
        String str = buf.toString();
        if(str.endsWith(",")){
            str = str.substring(0,str.length()-1);
        }
        str += " from "+tableName+" ";

        System.out.println(str);
        System.out.println();
        return str;
    }
    public static String buildQueryResult(List<TableColumn> list,String tableName){
        StringBuffer buf = new StringBuffer();
        for (TableColumn tab : list){
            String ctype = axaToAxA(tab.getColumnType());
            if(tab.getColumnType().startsWith("D")||tab.getColumnType().startsWith("S")||tab.getColumnType().startsWith("T")){
                ctype = tab.getColumnType();
            }
            String defult = "0";
            if(tab.getColumnType().startsWith("D")||tab.getColumnType().startsWith("T")){
                defult = "null";
            }else if(tab.getColumnType().startsWith("S")){
                defult = "\"\"";
            }
//            custIntegralChange.setCgCode(row.getObject("cg_code")==null?0:row.getInt("cg_code"));
            buf.append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(row.getObject(\"").append(tab.getColumnValue()).append("\")==null?").append(defult).append(":row.get").append(ctype).append("(\"").append(tab.getColumnValue()).append("\"));\n");
        }
        System.out.println(buf.toString());
        return  buf.toString();
    }

    public static String buildInsertDBSql(List<TableColumn> list,String tableName){
        StringBuffer buf = new StringBuffer();
//        StringBuffer buf1 = new StringBuffer();
        buf.append("insert into ").append(tableName).append(" (");
        for (TableColumn tab : list){
            if(tab.getColumnValue().equals("id")) continue;
            buf.append(tab.getColumnValue()).append(",");
//            buf1.append(axaToaxA(tableName)).append(".get").append(axaToAxA(tab.getColumnValue())).append("(),");
        }
        buf.setLength(buf.length()-1);
        buf.append(") VALUES (\n");
        int count = 2;
        for (TableColumn tab : list){
            if(tab.getColumnValue().equals("id")) continue;
            String value = "";
            if(tab.getColumnType().equals("int")||tab.getColumnType().equals("long")||tab.getColumnType().equals("double")){
                value = "1";
            }else if(tab.getColumnType().equals("String")){
                value ="str";
            }else{
                value = null;
            }
            if(value == null){
                buf.append(value).append(",");
            }else{
                buf.append("'").append(value).append("',");
            }
        }
        buf.setLength(buf.length()-1);
        buf.append(") ;");
//        buf1.setLength(buf1.length()-1);
        System.out.println(buf.toString());
        System.out.println();
//        System.out.println(buf1.toString());
//        System.out.println();
        return buf.toString();
    }

    public static String buildInsertSql(List<TableColumn> list,String tableName){
        StringBuffer buf = new StringBuffer();
//        StringBuffer buf1 = new StringBuffer();
        buf.append("insert into ").append(tableName).append(" (");
        for (TableColumn tab : list){
            if(tab.getColumnValue().equals("id")) continue;
            buf.append(tab.getColumnValue()).append(",");
//            buf1.append(axaToaxA(tableName)).append(".get").append(axaToAxA(tab.getColumnValue())).append("(),");
        }
        buf.setLength(buf.length()-1);
        buf.append(") VALUES (");
        for (TableColumn tab : list){
            if(tab.getColumnValue().equals("id")) continue;
            buf.append("?,");
        }
        buf.setLength(buf.length()-1);
        buf.append(") ");
//        buf1.setLength(buf1.length()-1);
        System.out.println(buf.toString());
        System.out.println();
//        System.out.println(buf1.toString());
//        System.out.println();
        return buf.toString();
    }

    public static String buildSetParam(List<TableColumn> list,String tableName){
        StringBuffer buf = new StringBuffer();
        for (TableColumn tab : list) {
            if("id".equals(tab.getColumnValue())) continue;
            buf.append(axaToaxA(tableName)).append(".get").append(axaToAxA(tab.getColumnValue())).append("(),");
        }
        buf.setLength(buf.length()-1);
        System.out.println(buf.toString());
        return buf.toString();
    }

    public static String buildUpdateSql(List<TableColumn> list,String tableName){
        StringBuffer buf = new StringBuffer();
        buf.append("update ").append(tableName).append(" set ");
        for (TableColumn tab : list){
            if("id".equals(tab.getColumnValue())) continue;

            buf.append(tab.getColumnValue()).append(" =?, ");
        }
        buf.setLength(buf.length()-1);
        System.out.println(buf.toString());
        return buf.toString();
    }
    public static String buildApiStr(List<TableColumn> list){
        StringBuffer buf = new StringBuffer();
        StringBuffer buf1 = new StringBuffer();

        for (TableColumn tab : list) {
            buf.append("|").append(axaToaxA(tab.getColumnValue())).append(" |").append(tab.getCanNull()).append("  |").append(tab.getColumnType()).append(" |").append(tab.getColumnName()).append("   |\n");
            buf1.append("|").append(axaToaxA(tab.getColumnValue())).append("  |").append(tab.getColumnType()).append(" |").append(tab.getColumnName()).append("   |\n");
        }
        System.out.println(buf.toString());
        System.out.println(buf1.toString());


        String str1 = "{\n" +
                "    \"success\": true,\n" +
                "    \"bizSuccess\": true,\n" +
                "    \"msg\": null,\n" +
                "    \"code\": null,\n" +
                "    \"data\": 1\n" +
                "}";
        System.out.println(str1);
        String str = "\n\n|success |boolean   |业务操作结果  |\n" +
                "|bizSuccess |boolean   |访问结果 |\n" +
                "|msg |String   |返回消息  |\n" +
                "|code |String   |返回消息代码  |\n" +
                "|data |int   |对数据库操作的数量 |";
        System.out.println(str);
        String a = "\n\n\"success\": true,\n" +
                "    \"bizSuccess\": true,\n" +
                "    \"msg\": \"查询成功\",\n" +
                "    \"code\": \"12101\",\n" +
                "    \"dataList\": [\n" +
                "        {\n" +
                "        }\n" +
                "    ],\n" +
                "    \"resultMap\": {},\n" +
                "    \"pageIndex\": 1,\n" +
                "    \"totalCount\": 1,\n" +
                "    \"pageSize\": 20,\n" +
                "    \"pageCount\": 1";
        System.out.println(a);
        String a1 = "\n\n|success |boolean   |操作结果  |\n" +
                "|bizSuccess |boolean   |压缩结果（无用） |\n" +
                "|msg |String   |返回消息  |\n" +
                "|code |String   |返回消息代码  |\n" +
                "|dataList |obj   |返回结果列表 |\n" +
                "|resultMap |Map |返回结果集合 |\n" +
                "|pageIndex |int |页码 |\n" +
                "|totalCount |int |数据条数 |\n" +
                "|pageSize |int |每页大小 |\n" +
                "|pageCount |int |页数 |";
        System.out.println(a1);
        return buf.toString();
    }
    public static String buildEntityJson(List<TableColumn> list,boolean showName){
        StringBuffer buf = new StringBuffer();
        buf.append("{\n");
        for (TableColumn tab : list) {

            String name = "";
            if(showName){
                name = "  // " + tab.getColumnName();
            }
            if(tab.getColumnType().equals("String")) {
                buf.append("    \"").append(axaToaxA(tab.getColumnValue())).append("\": \"").append(tab.getColumnName()).append("\",").append(name).append("\n");
            }else if(tab.getColumnType().equals("Date")){
                buf.append("    \"").append(axaToaxA(tab.getColumnValue())).append("\": \"\",").append(name).append("\n");
            }else {
                buf.append("    \"").append(axaToaxA(tab.getColumnValue())).append("\": 1,").append(name).append("\n");
            }
        }
        buf.append("}\n");
        System.out.println(buf.toString());
        return  buf.toString();
    }
    public static String buildMapToEntity(List<TableColumn> list,String tableName){
        StringBuffer buf = new StringBuffer();
        for (TableColumn tab : list) {

            //map转实体类
            if(tab.getColumnType().startsWith("l")) {
                buf.append("            ").append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(ConvertUtil.convertObjectTo").append(axaToAxA(tab.getColumnType())).append("(conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("\"),0L));\n");
            }else if(tab.getColumnType().startsWith("i")) {
                buf.append("            ").append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(ConvertUtil.convertObjectTo").append(axaToAxA(tab.getColumnType())).append("(conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("\"),0));\n");
            }else if(tab.getColumnType().startsWith("S")){
                buf.append("            ").append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(ConvertUtil.convertObjectTo").append(axaToaxA(tab.getColumnType())).append("(conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("\").toString(),\"\"));\n");
            }else if(tab.getColumnType().startsWith("T")){
                //wareLicenceType.setLastUpdateTime(conditionMap.get("LastUpdateTime")==null?null:(Timestamp) conditionMap.get(""));
                buf.append("            ").append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("\")==null?null:(Timestamp)conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("\"));\n");
            }else if(tab.getColumnType().startsWith("Date")){

                buf.append("            long ").append(axaToaxA(tab.getColumnValue())).append("Long = ConvertUtil.convertObjectToLong(conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("Long\"),0);\n");
                buf.append("            ").append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(").append(axaToaxA(tab.getColumnValue())).append("Long==0?null:new Date(").append(axaToaxA(tab.getColumnValue())).append("Long));\n");
//                buf.append("            ").append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(ConvertUtil.convertObjectTo").append(tab.getColumnType()).append("(conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("\"),null));\n");
            }else{
                buf.append("            ").append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(ConvertUtil.convertObjectTo").append(axaToAxA(tab.getColumnType())).append("(conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("\"),0));\n");
            }
        }
        System.out.println(buf.toString());
        return buf.toString();

    }
    public static String buildEntityToMap(List<TableColumn> list,String tableName){
        StringBuffer buf = new StringBuffer();
        for (TableColumn tab : list) {
            //实体类转map
            if(tab.getColumnType().startsWith("l")||tab.getColumnType().startsWith("i")||tab.getColumnType().startsWith("double")) {
                buf.append("map.put(\"").append(axaToaxA(tab.getColumnValue())).append("\",").append(axaToaxA(tableName)).append(".get").append(axaToAxA(tab.getColumnValue())).append("());\n");
            }else if(tab.getColumnType().startsWith("S")){
                buf.append("map.put(\"").append(axaToaxA(tab.getColumnValue())).append("\",").append(axaToaxA(tableName)).append(".get").append(axaToAxA(tab.getColumnValue())).append("()==null?\"\":").append(axaToaxA(tableName)).append(".get").append(axaToAxA(tab.getColumnValue())).append("());\n");
            }else{
                buf.append("map.put(\"").append(axaToaxA(tab.getColumnValue())).append("Long").append("\",").append(axaToaxA(tableName)).append(".get").append(axaToAxA(tab.getColumnValue())).append("()==null?0:").append(axaToaxA(tableName)).append(".get").append(axaToAxA(tab.getColumnValue())).append("().getTime());\n");
            }
        }
        System.out.println(buf.toString());
        return buf.toString();

    }
    public static String buildDaoStr(List<TableColumn> list,String tableComment,String tableName){
        StringBuffer buf = new StringBuffer();
        buf.append("package com.hydee;\n" +
                "\n" +
                "\n" +
                "import com.hydee.basedata.base.bean.serivce.meta.bean.Page;\n" +
                "import com.hydee.basedata.base.bean.serivce.meta.bean.ParameterWrapper;\n" +
                "import com.hydee.basedata.base.bean.serivce.meta.bean.ResultWrapper;\n" +
                "import com.hydee.basedata.base.bean.serivce.meta.bean.Row;\n" +
                "import com.hydee.basedata.connector.orm.service.Repository;\n" +
                "import com.hydee.common.util.ConvertUtil;\n" +
                "import com.hydee.h3.orderinfo.promotion.entity."+axaToAxA(tableName)+";\n" +
                "import com.hydee.common.beans.message.MessageQuery;\n" +
                "import com.hydee.common.beans.message.MessageData;\n" +
                "import com.hydee.common.beans.message.event.MessageEnum;\n" +
                "import org.apache.log4j.Logger;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.stereotype.Component;\n" +
                "\n" +
                "import java.sql.Timestamp;\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.HashMap;\n" +
                "import java.util.List;\n" +
                "\n" +
                "/**   \n" +
                " * @ClassName:  "+axaToAxA(tableName)+"Dao   \n" +
                " * @Description: "+tableComment+"("+tableName+")Dao   \n" +
                " * @author: QianTao \n" +
                " * @date:   "+dateStr+"\n" +
                " * @version: V1.0   \n" +
                " */\n"+
                "@Component\n" +
                "public class "+axaToAxA(tableName)+"Dao {\n" +
                "\n" +
                "    private  final String PAGEINDEX = \"pageIndex\";\n" +
                "    private  final String PAGESIZE = \"pageSize\";\n" +
                "    private final  String ORDER_SQL = \"orderByColumn\";\n" +
                "\n" +
                "    Logger log = Logger.getLogger("+axaToAxA(tableName)+"Dao.class);\n" +
                "    @Autowired\n" +
                "    Repository repository;\n" +
                "\n" +
                "    /**\n" +
                "     * 查询"+tableComment+"列表\n" +
                "     * @param queryCondition 查询条件\n" +
                "     * @return 查询结果对象\n" +
                "     */\n" +
                "    public MessageQuery query"+axaToAxA(tableName)+"ByCondition(HashMap<String, Object> queryCondition) {\n" +
                "        MessageQuery messageQuery = new MessageQuery();\n" +
                "        if(queryCondition==null||queryCondition.size()==0){\n" +
                "             return MessageQuery.fail(MessageEnum.BIZ_PARAMETER_ERROR);\n" +
//                "            messageQuery.setMessage(MessageEnum.BIZ_PARAMETER_ERROR);\n" +
//                "            return messageQuery;\n" +
                "        }\n" +
                "        int pageNum =1;\n" +
                "        int pageSize = 20;\n" +
                "        if(queryCondition.get(PAGESIZE)!=null&&queryCondition.get(PAGEINDEX)!=null){\n" +
                "            pageSize = Integer.parseInt(queryCondition.get(PAGESIZE).toString());\n" +
                "            pageNum = Integer.parseInt(queryCondition.get(PAGEINDEX).toString());\n" +
                "        }\n" +
                "        queryCondition.remove(PAGESIZE);\n" +
                "        queryCondition.remove(PAGEINDEX);\n" +
                "\n" +
                "        String sql = buildQuerySql(queryCondition,1);\n" +
                "\n" +
                "        List<"+axaToAxA(tableName)+"> "+axaToaxA(tableName)+"List = new ArrayList<>();\n" +
                "\n" +
                "        ParameterWrapper parameterWrapper = new ParameterWrapper();\n" +
                "        parameterWrapper.setStmt(sql);\n" +
                "        parameterWrapper.setPage(new Page(pageNum,pageSize));\n" +
                "        parameterWrapper.addParameters(buildParam(queryCondition));\n" +
                "        ResultWrapper resultWrapper = repository.select(parameterWrapper);\n" +
                "        for (Row row :resultWrapper.getRows()){\n" +
                "            "+axaToAxA(tableName)+" "+axaToaxA(tableName)+" =convertRowTo"+axaToAxA(tableName)+"(row);\n" +
                "            "+axaToaxA(tableName)+"List.add("+axaToaxA(tableName)+");\n" +
                "        }\n" +
//                "        messageQuery.setTotalCount((int)resultWrapper.getTotalCount());\n" +
//                "        messageQuery.setPageSize(pageSize);\n" +
//                "        messageQuery.setPageIndex(pageNum);\n" +
//                "        messageQuery.setMessage(MessageEnum.BIZ_QUERY_SUCCESS);\n" +
//                "        messageQuery.setBizSuccess(true);\n" +
//                "        messageQuery.setDataList("+axaToaxA(tableName)+"List);\n" +
                "        return MessageQuery.success(MessageEnum.BIZ_QUERY_SUCCESS,"+axaToaxA(tableName)+"List).pageIndex(pageNum)\n" +
                "                           .pagePageSize(pageSize).totalCount(resultWrapper.getTotalCount());\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    /**\n" +
                "     * 查询"+tableComment+"列表\n" +
                "     * @param queryCondition\n" +
                "     * @return\n" +
                "     */\n" +
                "    public List<"+axaToAxA(tableName)+"> get"+axaToAxA(tableName)+"List(HashMap<String, Object> queryCondition){\n" +
                "        List<"+axaToAxA(tableName)+"> "+axaToaxA(tableName)+"List = new ArrayList<>();\n" +
                "        if(queryCondition==null||queryCondition.size()==0){\n" +
                "            return "+axaToaxA(tableName)+"List;\n" +
                "        }\n" +
                "        queryCondition.remove(PAGESIZE);\n" +
                "        queryCondition.remove(PAGEINDEX);\n" +
                "\n" +
                "        String sql = buildQuerySql(queryCondition,1);\n" +
                "\n" +
                "\n" +
                "        ParameterWrapper parameterWrapper = new ParameterWrapper();\n" +
                "        parameterWrapper.setStmt(sql);\n" +
                "        parameterWrapper.addParameters(buildParam(queryCondition));\n" +
                "        ResultWrapper resultWrapper = repository.select(parameterWrapper);\n" +
                "        for (Row row :resultWrapper.getRows()){\n" +
                "            "+axaToAxA(tableName)+" "+axaToaxA(tableName)+" =convertRowTo"+axaToAxA(tableName)+"(row);\n" +
                "            "+axaToaxA(tableName)+"List.add("+axaToaxA(tableName)+");\n" +
                "        }\n" +
                "        return "+axaToaxA(tableName)+"List;\n" +
                "    }\n\n"+
                "    /**\n" +
                "     * 查询"+tableComment+"数量\n" +
                "     * @param queryCondition\n" +
                "     * @return\n" +
                "     */\n" +
                "    public int query"+axaToAxA(tableName)+"ByConditionCount(HashMap<String, Object> queryCondition){\n" +
                "        int count = 0;\n" +
                "        if(queryCondition==null||queryCondition.size()==0){\n" +
                "            return 0;\n" +
                "        }\n" +
                "        queryCondition.remove(PAGESIZE);\n" +
                "        queryCondition.remove(PAGEINDEX);\n" +
                "\n" +
                "        String sql = buildQuerySql(queryCondition,2);\n" +
                "\n" +
                "\n" +
                "        ParameterWrapper parameterWrapper = new ParameterWrapper();\n" +
                "        parameterWrapper.setStmt(sql);\n" +
                "        parameterWrapper.addParameters(buildParam(queryCondition));\n" +
                "        ResultWrapper resultWrapper = repository.select(parameterWrapper);\n" +
                "        for (Row row :resultWrapper.getRows()){\n" +
                "            count = Integer.parseInt(row.getColumn(0).toString());\n" +
                "        }\n" +
                "        return count;\n" +
                "    }\n" +
                "\n\n" +

                "    /**\n" +
                "     * 生成添加数据库操作对象\n" +
                "     * @param "+axaToaxA(tableName)+"\n" +
                "     * @return\n" +
                "     */\n" +
                "    public ParameterWrapper buildInsertParameterWrapperBy"+axaToAxA(tableName)+"("+axaToAxA(tableName)+" "+axaToaxA(tableName)+") {\n" +
                "        ParameterWrapper parameterWrapper = new ParameterWrapper();\n" +
//                "        Timestamp d = new Timestamp(System.currentTimeMillis());\n" +
//                "        if("+axaToaxA(tableName)+".getCreateTime()==null) {\n" +
//                "            "+axaToaxA(tableName)+".setCreateTime(d);\n" +
//                "        }\n" +
//                "        if("+axaToaxA(tableName)+".getLastUpdateTime()==null) {\n" +
//                "            "+axaToaxA(tableName)+".setLastUpdateTime(d);\n" +
//                "        }\n"+
                "        String sql = \"insert into "+tableName+" (" );
        for (TableColumn tab : list){
            if(tab.getColumnValue().equals("id")) continue;
            buf.append(tab.getColumnValue()).append(",");
//            buf1.append(axaToaxA(tableName)).append(".get").append(axaToAxA(tab.getColumnValue())).append("(),");
        }
        buf.setLength(buf.length()-1);

        buf.append(") VALUES (");
//                "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" +
        for (TableColumn tab : list){
            if(tab.getColumnValue().equals("id")) continue;
            buf.append("?,");
        }
        buf.setLength(buf.length()-1);
        buf.append("" +
                ")\";\n" +
                "        parameterWrapper.setStmt(sql);\n" +
                "        parameterWrapper.addParameters(");
        for (TableColumn tab : list){
            if(tab.getColumnValue().equals("id")) continue;
            buf.append(axaToaxA(tableName)).append(".get").append(axaToAxA(tab.getColumnValue())).append("(),");
        }
        buf.setLength(buf.length()-1);
        buf.append(");\n" +
                "        return parameterWrapper;\n" +
                "    }\n" +
                "    /**\n" +
                "     * 添加"+tableComment+"记录\n" +
                "     * @param "+axaToaxA(tableName)+"\n" +
                "     * @return\n" +
                "     */\n" +
                "    public long addBy"+axaToAxA(tableName)+"("+axaToAxA(tableName)+" "+axaToaxA(tableName)+") {\n" +
//                "        Timestamp d = new Timestamp(System.currentTimeMillis());\n" +
//                "        if("+axaToaxA(tableName)+".getCreateTime()==null) {\n" +
//                "            "+axaToaxA(tableName)+".setCreateTime(d);\n" +
//                "        }\n" +
//                "        if("+axaToaxA(tableName)+".getLastUpdateTime()==null) {\n" +
//                "            "+axaToaxA(tableName)+".setLastUpdateTime(d);\n" +
//                "        }\n" +
                "\n" +
                "        return repository.insert("+axaToaxA(tableName)+");\n" +
                "    }\n"+
                "\n\n"+
                "    /*--------------- 以下是私有方法 ---------------*/\n" +
                "\n" +
                "    /**\n" +
                "     *\n" +
                "     * @param queryCondition\n" +
                "     * @param type 1查询全部  2查询数量\n" +
                "     * @return\n" +
                "     */\n" +
                "    private String buildQuerySql(HashMap<String, Object> queryCondition,int type){\n" +
                "        List<String> paramList = getMapKeyToList(queryCondition);\n" +
                "\n" +
                "        String orderSql = \"\";\n" +
                "\n" +
                "        if(queryCondition.get(ORDER_SQL)!=null){\n" +
                "            orderSql = \" \"+queryCondition.get(ORDER_SQL).toString();\n" +
                "        }\n" +
                "        queryCondition.remove(ORDER_SQL);\n" +
                "        String querySql = \" count(*) \";\n" +
                "        if(type==1){\n" +
                "            querySql = \" " );
        for (TableColumn tab :list){
            buf.append(tab.getColumnValue()).append(",");
        }
        buf.setLength(buf.length()-1);

        buf.append("\";\n        }\n" +
                "        String sql =\"select \"+querySql+\" from "+tableName+" \" ;\n" +
                "        String whereSql = buildQueryWhereSql(paramList);\n" +
                "        if(whereSql.length()>0){\n" +
                "            sql = sql + \" where \" + whereSql ;\n" +
                "        }\n" +
                "\n" +
                "        return sql;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 生成查询条件中的查询参数\n" +
                "     * @param queryCondition 查询条件\n" +
                "     * @return 参数数组\n" +
                "     */\n" +
                "    private Object[] buildParam(HashMap<String, Object> queryCondition){\n" +
                "        Object[] objs = new Object[queryCondition.size()];\n" +
                "        int index = 0;\n" +
                "        for (String key:queryCondition.keySet()){\n" +
                "            objs[index] = queryCondition.get(key);\n" +
                "            index++;\n" +
                "        }\n" +
                "        return  objs;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 获取Map中的Key值\n" +
                "     * @param queryCondition 查询条件\n" +
                "     * @return 查询KEY\n" +
                "     */\n" +
                "    private List<String> getMapKeyToList(HashMap<String, Object> queryCondition){\n" +
                "        List<String> paramList = new ArrayList<>();\n" +
                "        for (String key:queryCondition.keySet()){\n" +
                "            paramList.add(key);\n" +
                "        }\n" +
                "        return  paramList;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 根据查询条件构建查询条件sql\n" +
                "     * @param paramList 查询条件\n" +
                "     * @return 查询条件Sql\n" +
                "     */\n" +
                "    private String buildQueryWhereSql(List<String> paramList){\n" +
                "        StringBuffer buf = new StringBuffer();\n" +
                "        String whereSql = \"\";\n" +
                "\n" +
                "        for (String str :paramList){\n" );
        int index = 0;
        for(TableColumn tab :list){
            if(index ==0||"String".equals(tab.getColumnType())||"long".equals(tab.getColumnType())||"int".equals(tab.getColumnType())){
                if(index ==0) {
                    buf.append("            if(\"").append(axaToaxA(tab.getColumnValue())).append("\".equals(str)){\n");
                }else {
                    buf.append("else if(\"").append(axaToaxA(tab.getColumnValue())).append("\".equals(str)){\n");
                }
                buf.append("                buf.append(\"").append(tab.getColumnValue()).append(" = ? AND \");\n");
                buf.append("            }");
                index++;
            }
        }
        buf.append("        \n        }\n"+
                "        whereSql = buf.toString();\n" +
                "        if(whereSql.endsWith(\"AND \")){\n" +
                "\n" +
                "            whereSql = whereSql.substring(0,whereSql.length()-\"AND \".length());\n" +
                "        }\n" +
                "\n" +
                "        return whereSql;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 转换查询结果为"+tableComment+"对象\n" +
                "     * @param row 查询结果\n" +
                "     * @return "+tableComment+"对象\n" +
                "     */\n" +
                "    private "+axaToAxA(tableName)+" convertRowTo"+axaToAxA(tableName)+"(Row row){\n" +
                "        "+axaToAxA(tableName)+" "+axaToaxA(tableName)+" = new "+axaToAxA(tableName)+"();\n" +
                "\n" );
        for (TableColumn tab : list){
            String ctype = axaToAxA(tab.getColumnType());
            if(tab.getColumnType().startsWith("D")||tab.getColumnType().startsWith("S")||tab.getColumnType().startsWith("T")){
                ctype = tab.getColumnType();
            }
            String defult = "0";
            if(tab.getColumnType().startsWith("D")||tab.getColumnType().startsWith("T")){
                defult = "null";
            }else if(tab.getColumnType().startsWith("S")){
                defult = "\"\"";
            }
//            custIntegralChange.setCgCode(row.getObject("cg_code")==null?0:row.getInt("cg_code"));
            buf.append("        ").append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(row.getObject(\"").append(tab.getColumnValue()).append("\")==null?").append(defult).append(":row.get").append(ctype).append("(\"").append(tab.getColumnValue()).append("\"));\n");
        }
        buf.append("\n\n        return "+axaToaxA(tableName)+";\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "}\n");
        return buf.toString();
    }
    public static String buildServiceStr(List<TableColumn> list,String tableComment,String tableName){
        StringBuffer buf = new StringBuffer();
        buf.append("package com.hydee.h3;\n" +
                "\n" +
                "import com.hydee.basedata.base.bean.serivce.meta.bean.ParameterWrapper;\n" +
                "import com.hydee.common.beans.message.MessageQuery;\n" +
                "import com.hydee.common.beans.message.MessageData;\n" +
                "import com.hydee.h3.orderinfo.promotion.entity."+axaToAxA(tableName)+";\n" +
                "\n" +
                "import java.util.HashMap;\n" +
                "import java.util.List;\n" +
                "\n" +
                "/**   \n" +
                " * @ClassName:  "+axaToAxA(tableName)+"Service   \n" +
                " * @Description: "+tableComment+"("+tableName+")Service   \n" +
                " * @author: QianTao \n" +
                " * @date:   "+dateStr+"\n" +
                " * @version: V1.0   \n" +
                " */\n"+
                "public interface "+axaToAxA(tableName)+"Service {\n" +
                "    /**\n" +
                "     *\n" +
                "     * @param queryCondition 查询条件 包含<br>\n");
        for (TableColumn tab :list){
            if("String".equals(tab.getColumnType())||"long".equals(tab.getColumnType())||"int".equals(tab.getColumnType())){
                buf.append("     * ").append(axaToaxA(tab.getColumnValue())).append(" ").append(tab.getColumnName()).append("<br>\n");
            }
        }
        buf.append("     * pageIndex 第几页<br>\n" +
                "     * pageSize 每页大小<br>\n" +
                "     * @return\n" +
                "     */\n" +
                "    MessageQuery query"+axaToAxA(tableName)+"ByCondition(HashMap<String,Object> queryCondition);\n" +
                "\n" +
                "    /**\n" +
                "     *\n" +
                "     * @param queryCondition 查询条件 包含<br>\n");
        for (TableColumn tab :list){
            buf.append("     * ").append(axaToaxA(tab.getColumnValue())).append(" ").append(tab.getColumnName()).append("<br>\n");
        }
        buf.append("     * @return\n" +
                "     */\n" +
                "    List<"+axaToAxA(tableName)+"> get"+axaToAxA(tableName)+"List(HashMap<String,Object> queryCondition);\n" +
                "\n" +
                "    /**\n" +
                "     * 查询记录条数\n" +
                "     * @param queryCondition\n" +
                "     * @return\n" +
                "     */\n" +
                "    long query"+axaToAxA(tableName)+"ByConditionCount(HashMap<String, Object> queryCondition);\n" +
                "\n" +
                "    /**\n" +
                "     * 添加"+tableComment+"记录数据库操作对象\n" +
                "     * @param "+axaToaxA(tableName)+"\n" +
                "     * @return\n" +
                "     */\n" +
                "    ParameterWrapper buildInsertParameterWrapperBy"+axaToAxA(tableName)+"("+axaToAxA(tableName)+" "+axaToaxA(tableName)+");\n" +
                "\n" +
                "    /**\n" +
                "     * 添加"+tableComment+"记录数据库操作对象\n" +
                "     * @param conditionMap\n" +
                "     * @return\n" +
                "     */\n" +
                "    ParameterWrapper buildInsertParameterWrapperByMap(HashMap<String, Object> conditionMap);\n" +

                "    /**\n" +
                "     * 添加"+tableComment+"记录\n" +
                "     * @param "+axaToaxA(tableName)+"\n" +
                "     * @return\n" +
                "     */\n" +
                "    MessageData addBy"+axaToAxA(tableName)+"("+axaToAxA(tableName)+" "+axaToaxA(tableName)+");"+

                "}\n\n");


        return  buf.toString();
    }
    public static String buildServiceImplStr(List<TableColumn> list,String tableComment,String tableName){
        StringBuffer buf = new StringBuffer();
        buf.append("package com.hydee.h3;\n" +
                "\n" +
                "import com.hydee.basedata.base.bean.serivce.meta.bean.ParameterWrapper;\n" +
                "import com.hydee.common.beans.message.MessageQuery;\n" +
                "import com.hydee.common.beans.message.MessageData;\n" +
                "import com.hydee.common.beans.message.event.MessageEnum;\n" +
                "import com.hydee.h3.orderinfo.promotion.dao."+axaToAxA(tableName)+"Dao;\n" +
                "import com.hydee.h3.orderinfo.promotion.entity."+axaToAxA(tableName)+";\n" +
                "import com.hydee.h3.orderinfo.promotion.service."+axaToAxA(tableName)+"Service;\n" +
                "import org.apache.log4j.Logger;\n" +
                "import com.hydee.common.util.ConvertUtil;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "\n" +
                "import java.sql.Timestamp;\n" +
                "import java.util.HashMap;\n" +
                "import java.util.List;\n" +
                "\n" +
                "/**   \n" +
                " * @ClassName:  "+axaToAxA(tableName)+"ServiceImpl   \n" +
                " * @Description: "+tableComment+"("+tableName+")ServiceImpl   \n" +
                " * @author: QianTao \n" +
                " * @date:   "+dateStr+"\n" +
                " * @version: V1.0   \n" +
                " */\n"+
                "@Service\n" +
                "public class "+axaToAxA(tableName)+"ServiceImpl implements "+axaToAxA(tableName)+"Service {\n" +
                "\n" +
                "    private Logger log = Logger.getLogger("+axaToAxA(tableName)+"ServiceImpl.class);\n" +
                "\n" +
                "    @Autowired\n" +
                "    private "+axaToAxA(tableName)+"Dao "+axaToaxA(tableName)+"Dao;\n" +
                "    @Override\n" +
                "    public MessageQuery query"+axaToAxA(tableName)+"ByCondition(HashMap<String, Object> queryCondition) {\n" +
                "        if(queryCondition==null){\n" +
                "            return MessageQuery.fail(MessageEnum.BIZ_PARAMETER_ERROR);\n" +
//                "            messageQuery.setBizSuccess(false);\n" +
//                "            messageQuery.setMessage(MessageEnum.BIZ_PARAMETER_ERROR);\n" +
//                "            return messageQuery;\n" +
                "        }\n" +
                "        MessageQuery messageQuery = "+axaToaxA(tableName)+"Dao.query"+axaToAxA(tableName)+"ByCondition(queryCondition);\n" +
                "\n" +
                "        return messageQuery;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public List<"+axaToAxA(tableName)+"> get"+axaToAxA(tableName)+"List(HashMap<String, Object> queryCondition) {\n" +
                "        if(!queryCondition.containsKey(\"cgCode\")){\n" +
                "            return null;\n" +
                "        }\n" +
                "        List<"+axaToAxA(tableName)+"> "+axaToaxA(tableName)+"List = "+axaToaxA(tableName)+"Dao.get"+axaToAxA(tableName)+"List(queryCondition);\n" +
                "        return "+axaToaxA(tableName)+"List;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public long query"+axaToAxA(tableName)+"ByConditionCount(HashMap<String,Object> queryCondition){\n" +
                "        long count  = "+axaToaxA(tableName)+"Dao.query"+axaToAxA(tableName)+"ByConditionCount(queryCondition);\n" +
                "        return count;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public ParameterWrapper buildInsertParameterWrapperBy"+axaToAxA(tableName)+"("+axaToAxA(tableName)+" "+axaToaxA(tableName)+") {\n" +
                "        if("+axaToaxA(tableName)+"==null) return null;\n" +
                "        return "+axaToaxA(tableName)+"Dao.buildInsertParameterWrapperBy"+axaToAxA(tableName)+"("+axaToaxA(tableName)+");\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public ParameterWrapper buildInsertParameterWrapperByMap(HashMap<String, Object> conditionMap) {\n" +
                "        if(conditionMap==null||conditionMap.size()<1) return null;\n" +
                "        "+axaToAxA(tableName)+" "+axaToaxA(tableName)+" =this.exchangeMapTo"+axaToAxA(tableName)+"(conditionMap);\n" +
                "        if("+axaToaxA(tableName)+"==null) return null;\n" +
                "        return "+axaToaxA(tableName)+"Dao.buildInsertParameterWrapperBy"+axaToAxA(tableName)+"("+axaToaxA(tableName)+");\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public MessageData addBy"+axaToAxA(tableName)+"("+axaToAxA(tableName)+" "+axaToaxA(tableName)+") {\n" +
                "        if("+axaToaxA(tableName)+"==null || "+axaToaxA(tableName)+".getCgCode()<1){\n" +
                "            return MessageData.fail(MessageEnum.BIZ_PARAMETER_ERROR,-1);\n" +
                "        }\n" +
                "        long rs = "+axaToaxA(tableName)+"Dao.addBy"+axaToAxA(tableName)+"("+axaToaxA(tableName)+");\n" +
                "\n" +
                "        return MessageData.success(MessageEnum.BIZ_MODIFY_SUCCESS,rs);\n" +
                "    }"+
                "\n" +
                "\n" +
                "    /**\n" +
                "     * 转换map对象为"+tableComment+"对象\n" +
                "     * @param conditionMap\n" +
                "     * @return\n" +
                "     */\n" +
                "    private "+axaToAxA(tableName)+" exchangeMapTo"+axaToAxA(tableName)+"(HashMap<String,Object> conditionMap){\n" +
                "        "+axaToAxA(tableName)+" "+axaToaxA(tableName)+" = new "+axaToAxA(tableName)+"();\n" +
                "        if(conditionMap.get(\"cgCode\")==null){\n" +
                "            log.error(\"参数不对\");\n" +
                "            return null;\n" +
                "        }\n" +
                "        Timestamp d = new Timestamp(System.currentTimeMillis());\n" +
                "        try {\n");
        for (TableColumn tab : list) {

            //map转实体类
            if(tab.getColumnType().startsWith("l")) {
                buf.append("            ").append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(ConvertUtil.convertObjectTo").append(axaToAxA(tab.getColumnType())).append("(conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("\"),0L));\n");
            }else if(tab.getColumnType().startsWith("i")) {
                buf.append("            ").append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(ConvertUtil.convertObjectTo").append(axaToAxA(tab.getColumnType())).append("(conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("\"),0));\n");
            }else if(tab.getColumnType().startsWith("S")){
                buf.append("            ").append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(ConvertUtil.convertObjectTo").append(axaToaxA(tab.getColumnType())).append("(conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("\").toString(),\"\"));\n");
            }else if(tab.getColumnType().startsWith("T")){
                //wareLicenceType.setLastUpdateTime(conditionMap.get("LastUpdateTime")==null?null:(Timestamp) conditionMap.get(""));
                buf.append("            ").append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("\")==null?null:(Timestamp)conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("\"));\n");
            }else if(tab.getColumnType().startsWith("Date")){

                buf.append("            long ").append(axaToaxA(tab.getColumnValue())).append("Long = ConvertUtil.convertObjectToLong(conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("Long\"),0);\n");
                buf.append("            ").append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(").append(axaToaxA(tab.getColumnValue())).append("Long==0?null:new Date(").append(axaToaxA(tab.getColumnValue())).append("Long));\n");
//                buf.append("            ").append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(ConvertUtil.convertObjectTo").append(tab.getColumnType()).append("(conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("\"),null));\n");
            }else{
                buf.append("            ").append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(ConvertUtil.convertObjectTo").append(axaToAxA(tab.getColumnType())).append("(conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("\"),0));\n");
            }
        }
        buf.append("\n" +
                "\n" +
                "        }catch (Exception e){\n" +
                "            log.error(\"用户数据转换失败！\");\n" +
                "            return null;\n" +
                "        }\n" +
                "        return "+axaToaxA(tableName)+";\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "}\n");


        return  buf.toString();
    }

    public static String buildDaoServiceStr(List<TableColumn> list,String tableComment,String tableName){
        StringBuffer buf = new StringBuffer();
        buf.append("package com.hydee.h3;\n" +
                "\n" +
//                "import com.hydee.basedata.base.bean.serivce.meta.bean.ParameterWrapper;\n" +
                "import com.hydee.common.beans.message.MessageQuery;\n" +
                "import com.hydee.h3.pos.dataprotocol.beans.promotion."+axaToAxA(tableName)+";\n" +
                "\n" +
                "import java.util.HashMap;\n" +
                "import java.util.List;\n" +
                "\n" +
                "/**   \n" +
                " * @ClassName:  "+axaToAxA(tableName)+"Dao   \n" +
                " * @Description: "+tableComment+"("+tableName+")Dao   \n" +
                " * @author: QianTao \n" +
                " * @date:   "+dateStr+"\n" +
                " * @version: V1.0   \n" +
                " */\n"+
                "public interface "+axaToAxA(tableName)+"Dao {\n" +
                "    /**\n" +
                "     *\n" +
                "     * @param queryCondition 查询条件 包含<br>\n");
        for (TableColumn tab :list){
            if("String".equals(tab.getColumnType())||"long".equals(tab.getColumnType())||"int".equals(tab.getColumnType())){
                buf.append("     * ").append(axaToaxA(tab.getColumnValue())).append(" ").append(tab.getColumnName()).append("<br>\n");
            }
        }
        buf.append("     * pageIndex 第几页<br>\n" +
                "     * pageSize 每页大小<br>\n" +
                "     * @return\n" +
                "     */\n" +
                "    MessageQuery query"+axaToAxA(tableName)+"ByCondition(HashMap<String,Object> queryCondition);\n" +
                "\n" +
                "    /**\n" +
                "     *\n" +
                "     * @param queryCondition 查询条件 包含<br>\n");
        for (TableColumn tab :list){
            buf.append("     * ").append(axaToaxA(tab.getColumnValue())).append(" ").append(tab.getColumnName()).append("<br>\n");
        }
        buf.append("     * @return\n" +
                "     */\n" +
                "    List<"+axaToAxA(tableName)+"> get"+axaToAxA(tableName)+"List(HashMap<String,Object> queryCondition);\n" +
                "\n" +
                "    /**\n" +
                "     * 查询记录条数\n" +
                "     * @param queryCondition\n" +
                "     * @return\n" +
                "     */\n" +
                "    long query"+axaToAxA(tableName)+"ByConditionCount(HashMap<String, Object> queryCondition);\n" +
                "\n" +
//                "    /**\n" +
//                "     * 添加"+tableComment+"记录数据库操作对象\n" +
//                "     * @param "+axaToaxA(tableName)+"\n" +
//                "     * @return\n" +
//                "     */\n" +
//                "    ParameterWrapper buildInsertParameterWrapperBy"+axaToAxA(tableName)+"("+axaToAxA(tableName)+" "+axaToaxA(tableName)+");\n" +
//                "\n" +
//                "    /**\n" +
//                "     * 添加"+tableComment+"记录数据库操作对象\n" +
//                "     * @param conditionMap\n" +
//                "     * @return\n" +
//                "     */\n" +
//                "    ParameterWrapper buildInsertParameterWrapperByMap(HashMap<String, Object> conditionMap);\n" +

                "    /**\n" +
                "     * 添加"+tableComment+"记录\n" +
                "     * @param "+axaToaxA(tableName)+"\n" +
                "     * @return\n" +
                "     */\n" +
                "    long addBy"+axaToAxA(tableName)+"("+axaToAxA(tableName)+" "+axaToaxA(tableName)+");"+

                "}\n\n");


        return  buf.toString();
    }
    public static String buildDaoImplStr(List<TableColumn> list,String tableComment,String tableName){
        StringBuffer buf = new StringBuffer();
        buf.append("package com.hydee;\n" +
                "\n" +
                "\n" +
                "import com.hydee.common.beans.message.MessageQuery;\n" +
                "import com.hydee.common.beans.message.event.MessageEnum;\n" +
                "import com.hydee.h3.pos.dataprotocol.beans.promotion."+axaToAxA(tableName)+";\n" +
                "import com.hydee.h3.pos.dataprotocol.dao.promotion."+axaToAxA(tableName)+"Dao;\n" +
                "import com.hydee.h3.pos.datasource.local.session.Session;\n" +
                "import com.hydee.h3.pos.datasource.local.session.SessionFactory;\n" +
                "import com.hydee.h3.pos.datasource.local.session.bean.Page;\n" +
                "import com.hydee.h3.pos.datasource.local.session.bean.ParameterWrapper;\n" +
                "import com.hydee.h3.pos.datasource.local.session.bean.ResultWrapper;\n" +
                "import com.hydee.h3.pos.datasource.local.session.bean.Row;\n" +
                "import org.slf4j.Logger;\n" +
                "import org.slf4j.LoggerFactory;\n" +
                "\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.HashMap;\n" +
                "import java.util.List;" +
                "\n" +
                "/**   \n" +
                " * @ClassName:  "+axaToAxA(tableName)+"DaoImpl   \n" +
                " * @Description: "+tableComment+"("+tableName+")DaoImpl   \n" +
                " * @author: QianTao \n" +
                " * @date:   "+dateStr+"\n" +
                " * @version: V1.0   \n" +
                " */\n"+
//                "@Component\n" +
                "public class "+axaToAxA(tableName)+"DaoImpl implements "+axaToAxA(tableName)+"Dao {\n" +
                "\n" +
                "    private  final String PAGEINDEX = \"pageIndex\";\n" +
                "    private  final String PAGESIZE = \"pageSize\";\n" +
                "    private final  String ORDER_SQL = \"orderByColumn\";\n" +
                "\n" +
                "    Logger log = LoggerFactory.getLogger("+axaToAxA(tableName)+"Dao.class);\n" +
                "    \n" +
                "    Session repository = SessionFactory.getDefaultSession();\n" +
                "\n" +
                "    /**\n" +
                "     * 查询"+tableComment+"列表\n" +
                "     * @param queryCondition 查询条件\n" +
                "     * @return 查询结果对象\n" +
                "     */\n" +
                "    public MessageQuery query"+axaToAxA(tableName)+"ByCondition(HashMap<String, Object> queryCondition) {\n" +
                "        try{\n" +
                "           MessageQuery messageQuery = new MessageQuery();\n" +
                "           if(queryCondition==null||queryCondition.size()==0){\n" +
                "               return MessageQuery.fail(MessageEnum.BIZ_PARAMETER_ERROR);\n" +
//                "             messageQuery.setMessage(MessageEnum.BIZ_PARAMETER_ERROR);\n" +
//                "             return messageQuery;\n" +
                "           }\n" +
                "           int pageNum =1;\n" +
                "           int pageSize = 20;\n" +
                "           if(queryCondition.get(PAGESIZE)!=null&&queryCondition.get(PAGEINDEX)!=null){\n" +
                "                pageSize = Integer.parseInt(queryCondition.get(PAGESIZE).toString());\n" +
                "               pageNum = Integer.parseInt(queryCondition.get(PAGEINDEX).toString());\n" +
                "           }\n" +
                "           queryCondition.remove(PAGESIZE);\n" +
                "           queryCondition.remove(PAGEINDEX);\n" +
                "\n" +
                "           String sql = buildQuerySql(queryCondition,1);\n" +
                "\n" +
                "           List<"+axaToAxA(tableName)+"> "+axaToaxA(tableName)+"List = new ArrayList<>();\n" +
                "\n" +
                "           ParameterWrapper parameterWrapper = new ParameterWrapper();\n" +
                "           parameterWrapper.setStmt(sql);\n" +
                "           parameterWrapper.setPage(new Page(pageNum,pageSize));\n" +
                "           parameterWrapper.addParameters(buildParam(queryCondition));\n" +
                "           ResultWrapper resultWrapper = repository.select(parameterWrapper);\n" +
                "           for (Row row :resultWrapper.getRows()){\n" +
                "               "+axaToAxA(tableName)+" "+axaToaxA(tableName)+" =convertRowTo"+axaToAxA(tableName)+"(row);\n" +
                "               "+axaToaxA(tableName)+"List.add("+axaToaxA(tableName)+");\n" +
                "           }\n" +
//                "        messageQuery.setTotalCount((int)resultWrapper.getTotalCount());\n" +
//                "        messageQuery.setPageSize(pageSize);\n" +
//                "        messageQuery.setPageIndex(pageNum);\n" +
//                "        messageQuery.setMessage(MessageEnum.BIZ_QUERY_SUCCESS);\n" +
//                "        messageQuery.setBizSuccess(true);\n" +
//                "        messageQuery.setDataList("+axaToaxA(tableName)+"List);\n" +
                "           return MessageQuery.success(MessageEnum.BIZ_QUERY_SUCCESS,"+axaToaxA(tableName)+"List).pageIndex(pageNum)\n" +
                "                              .pagePageSize(pageSize).totalCount(resultWrapper.getTotalCount());\n" +
                "         }catch(Exception e){\n" +
                "           return null;\n" +
                "         }\n" +
                "     }\n" +
                "\n" +
                "\n" +
                "    /**\n" +
                "     * 查询"+tableComment+"列表\n" +
                "     * @param queryCondition\n" +
                "     * @return\n" +
                "     */\n" +
                "    public List<"+axaToAxA(tableName)+"> get"+axaToAxA(tableName)+"List(HashMap<String, Object> queryCondition){\n" +
                "        try{\n" +
                "           List<"+axaToAxA(tableName)+"> "+axaToaxA(tableName)+"List = new ArrayList<>();\n" +
                "           if(queryCondition==null||queryCondition.size()==0){\n" +
                "               return "+axaToaxA(tableName)+"List;\n" +
                "           }\n" +
                "           queryCondition.remove(PAGESIZE);\n" +
                "           queryCondition.remove(PAGEINDEX);\n" +
                "\n" +
                "           String sql = buildQuerySql(queryCondition,1);\n" +
                "\n" +
                "\n" +
                "           ParameterWrapper parameterWrapper = new ParameterWrapper();\n" +
                "           parameterWrapper.setStmt(sql);\n" +
                "            parameterWrapper.addParameters(buildParam(queryCondition));\n" +
                "            ResultWrapper resultWrapper = repository.select(parameterWrapper);\n" +
                "           for (Row row :resultWrapper.getRows()){\n" +
                "               "+axaToAxA(tableName)+" "+axaToaxA(tableName)+" =convertRowTo"+axaToAxA(tableName)+"(row);\n" +
                "              "+axaToaxA(tableName)+"List.add("+axaToaxA(tableName)+");\n" +
                "           }\n" +
                "           return "+axaToaxA(tableName)+"List;\n" +
                "         }catch(Exception e){\n" +
                "           return null;\n" +
                "         }\n" +
                "    }\n\n"+
                "    /**\n" +
                "     * 查询"+tableComment+"数量\n" +
                "     * @param queryCondition\n" +
                "     * @return\n" +
                "     */\n" +
                "    public long query"+axaToAxA(tableName)+"ByConditionCount(HashMap<String, Object> queryCondition){\n" +
                "        try{\n" +
                "           long count = 0;\n" +
                "           if(queryCondition==null||queryCondition.size()==0){\n" +
                "                return 0;\n" +
                "            }\n" +
                "            queryCondition.remove(PAGESIZE);\n" +
                "            queryCondition.remove(PAGEINDEX);\n" +
                "\n" +
                "            String sql = buildQuerySql(queryCondition,2);\n" +
                "\n" +
                "\n" +
                "            ParameterWrapper parameterWrapper = new ParameterWrapper();\n" +
                "            parameterWrapper.setStmt(sql);\n" +
                "            parameterWrapper.addParameters(buildParam(queryCondition));\n" +
                "            ResultWrapper resultWrapper = repository.select(parameterWrapper);\n" +
                "            for (Row row :resultWrapper.getRows()){\n" +
                "               count = Long.parseLong(row.getColumn(0).toString());\n" +
                "            }\n" +
                "            return count;\n" +
                "         }catch(Exception e){\n" +
                "           return 0;\n" +
                "         }\n" +
                "    }\n" +
                "\n\n" +

//                "    /**\n" +
//                "     * 生成添加数据库操作对象\n" +
//                "     * @param "+axaToaxA(tableName)+"\n" +
//                "     * @return\n" +
//                "     */\n" +
//                "    public ParameterWrapper buildInsertParameterWrapperBy"+axaToAxA(tableName)+"("+axaToAxA(tableName)+" "+axaToaxA(tableName)+") {\n" +
//                "        ParameterWrapper parameterWrapper = new ParameterWrapper();\n" +
////                "        Timestamp d = new Timestamp(System.currentTimeMillis());\n" +
////                "        if("+axaToaxA(tableName)+".getCreateTime()==null) {\n" +
////                "            "+axaToaxA(tableName)+".setCreateTime(d);\n" +
////                "        }\n" +
////                "        if("+axaToaxA(tableName)+".getLastUpdateTime()==null) {\n" +
////                "            "+axaToaxA(tableName)+".setLastUpdateTime(d);\n" +
////                "        }\n"+
//                "        String sql = \"insert into "+tableName+" (" );
//        for (TableColumn tab : list){
//            if(tab.getColumnValue().equals("id")) continue;
//            buf.append(tab.getColumnValue()).append(",");
////            buf1.append(axaToaxA(tableName)).append(".get").append(axaToAxA(tab.getColumnValue())).append("(),");
//        }
//        buf.setLength(buf.length()-1);
//
//        buf.append(") VALUES (");
////                "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" +
//        for (TableColumn tab : list){
//            if(tab.getColumnValue().equals("id")) continue;
//            buf.append("?,");
//        }
//        buf.setLength(buf.length()-1);
//        buf.append("" +
//                ")\";\n" +
//                "        parameterWrapper.setStmt(sql);\n" +
//                "        parameterWrapper.addParameters(");
//        for (TableColumn tab : list){
//            if(tab.getColumnValue().equals("id")) continue;
//            buf.append(axaToaxA(tableName)).append(".get").append(axaToAxA(tab.getColumnValue())).append("(),");
//        }
//        buf.setLength(buf.length()-1);
//        buf.append(");\n" +
//                "        return parameterWrapper;\n" +
//                "    }\n" +
                "    /**\n" +
                "     * 添加"+tableComment+"记录\n" +
                "     * @param "+axaToaxA(tableName)+"\n" +
                "     * @return\n" +
                "     */\n" +
                "    public long addBy"+axaToAxA(tableName)+"("+axaToAxA(tableName)+" "+axaToaxA(tableName)+") {\n" +
                "       try{\n" +
//                "        Timestamp d = new Timestamp(System.currentTimeMillis());\n" +
//                "        if("+axaToaxA(tableName)+".getCreateTime()==null) {\n" +
//                "            "+axaToaxA(tableName)+".setCreateTime(d);\n" +
//                "        }\n" +
//                "        if("+axaToaxA(tableName)+".getLastUpdateTime()==null) {\n" +
//                "            "+axaToaxA(tableName)+".setLastUpdateTime(d);\n" +
//                "        }\n" +
                "\n" +
                "           return repository.insert("+axaToaxA(tableName)+");\n" +
                "         }catch(Exception e){\n" +
                "           return 0;\n" +
                "         }\n" +
                "    }\n"+
                "\n\n"+
                "    /*--------------- 以下是私有方法 ---------------*/\n" +
                "\n" +
                "    /**\n" +
                "     *\n" +
                "     * @param queryCondition\n" +
                "     * @param type 1查询全部  2查询数量\n" +
                "     * @return\n" +
                "     */\n" +
                "    private String buildQuerySql(HashMap<String, Object> queryCondition,int type){\n" +
                "        List<String> paramList = getMapKeyToList(queryCondition);\n" +
                "\n" +
                "        String orderSql = \"\";\n" +
                "\n" +
                "        if(queryCondition.get(ORDER_SQL)!=null){\n" +
                "            orderSql = \" \"+queryCondition.get(ORDER_SQL).toString();\n" +
                "        }\n" +
                "        queryCondition.remove(ORDER_SQL);\n" +
                "        String querySql = \" count(*) \";\n" +
                "        if(type==1){\n" +
                "            querySql = \" " );
        for (TableColumn tab :list){
            buf.append(tab.getColumnValue()).append(",");
        }
        buf.setLength(buf.length()-1);

        buf.append("\";\n        }\n" +
                "        String sql =\"select \"+querySql+\" from "+tableName+" \" ;\n" +
                "        String whereSql = buildQueryWhereSql(paramList);\n" +
                "        if(whereSql.length()>0){\n" +
                "            sql = sql + \" where \" + whereSql ;\n" +
                "        }\n" +
                "\n" +
                "        return sql;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 生成查询条件中的查询参数\n" +
                "     * @param queryCondition 查询条件\n" +
                "     * @return 参数数组\n" +
                "     */\n" +
                "    private Object[] buildParam(HashMap<String, Object> queryCondition){\n" +
                "        Object[] objs = new Object[queryCondition.size()];\n" +
                "        int index = 0;\n" +
                "        for (String key:queryCondition.keySet()){\n" +
                "            objs[index] = queryCondition.get(key);\n" +
                "            index++;\n" +
                "        }\n" +
                "        return  objs;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 获取Map中的Key值\n" +
                "     * @param queryCondition 查询条件\n" +
                "     * @return 查询KEY\n" +
                "     */\n" +
                "    private List<String> getMapKeyToList(HashMap<String, Object> queryCondition){\n" +
                "        List<String> paramList = new ArrayList<>();\n" +
                "        for (String key:queryCondition.keySet()){\n" +
                "            paramList.add(key);\n" +
                "        }\n" +
                "        return  paramList;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 根据查询条件构建查询条件sql\n" +
                "     * @param paramList 查询条件\n" +
                "     * @return 查询条件Sql\n" +
                "     */\n" +
                "    private String buildQueryWhereSql(List<String> paramList){\n" +
                "        StringBuffer buf = new StringBuffer();\n" +
                "        String whereSql = \"\";\n" +
                "\n" +
                "        for (String str :paramList){\n" );
        int index = 0;
        for(TableColumn tab :list){
            if(index ==0||"String".equals(tab.getColumnType())||"long".equals(tab.getColumnType())||"int".equals(tab.getColumnType())){
                if(index ==0) {
                    buf.append("            if(\"").append(axaToaxA(tab.getColumnValue())).append("\".equals(str)){\n");
                }else {
                    buf.append("else if(\"").append(axaToaxA(tab.getColumnValue())).append("\".equals(str)){\n");
                }
                buf.append("                buf.append(\"").append(tab.getColumnValue()).append(" = ? AND \");\n");
                buf.append("            }");
                index++;
            }
        }
        buf.append("        \n        }\n"+
                "        whereSql = buf.toString();\n" +
                "        if(whereSql.endsWith(\"AND \")){\n" +
                "\n" +
                "            whereSql = whereSql.substring(0,whereSql.length()-\"AND \".length());\n" +
                "        }\n" +
                "\n" +
                "        return whereSql;\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 转换查询结果为"+tableComment+"对象\n" +
                "     * @param row 查询结果\n" +
                "     * @return "+tableComment+"对象\n" +
                "     */\n" +
                "    private "+axaToAxA(tableName)+" convertRowTo"+axaToAxA(tableName)+"(Row row){\n" +
                "        "+axaToAxA(tableName)+" "+axaToaxA(tableName)+" = new "+axaToAxA(tableName)+"();\n" +
                "\n" );
        for (TableColumn tab : list){
            String ctype = axaToAxA(tab.getColumnType());
            if(tab.getColumnType().startsWith("D")||tab.getColumnType().startsWith("S")||tab.getColumnType().startsWith("T")){
                ctype = tab.getColumnType();
            }
            String defult = "0";
            if(tab.getColumnType().startsWith("D")||tab.getColumnType().startsWith("T")){
                defult = "null";
            }else if(tab.getColumnType().startsWith("S")){
                defult = "\"\"";
            }
//            custIntegralChange.setCgCode(row.getObject("cg_code")==null?0:row.getInt("cg_code"));
            buf.append("        ").append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(row.getObject(\"").append(tab.getColumnValue()).append("\")==null?").append(defult).append(":row.get").append(ctype).append("(\"").append(tab.getColumnValue()).append("\"));\n");
        }
        buf.append("\n\n        return "+axaToaxA(tableName)+";\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "}\n");
        return buf.toString();
    }
    public static String buildPosServiceStr(List<TableColumn> list,String tableComment,String tableName){
        StringBuffer buf = new StringBuffer();
        buf.append("package com.hydee.h3;\n" +
                "\n" +
                "import com.hydee.common.beans.message.MessageQuery;\n" +
                "import com.hydee.common.beans.message.MessageData;\n" +
                "import com.hydee.h3.pos.dataprotocol.beans.promotion."+axaToAxA(tableName)+";\n" +
                "\n" +
                "import java.util.HashMap;\n" +
                "import java.util.List;\n" +
                "\n" +
                "/**   \n" +
                " * @ClassName:  "+axaToAxA(tableName)+"Service   \n" +
                " * @Description: "+tableComment+"("+tableName+")Service   \n" +
                " * @author: QianTao \n" +
                " * @date:   "+dateStr+"\n" +
                " * @version: V1.0   \n" +
                " */\n"+
                "public interface "+axaToAxA(tableName)+"Service {\n" +
                "    /**\n" +
                "     *\n" +
                "     * @param queryCondition 查询条件 包含<br>\n");
        for (TableColumn tab :list){
            if("String".equals(tab.getColumnType())||"long".equals(tab.getColumnType())||"int".equals(tab.getColumnType())){
                buf.append("     * ").append(axaToaxA(tab.getColumnValue())).append(" ").append(tab.getColumnName()).append("<br>\n");
            }
        }
        buf.append("     * pageIndex 第几页<br>\n" +
                "     * pageSize 每页大小<br>\n" +
                "     * @return\n" +
                "     */\n" +
                "    MessageQuery query"+axaToAxA(tableName)+"ByCondition(HashMap<String,Object> queryCondition);\n" +
                "\n" +
                "    /**\n" +
                "     *\n" +
                "     * @param queryCondition 查询条件 包含<br>\n");
        for (TableColumn tab :list){
            buf.append("     * ").append(axaToaxA(tab.getColumnValue())).append(" ").append(tab.getColumnName()).append("<br>\n");
        }
        buf.append("     * @return\n" +
                "     */\n" +
                "    List<"+axaToAxA(tableName)+"> get"+axaToAxA(tableName)+"List(HashMap<String,Object> queryCondition);\n" +
                "\n" +
                "    /**\n" +
                "     * 查询记录条数\n" +
                "     * @param queryCondition\n" +
                "     * @return\n" +
                "     */\n" +
                "    long query"+axaToAxA(tableName)+"ByConditionCount(HashMap<String, Object> queryCondition);\n" +
                "\n" +
//                "    /**\n" +
//                "     * 添加"+tableComment+"记录数据库操作对象\n" +
//                "     * @param "+axaToaxA(tableName)+"\n" +
//                "     * @return\n" +
//                "     */\n" +
//                "    ParameterWrapper buildInsertParameterWrapperBy"+axaToAxA(tableName)+"("+axaToAxA(tableName)+" "+axaToaxA(tableName)+");\n" +
//                "\n" +
//                "    /**\n" +
//                "     * 添加"+tableComment+"记录数据库操作对象\n" +
//                "     * @param conditionMap\n" +
//                "     * @return\n" +
//                "     */\n" +
//                "    ParameterWrapper buildInsertParameterWrapperByMap(HashMap<String, Object> conditionMap);\n" +

                "    /**\n" +
                "     * 添加"+tableComment+"记录\n" +
                "     * @param "+axaToaxA(tableName)+"\n" +
                "     * @return\n" +
                "     */\n" +
                "    MessageData addBy"+axaToAxA(tableName)+"("+axaToAxA(tableName)+" "+axaToaxA(tableName)+");"+

                "}\n\n");


        return  buf.toString();
    }
    public static String buildPosServiceImplStr(List<TableColumn> list,String tableComment,String tableName){
        StringBuffer buf = new StringBuffer();
        buf.append("package com.hydee.h3;\n" +
                "\n" +
                "import com.hydee.common.beans.message.MessageData;\n" +
                "import com.hydee.common.beans.message.MessageQuery;\n" +
                "import com.hydee.common.beans.message.event.MessageEnum;\n" +
                "import com.hydee.common.util.ConvertUtil;\n" +
                "import com.hydee.h3.pos.DaoFactory;\n" +
                "import com.hydee.h3.pos.dataprotocol.beans.promotion."+axaToAxA(tableName)+";\n" +
                "import com.hydee.h3.pos.dataprotocol.dao.promotion."+axaToAxA(tableName)+"Dao;\n" +
                "import com.hydee.h3.pos.service.orderinfo."+axaToAxA(tableName)+"Service;\n" +
                "import org.slf4j.Logger;\n" +
                "import org.slf4j.LoggerFactory;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "\n" +
                "import java.sql.Timestamp;\n" +
                "import java.util.HashMap;\n" +
                "import java.util.List;\n" +
                "\n" +
                "/**   \n" +
                " * @ClassName:  "+axaToAxA(tableName)+"ServiceImpl   \n" +
                " * @Description: "+tableComment+"("+tableName+")ServiceImpl   \n" +
                " * @author: QianTao \n" +
                " * @date:   "+dateStr+"\n" +
                " * @version: V1.0   \n" +
                " */\n"+
                "@Service\n" +
                "public class "+axaToAxA(tableName)+"ServiceImpl implements "+axaToAxA(tableName)+"Service {\n" +
                "\n" +
                "     private Logger log = LoggerFactory.getLogger("+axaToAxA(tableName)+"ServiceImpl.class);\n" +
                "\n" +
                "    \n" +
                "    private "+axaToAxA(tableName)+"Dao "+axaToaxA(tableName)+"Dao = DaoFactory.getDao("+axaToAxA(tableName)+"Dao.class);\n" +
                "    @Override\n" +
                "    public MessageQuery query"+axaToAxA(tableName)+"ByCondition(HashMap<String, Object> queryCondition) {\n" +
                "        if(queryCondition==null){\n" +
                "            return MessageQuery.fail(MessageEnum.BIZ_PARAMETER_ERROR);\n" +
//                "            messageQuery.setBizSuccess(false);\n" +
//                "            messageQuery.setMessage(MessageEnum.BIZ_PARAMETER_ERROR);\n" +
//                "            return messageQuery;\n" +
                "        }\n" +
                "        MessageQuery messageQuery = "+axaToaxA(tableName)+"Dao.query"+axaToAxA(tableName)+"ByCondition(queryCondition);\n" +
                "\n" +
                "        return messageQuery;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public List<"+axaToAxA(tableName)+"> get"+axaToAxA(tableName)+"List(HashMap<String, Object> queryCondition) {\n" +
                "        if(!queryCondition.containsKey(\"cgCode\")){\n" +
                "            return null;\n" +
                "        }\n" +
                "        List<"+axaToAxA(tableName)+"> "+axaToaxA(tableName)+"List = "+axaToaxA(tableName)+"Dao.get"+axaToAxA(tableName)+"List(queryCondition);\n" +
                "        return "+axaToaxA(tableName)+"List;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public long query"+axaToAxA(tableName)+"ByConditionCount(HashMap<String,Object> queryCondition){\n" +
                "        long count  = "+axaToaxA(tableName)+"Dao.query"+axaToAxA(tableName)+"ByConditionCount(queryCondition);\n" +
                "        return count;\n" +
                "    }\n" +
//                "\n" +
//                "    @Override\n" +
//                "    public ParameterWrapper buildInsertParameterWrapperBy"+axaToAxA(tableName)+"("+axaToAxA(tableName)+" "+axaToaxA(tableName)+") {\n" +
//                "        if("+axaToaxA(tableName)+"==null) return null;\n" +
//                "        return "+axaToaxA(tableName)+"Dao.buildInsertParameterWrapperBy"+axaToAxA(tableName)+"("+axaToaxA(tableName)+");\n" +
//                "    }\n" +
//                "\n" +
//                "    @Override\n" +
//                "    public ParameterWrapper buildInsertParameterWrapperByMap(HashMap<String, Object> conditionMap) {\n" +
//                "        if(conditionMap==null||conditionMap.size()<1) return null;\n" +
//                "        "+axaToAxA(tableName)+" "+axaToaxA(tableName)+" =this.exchangeMapTo"+axaToAxA(tableName)+"(conditionMap);\n" +
//                "        if("+axaToaxA(tableName)+"==null) return null;\n" +
//                "        return "+axaToaxA(tableName)+"Dao.buildInsertParameterWrapperBy"+axaToAxA(tableName)+"("+axaToaxA(tableName)+");\n" +
//                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public MessageData addBy"+axaToAxA(tableName)+"("+axaToAxA(tableName)+" "+axaToaxA(tableName)+") {\n" +
                "        if("+axaToaxA(tableName)+"==null || "+axaToaxA(tableName)+".getCgCode()<1){\n" +
                "            return MessageData.fail(MessageEnum.BIZ_PARAMETER_ERROR,-1);\n" +
                "        }\n" +
                "        long rs = "+axaToaxA(tableName)+"Dao.addBy"+axaToAxA(tableName)+"("+axaToaxA(tableName)+");\n" +
                "\n" +
                "        return MessageData.success(MessageEnum.BIZ_MODIFY_SUCCESS,rs);\n" +
                "    }"+
                "\n" +
                "\n" +
                "    /**\n" +
                "     * 转换map对象为"+tableComment+"对象\n" +
                "     * @param conditionMap\n" +
                "     * @return\n" +
                "     */\n" +
                "    private "+axaToAxA(tableName)+" exchangeMapTo"+axaToAxA(tableName)+"(HashMap<String,Object> conditionMap){\n" +
                "        "+axaToAxA(tableName)+" "+axaToaxA(tableName)+" = new "+axaToAxA(tableName)+"();\n" +
                "        if(conditionMap.get(\"cgCode\")==null){\n" +
                "            log.error(\"参数不对\");\n" +
                "            return null;\n" +
                "        }\n" +
                "        Timestamp d = new Timestamp(System.currentTimeMillis());\n" +
                "        try {\n");
        for (TableColumn tab : list) {

            //map转实体类
            if(tab.getColumnType().startsWith("l")) {
                buf.append("            ").append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(ConvertUtil.convertObjectTo").append(axaToAxA(tab.getColumnType())).append("(conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("\"),0L));\n");
            }else if(tab.getColumnType().startsWith("i")) {
                buf.append("            ").append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(ConvertUtil.convertObjectTo").append(axaToAxA(tab.getColumnType())).append("(conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("\"),0));\n");
            }else if(tab.getColumnType().startsWith("S")){
                buf.append("            ").append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(ConvertUtil.convertObjectTo").append(axaToaxA(tab.getColumnType())).append("(conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("\").toString(),\"\"));\n");
            }else if(tab.getColumnType().startsWith("T")){
                //wareLicenceType.setLastUpdateTime(conditionMap.get("LastUpdateTime")==null?null:(Timestamp) conditionMap.get(""));
                buf.append("            ").append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("\")==null?null:(Timestamp)conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("\"));\n");
            }else if(tab.getColumnType().startsWith("Date")){

                buf.append("            long ").append(axaToaxA(tab.getColumnValue())).append("Long = ConvertUtil.convertObjectToLong(conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("Long\"),0);\n");
                buf.append("            ").append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(").append(axaToaxA(tab.getColumnValue())).append("Long==0?null:new Date(").append(axaToaxA(tab.getColumnValue())).append("Long));\n");
//                buf.append("            ").append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(ConvertUtil.convertObjectTo").append(tab.getColumnType()).append("(conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("\"),null));\n");
            }else{
                buf.append("            ").append(axaToaxA(tableName)).append(".set").append(axaToAxA(tab.getColumnValue())).append("(ConvertUtil.convertObjectTo").append(axaToAxA(tab.getColumnType())).append("(conditionMap.get(\"").append(axaToaxA(tab.getColumnValue())).append("\"),0));\n");
            }
        }
        buf.append("\n" +
                "\n" +
                "        }catch (Exception e){\n" +
                "            log.error(\"用户数据转换失败！\");\n" +
                "            return null;\n" +
                "        }\n" +
                "        return "+axaToaxA(tableName)+";\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "}\n");


        return  buf.toString();
    }
    public static String axaToAxA(String name) {
        if (name.startsWith("t_")){
            name = name.substring(2, name.length());
        }
        String tarName = "";
        for (int i = 0; i <name.length(); i++) {
            if(i==0){
                char a = name.charAt(i);
                char b = (char)(a-32);
                tarName += b;
            }else if(name.charAt(i)=='_'){
                char a = name.charAt(i+1);
                char b = (char)(a-32);
                tarName += b;
                i++;
            }else {
                tarName += name.charAt(i);
            }
        }

        return tarName;
    }
    public static String axaToaxA(String name){
        if (name.startsWith("t_")){
            name = name.substring(2, name.length());
        }
        String tarName = "";
        for (int i = 0; i <name.length(); i++) {
            if(name.charAt(i)=='_'){
                char a = name.charAt(i+1);
                char b = (char)(a-32);
                tarName += b;
                i++;
            }else {
                tarName += name.charAt(i);
            }
        }
        return tarName;
    }

    public static String aToa_x_a(String name) {
        String tarName = "";
        for (int i = 0; i <name.length(); i++) {
            if(name.charAt(i)>=65 && name.charAt(i)<=91){
                char a = name.charAt(i);
                char b = (char)(a+32);
                tarName =tarName+ "_"+b;
            }else{
                tarName = tarName+name.charAt(i);
            }
        }
        return tarName;
    }
}
