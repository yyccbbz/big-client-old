package com.evergrande.report.service;

import java.util.List;

import com.evergrande.report.entity.RptCurrentSalesDetail;

import com.evergrande.report.entity.RptAssetsBalance;
import com.evergrande.report.entity.RptSalesDetail;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface ExportService {

	 XSSFWorkbook export_assets_balance(List<RptAssetsBalance> list);
	 
	 XSSFWorkbook exportSalesDetail(List<RptSalesDetail> list);

    XSSFWorkbook exportCurrentSalesDetail(List<RptCurrentSalesDetail> list);
}
