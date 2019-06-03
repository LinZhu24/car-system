package com.lxr.carsystem.controller;

import com.lxr.carsystem.tool.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
@RequestMapping("/upload")
public class UploadController {
    /**
     *声明外置tomcat8的服务器文件存放地址
     **/
    static final String uploadPath = "http://localhost:8081/upload/";
    /**
     * Ajax异步回显操作
     **/
    @RequestMapping(value="/pic",method= RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String uploadImg(@RequestParam("pic") MultipartFile file) {
        String fileName = file.getOriginalFilename();//获取文件名
        String actualName="";
        String filePath ="D:\\Program Files\\apache-tomcat-8.5.29\\webapps\\upload\\";
        try {
             actualName = FileUtil.uploadFile(file.getBytes(), filePath, fileName);

        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
        //返回json
        return uploadPath+actualName;
    }

    /**
     * Ajax异步回显，并插入数据库操作(该方法没怎么被使用)
     **/
    @RequestMapping(value="/carPic",method= RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String uploadCarImg(@RequestParam("carPic") MultipartFile file ) {
        String fileName = file.getOriginalFilename();//获取文件名
        String filePath ="D:\\Program Files\\apache-tomcat-8.5.29\\webapps\\upload\\";
        try {
            File targetFile = new File(filePath);
            if(!targetFile.exists()){
                targetFile.mkdirs();//目录不存在则创建目录
            }
            FileOutputStream out = new FileOutputStream(filePath+fileName);//创建一个向指定File对象表示的文件中写入数据的文件输出流。
            out.write(file.getBytes());//将 file的每个字节从指定 byte 数组写入此文件输出流中。
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
        return uploadPath+fileName;//返回json,即图片在外置tomcat服务器中的路径
    }


}
