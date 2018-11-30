package com.dcmd.service.demand.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
public class Version implements Serializable {
    private Long id;

    private Date createTime;

    private Date updateTime;

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

    private String markDownContent;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
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

    public String getMarkDownContent() {
        return markDownContent;
    }

    public void setMarkDownContent(String markDownContent) {
        this.markDownContent = markDownContent;
    }

    @Override
    public String toString() {
        return "Version{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", changeStaffName='" + changeStaffName + '\'' +
                ", demandId=" + demandId +
                ", versionNum=" + versionNum +
                ", demandDescribe='" + demandDescribe + '\'' +
                ", markDownContent='" + markDownContent + '\'' +
                '}';
    }
}