package com.qt.mybatisDemo.builder;

import com.qt.demo.BuildEntityStr;
import com.qt.mybatisDemo.entity.TableInfo;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/09/17 11:42
 * @version: V1.0
 */
public class UtilBuilder {
    private static String dateStr = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

    public static String buildUtil(TableInfo tableInfo, String h3module){

        String tableName = tableInfo.getTableName();
        String utilPath = tableInfo.getUtilPath();
        String managerPath=tableInfo.getManagerPath();
        StringBuffer buf = new StringBuffer();


        buf.append("package ").append(utilPath).append(";\n");
        buf.append("\n");
        buf.append("import com.hydee.h3.common.log.LoggerBuilder;\n");
        buf.append("import org.slf4j.Logger;\n");
        buf.append("import com.hydee.common.util.ConvertUtil;\n");

        buf.append("/**\n" +
                " * @ClassName:  ").append(BuildEntityStr.axaToAxA(h3module)).append("Util\n" +
                " * @Description: ").append(BuildEntityStr.axaToAxA(h3module)).append("Util\n" +
                " * @author: Muyang\n" +
                " * @date:  ").append(dateStr).append("\n" +
                " * @version: V1.0\n" +
                " */\n");
        buf.append("public class ").append(BuildEntityStr.axaToAxA(h3module)).append("Util {\n");
        buf.append("    private Logger log = LoggerBuilder.getLogger(this.getClass());\n");
        buf.append("   //保存当前登录信息\n");
        buf.append("    private static ThreadLocal<LoginSession> sessionThreadLocal = new ThreadLocal<>();");

        buf.append("    public static void setSession(String cgCode,String cpCode,String busiCode,long userId){\n" +
                "        int cg = ConvertUtil.convertStringToInt(cgCode,100);\n" +
                "        int cp = ConvertUtil.convertStringToInt(cpCode,10002);\n" +
                "        int busi = ConvertUtil.convertStringToInt(busiCode,10002);\n" +
                "        LoginSession loginSession = new LoginSession(cg,cp,userId<1?1003:userId,busi);\n" +
                "        sessionThreadLocal.set(loginSession);\n" +
                "    }\n");

        buf.append("\n\n}");


        return buf.toString();
    }
    public static String buildUtilEntity(TableInfo tableInfo,String h3module){
        String utilPath = tableInfo.getUtilPath();
        StringBuffer buf = new StringBuffer();

        buf.append("package ").append(utilPath).append(";\n" +
                "\n" +
                "/**\n" +
                " * @ClassName: LoginSession\n" +
                " * @Description: 登录信息实体\n" +
                " * @author: Muyang\n" +
                " * @date: ").append(dateStr).append("\n" +
                " * @version: V1.0\n" +
                " */\n" +
                "public class LoginSession {\n" +
                "    private int cgCode;\n" +
                "    private int cpCode;\n" +
                "    private long userId;\n" +
                "    private int busiCode;\n" +
                "    public LoginSession(int cgCode, int cpCode, long userId){\n" +
                "        this.cgCode = cgCode;\n" +
                "        this.cpCode = cpCode;\n" +
                "        this.userId = userId;\n" +
                "    }\n" +
                "    public LoginSession(int cgCode, int cpCode, long userId,int busiCode){\n" +
                "        this.cgCode = cgCode;\n" +
                "        this.cpCode = cpCode;\n" +
                "        this.userId = userId;\n" +
                "        this.busiCode=busiCode;\n" +
                "    }\n" +
                "    public int getCgCode() {\n" +
                "        return cgCode;\n" +
                "    }\n" +
                "\n" +
                "    public void setCgCode(int cgCode) {\n" +
                "        this.cgCode = cgCode;\n" +
                "    }\n" +
                "\n" +
                "    public int getCpCode() {\n" +
                "        return cpCode;\n" +
                "    }\n" +
                "\n" +
                "    public void setCpCode(int cpCode) {\n" +
                "        this.cpCode = cpCode;\n" +
                "    }\n" +
                "\n" +
                "    public long getUserId() {\n" +
                "        return userId;\n" +
                "    }\n" +
                "\n" +
                "    public void setUserId(long userId) {\n" +
                "        this.userId = userId;\n" +
                "    }\n" +
                "\n" +
                "    public int getBusiCode() {\n" +
                "        return busiCode;\n" +
                "    }\n" +
                "\n" +
                "    public void setBusiCode(int busiCode) {\n" +
                "        this.busiCode = busiCode;\n" +
                "    }\n" +
                "}\n");

        return buf.toString();
    }

}
