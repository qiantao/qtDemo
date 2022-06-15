package mybatisDemo.builder;

import demo.BuildEntityStr;
import mybatisDemo.entity.ColumnInfo;
import mybatisDemo.enums.JDBCTypeEnum;
import mybatisDemo.entity.TableInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:
 * @Description: @DPTable生成
 * @author: QianTao
 * @date: 2019/07/18 15:02
 * @version: V1.0
 */
public class EntityBuilder {
    private static String dateStr = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
    public static String buildEntity(TableInfo tableInfo, Map<String, String> map){
        String tableName = tableInfo.getTableName();
        List<ColumnInfo> columnInfoList =tableInfo.getColumnInfoList();
        String bsaeMap = tableInfo.getBsaeMap();
        String baseSql=tableInfo.getBaseSql();
        String mapperPath=tableInfo.getMapperPath()+"."+ BuildEntityStr.axaToAxA(tableName)+"Mapper";
        String entityPath = tableInfo.getEntityPath()+"."+BuildEntityStr.axaToAxA(tableName);
        String tableComment=tableInfo.getTableComent();

        StringBuffer buf = new StringBuffer();
        String className = "";
        buf.append("package ").append(tableInfo.getEntityPath()).append(";\n" +
                "\n" +
                "\n" +
                "import com.hydee.h3.basedata.meta.annotation.DPColumn;\n" +
                "import com.hydee.h3.basedata.meta.annotation.DPEntityReference;\n" +
                "import com.hydee.h3.basedata.meta.annotation.DPTable;\n" +
                "import com.hydee.h3.basedata.enums.ColumnDefineTypeEnum;\n"+
                "\n" +
                "import java.sql.Timestamp;\n"+
                "import java.io.Serializable;\n"+
                "import java.util.Date;\n" +
                "\n" +
                "/**   \n" +
                " * @ClassName:  "+BuildEntityStr.axaToAxA(tableName)+"   \n" +
                " * @Description: "+tableComment+"("+tableName+")实体类   \n" +
                " * @author: QianTao \n" +
                " * @date:   "+dateStr+"\n" +
                " * @version: V1.0   \n" +
                " */\n" +
                "@DPTable(name = \"" + tableName + "\",displayName = \"" + tableComment + "\", entityReference = {\n" +
                "        @DPEntityReference(referenceTable = \"t_org_organization_base\", currentColumnName = \"group_id\", referenceColumnName = \"id\", referenceType = RelaTypeEnum.ONE),\n" +
                "        @DPEntityReference(referenceTable = \"t_org_user_base\", currentColumnName = \"create_user\", referenceColumnName = \"id\",relaColumnTitle = \"user_name\", referenceType = RelaTypeEnum.ONE),\n" +
                "        @DPEntityReference(referenceTable = \"t_org_user_base\", currentColumnName = \"modify_user\", referenceColumnName = \"id\",relaColumnTitle = \"user_name\", referenceType = RelaTypeEnum.ONE)\n" +
                "\n" +
                "})\n");
        if (tableName.contains("_")) {
            className = BuildEntityStr.axaToAxA(tableName);
        }

        buf.append("public class " + className + " implements Serializable {\n");
        for (ColumnInfo tab : columnInfoList) {
            buf.append("\n");
            String nullable = "";
            if("NO".equals(tab.getIsNull())){
                nullable = ",nullable = false ";
            }
//            nullable = false,defaultValue = "",length = 0,scale = 0
            String displayName = tab.getColumnComment();
            String columnDefine = "ColumnDefineTypeEnum.TEXT";
            if(displayName.indexOf("(")>0){
                if(displayName.contains("1.是")){
                    columnDefine = "ColumnDefineTypeEnum.OPTIONSET, optionsSetClassName = BooleanEnums.class";
                }else {
                    columnDefine = "ColumnDefineTypeEnum.OPTIONSET, optionsSetClassName = " + BuildEntityStr.axaToAxA(tab.getColumnName()) + "Enum.class";
                }
                displayName = displayName.substring(0, displayName.indexOf("("));
            }
            buf.append("    @DPColumn(name = \"").append(tab.getColumnName()).append("\", displayName = \"").append(displayName).append("\",comment = \"").append(displayName).append("\"");
            if("datetime".equals(tab.getDataType())||"timestamp".equals(tab.getDataType())||"date".equals(tab.getDataType())){
                if("date".equals(tab.getDataType())){
                    buf.append(", columnDefine = ColumnDefineTypeEnum.DATE");
                }else {
                    buf.append(", columnDefine = ColumnDefineTypeEnum.DATE_TIME");
                }
                buf.append(")\n");
                buf.append("    private ").append(JDBCTypeEnum.getJavaTypeByDBType(tab.getDataType())).append(" ").append(BuildEntityStr.axaToaxA(tab.getColumnName())).append(";\n");
                continue;
            }
            buf.append(nullable);
            if(tab.getPrimaryKey()== null|| "".equals(tab.getPrimaryKey())) {
                if(!"null".equals(tab.getColumnDefault())&& tab.getColumnDefault()!=null) {
                    buf.append(",defaultValue=\"").append(tab.getColumnDefault()).append("\"");
                }
            }else{
                buf.append(",primaryKey = true,").append(tab.getPrimaryKey()).append(" ");
            }
            if(!"datetime".equals(tab.getDataType())&&!"timestamp".equals(tab.getDataType())) {
                if("varchar".equals(tab.getDataType())) {
                    buf.append(",length=").append(tab.getCharacterMaximumLength());
                }else {

                    if(!columnDefine.contains("OPTIONSET")) {
                        if("4".equals(tab.getNumScale())) {
                            columnDefine = "ColumnDefineTypeEnum.NUMBER, length = 14 , scale = 2";
                        }else if("6".equals(tab.getNumScale())){
                            columnDefine = "ColumnDefineTypeEnum.MONEY, length = 14 , scale = 4";
                        }else if("0".equals(tab.getNumScale())){
                            columnDefine = "ColumnDefineTypeEnum.NUMBER, length = 11";
                        }else{
                            columnDefine = "ColumnDefineTypeEnum.NUMBER, length = 14 , scale = 2";
                        }
                    }else{
                        buf.append(",length=").append(tab.getNumLenth());
                        if(!"0".equals(tab.getNumScale())){
                            buf.append(",scale = ").append(tab.getNumScale()==null?"0":tab.getNumScale());
                        }
                    }
                }
            }else{
                columnDefine = "ColumnDefineTypeEnum.DATE";
            }

            if(tab.getColumnName().endsWith("_id")||tab.getColumnName().endsWith("_code")||tab.getColumnName().endsWith("_user")){
                columnDefine = "ColumnDefineTypeEnum.RELA";
            }
            if(!tab.getPrimaryKey().contains("AUTOCODE")) {
                buf.append(", columnDefine = ").append(columnDefine);
            }
            buf.append(" )\n");

            String defultValue = "\"\"";
            if(tab.getDataType().equals("int")||tab.getDataType().equals("bigint")||tab.getDataType().equals("double")){
                defultValue = tab.getColumnDefault();
            }
            buf.append("    private ").append(JDBCTypeEnum.getJavaTypeByDBType(tab.getDataType())).append(" ").append(BuildEntityStr.axaToaxA(tab.getColumnName())).append(";\n");

        }
        buf.append("\n");
        buf.append("\n");
        buf.append("\n");

        for (ColumnInfo tab : columnInfoList) {

            buf.append("    /**\n");
            buf.append("    *   ").append(tab.getColumnComment()).append("\n");
//            buf.append("    * @param ").append(axaToaxA(tab.getColumnValue())).append("\n");
            buf.append("    */\n");
            buf.append("    public ").append(JDBCTypeEnum.getJavaTypeByDBType(tab.getDataType())).append(" get").append(BuildEntityStr.axaToAxA(tab.getColumnName())).append(" () {return ").append(BuildEntityStr.axaToaxA(tab.getColumnName())).append("; }\n");
            buf.append("\n");
            buf.append("    /**\n");
//            buf.append("    *").append(tab.getColumnName()).append("\n");
            buf.append("    * @param ").append(BuildEntityStr.axaToaxA(tab.getColumnName())).append("  ").append(tab.getColumnComment()).append("\n");
            buf.append("    */\n");
            buf.append("    public void set").append(BuildEntityStr.axaToAxA(tab.getColumnName())).append(" (").append(JDBCTypeEnum.getJavaTypeByDBType(tab.getDataType())).append(" ").append(BuildEntityStr.axaToaxA(tab.getColumnName())).append("){this.")
                    .append(BuildEntityStr.axaToaxA(tab.getColumnName())).append(" = ").append(BuildEntityStr.axaToaxA(tab.getColumnName())).append(";}\n");
            buf.append("\n");
        }
        buf.append("\n");
        buf.append("\n");
        buf.append("\n");
        buf.append("}");

        return buf.toString();
    }
    public static String buildEntityNoColumn(TableInfo tableInfo){
        String tableName = tableInfo.getTableName();
        List<ColumnInfo> columnInfoList =tableInfo.getColumnInfoList();
        String bsaeMap = tableInfo.getBsaeMap();
        String baseSql=tableInfo.getBaseSql();
        String mapperPath=tableInfo.getMapperPath()+"."+ BuildEntityStr.axaToAxA(tableName)+"Mapper";
        String entityPath = tableInfo.getEntityPath()+"."+BuildEntityStr.axaToAxA(tableName);
        String voPath = tableInfo.getVoPath();
        String tableComment=tableInfo.getTableComent();

        StringBuffer buf = new StringBuffer();
        String className = "";
        buf.append("package ").append(voPath).append(";\n" +
                "\n" +
                "import com.hydee.common.validate.group.Group;\n"+
                "\n" +
                "import javax.validation.constraints.NotNull;\n"+
                "import java.sql.Timestamp;\n"+
                "import java.io.Serializable;\n"+
                "import java.util.Date;\n" +
                "\n" +
                "/**   \n" +
                " * @ClassName:  "+BuildEntityStr.axaToAxA(tableName)+"   \n" +
                " * @Description: "+tableComment+"("+tableName+")实体VO类   \n" +
                " * @author: QianTao \n" +
                " * @date:   "+dateStr+"\n" +
                " * @version: V1.0   \n" +
                " */\n");
//                "@Table(name = \"" + tableName + "\",comment = \"" + tableComment + "\")\n");
        if (tableName.contains("_")) {
            className = BuildEntityStr.axaToAxA(tableName);
        }
        List<String> conList = new ArrayList<>();
        conList.add("create_user");
        conList.add("create_time");
        conList.add("modify_user");
        conList.add("modify_time");
        buf.append("public class " + className + "VO implements Serializable {\n");
        for (ColumnInfo tab : columnInfoList) {
            buf.append("\n");
            if(conList.contains(tab.getColumnName())){
                continue;
            }
            String displayName = tab.getColumnComment();
            if(displayName.indexOf("(")>0){
                displayName = displayName.substring(0,displayName.indexOf("("));
            }
            buf.append("    /**\n" +
                    "     * ").append(tab.getColumnComment()).append("\n" +
                    "     */\n");
            buf.append("    @NotNull(message = \"").append(displayName).append("不允许为空\", groups = {Group.ADD.class})\n");
            if("datetime".equals(tab.getDataType())||"timestamp".equals(tab.getDataType())){
                buf.append("    private ").append(JDBCTypeEnum.getJavaTypeByDBType(tab.getDataType())).append(" ").append(BuildEntityStr.axaToaxA(tab.getColumnName())).append(";\n");
                continue;
            }

            buf.append("    private ").append(JDBCTypeEnum.getJavaTypeByDBType(tab.getDataType())).append(" ").append(BuildEntityStr.axaToaxA(tab.getColumnName())).append(";\n");

        }
        buf.append("\n");
        buf.append("\n");
        buf.append("\n");

        for (ColumnInfo tab : columnInfoList) {
            if(conList.contains(tab.getColumnName())){
                continue;
            }
            buf.append("    /**\n");
            buf.append("    *   ").append(tab.getColumnComment()).append("\n");
//            buf.append("    * @param ").append(axaToaxA(tab.getColumnValue())).append("\n");
            buf.append("    */\n");
            buf.append("    public ").append(JDBCTypeEnum.getJavaTypeByDBType(tab.getDataType())).append(" get").append(BuildEntityStr.axaToAxA(tab.getColumnName())).append(" () {return ").append(BuildEntityStr.axaToaxA(tab.getColumnName())).append("; }\n");
            buf.append("\n");
            buf.append("    /**\n");
//            buf.append("    *").append(tab.getColumnName()).append("\n");
            buf.append("    * @param ").append(BuildEntityStr.axaToaxA(tab.getColumnName())).append("  ").append(tab.getColumnComment()).append("\n");
            buf.append("    */\n");
            buf.append("    public void set").append(BuildEntityStr.axaToAxA(tab.getColumnName())).append(" (").append(JDBCTypeEnum.getJavaTypeByDBType(tab.getDataType())).append(" ").append(BuildEntityStr.axaToaxA(tab.getColumnName())).append("){this.")
                    .append(BuildEntityStr.axaToaxA(tab.getColumnName())).append(" = ").append(BuildEntityStr.axaToaxA(tab.getColumnName())).append(";}\n");
            buf.append("\n");
        }
        buf.append("\n");
        buf.append("\n");
        buf.append("\n");
        buf.append("}");

        return buf.toString();
    }
}
