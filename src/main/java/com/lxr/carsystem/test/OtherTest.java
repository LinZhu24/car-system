package com.lxr.carsystem.test;

import com.xiaoleilu.hutool.date.DatePattern;
import com.xiaoleilu.hutool.date.DateTime;
import com.xiaoleilu.hutool.date.DateUtil;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

/**
 * @Author: LinXueRui
 * @Date: 2019/5/14 15:59
 * @Desc:
 */
public class OtherTest {
    public static void main(String[] args) {
        String startDate = "2019-01-14";
        String endDate = "2019-01-14";
        if (startDate.equals(endDate)){
            Date day = DateUtil.parse(startDate, DatePattern.NORM_DATE_PATTERN);
            String dayStr = DateUtil.format(day,"yyyy年M月dd号");
            System.out.println(dayStr);
        }


    }
}
