package com.evergrande.report.service;

import java.util.List;

import com.evergrande.report.entity.RptCurrentSalesDetail;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.evergrande.report.entity.RptAssetsBalance;
import com.evergrande.report.entity.RptSalesDetail;

public interface ExportService {

	 HSSFWorkbook export_assets_balance(List<RptAssetsBalance> list);
	 
	 HSSFWorkbook exportSalesDetail(List<RptSalesDetail> list);

    HSSFWorkbook exportCurrentSalesDetail(List<RptCurrentSalesDetail> list);
}
