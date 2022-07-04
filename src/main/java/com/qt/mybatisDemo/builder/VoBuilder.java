package com.qt.mybatisDemo.builder;

import com.qt.demo.BuildEntityStr;
import com.qt.mybatisDemo.entity.TableInfo;
import com.qt.util.StringUtil;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/09/17 14:39
 * @version: V1.0
 */
public class VoBuilder {
    private static String dateStr = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

    public static String buildVO(TableInfo tableInfo, String module, String h3module){
        String tableName = tableInfo.getTableName();
        String voPath=tableInfo.getVoPath();
        String groupPath = tableInfo.getGroupPath();
        StringBuffer buf = new StringBuffer();
        String mName = StringUtil.isNullTrim(module)?h3module:module;

        buf.append("package ").append(voPath).append(";\n");
        buf.append("\n");
        buf.append("import javax.validation.constraints.NotNull;\n");
        buf.append("import ").append(groupPath).append(".").append(BuildEntityStr.axaToAxA(mName)).append("Group;\n");
        buf.append("import java.sql.Timestamp;\n");
        buf.append("import java.io.Serializable;\n");
        buf.append("import org.springframework.stereotype.Component;\n");

        buf.append("/**\n" +
                " * @ClassName:  ").append(BuildEntityStr.axaToAxA(mName)).append("VO\n" +
                " * @Description: ").append(BuildEntityStr.axaToAxA(mName)).append("VO\n" +
                " * @author: Muyang\n" +
                " * @date:  ").append(dateStr).append("\n" +
                " * @version: V1.0\n" +
                " */\n");
        buf.append("@Component\n");
        buf.append("public class ").append(BuildEntityStr.axaToAxA(mName)).append("VO implements Serializable {\n");
        buf.append("// @NotNull(message = \"不允许为空\", groups = {").append(BuildEntityStr.axaToAxA(mName)).append("Group.ADD").append(BuildEntityStr.axaToAxA(mName)).append(".class})");
        buf.append("\n\n}");


        return buf.toString();
    }
}
