package com.dcmd.service.demand.vo;

/**
 * @ClassName VersionVo
 * @Author hoper
 * @Date 2018/11/7 18:13
 * Version 1.0
 */
public class VersionVo {

    private Long id;

    private String createTime;

    private String updateTime;

    /**
     * 变更人员
     */
    private String changeStaffName;

    /**
     * 需求id
     */
    private Long demandId;

    /**
     * 版本號
     */
    private String versionNum;

    /**
     * 历史版本描述
     */
    private String demandDescribe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getChangeStaffName() {
        return changeStaffName;
    }

    public void setChangeStaffName(String changeStaffName) {
        this.changeStaffName = changeStaffName;
    }

    public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }

    public String getDemandDescribe() {
        return demandDescribe;
    }

    public void setDemandDescribe(String demandDescribe) {
        this.demandDescribe = demandDescribe;
    }

    @Override
    public String toString() {
        return "VersionVo{" +
                "id=" + id +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", changeStaffName='" + changeStaffName + '\'' +
                ", demandId=" + demandId +
                ", versionNum='" + versionNum + '\'' +
                ", demandDescribe='" + demandDescribe + '\'' +
                '}';
    }
}
