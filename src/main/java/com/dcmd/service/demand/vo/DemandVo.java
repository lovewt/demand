package com.dcmd.service.demand.vo;

/**
 * @ClassName DemandVo
 * @Author yy
 * @Date 2018/10/26 16:45
 * @Version 1.0.0
 */
public class DemandVo {
    private Long id;

    /**
     * 需求类型（1：技术需求，2：项目需求）
     */
    private String type;
    private String typeName;

    /**
     * 需求编号
     */
    private String demandNumber;

    /**
     * 标签
     */
    private String label;
    private String labelName;

    /**
     * 需求负责人
     */
    private String demandLeader;
    private String demandLeaderName;

    /**
     * 状态
     */
    private String status;
    private String statusName;

    /**
     * 期望解决时间
     */
    private String expResTime;

    /**
     * 产品线id
     */
    private String productLineId;
    private String productLineName;

    /**
     * 产品id
     */
    private String productModuleId;
    private String productModuleName;

    /**
     * 业务功能ID
     */
    private String businessId;
    private String businessName;

    /**
     * 关联关系  D,直接;L,间接
     */
    private String futuresBusiness;
    private String futuresBusinessName;

    /**
     * 需求描述
     */
    private String demandDescribe;

    /**
     * 条目登级
     */
    private String entry;
    private String entryName;


    /**
     * 功能点ID
     */
    private String functionId;
    private String functionName;


    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 最后更新时间
     */
    private String updateTime;

    /**需求版本号*/
    private String versionNum;

    /**需求ID*/
    private Long versionId;

    /**备注*/
    private String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getDemandLeader() {
        return demandLeader;
    }

    public void setDemandLeader(String demandLeader) {
        this.demandLeader = demandLeader;
    }

    public String getDemandLeaderName() {
        return demandLeaderName;
    }

    public void setDemandLeaderName(String demandLeaderName) {
        this.demandLeaderName = demandLeaderName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getExpResTime() {
        return expResTime;
    }

    public void setExpResTime(String expResTime) {
        this.expResTime = expResTime;
    }

    public String getProductModuleId() {
        return productModuleId;
    }

    public void setProductModuleId(String productModuleId) {
        this.productModuleId = productModuleId;
    }

    public String getProductModuleName() {
        return productModuleName;
    }

    public void setProductModuleName(String productModuleName) {
        this.productModuleName = productModuleName;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getFuturesBusiness() {
        return futuresBusiness;
    }

    public void setFuturesBusiness(String futuresBusiness) {
        this.futuresBusiness = futuresBusiness;
    }

    public String getFuturesBusinessName() {
        return futuresBusinessName;
    }

    public void setFuturesBusinessName(String futuresBusinessName) {
        this.futuresBusinessName = futuresBusinessName;
    }

    public String getDemandDescribe() {
        return demandDescribe;
    }

    public void setDemandDescribe(String demandDescribe) {
        this.demandDescribe = demandDescribe;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entryLevel) {
        this.entry = entryLevel;
    }

    public String getEntryLevelName() {
        return entryName;
    }

    public void setEntryLevelName(String entryLevelName) {
        this.entryName = entryLevelName;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
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

    public String getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(String productLineId) {
        this.productLineId = productLineId;
    }

    public String getProductLineName() {
        return productLineName;
    }

    public void setProductLineName(String productLineName) {
        this.productLineName = productLineName;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    @Override
    public String toString() {
        return "DemandVo{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", typeName='" + typeName + '\'' +
                ", demandNumber='" + demandNumber + '\'' +
                ", label='" + label + '\'' +
                ", labelName='" + labelName + '\'' +
                ", demandLeader='" + demandLeader + '\'' +
                ", demandLeaderName='" + demandLeaderName + '\'' +
                ", status='" + status + '\'' +
                ", statusName='" + statusName + '\'' +
                ", expResTime='" + expResTime + '\'' +
                ", productLineId='" + productLineId + '\'' +
                ", productLineName='" + productLineName + '\'' +
                ", productModuleId='" + productModuleId + '\'' +
                ", productModuleName='" + productModuleName + '\'' +
                ", businessId='" + businessId + '\'' +
                ", businessName='" + businessName + '\'' +
                ", futuresBusiness='" + futuresBusiness + '\'' +
                ", futuresBusinessName='" + futuresBusinessName + '\'' +
                ", demandDescribe='" + demandDescribe + '\'' +
                ", entry='" + entry + '\'' +
                ", entryName='" + entryName + '\'' +
                ", functionId='" + functionId + '\'' +
                ", functionName='" + functionName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", versionNum='" + versionNum + '\'' +
                ", versionId=" + versionId +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
