package com.qt.mybatisDemo.enums;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/07/18 10:51
 * @version: V1.0
 */
public enum JDBCTypeEnum {
    INT("int","INTEGER","Integer"),
    VARCHAR("varchar","VARCHAR","String"),
    BIGINT("bigint","BIGINT","Long"),
    TINYINT("tinyint","TINYINT","Integer"),
    SMALLINT("smallint","SMALLINT","Integer"),
    DOUBLE("double","DOUBLE","Double"),
    DATATIME("datetime","TIMESTAMP","Date"),
    DATE("date","DATE","Date"),
    TIMESTAMP("timestamp","TIMESTAMP","Date"),
    JSON("json","VARCHAR","String"),
    TEXT("text","VARCHAR","String"),
    BLOB("blob","VARCHAR","String"),

    ;



    private String dbType;
    private String jdbcType;
    private String javaType;
    JDBCTypeEnum(String dbType,String jdbcType,String javaType){
        this.dbType = dbType;
        this.jdbcType = jdbcType;
        this.javaType = javaType;
    }

    public static String getJDBCTypeByDBType(String dbType){
        JDBCTypeEnum[] enums = JDBCTypeEnum.values();
        for (int i=0;i<enums.length;i++){
            if(dbType.equals(enums[i].dbType)){
                return enums[i].jdbcType;
            }
        }
        System.out.println(String.format("数据库类型缺少---------%s",dbType));
        return null;
    }
    public static String getJavaTypeByDBType(String dbType){
        JDBCTypeEnum[] enums = JDBCTypeEnum.values();
        for (int i=0;i<enums.length;i++){
            if(dbType.equals(enums[i].dbType)){
                return enums[i].javaType;
            }
        }
        System.out.println(String.format("数据库类型缺少--------!@@-%s",dbType));
        return null;
    }
    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }
}
