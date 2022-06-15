package hcloud;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2020/10/30 17:15
 * @version: V1.0
 */
public class MatchCodeEntity {
    private String id;
    private String code;
    private String ybbm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getYbbm() {
        return ybbm;
    }

    public void setYbbm(String ybbm) {
        this.ybbm = ybbm;
    }
}
