package com.lxr.carsystem.test;

import com.lxr.carsystem.util.CommonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: LinXueRui
 * @Date: 2019/5/17 15:48
 * @Desc:
 */
public class NumberOperationTest {
    public static void main(String[] args) {
        Integer totalTimes = 509;
        Double result = 0.0;
        List<Map<Integer, String>> list = new ArrayList<>();
        Integer[] arr = new Integer[]{28, 437, 2, 32, 6, 4};
        for (int i = 0; i < arr.length; i++) {
            Map<Integer, String> map = new HashMap<>(1);
            Double rate = (double) arr[i] / totalTimes * 100;
            Double finalRate = CommonUtil.roundUpTwo(rate);
            String str = finalRate + "%";
            map.put(arr[i], str);
            list.add(map);
            result += finalRate;
        }
        list.forEach(e -> e.forEach((k, v) -> System.out.println(k + "   " + v)));
        System.out.println("总和为:"+result);
        System.out.println("=================");
        Double result1 = 0.0;
        Integer totalTimes1 = 515;
        List<Map<Integer, String>> list1 = new ArrayList<>();
        Integer[] arr1 = new Integer[]{28, 442, 2, 33, 6, 4};
        for (int i = 0; i < arr1.length; i++) {
            Map<Integer, String> map = new HashMap<>(1);
            Double rate = (double) arr1[i] / totalTimes1 * 100;
            Double finalRate = CommonUtil.roundUpTwo(rate);
            String str = finalRate + "%";
            map.put(arr1[i], str);
            list1.add(map);
            result1 += finalRate;

        }
        list1.forEach(e -> e.forEach((k, v) -> System.out.println(k + "   " + v)));
        System.out.println("总和为:"+result1);


    }
}
