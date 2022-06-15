package mybatisDemo.builder;

import demo.BuildEntityStr;
import mybatisDemo.entity.ColumnInfo;
import mybatisDemo.enums.JDBCTypeEnum;
import mybatisDemo.entity.TableInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName:
 * @Description: @Table生成类
 * @author: QianTao
 * @date: 2019/07/18 15:02
 * @version: V1.0
 */
public class EntityBuild {
    public static void main(String[] args) {
        for (int i = 0; i < 150; i++) {
            char b = (char)i;
            System.out.println(i + ":" +b);
        }
    }
    private static String dateStr = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
    public static String buildEntity(TableInfo tableInfo){
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
//                "import com.hydee.basedata.base.bean.annotation.Column;\n" +
//                "import com.hydee.basedata.base.bean.annotation.Table;\n" +
//                "import com.hydee.basedata.base.bean.serivce.meta.bean.GenerationType;\n"+
//                "\n" +
                "import java.sql.Timestamp;\n"+
                "import java.io.Serializable;\n"+
                "import java.util.Date;\n" +
                "import lombok.Builder;\n" +
                "import lombok.Data;\n" +
                "/**   \n" +
                " * @ClassName:  "+BuildEntityStr.axaToAxA(tableName)+"   \n" +
                " * @Description: "+tableComment+"("+tableName+")实体类   \n" +
                " * @author: QianTao \n" +
                " * @date:   "+dateStr+"\n" +
                " * @version: V1.0   \n" +
                " */\n" +
                "@Data\n" +
                "@Builder\n" );
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
            buf.append("    /**\n" +
                    "     * ").append(tab.getColumnComment()).append("\n" +
                    "     */\n");
            if("datetime".equals(tab.getDataType())||"timestamp".equals(tab.getDataType())){
                buf.append("    private ").append(JDBCTypeEnum.getJavaTypeByDBType(tab.getDataType())).append(" ").append(BuildEntityStr.axaToaxA(tab.getColumnName())).append(";\n");
                continue;
            }

            String defultValue = "\"\"";
            if(tab.getDataType().equals("int")||tab.getDataType().equals("bigint")||tab.getDataType().equals("double")){
                defultValue = tab.getColumnDefault();
            }
            buf.append("    private ").append(JDBCTypeEnum.getJavaTypeByDBType(tab.getDataType())).append(" ").append(BuildEntityStr.axaToaxA(tab.getColumnName())).append(";\n");

        }
        buf.append("\n");
        buf.append("\n");
        buf.append("\n");

//        for (ColumnInfo tab : columnInfoList) {
//
//            buf.append("    /**\n");
//            buf.append("    *   ").append(tab.getColumnComment()).append("\n");
//            buf.append("    */\n");
//            buf.append("    public ").append(JDBCTypeEnum.getJavaTypeByDBType(tab.getDataType())).append(" get").append(BuildEntityStr.axaToAxA(tab.getColumnName())).append(" () {return ").append(BuildEntityStr.axaToaxA(tab.getColumnName())).append("; }\n");
//            buf.append("\n");
//            buf.append("    /**\n");
//            buf.append("    * @param ").append(BuildEntityStr.axaToaxA(tab.getColumnName())).append("  ").append(tab.getColumnComment()).append("\n");
//            buf.append("    */\n");
//            buf.append("    public void set").append(BuildEntityStr.axaToAxA(tab.getColumnName())).append(" (").append(JDBCTypeEnum.getJavaTypeByDBType(tab.getDataType())).append(" ").append(BuildEntityStr.axaToaxA(tab.getColumnName())).append("){this.")
//                    .append(BuildEntityStr.axaToaxA(tab.getColumnName())).append(" = ").append(BuildEntityStr.axaToaxA(tab.getColumnName())).append(";}\n");
//            buf.append("\n");
//        }
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
        String tableComment=tableInfo.getTableComent();

        StringBuffer buf = new StringBuffer();
        String className = "";
        buf.append("package ").append(entityPath).append(";\n" +
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
                " * @ClassName:  "+BuildEntityStr.axaToAxA(tableName)+"   \n" +
                " * @Description: "+tableComment+"("+tableName+")实体类   \n" +
                " * @author: QianTao \n" +
                " * @date:   "+dateStr+"\n" +
                " * @version: V1.0   \n" +
                " */\n");
//                "@Table(name = \"" + tableName + "\",comment = \"" + tableComment + "\")\n");
        if (tableName.contains("_")) {
            className = BuildEntityStr.axaToAxA(tableName);
        }else{
            className = BuildEntityStr.axaToAxA(tableName);
        }

        buf.append("public class " + className + " implements Serializable {\n");
        for (ColumnInfo tab : columnInfoList) {
            buf.append("\n");
            String nullable = "";
//            if("NO".equals(tab.getIsNull())){
//                nullable = ",nullable = false ";
//            }
//            nullable = false,defaultValue = "",length = 0,scale = 0
//            buf.append("    @Column(name = \"").append(tab.getColumnName()).append("\", comment = \"").append(tab.getColumnComment()).append("\"");
            if("datetime".equals(tab.getDataType())||"timestamp".equals(tab.getDataType())){
//                buf.append(")\n");
                buf.append("    private ").append(JDBCTypeEnum.getJavaTypeByDBType(tab.getDataType())).append(" ").append(BuildEntityStr.axaToaxA(tab.getColumnName())).append(";\n");
                continue;
            }
//            buf.append(nullable);
//            if(!"id".equals(tab.getColumnName())) {
//                buf.append(",defaultValue=\"").append(tab.getColumnDefault()).append("\"");
//            }
//            if(!"datetime".equals(tab.getDataType())&&!"timestamp".equals(tab.getDataType())) {
//                if("varchar".equals(tab.getDataType())) {
//                    buf.append(",length=").append(tab.getCharacterMaximumLength());
//                }else {
//                    buf.append(",length=").append(tab.getNumLenth());
//                    if(!"0".equals(tab.getNumScale())){
//                        buf.append(",scale = ").append(tab.getNumScale());
//                    }
//                }
//            }

//            buf.append(" )\n");

            String defultValue = "\"\"";
            if(tab.getDataType().equals("int")||tab.getDataType().equals("bigint")||tab.getDataType().equals("double")){
                defultValue = tab.getColumnDefault();
            }
            buf.append("    private ").append(JDBCTypeEnum.getJavaTypeByDBType(tab.getDataType())).append(" ").append(BuildEntityStr.axaToaxA(tab.getColumnName())).append(";\n");
//            buf.append("    private ").append(JDBCTypeEnum.getJavaTypeByDBType(tab.getDataType())).append(" ").append(BuildEntityStr.axaToaxA(tab.getColumnName())).append(" = ").append(defultValue).append(";\n");

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
}
