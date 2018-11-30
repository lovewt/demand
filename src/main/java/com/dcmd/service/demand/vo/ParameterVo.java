package com.dcmd.service.demand.vo;

/**
 * @ClassName ParameterVo
 * @Author hoper
 * @Date 2018/11/6 16:20
 * Version 1.0
 */
public class ParameterVo {
    private String value;

    private String label;

    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ParameterVo{" +
                "value='" + value + '\'' +
                ", label='" + label + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
