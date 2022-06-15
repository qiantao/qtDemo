package util;

/**
 * @ClassName:
 * @Description: 搜索枚举
 * @author: QianTao
 * @date: 2019/01/29 17:23
 * @version: V1.0
 */
public class SearchEnums {
    //搜索枚举
    private String typeSetValue = "";
    private String typeSetDisabled = "0";//是否弃用
    private String typeSetLabel = "";

    public String getTypeSetValue() {
        return typeSetValue;
    }

    public void setTypeSetValue(String typeSetValue) {
        this.typeSetValue = typeSetValue;
    }

    public String getTypeSetDisabled() {
        return typeSetDisabled;
    }

    public void setTypeSetDisabled(String typeSetDisabled) {
        this.typeSetDisabled = typeSetDisabled;
    }

    public String getTypeSetLabel() {
        return typeSetLabel;
    }

    public void setTypeSetLabel(String typeSetLabel) {
        this.typeSetLabel = typeSetLabel;
    }
}
