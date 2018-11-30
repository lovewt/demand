package com.dcmd.service.demand.entity;

import java.io.Serializable;

/**
 * @ClassName Features
 * @Author hoper
 * @Date 2018/11/6 14:18
 * Version 1.0
 */
public class Features implements Serializable {
    private Long id;

    /**
     * 功能点名称
     */
    private String name;

    /**
     * 关联关系:D,直接;L,间接
     */
    private String featuresBusiness;

    /**
     * 关联软件产品ID
     */
    private String softwareNum;

    /**
     * PID(t_comp_business_level)
     */
    private Long businessId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeaturesBusiness() {
        return featuresBusiness;
    }

    public void setFeaturesBusiness(String featuresBusiness) {
        this.featuresBusiness = featuresBusiness;
    }

    public String getSoftwareNum() {
        return softwareNum;
    }

    public void setSoftwareNum(String softwareNum) {
        this.softwareNum = softwareNum;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    @Override
    public String toString() {
        return "Features{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", featuresBusiness='" + featuresBusiness + '\'' +
                ", softwareNum='" + softwareNum + '\'' +
                ", businessId=" + businessId +
                '}';
    }
}
