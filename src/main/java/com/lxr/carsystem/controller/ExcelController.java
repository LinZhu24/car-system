package com.lxr.carsystem.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.lxr.carsystem.common.ResponseEntity;
import com.lxr.carsystem.entity.CarBrand;
import com.lxr.carsystem.service.CarBrandService;
import com.lxr.carsystem.tool.ExcelUtil;
import com.lxr.carsystem.tool.Result;
import com.xiaoleilu.hutool.date.DatePattern;
import com.xiaoleilu.hutool.date.DateUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author: LinXueRui
 * @Date: 2019/3/11 19:00
 * @Desc:
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {
    private static Logger logger = LoggerFactory.getLogger(ExcelController.class);

    @Autowired
    CarBrandService brandService;

    /**
     * 导出Excel表格的方式------第 1 种方式
     * @param response
     * @return
     */
    @RequestMapping("/getBrandExcel")
    @ResponseBody
    public Result getBrandExcel(HttpServletResponse response) throws ClassNotFoundException {
        List<CarBrand> brandList = brandService.findAll();
        CarBrand c = new CarBrand();
        List<ExcelExportEntity> tableHeader = ExcelUtil.getTableHeader("brand");
        List<Map<String, Object>> tableBody = ExcelUtil.getTableBody(brandList);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("汽车品牌", "汽车品牌", ExcelType.XSSF), tableHeader,tableBody);
        ExcelUtil.exportXls(workbook, "汽车品牌" + DateUtil.format(new Date(), "yyyy-MM-dd"), response);
        return Result.success("导出成功",brandList);
    }

    /**
     * 导出Excel表格的方式------第 2 种方式（导出数据到本地）
     * @return
     */
    @RequestMapping("/getBrandExcelToLocal")
    @ResponseBody
    public Result getBrandExcelToLocal(){
        List<CarBrand> brandList = brandService.findAll();
        List<ExcelExportEntity> tableHeader = ExcelUtil.getTableHeader("brand");
        List<Map<String, Object>> tableBody = ExcelUtil.getTableBody(brandList);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("汽车品牌", "汽车品牌", ExcelType.XSSF), tableHeader,tableBody);
        try {
            String filePath = "D:\\file\\excel\\";
            String fileName = "汽车品牌" + DateUtil.format(new Date(),"yyyy-MM-dd") + ".xlsx";
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream output = new FileOutputStream(filePath+fileName);
            workbook.write(output);
            output.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Result.success("导出成功",brandList);
    }



    @RequestMapping("/hello")
    @ResponseBody
    public Result getHello(){
        return Result.success("hello");
    }


    @RequestMapping("/getBrandExcelZip")
    @ResponseBody
    public Result getBrandExcelZip(HttpServletResponse response) throws ClassNotFoundException {
        List<CarBrand> brandList = brandService.findAll();
        List<CarBrand> brandList1 = brandService.findAll();

        List<ExcelExportEntity> tableHeader = ExcelUtil.getTableHeader("brand");
        List<Map<String, Object>> tableBody = ExcelUtil.getTableBody(brandList);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("汽车品牌", "汽车品牌", ExcelType.XSSF), tableHeader,tableBody);
        Workbook workbook1 = ExcelExportUtil.exportExcel(new ExportParams("汽车品牌", "汽车品牌", ExcelType.XSSF), tableHeader,tableBody);

        Workbook [] array = new Workbook[2];
        array[0] = workbook;
        array[1] = workbook1;
        for (int i = 0; i < 2; i++) {
            ExcelUtil.exportXls(array[i], i == 0 ? "汽车品牌" : "汽车品牌1" + DateUtil.format(new Date(), "yyyy-MM-dd"), response);
        }
        return Result.success("导出成功",brandList);
    }

    @RequestMapping(value = "/poizip")
    public void poizip(HttpServletResponse response) throws IOException {

        //response 输出流
        OutputStream out = response.getOutputStream();
        //压缩输出流
        ZipOutputStream zipOutputStream = new ZipOutputStream(out);
        try {
            for (int i = 0; i < 6; i++) {
                //创建工作簿
                HSSFWorkbook wb = new HSSFWorkbook();
                HSSFSheet sheet = wb.createSheet("汽车品牌" + i);
                HSSFRow titlerRow = sheet.createRow(0);
                HSSFRow row = sheet.createRow(0);
                HSSFCell cell = row.createCell(0);
                cell.setCellValue("内容" + i);
                response.setContentType("application/octet-stream; charset=utf-8");
                response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("测试.zip", "UTF-8"));
                //重点开始,创建压缩文件
                ZipEntry z = new ZipEntry(i + ".xls");
                zipOutputStream.putNextEntry(z);
                //写入一个压缩文件
                wb.write(zipOutputStream);
            }
            zipOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //注意关闭顺序，否则可能文件错误
            if (zipOutputStream != null) {
                zipOutputStream.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }


    @RequestMapping(value = "/zip")
    @ResponseBody
    public ResponseEntity zip(HttpServletResponse response) throws IOException {
        List<CarBrand> brandList = brandService.findAll();
        HSSFWorkbook workbook1 = getExcel(brandList, "汽车品牌1");
        HSSFWorkbook workbook2 = getExcel(brandList, "汽车品牌2");
        HSSFWorkbook [] workbooks = new HSSFWorkbook[]{workbook1,workbook2};
        String[] arrExcelName = new String[]{"表1","表2"};
        String zipName = "汽车品牌";
        ExcelUtil.exportXlsToZip(workbooks,arrExcelName,zipName,response);
        return ResponseEntity.successResponse(null);
    }

    /**
     * 获取Excel的 workbook
     * @param brandList
     * @param sheetName
     * @return
     */
    private HSSFWorkbook getExcel(List<CarBrand> brandList, String sheetName) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName);

        //在第一行设置表格的标题
        HSSFRow firstRow = sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
        HSSFCell titleCell = firstRow.createCell(0);
        titleCell.setCellValue("汽车品牌表");

        HSSFRow secondRow = sheet.createRow(1);
        secondRow.createCell(0).setCellValue("品牌ID");
        secondRow.createCell(1).setCellValue("品牌首字母");
        secondRow.createCell(2).setCellValue("品牌名+时间");
        sheet.setDefaultColumnWidth(60*100);
        for (CarBrand map : brandList) {
            //获取最后一行的行号
            int lastRowNum = sheet.getLastRowNum();
            HSSFRow dataRow = sheet.createRow(lastRowNum + 1);
            dataRow.createCell(0).setCellValue(map.getBrandid());
            dataRow.createCell(1).setCellValue(map.getBrandinitial());
            dataRow.createCell(2).setCellValue(map.getBrandname() + DateUtil.format(new Date(), DatePattern.NORM_DATETIME_PATTERN));
        }

        HSSFCellStyle style = workbook.createCellStyle();
        //设置上下左右四个边框宽度
        HSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short)12);
        font.setColor(HSSFColor.BLACK.index);
        //将字体格式设置到HSSFCellStyle上
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        for (Row row : sheet) {
            row.setHeight((short)(15.625*40));
            for (Cell cell : row) {
                cell.setCellStyle(style);
            }
        }
        return workbook;
    }




    @RequestMapping(value = "/test1")
    @ResponseBody
    public ResponseEntity test1() {
        List<CarBrand> brandList = brandService.findAll();
        return ResponseEntity.successResponse(brandList);
    }
}
