package com.lxr.carsystem.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: LinXueRui
 * @Date: 2019/6/28 10:24
 * @Desc:
 */
public class CsvUtil {
    public static void read(String filePath){
        File file = new File(filePath);
        read(file);

    }

    private static void read(File file){

    }

    public static List<List<String>> readCSV(String srcPath) {
        String charset = "GBK";
        List<List<String>> finalList = new ArrayList<>();
        try (CSVReader csvReader = new CSVReaderBuilder(new BufferedReader(new InputStreamReader(new FileInputStream(new File(srcPath)), charset))).build()) {
            Iterator<String[]> iterator = csvReader.iterator();
            while (iterator.hasNext()) {
                List<String> list = new ArrayList<>();
                String[] line = iterator.next();
                list.addAll(Arrays.asList(line));
//                Arrays.stream(iterator.next()).forEach(e-> System.out.print(e + "  "));
//                System.out.println();
                finalList.add(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalList;
    }

    public static void main(String[] args) {
        String srcPath = "D:\\万相工作\\LogSystem\\csv的大问题\\1.csv";
        String dir = srcPath.substring(0,srcPath.lastIndexOf("\\"));
        String finalName = "111.xlsx";
        List<List<String>> finalList = readCSV(srcPath);
        Workbook workbook = getWorkbook(finalList);
        exportXls(workbook, dir, finalName);
    }

    /**
     * 获得workbook
     *
     * @param finalList
     * @return
     */
    public static XSSFWorkbook getWorkbook(List<List<String>> finalList) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(" ");
        for (int i = 0; i < finalList.size(); i++) {
            List<String> tempList = finalList.get(i);
            int size = tempList.size();
            XSSFRow dataRow = sheet.createRow(i);
            for (int j = 0; j < size; j++) {
                dataRow.createCell(j).setCellValue(tempList.get(j));
            }
        }
        return workbook;
    }

    /**
     * 导出Excel到指定目录
     *
     * @param workbook Workbook
     * @param filePath 目录
     * @param fileName 文件名
     */
    public static void exportXls(Workbook workbook, String filePath, String fileName) {
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            fileOutputStream = new FileOutputStream(filePath + File.separator + fileName);
            workbook.write(fileOutputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage() + e);
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage() + e);
                }
            }
        }
    }


}
