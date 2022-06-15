package mybatisDemo.builder;

import demo.BuildEntityStr;
import mybatisDemo.entity.TableInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/09/06 11:28
 * @version: V1.0
 */
public class ManagerBuilder {
    private static String dateStr = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

    public static String buildManager(TableInfo tableInfo){

        String tableName = tableInfo.getTableName();
        String managerPath=tableInfo.getManagerPath();
        String servicePath = tableInfo.getServicePath();
        String voPath = tableInfo.getVoPath();
        StringBuffer buf = new StringBuffer();


        buf.append("package ").append(managerPath).append(";\n");
        buf.append("\n");
        buf.append("import com.hydee.h3.common.log.LoggerBuilder;\n");
        buf.append("import org.slf4j.Logger;\n");
        buf.append("import org.springframework.stereotype.Component;\n");
        buf.append("import com.hydee.common.beans.message.MessageData;\n");
        buf.append("import com.hydee.common.beans.message.enums.MessageEnum;\n");
        buf.append("import ").append(voPath).append(".").append(BuildEntityStr.axaToAxA(tableName)).append("VO;\n");
        buf.append("/**\n" +
                " * @ClassName:  ").append(BuildEntityStr.axaToAxA(tableName)).append("Manager\n" +
                " * @Description: ").append(BuildEntityStr.axaToAxA(tableName)).append("Manager\n" +
                " * @author: QianTao\n" +
                " * @date:  ").append(dateStr).append("\n" +
                " * @version: V1.0\n" +
                " */\n");
        buf.append("@Component\n");
        buf.append("public class ").append(BuildEntityStr.axaToAxA(tableName)).append("Manager {\n");
        buf.append("    private Logger log = LoggerBuilder.getLogger(this.getClass());\n\n");

        buf.append("    /**\n");
        buf.append("     * 添加\n");
        buf.append("     * @param ").append(BuildEntityStr.axaToaxA(tableName)).append("VO\n");
        buf.append("     * @return\n");
        buf.append("     */\n");
        buf.append("    public MessageData add(").append(BuildEntityStr.axaToAxA(tableName)).append("VO ").append(BuildEntityStr.axaToaxA(tableName)).append("VO) {\n");
        buf.append("\n");
        buf.append("\n");
        buf.append("        return MessageData.success(MessageEnum.BIZ_ADD_SUCCESS);\n");
        buf.append("    }\n\n");

        buf.append("    /**\n");
        buf.append("     * 修改\n");
        buf.append("     * @param ").append(BuildEntityStr.axaToaxA(tableName)).append("VO\n");
        buf.append("     * @return\n");
        buf.append("     */\n");
        buf.append("    public MessageData mod(").append(BuildEntityStr.axaToAxA(tableName)).append("VO ").append(BuildEntityStr.axaToaxA(tableName)).append("VO) {\n");
        buf.append("\n");
        buf.append("\n");
        buf.append("        return MessageData.success(MessageEnum.BIZ_ADD_SUCCESS);\n");
        buf.append("    }\n\n");

        buf.append("    /**\n");
        buf.append("     * 根据ID查询详情\n");
        buf.append("     * @param id \n");
        buf.append("     * @return\n");
        buf.append("     */\n");
        buf.append("    public MessageData detail(Long id) {\n");
        buf.append("\n");
        buf.append("\n");
        buf.append("        return MessageData.success(MessageEnum.BIZ_ADD_SUCCESS);\n");
        buf.append("    }");



        buf.append("\n\n}");


        return buf.toString();
    }
}
