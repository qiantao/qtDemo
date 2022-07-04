package com.qt.mybatisDemo.resource;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/07/23 11:34
 * @version: V1.0
 */
public class SourceEntity implements Serializable {
    private String h3Module;
    private String module;
    List<String> tableNames ;
    private Map<String,Map<String,String>> tableNameHasMethod;

    public String getH3Module() {
        return h3Module;
    }

    public void setH3Module(String h3Module) {
        this.h3Module = h3Module;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Map<String, Map<String, String>> getTableNameHasMethod() {
        return tableNameHasMethod;
    }

    public void setTableNameHasMethod(Map<String, Map<String, String>> tableNameHasMethod) {
        this.tableNameHasMethod = tableNameHasMethod;
    }

    public List<String> getTableNames() {
        return tableNames;
    }

    public void setTableNames(List<String> tableNames) {
        this.tableNames = tableNames;
    }
}
