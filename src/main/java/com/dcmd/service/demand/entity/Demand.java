package com.dcmd.service.demand.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * 需求
 * @author yy
 */
public class Demand implements Serializable{

    private Long id;

    /**
     * 需求类型（1：技术需求，2：项目需求）
     *
     */
    private String type;

    /**需求编号*/
    private String demandNumber;

    /**标签*/
    private String label;

    /**需求负责人*/
    private String demandLeader;

    /**状态*/
    private String status;

    /**期望解决时间*/
    private Date expResTime;

    /**产品产品线id*/
    private String productModuleId;

    /**业务功能ID*/
    private String businessId;

    /**关联关系  D,直接;L,间接*/
    private String futuresBusiness;

    /**
     * 条目登级
     */
    private Long entry;

    /**
     * 功能点ID
     */
    private String functionId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后更新时间
     */
    private Date updateTime;

    /**备注*/
    private String remarks;

    @Override
    public String toString() {
        return "Demand{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", demandNumber='" + demandNumber + '\'' +
                ", label='" + label + '\'' +
                ", demandLeader='" + demandLeader + '\'' +
                ", status='" + status + '\'' +
                ", expResTime=" + expResTime +
                ", productModuleId='" + productModuleId + '\'' +
                ", businessId='" + businessId + '\'' +
                ", futuresBusiness='" + futuresBusiness + '\'' +
                ", entry=" + entry +
                ", functionId='" + functionId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDemandNumber() {
        return demandNumber;
    }

    public void setDemandNumber(String demandNumber) {
        this.demandNumber = demandNumber;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDemandLeader() {
        return demandLeader;
    }

    public void setDemandLeader(String demandLeader) {
        this.demandLeader = demandLeader;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getExpResTime() {
        return expResTime;
    }

    public void setExpResTime(Date expResTime) {
        this.expResTime = expResTime;
    }

    public String getProductModuleId() {
        return productModuleId;
    }

    public void setProductModuleId(String productModuleId) {
        this.productModuleId = productModuleId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getFuturesBusiness() {
        return futuresBusiness;
    }

    public void setFuturesBusiness(String futuresBusiness) {
        this.futuresBusiness = futuresBusiness;
    }

    public Long getEntry() {
        return entry;
    }

    public void setEntry(Long entry) {
        this.entry = entry;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}