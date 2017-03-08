package com.evergrande.report.service.impl;

import com.evergrande.report.entity.AssignUser;
import com.evergrande.report.entity.ExtUser;
import com.evergrande.report.entity.OfficialUser;
import com.evergrande.report.service.ExportExcelService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cc on 16-7-25.
 */
@Service
public class ExportExcelServiceImpl implements ExportExcelService {

    /**
     * 导出所有外部拓展用户名单
     *
     * @param list
     * @return
     */
    @Override
    public XSSFWorkbook export_extUserList(List<ExtUser> list) {
        String[] excelHeader = {"手机号码", "系统内姓名", "是否注册", "注册日期", "是否实名", "是否绑卡", "是否有过交易", "扫码推荐人", "返利失效日期"};
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("ext_customer_report");
        sheet.setDefaultColumnWidth(16);
        XSSFRow row0 = sheet.createRow((int) 0);
        //HSSFRow row1 = sheet.createRow((int) 1);
        XSSFCellStyle style = wb.createCellStyle();
        //HSSFCellStyle bg_color_style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
        //style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //bg_color_style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
        //bg_color_style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //bg_color_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        for (int i = 0; i < excelHeader.length; i++) {
            XSSFCell cell = row0.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
            //sheet.autoSizeColumn(i);
        }

        for (int i = 0; i < list.size(); i++) {
            XSSFRow row = sheet.createRow(i + 1);
            ExtUser item = list.get(i);
            String mobile_no = item.getMobile_no();
            if (StringUtils.isBlank(mobile_no)) {
                mobile_no = "";
            }
            row.createCell(0).setCellValue(mobile_no);
            String user_name = item.getUser_name();
            if (StringUtils.isBlank(mobile_no)) {
                user_name = "";
            }
            row.createCell(1).setCellValue(user_name);
            String str = "";
            Integer is_reg = item.getIs_reg();
            if (is_reg != null && is_reg == 1) {
                str = "是";
            } else if (is_reg != null && is_reg == 0) {
                str = "否";
            }
            row.createCell(2).setCellValue(str);
            String reg_date = item.getFormat_reg_date();
            if (StringUtils.isBlank(reg_date)) {
                reg_date = "";
            }
            row.createCell(3).setCellValue(reg_date);
            Integer is_real_name = item.getIs_real_name();
            if (is_real_name != null && is_real_name == 1) {
                str = "是";
            } else if (is_real_name != null && is_real_name == 0) {
                str = "否";
            }
            row.createCell(4).setCellValue(str);
            Integer is_tie_card = item.getIs_tie_card();
            if (is_tie_card != null && is_tie_card == 1) {
                str = "是";
            } else if (is_tie_card != null && is_tie_card == 0) {
                str = "否";
            }
            row.createCell(5).setCellValue(str);
            Integer is_trans = item.getIs_trans();
            if (is_trans != null && is_trans == 1) {
                str = "是";
            } else if (is_trans != null && is_trans == 0) {
                str = "否";
            }
            row.createCell(6).setCellValue(str);
            String scan_code_rec = item.getScan_code_rec();
            if (StringUtils.isBlank(scan_code_rec)) {
                scan_code_rec = "";
            }
            row.createCell(7).setCellValue(scan_code_rec);
            String rebate_expire_date = item.getFormat_rebate_expire_date();
            if (StringUtils.isBlank(rebate_expire_date)) {
                rebate_expire_date = "";
            }
            row.createCell(8).setCellValue(item.getFormat_rebate_expire_date());
        }
        return wb;
    }

    /**
     * 导出所有正式名单
     *
     * @param list
     * @return
     */
    @Override
    public XSSFWorkbook export_officialUserList(List<OfficialUser> list) {
        String[] excelHeader = {"客户名称", "手机号", "上报/分配", "上报分配时间", "投资顾问", "客户标识"};
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("official_customer_report");
        sheet.setDefaultColumnWidth(16);
        XSSFRow row0 = sheet.createRow((int) 0);
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
        for (int i = 0; i < excelHeader.length; i++) {
            XSSFCell cell = row0.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
        }
        for (int i = 0; i < list.size(); i++) {
            XSSFRow row = sheet.createRow(i + 1);

            OfficialUser item = list.get(i);
            row.createCell(0).setCellValue(item.getUser_name());
            row.createCell(1).setCellValue(item.getMobile_no());
            String str = "";
            if (item.getReport_or_allot() == 1) {
                str = "上报";
            } else {
                str = "分配";
            }
            row.createCell(2).setCellValue(str);
            row.createCell(3).setCellValue(item.getFormat_report_allot_date());
            row.createCell(4).setCellValue(item.getInvest_adviser());
            row.createCell(5).setCellValue(item.getCustomer_ids());
        }
        return wb;

    }

    /**
     * 导出所有平台分配客户的名单
     *
     * @param list
     * @return
     */
    @Override
    public XSSFWorkbook export_assignUserList(List<AssignUser> list) {
        String[] excelHeader = {"客户名称", "手机号", "注册日期", "身份证号", "是否员工", "资产金额", "性别", "年龄", "出生日期", "邀请人", "邀请人手机", "邀请人是否员工"};
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("assign_customer_report");
        sheet.setDefaultColumnWidth(16);
        XSSFRow row0 = sheet.createRow((int) 0);
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
        for (int i = 0; i < excelHeader.length; i++) {
            XSSFCell cell = row0.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
        }
        for (int i = 0; i < list.size(); i++) {
            XSSFRow row = sheet.createRow(i + 1);

            AssignUser item = list.get(i);
            row.createCell(0).setCellValue(item.getUser_name());
            row.createCell(1).setCellValue(item.getMobile_no());
            row.createCell(2).setCellValue(item.getFormat_reg_date());
            row.createCell(3).setCellValue(item.getId_card_no());
            String str = "";
            if (item.getIs_staff() == 1) {
                str = "是";
            } else if (item.getIs_staff() == 0) {
                str = "否";
            }
            row.createCell(4).setCellValue(str);
            row.createCell(5).setCellValue(item.getAssets());
            String gender = null;
            if (item.getGender() == 1) {
                gender = "男";
            } else {
                gender = "女";
            }
            row.createCell(6).setCellValue(gender);
            row.createCell(7).setCellValue(item.getAge());
            row.createCell(8).setCellValue(item.getFormat_birthday());
            row.createCell(9).setCellValue(item.getInvite_user());
            row.createCell(10).setCellValue(item.getInvite_user_mobile());
            if (item.getInvite_user_is_staff() == 1) {
                str = "是";
            } else {
                str = "否";
            }
            row.createCell(11).setCellValue(str);
        }
        return wb;
    }
}
