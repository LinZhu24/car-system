package com.lxr.carsystem.tool;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.lxr.carsystem.entity.CarBrand;
import org.apache.poi.hssf.usermodel.HSSFBorderFormatting;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author: LinXueRui
 * @Date: 2019/3/11 17:50
 * @Desc: excel的导入和导出
 */
public class ExcelUtil {
    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 生成Excel的表头
     * @param type
     * @return
     */
    public static List<ExcelExportEntity> getTableHeader(String type){
        List<ExcelExportEntity> result = new ArrayList<>();
        switch (type){
            case "brand":
                result.add(new ExcelExportEntity("品牌ID","brandid"));
                result.add(new ExcelExportEntity("品牌首字母","brandinitial"));
                result.add(new ExcelExportEntity("品牌名","brandname"));
                break;
        }
        return result;
    }

    /**
     * 生成Ecxel的表体
     * @param brandList
     * @return
     */
    public static List<Map<String, Object>> getTableBody(List<CarBrand> brandList) {
        List<Map<String, Object>> tableBody = new ArrayList<>();
        brandList.forEach(e->{
            Map<String,Object> map = new HashMap<>();
            map.put("brandid",e.getBrandid());
            map.put("brandinitial",e.getBrandinitial());
            map.put("brandname",e.getBrandname());
            tableBody.add(map);
        });
        return tableBody;
    }

    /**
     * 导出Excel
     * @param workbook
     * @param name
     * @param response
     */
    public static void  exportXls(Workbook workbook, String name, HttpServletResponse response) {
        BufferedOutputStream bufferedOutputStream = null;
        try {
            String fileName = name + ".xlsx";
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
            bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            workbook.write(bufferedOutputStream);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        } finally {
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(),e);
                }
            }
        }
    }

    /**
     * 将多个Excel导出，以压缩包的形式保存
     * @param workbook  workbook数组
     * @param excelName Excel文件名数组
     * @param zipName   压缩包名称
     * @param response  响应
     * @throws IOException
     */
    public static void exportXlsToZip(HSSFWorkbook[] workbook, String[] excelName, String zipName, HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        try {
            for (int i = 0; i < workbook.length; i++) {
                //创建工作簿
                response.setContentType("application/octet-stream; charset=utf-8");
                response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(zipName +".zip", "UTF-8"));
                //重点开始,创建压缩文件
                ZipEntry z = new ZipEntry(excelName[i] + ".xls");
                zipOutputStream.putNextEntry(z);
                //写入一个压缩文件
                workbook[i].write(zipOutputStream);
            }
            zipOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //注意关闭顺序，否则可能文件错误
            if (zipOutputStream != null) {
                zipOutputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
