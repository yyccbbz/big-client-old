package com.evergrande.report.service;

import com.evergrande.report.entity.RptAssetsBalance;
import com.evergrande.report.entity.RptCurrentSalesDetail;
import com.evergrande.report.entity.RptSalesDetail;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportServiceImpl implements ExportService {

	public HSSFWorkbook export_assets_balance(List<RptAssetsBalance> list) {
		String[] excelHeader = { "客户姓名", "注册手机", "注册日期", "当前AUM（资产总额）", "AUM时间点" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("assets_balance_report");
		sheet.setDefaultColumnWidth(21);
		HSSFRow row0 = sheet.createRow((int) 0);
		// HSSFRow row1 = sheet.createRow((int) 1);
		HSSFCellStyle style = wb.createCellStyle();
		// HSSFCellStyle bg_color_style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		// style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		// style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// bg_color_style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		// bg_color_style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		// bg_color_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		for (int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row0.createCell(i);
			cell.setCellValue(excelHeader[i]);
			cell.setCellStyle(style);
		}
		for (int i = 0; i < list.size(); i++) {
			HSSFRow row = sheet.createRow(i + 1);
			RptAssetsBalance item = list.get(i);
			row.createCell(0).setCellValue(item.getUser_name());
			row.createCell(1).setCellValue(item.getMobile_no());
			row.createCell(2).setCellValue(item.getFormat_reg_date());
			row.createCell(3).setCellValue(item.getAssets_total());
			row.createCell(4).setCellValue(item.getFormat_aum_time());
		}
		return wb;
	}

	@Override
	public HSSFWorkbook exportSalesDetail(List<RptSalesDetail> list) {
		String[] excelHeader = { "姓名", "手机号", "邀请有礼推荐人", "返利失效日期", "上报/分配", "上报/分配日期", "投资顾问", "客户标识", "基础产品名称", "投资金额",
				"现有资产", "购买日期", "产品成立日期" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sales_detail_report");
		sheet.setDefaultColumnWidth(15);
		sheet.setColumnWidth((short) 0, (short) 2768);
		sheet.setColumnWidth((short) 8, (short) 7115);
		sheet.setColumnWidth((short) 11, (short) 5194);
		HSSFRow row0 = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		for (int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row0.createCell(i);
			cell.setCellValue(excelHeader[i]);
			cell.setCellStyle(style);
		}
		for (int i = 0; i < list.size(); i++) {
			HSSFRow row = sheet.createRow(i + 1);
			RptSalesDetail item = list.get(i);
			row.createCell(0).setCellValue(item.getUser_name());
			row.createCell(1).setCellValue(item.getMobile_no());
			row.createCell(2).setCellValue(item.getInvite_user());
			row.createCell(3).setCellValue(item.getFormat_rebate_expire_date());
			row.createCell(4).setCellValue(item.getStr_report_allot());
			row.createCell(5).setCellValue(item.getFormat_report_allot_date());
			row.createCell(6).setCellValue(item.getInvest_adviser());
			row.createCell(7).setCellValue(item.getCustomer_ind());
			row.createCell(8).setCellValue(item.getBasic_product_name());
			row.createCell(9).setCellValue(item.getInvest_amount());
			row.createCell(10).setCellValue(item.getExist_assets());
			row.createCell(11).setCellValue(item.getFormat_buy_date());
			row.createCell(12).setCellValue(item.getFormat_product_date());
		}
		return wb;
	}

	@Override
	public HSSFWorkbook exportCurrentSalesDetail(List<RptCurrentSalesDetail> list) {
		String[] excelHeader = { "客户姓名", "手机号码", "上报/分配", "上报/分配日期", "投资顾问", "客户标识", "基础产品名称", "申购金额", "申购日期" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("current_sales_detail_report");
		sheet.setDefaultColumnWidth(15);
		sheet.setColumnWidth((short) 0, (short) 2768);
		sheet.setColumnWidth((short) 8, (short) 7115);
		sheet.setColumnWidth((short) 11, (short) 5194);
		HSSFRow row0 = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		for (int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row0.createCell(i);
			cell.setCellValue(excelHeader[i]);
			cell.setCellStyle(style);
		}
		for (int i = 0; i < list.size(); i++) {
			HSSFRow row = sheet.createRow(i + 1);
			RptCurrentSalesDetail item = list.get(i);
			row.createCell(0).setCellValue(item.getUser_name());
			row.createCell(1).setCellValue(item.getMobile_no());
			row.createCell(2).setCellValue(item.getStr_report_allot());
			row.createCell(3).setCellValue(item.getFormat_report_allot_date());
			row.createCell(4).setCellValue(item.getInvest_adviser());
			row.createCell(5).setCellValue(item.getCustomer_ind());
			row.createCell(6).setCellValue(item.getBasic_product_name());
			row.createCell(7).setCellValue(item.getInvest_amount());
			row.createCell(8).setCellValue(item.getFormat_buy_date());
		}
		return wb;
	}

}
