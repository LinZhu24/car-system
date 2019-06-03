package com.lxr.carsystem.util.enums;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: LinXueRui
 * @Date: 2019/5/17 15:06
 * @Desc:
 */
public enum BreakDownAlarmEnum {
    /**
     * 故障分类统计需要的华为告警
     */
    HW("华为", Arrays.asList("4G基站退服告警","ENodeB退服告警","网元连接中断","小区不可用告警"));

    private String vendor;
    private List<String> alarmList;

    BreakDownAlarmEnum(String vendor, List<String> alarmList){
        this.vendor = vendor;
        this.alarmList = alarmList;
    }

    public static List<String> getAlarmListByVendor(String vendor){
        for (BreakDownAlarmEnum breakDownAlarmEnum:values()) {
            if (breakDownAlarmEnum.vendor.equals(vendor)){
                return breakDownAlarmEnum.getAlarmList();
            }
        }
        return null;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public List<String> getAlarmList() {
        return alarmList;
    }

    public void setAlarmList(List<String> alarmList) {
        this.alarmList = alarmList;
    }
}
