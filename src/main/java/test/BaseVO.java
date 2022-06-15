package test;

/**
 * @ClassName:
 * @Description:
 * @author: QianTao
 * @date: 2020/05/16 10:49
 * @version: V1.0
 */
public class BaseVO {
    public BaseVO(){

    }
    public BaseVO(Long groupId){
        this.groupId = groupId;
    }
    private Long groupId;
    private Long companyId;
    private Long businessId;
    private Long userId;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


}
