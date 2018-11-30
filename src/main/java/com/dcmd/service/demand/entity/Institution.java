package com.dcmd.service.demand.entity;

import java.io.Serializable;

/**
 * 机构
 */
public class Institution implements Serializable {
    String instNo;
    String instName;
    String instProperty;
    String highInstNo;
    String highInstName;

    public String getInstNo() {
        return instNo;
    }

    public void setInstNo(String instNo) {
        this.instNo = instNo;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public String getInstProperty() {
        return instProperty;
    }

    public void setInstProperty(String instProperty) {
        this.instProperty = instProperty;
    }

    public String getHighInstNo() {
        return highInstNo;
    }

    public void setHighInstNo(String highInstNo) {
        this.highInstNo = highInstNo;
    }

    public String getHighInstName() {
        return highInstName;
    }

    public void setHighInstName(String highInstName) {
        this.highInstName = highInstName;
    }
}

