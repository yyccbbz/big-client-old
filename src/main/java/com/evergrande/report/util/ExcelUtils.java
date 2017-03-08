package com.evergrande.report.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by cc on 16-7-26.
 */
public class ExcelUtils {


    /**
     * 自动识别读取方式
     *
     * 合并方法，读取excel文件
     * 根据文件名自动识别读取方式
     * 支持97-2013格式的excel文档
     * @param fileName 上传文件名
     * @param file     上传的文件
     */
	public static List<Map<String, String>> readExcel(String fileName, MultipartFile file) throws Exception {
        //准备返回值列表
        List<Map<String, String>> valueList = new ArrayList<Map<String, String>>();
        //创建缓存文件目录
        String filepathtemp = "/tmp/big-client-import/";
        String tmpFileName = System.currentTimeMillis() + "." + UploadUtils.getExtensionName(fileName);
        //获取文件扩展名
        String ExtensionName = UploadUtils.getExtensionName(fileName);
        //
        File filelist = new File(filepathtemp);
        if (!filelist.exists() && !filelist.isDirectory()) {
            filelist.mkdir();
        }
        String filePath = filepathtemp + System.getProperty("file.separator") + tmpFileName;
        File tmpfile = new File(filePath);
        //拷贝文件到服务器缓存目录（在项目下）
        //copy(file,tmpfile); //stuts用的方法
        UploadUtils.copy(file, filepathtemp, tmpFileName);//spring mvc用的方法

        System.out.println("后缀名："+ExtensionName);

        if (ExtensionName.equalsIgnoreCase("xls")) {
            valueList = readExcel2003(filePath);
        } else if (ExtensionName.equalsIgnoreCase("xlsx")) {
            valueList = readExcel2007(filePath);
        }
        //删除缓存文件
        tmpfile.delete();


        /**遍历集合,打印出来格式
         // 查看单个Map中的数据
         for(int x = 0 ; x < valueList.size(); x ++){
         System.out.print(valueList.get(x));
         }
         // 查看map的key value
         for (Map<String, Object> m : valueList) {
         for (String k : m.keySet()) {
         System.out.println(k + " : " + m.get(k));
         }
         }*/

        return valueList;

    }

