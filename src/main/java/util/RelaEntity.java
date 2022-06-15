package util;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/01/31 9:38
 * @version: V1.0
 */
public class RelaEntity {
    private String columnIds="";
    private String columnNames="";
    private String tableName="";
    private String tableId="";
    private String relationType="1";

    public String getColumnIds() {
        return columnIds;
    }

    public void setColumnIds(String columnIds) {
        this.columnIds = columnIds;
    }

    public String getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String columnNames) {
        this.columnNames = columnNames;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }
}
