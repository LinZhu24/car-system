package com.lxr.carsystem.util;

import java.math.BigDecimal;

/**
 * @Author: LinXueRui
 * @Date: 2019/5/17 15:13
 * @Desc: 数字运算时常用的工具类
 */
public class CommonUtil {
    /**
     * 四舍五入，保留两位小数
     * @param value
     * @return
     */
    public static Double roundUpTwo(Double value){
        BigDecimal bigDecimal = new BigDecimal(value);
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 不四舍五入，保留两位小数
     * @param value
     * @return
     */
    public static Double roundFloorTwo(Double value){
        BigDecimal bigDecimal = new BigDecimal(value);
        return bigDecimal.setScale(2, BigDecimal.ROUND_FLOOR).doubleValue();
    }

    /**
     * 当前线程等待一秒
     */
    public static void waitOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
