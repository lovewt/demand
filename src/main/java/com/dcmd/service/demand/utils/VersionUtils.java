package com.dcmd.service.demand.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName VersionUtils
 * @Author hoper
 * @Date 2018/11/5 15:55
 * Version 1.0
 */
public class VersionUtils {
    /**
     * 项目初始版本号
     */
    public static final String PV = "V1.0";

    /**
     * 任务初始版本号
     */
    public static final String V = "V1.0";

    /**
     * 项目类型
     */
    public static final String PROJECT = "Project";

    /**
     * 任务类型
     */
    public static final String TASK = "Task";

    /**
     * 万金油
     */
    public static final String POINT = ".";

    /**
     * 万金油
     */
    public static final String COMMA = ",";

    /**
     * 正则
     */
    public static final Pattern PATTERN = Pattern.compile("[a-zA-z]");

    public static final String REGX = "@(.*?)@";

    /**
     * 生成项目初始版本号
     *
     * @return PV  V1.0
     */
    public static String generatePV() {
        return PV;
    }

    /**
     * 生成任务初始版本号
     *
     * @return V  V1.0
     */
    public static String geterateV() {
        return V;
    }

    /**
     * 年终大促销  Vx.x.x---@Vn+1.x   Vx.x---@Vn+1.x
     *
     * @param versionNumber
     * @param type
     * @return
     */
    public static String bigOnline(String versionNumber, String type) {
        if (type.equals(TASK)) {
            return versionNumber.substring(0, 1) + (Integer.valueOf(versionNumber.substring(1, versionNumber.indexOf(POINT))) + 1) + POINT + 0;
        } else if (type.equals(PROJECT)) {
            return versionNumber.substring(0, 2) + (Integer.valueOf(versionNumber.substring(2, versionNumber.indexOf(POINT))) + 1) + POINT + 0;
        }
        return null;
    }

    /**
     * 生成变更版本号 Vx.x---@Vx.x.n+1   Vx.x.x---@Vx.x.n+1
     *
     * @param versionNumber
     * @return
     */
    public static String changeVersionNumber(String versionNumber) {
        String[] split = versionNumber.split("\\.");
        String s = split[split.length - 1];
        if (PATTERN.matcher(s).find()) {
            return versionNumber;
        }
        if (split.length < 3) {
            return versionNumber + ".1";
        }
        split[split.length - 1] = String.valueOf(Integer.valueOf(s) + 1);
        return String.join(POINT, split);
    }

    /**
     * 上线版本号递增 Vx.x.x---@Vx.n+1
     *
     * @param versionNumber
     * @return
     */
    public static String onlineVersionNumber(String versionNumber) {
        // V1.0.x  拿到最后一次.出现的位置
        int i = versionNumber.lastIndexOf(POINT);
        // Vx.
        String substring = versionNumber.substring(0, i);
        // 拿到一次.出现的位置
        int i1 = versionNumber.indexOf(POINT);
        // 那么此版本为Vx.x
        if (i == i1) {
            String s = versionNumber.substring(0, versionNumber.indexOf(POINT) + 1);
            String s1 = versionNumber.substring(versionNumber.indexOf(POINT) + 1);
            return s + (Integer.valueOf(s1) + 1);
        } else {
            //V10.0.1---@V10.n+1
            String substring1 = versionNumber.substring(0, i);
            String substring2 = substring1.substring(0, substring1.indexOf(POINT) + 1);
            String substring3 = substring1.substring(substring.indexOf(POINT) + 1);
            int i2 = Integer.valueOf(substring3) + 1;
            return substring2 + String.valueOf(i2);
        }
    }

    /**
     * 正则表达式匹配两个指定字符串中间的内容
     *
     * @param soap
     * @return
     */

    public static List<String> getSubUtil(String soap, String rgex) {
        List<String> list = new ArrayList<>();
        // 匹配的模式
        Pattern pattern = Pattern.compile(rgex);
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            int i = 1;
            list.add(m.group(i));
            i++;
        }
        return list;
    }

    /**
     * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样
     *
     * @param soap
     * @param rgex
     * @return
     */
    public static String getSubUtilSimple(String soap, String rgex) {
        Pattern pattern = Pattern.compile(rgex);
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            return m.group(1);
        }
        return "";
    }

    public static void main(String[] args) {
        String a[] = {"V0.10","V0.11","V0.12","V0.13","V0.14"};
        for (int i = 0; i <a.length; i++) {
        String s = onlineVersionNumber(a[i]);
            System.out.println(s);
        }
    }
}
