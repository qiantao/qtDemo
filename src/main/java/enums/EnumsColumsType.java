package enums;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2019/01/29 17:46
 * @version: V1.0
 */
public enum  EnumsColumsType {
    nomal("0","正常"),
    search("1","搜索"),
    en("2","枚举"),
    group("3","范围"),
    time("4","时间"),
    rela("5","关联");
    private String id ;
    private String name ;
    EnumsColumsType(String id,String name){
        this.id = id;
        this.name=name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
