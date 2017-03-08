package com.evergrande.report.service;

import java.util.List;
import java.util.Map;

import com.evergrande.report.entity.RptAssetsBalance;
import com.evergrande.report.entity.RptCurrentSalesDetail;
import com.evergrande.report.entity.RptSalesDetail;

public interface ReportService {

	String findRptAssetsBalanceListByPage(Map<String, Object> paramMap);

	int findRptAssetsBalanceTotalNum(Map<String, Object> paramMap);

	public List<RptAssetsBalance> findAllRptAssetsBalance(Map<String, Object> paramMap);
	
	String findRptSalesDetailListByPage(Map<String, Object> paramMap);

	int findRptSalesDetailTotalNum(Map<String, Object> paramMap);

	public List<RptSalesDetail> findAllRptSalesDetailByBuyDate(Map<String, Object> paramMap);

    String findRptCurrentSalesDetailListByPage(Map<String, Object> paramMap);

	int findRptCurrentSalesDetailTotalNum(Map<String, Object> paramMap);

	List<RptCurrentSalesDetail> findAllRptCurrentSalesDetailByBuyDate(Map<String, Object> paramMap);
}
