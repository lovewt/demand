package com.dcmd.service.demand.vo;

import java.io.Serializable;

/**
 * @ClassName FeaturesVo
 * @Author hoper
 * @Date 2018/11/6 14:36
 * Version 1.0
 */
public class FeaturesVo implements Serializable {
    private  Long id;

    private String businessId;

    private String featuresBusiness;

    private String softwareNum;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FeaturesVo{" +
                "id=" + id +
                ", businessId='" + businessId + '\'' +
                ", featuresBusiness='" + featuresBusiness + '\'' +
                ", softwareNum='" + softwareNum + '\'' +
                '}';
    }
}
