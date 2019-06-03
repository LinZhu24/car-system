package com.lxr.carsystem.tool;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;

/**
 * 帮助类
 */
public class Helper {

    /**
     * 获取随机数
     *
     * @return
     */
    public static int getRandom(int size) {
        if (size < 0) {
            return 0;
        }
        Random rad = new Random();
        return rad.nextInt(size);
    }

    /**
     * 获取UUID
     *
     * @return
     */
    public static String getUUID() {
        String uuid=java.util.UUID.randomUUID().toString();
        String newUUID=uuid.replace("-","");
        return newUUID.substring(0,13);
    }

    /**
     * 时间转换
     *
     * @return yyyy-MM-dd
     */
    public static Date StrToDate(String str) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (null != str && !str.equals("")) {
            try {
                return format.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * date转String 时间转换
     *
     * @return yyyy-MM-dd
     */
    public static String DateToStr(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (null != date && !date.equals("")) {
            return format.format(date);
        } else {
            return null;
        }
    }

    /**
     * 时间转换
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static Date StrToDate1(String str) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (null != str && !str.equals("")&& !str.equals("null")) {
            try {
                return format.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 在curday基础上加AddNum天
     *
     * @param curday
     * @param AddNum
     * @return
     */
    public static String getNextDay(String curday, int addNum) {
        // 将String 转换成date
        Date curDate = StrToDate(curday);
        Calendar cal = Calendar.getInstance();
        // 将日期设为当前的
        cal.setTime(curDate);
        cal.add(Calendar.DATE, addNum);
        return DateToStr(cal.getTime());
    }

    /**
     * 获得curday的下AddMonth个月第一天的日期
     *
     * @param curday
     * @param AddMonth
     * @return
     */
    public static String getNextMonthFirst(String curday, int addMonth) {
        // 将String 转换成date
        Date curDate = StrToDate(curday);
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(Calendar.MONTH, addMonth);// 加AddMonth个月
        cal.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        return DateToStr(cal.getTime());
    }

    /**
     * 获得curday的下AddMonth个月最后一天的日期
     *
     * @param curday
     * @param AddMonth
     * @return
     */
    public static String getNextMonthEnd(String curday, int addMonth) {
        // 将String 转换成date
        Date curDate = StrToDate(curday);
        Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(Calendar.MONTH, addMonth);// 加AddMonth个月
        cal.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        cal.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
        return DateToStr(cal.getTime());
    }

    /**
     * 获得本周一的日期
     *
     * @param curDay
     * @return
     */
    public static String getMondayOFWeek(String curDay) {
        // 将String 转换成date
        Date curDate = StrToDate(curDay);
        int mondayPlus = getMondayPlus(curDate);
        String monday = getNextDay(curDay, mondayPlus);
        return monday;
    }

    /**
     * 获得当前日期与本周日相差的天数
     *
     * @param date
     * @return
     */
    private static int getMondayPlus(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        // if(Calendar.DAY_OF_WEEK)
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 0) {
            return -6;
        } else {
            return 1 - dayOfWeek;
        }
    }
    /**
     *
     * @param随机数
     * @author dingqun  @date 2016年3月18日 上午9:52:52
     */
    public static String getRandom2(int length){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(d) + RandomStringUtils.randomAlphanumeric(length - 8);
    }
    /**
     * @Description:把list转换为一个用逗号分隔的字符串
     * @auth dingqun 2016-3-29 22:02:53
     */
    public static String listToString(List list){
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0){
            for (int i = 0; i < list.size(); i++){
                if (i < list.size() - 1){
                    sb.append(list.get(i) + ",");
                } else{
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 格式化double四舍五入保留一位小数,
     * @author dingqun 2016年4月20日08:44:10
     */
    public static Double double2(Double d){
    	/*DecimalFormat df = new DecimalFormat("######0.0");
    	return Double.parseDouble(df.format(d));*/
        //解决原方法不可正常四舍五入的问题 2017-10-9 by zzm
        BigDecimal   b   =   new   BigDecimal(d);
        double   result   =   b.setScale(1,   BigDecimal.ROUND_HALF_UP).doubleValue();
        return result;
    }

    /**
     * 格式化double四舍五入保留2位小数
     * @author dingqun 2016年4月20日08:44:10
     */
    public static Double double22(Double d){
        DecimalFormat df = new DecimalFormat("######0.00");
        return Double.parseDouble(df.format(d));
    }

    /**
     *
     * @param获取出数字字符春随机数
     * @author dingqun  @date 2016-5-25 15:09:52
     */
    public static String getMathRandom(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String randomString = d.getTime() + "";
        return sdf.format(d) + randomString.substring(7);
    }
}
