package mybatisDemo.builder;

import demo.BuildEntityStr;
import mybatisDemo.entity.TableInfo;
import util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/09/17 11:56
 * @version: V1.0
 */
public class GroupBuilder {
    private static String dateStr = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

    public static String buildGroup(TableInfo tableInfo,String module,String h3module) {
        String tableName = tableInfo.getTableName();
        String groupPath=tableInfo.getGroupPath();
        String servicePath = tableInfo.getServicePath();
        String mName = StringUtil.isNullTrim(module)?h3module:module;
        StringBuffer buf = new StringBuffer();


        buf.append("package ").append(groupPath).append(";\n");
        buf.append("\n");
        buf.append("import com.hydee.common.validate.group.Group;\n");

        buf.append("/**\n" +
                " * @ClassName:  ").append(BuildEntityStr.axaToAxA(mName)).append("Manager\n" +
                " * @Description: ").append(BuildEntityStr.axaToAxA(mName)).append("Group\n" +
                " * @author: QianTao\n" +
                " * @date:  ").append(dateStr).append("\n" +
                " * @version: V1.0\n" +
                " */\n");
        buf.append("public interface ").append(BuildEntityStr.axaToAxA(mName)).append("Group extends Group  {\n");
        buf.append("     /**\n");
        buf.append("     * 添加\n");
        buf.append("     */\n");
        buf.append("    static interface ADD").append(mName.toUpperCase()).append("{}\n");
        buf.append("    /**\n");
        buf.append("     * 修改\n");
        buf.append("     */\n");
        buf.append("    static interface MOD").append(mName.toUpperCase()).append("{}\n");

        buf.append("\n\n}");


        return buf.toString();
    }
}