    /**
     * 读取97-2003格式
     *
     * @param filePath 文件路径
     * @throws IOException
     */
    public static List<Map<String, String>> readExcel2003(String filePath) throws IOException {
        //返回结果集
        List<Map<String, String>> valueList = new ArrayList<Map<String, String>>();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            HSSFWorkbook wookbook = new HSSFWorkbook(fis);  // 创建对Excel工作簿文件的引用
            HSSFSheet sheet = wookbook.getSheetAt(0);   // 在Excel文档中，第一张工作表的缺省索引是0
            int rows = sheet.getPhysicalNumberOfRows(); // 获取到Excel文件中的所有行数­
            Map<Integer, String> keys = new HashMap<Integer, String>();
            int cells = 0;
            // 遍历行­（第1行  表头） 准备Map里的key
            HSSFRow firstRow = sheet.getRow(0);
            if (firstRow != null) {
                // 获取到Excel文件中的所有的列
                cells = firstRow.getPhysicalNumberOfCells();
                // 遍历列
                for (int j = 0; j < cells; j++) {
                    // 获取到列的值­
                    try {
                        HSSFCell cell = firstRow.getCell(j);
                        String cellValue = getCellValue(cell);
                        keys.put(j, cellValue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            // 遍历行­（从第二行开始）
            for (int i = 1; i < rows; i++) {
                // 读取左上端单元格(从第二行开始)
                HSSFRow row = sheet.getRow(i);
                // 行不为空
                if (row != null) {
                    //准备当前行 所储存值的map
                    Map<String, String> val = new HashMap<String, String>();

                    boolean isValidRow = false;

                    // 遍历列
                    for (int j = 0; j < cells; j++) {
                        // 获取到列的值­
                        try {
                            HSSFCell cell = row.getCell(j);
                            String cellValue = getCellValue(cell);
                            val.put(keys.get(j), cellValue);
                            if (!isValidRow && cellValue != null && cellValue.trim().length() > 0) {
                                isValidRow = true;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    //第I行所有的列数据读取完毕，放入valuelist
                    if (isValidRow) {
                        valueList.add(val);
                    }


                }
            }
            wookbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fis.close();
        }
        return valueList;
    }


    /**
     * 读取2007-2013格式
     *
     * @param filePath 文件路径
     * @return
     * @throws IOException
     */
    public static List<Map<String, String>> readExcel2007(String filePath) throws IOException {
        List<Map<String, String>> valueList = new ArrayList<Map<String, String>>();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            XSSFWorkbook xwb = new XSSFWorkbook(fis);   // 构造 XSSFWorkbook 对象，strPath 传入文件路径
            XSSFSheet sheet = xwb.getSheetAt(0);            // 读取第一章表格内容
            // 定义 row、cell
            XSSFRow row;
            // 循环输出表格中的第一行内容   表头
            Map<Integer, String> keys = new HashMap<Integer, String>();
            row = sheet.getRow(0);
            if (row != null) {
                //System.out.println("j = row.getFirstCellNum()::"+row.getFirstCellNum());
                //System.out.println("row.getPhysicalNumberOfCells()::"+row.getPhysicalNumberOfCells());
                for (int j = row.getFirstCellNum(); j <= row.getPhysicalNumberOfCells(); j++) {
                    // 通过 row.getCell(j).toString() 获取单元格内容，
                    if (row.getCell(j) != null) {
                        if (!row.getCell(j).toString().isEmpty()) {
                            keys.put(j, row.getCell(j).toString());
                        }
                    } else {
                        keys.put(j, "K-R1C" + j + "E");
                    }
                }
            }
            // 循环输出表格中的从第二行开始内容
            for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    boolean isValidRow = false;
                    Map<String, String> val = new HashMap<String, String>();
                    for (int j = row.getFirstCellNum(); j <= row.getPhysicalNumberOfCells(); j++) {
                        XSSFCell cell = row.getCell(j);
                        if (cell != null) {
                            String cellValue = null;
                            if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                                if (DateUtil.isCellDateFormatted(cell)) {
                                	//yyyy-MM-dd HH:mm:ss
                                    cellValue = new DataFormatter().formatRawCellContents(cell.getNumericCellValue(), 0, "yyyy-MM-dd");
                                } else {
                                	//这里要判断手机号，识别手机号
                                	DecimalFormat df = new DecimalFormat("0");  
                                    String strCell = df.format(cell.getNumericCellValue()); 
//                                    cellValue = String.valueOf(cell.getNumericCellValue());
                                    cellValue = strCell;
                                }
                            } else {
                                cellValue = cell.toString();
                            }
                            if (cellValue != null && cellValue.trim().length() <= 0) {
                                cellValue = null;
                            }
                            val.put(keys.get(j), cellValue);
                            if (!isValidRow && cellValue != null && cellValue.trim().length() > 0) {
                                isValidRow = true;
                            }
                        }
                    }

                    // 第I行所有的列数据读取完毕，放入valuelist
                    if (isValidRow) {
                        valueList.add(val);
                    }
                }
            }
            xwb.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fis.close();
        }

        return valueList;
    }




    private static String getCellValue(HSSFCell cell) {
        DecimalFormat df = new DecimalFormat("#");
        String cellValue = null;
        if (cell == null)
            return null;
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellValue = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                    break;
                }
                cellValue = df.format(cell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_STRING:
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                cellValue = null;
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                cellValue = String.valueOf(cell.getErrorCellValue());
                break;
        }
        if (cellValue != null && cellValue.trim().length() <= 0) {
            cellValue = null;
        }
        return cellValue;
    }


}
