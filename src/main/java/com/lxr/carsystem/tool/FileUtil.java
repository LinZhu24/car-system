package com.lxr.carsystem.tool;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {//             文件流          路径           文件名
    public static String uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();//创建目录
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);//创建一个向指定File对象表示的文件中写入数据的文件输出流。
        out.write(file);//将 file的每个字节从指定 byte 数组写入此文件输出流中。
        out.flush();
        out.close();
        return fileName;//返回文件名
    }
}
