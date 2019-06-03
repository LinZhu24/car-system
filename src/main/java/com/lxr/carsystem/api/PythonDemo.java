package com.lxr.carsystem.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author: LinXueRui
 * @Date: 2019/3/27 15:14
 * @Desc:  原文：https://blog.csdn.net/IT_xiao_bai/article/details/79074988
 */
public class PythonDemo {
    public static void main(String[] args) {
        System.out.println("开始执行python接口");
        try{
            String python_Path = "python";
            String pyFile_path = "D:\\file\\excel\\demo1.py";
            String Parameter_value1 = "";
            String Parameter_value2 = "";
            //拼接python运行指令，如下举例，带两个参数，以此类推
            String cmdString = python_Path + " " + pyFile_path + " " + "hello,test java and python" ;
            //java调用
            Process process = Runtime.getRuntime().exec(cmdString);
            //java持续捕获python输出，打印log，此段代码也可以不用
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(),"GBK"));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                //logger.info(line);
            }
            in.close();
            //因此处等待python执行，因此该段代码要放入java线程类的run函数里面
            process.waitFor();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //logger.info(e.getMessage());
        }
        System.out.println("python接口执行结束");
    }
}
