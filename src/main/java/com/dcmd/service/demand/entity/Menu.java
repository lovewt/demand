package com.dcmd.service.demand.entity;


import java.util.ArrayList;
import java.util.List;

/**
 * 产品
 */

public class Menu {
    private  String value;
    private  String label;//产品名称
    private  String pid;
    public List<Menu> children = new ArrayList<Menu>();

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
