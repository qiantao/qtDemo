package mybatisDemo.builder;

import demo.BuildEntityStr;
import mybatisDemo.enums.MethodEnum;
import mybatisDemo.entity.TableInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/07/18 10:38
 * @version: V1.0
 */
public class MapperBuillder {
    private static String dateStr = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

    public static String buildMapper(TableInfo tableInfo, Map<String, String> hasMethod){
        if(hasMethod == null) hasMethod = new HashMap<>();

        String tableName = tableInfo.getTableName();
        String mapperPath=tableInfo.getMapperPath();
        String entityPath = tableInfo.getEntityPath()+"."+BuildEntityStr.axaToAxA(tableName);



        StringBuffer buf = new StringBuffer();


        buf.append("package ").append(mapperPath).append(";\n");
        buf.append("\n");
        buf.append("import ").append(entityPath).append(";\n");
        buf.append("import org.apache.ibatis.annotations.Param;;\n");
        buf.append("import org.apache.ibatis.annotations.Mapper;\n\n");
        buf.append("import java.util.List;\n\n\n");

        buf.append("/**\n" +
                " * @ClassName:  ").append(BuildEntityStr.axaToAxA(tableName)).append("Dao\n" +
                " * @Description: ").append(BuildEntityStr.axaToAxA(tableName)).append("Dao\n" +
                " * @author: QianTao\n" +
                " * @date:  ").append(dateStr).append("\n" +
                " * @version: V1.0\n" +
                " */\n");
        buf.append("@Mapper\n");
        buf.append("public interface ").append(BuildEntityStr.axaToAxA(tableName)).append("Dao {\n");

        if(!hasMethod.containsKey(MethodEnum.SELECT.getValue())||"true".equals(hasMethod.get(MethodEnum.SELECT.getValue()))) {
            buf.append("     /**\n" +
                    "     * 根据实体进行查询多个\n" +
                    "     * @param ").append(BuildEntityStr.axaToaxA(tableName)).append(" 查询实体\n" +
                    "     * @return\n" +
                    "     */\n");
            buf.append("    List<").append(BuildEntityStr.axaToAxA(tableName)).append("> ").append(MethodEnum.SELECT.getValue()).append("(").append(BuildEntityStr.axaToAxA(tableName)).append(" ").append(BuildEntityStr.axaToaxA(tableName)).append(");\n");
            buf.append("\n");
        }
        if(!hasMethod.containsKey(MethodEnum.SELECTONE.getValue())||"true".equals(hasMethod.get(MethodEnum.SELECTONE.getValue()))) {
            buf.append("     /**\n" +
                    "     * 根据实体进行查询一个\n" +
                    "     * @param ").append(BuildEntityStr.axaToaxA(tableName)).append(" 查询实体\n" +
                    "     * @return\n" +
                    "     */\n");
            buf.append("    ").append(BuildEntityStr.axaToAxA(tableName)).append(" ").append(MethodEnum.SELECTONE.getValue()).append("(").append(BuildEntityStr.axaToAxA(tableName)).append(" ").append(BuildEntityStr.axaToaxA(tableName)).append(");\n");
            buf.append("\n");
        }
//        if(!hasMethod.containsKey(MethodEnum.UPDATE.getValue())||"true".equals(hasMethod.get(MethodEnum.UPDATE.getValue()))) {
//            buf.append("    /**\n" +
//                    "     * 更新方法\n" +
//                    "     * @param ").append(BuildEntityStr.axaToaxA(tableName)).append("\n" +
//                    "     * @return\n" +
//                    "     */\n");
//            buf.append("    int ").append(MethodEnum.UPDATE.getValue()).append("(").append(BuildEntityStr.axaToAxA(tableName)).append(" ").append(BuildEntityStr.axaToaxA(tableName)).append(");\n");
//            buf.append("\n");
//
//        }

//        if(!hasMethod.containsKey(MethodEnum.UPDATELIST.getValue())||"true".equals(hasMethod.get(MethodEnum.UPDATELIST.getValue()))) {
//            buf.append("    /**\n" +
//                    "     * 更新方法\n" +
//                    "     * @param ").append(BuildEntityStr.axaToaxA(tableName)).append("List\n" +
//                    "     * @return\n" +
//                    "     */\n");
//            buf.append("    int ").append(MethodEnum.UPDATELIST.getValue()).append("(List<").append(BuildEntityStr.axaToAxA(tableName)).append("> ").append(BuildEntityStr.axaToaxA(tableName)).append("List);\n");
//            buf.append("\n");
//
//        }

        if(!hasMethod.containsKey(MethodEnum.UPDATE_SELECTIVE.getValue())||"true".equals(hasMethod.get(MethodEnum.UPDATE_SELECTIVE.getValue()))) {
            buf.append("    /**\n" +
                    "     * 更新字段不为空的数据\n" +
                    "     * @param ").append(BuildEntityStr.axaToaxA(tableName)).append("\n" +
                    "     * @return\n" +
                    "     */\n");
            buf.append("    int ").append(MethodEnum.UPDATE_SELECTIVE.getValue()).append("(").append(BuildEntityStr.axaToAxA(tableName)).append(" ").append(BuildEntityStr.axaToaxA(tableName)).append(");\n");
            buf.append("\n");
        }

//        if(!hasMethod.containsKey(MethodEnum.INSERT_METHOD.getValue())||"true".equals(hasMethod.get(MethodEnum.INSERT_METHOD.getValue()))) {
//            buf.append("    /**\n" +
//                    "     * 新增数据\n" +
//                    "     * @param ").append(BuildEntityStr.axaToaxA(tableName)).append("\n" +
//                    "     * @return\n" +
//                    "     */\n");
//            buf.append("    int ").append(MethodEnum.INSERT_METHOD.getValue()).append("(").append(BuildEntityStr.axaToAxA(tableName)).append(" ").append(BuildEntityStr.axaToaxA(tableName)).append(");\n");
//            buf.append("\n");
//
//        }
//        if(!hasMethod.containsKey(MethodEnum.INSERT_LIST.getValue())||"true".equals(hasMethod.get(MethodEnum.INSERT_LIST.getValue()))) {
//            buf.append("/**\n");
//            buf.append("     * 新增数据集合\n");
//            buf.append("     * @param ").append(BuildEntityStr.axaToaxA(tableName)).append("List\n");
//            buf.append("     * @return\n");
//            buf.append("     */\n");
//            buf.append("    int insertList(@Param(\"").append(BuildEntityStr.axaToaxA(tableName)).append("List\") List<").append(BuildEntityStr.axaToAxA(tableName)).append("> ").append(BuildEntityStr.axaToaxA(tableName)).append("List);\n");
//            buf.append("\n");
//        }

        if(!hasMethod.containsKey(MethodEnum.INSERT_SELECTIVE.getValue())||"true".equals(hasMethod.get(MethodEnum.INSERT_SELECTIVE.getValue()))) {
            buf.append("    /**\n" +
                    "     * 新增字段不为空的数据\n" +
                    "     * @param ").append(BuildEntityStr.axaToaxA(tableName)).append("\n" +
                    "     * @return\n" +
                    "     */\n");
            buf.append("    int ").append(MethodEnum.INSERT_SELECTIVE.getValue()).append("(").append(BuildEntityStr.axaToAxA(tableName)).append(" ").append(BuildEntityStr.axaToaxA(tableName)).append(");\n");
            buf.append("\n");
        }
        if(!hasMethod.containsKey(MethodEnum.INSERT_SELECTIVE_LIST.getValue())||"true".equals(hasMethod.get(MethodEnum.INSERT_SELECTIVE_LIST.getValue()))) {
            buf.append("    /**\n" +
                    "     * 新增字段不为空的数据\n" +
                    "     * @param ").append(BuildEntityStr.axaToaxA(tableName)).append("List\n" +
                    "     * @return\n" +
                    "     */\n");
            buf.append("    int ").append(MethodEnum.INSERT_SELECTIVE_LIST.getValue()).append("(List<").append(BuildEntityStr.axaToAxA(tableName)).append("> ").append(BuildEntityStr.axaToaxA(tableName)).append("List);\n");
            buf.append("\n");
        }

//        if(!hasMethod.containsKey(MethodEnum.UPDATE_SELECTIVE_LIST.getValue())||"true".equals(hasMethod.get(MethodEnum.UPDATE_SELECTIVE_LIST.getValue()))) {
//            buf.append("    /**\n" +
//                    "     * 新增字段不为空的数据集合\n" +
//                    "     * @param ").append(BuildEntityStr.axaToaxA(tableName)).append("List\n" +
//                    "     * @return\n" +
//                    "     */\n");
//            buf.append("    int ").append(MethodEnum.UPDATE_SELECTIVE_LIST.getValue()).append("(List<").append(BuildEntityStr.axaToAxA(tableName)).append("> ").append(BuildEntityStr.axaToaxA(tableName)).append("List);\n");
//            buf.append("\n");
//        }

        buf.append("}");


        return buf.toString();
    }
}
