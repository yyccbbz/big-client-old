package com.evergrande.report.service;

import java.util.List;
import java.util.Map;

import com.evergrande.report.entity.RptCurrentSalesDetail;
import org.springframework.stereotype.Service;

import com.evergrande.base.service.BaseService;
import com.evergrande.report.entity.RptAssetsBalance;
import com.evergrande.report.entity.RptSalesDetail;
import com.evergrande.sys.entity.PageBean;

@Service
public class ReportServiceImpl extends BaseService implements ReportService {

	@Override
	public String findRptAssetsBalanceListByPage(Map<String, Object> paramMap) {
		PageBean<RptAssetsBalance> pageBean = relationDbTemplate.selectListForPage(
				"rptAssetsBalance.findRptAssetsBalanceListByPage", "rptAssetsBalance.findRptAssetsBalanceTotalNum",
				paramMap);
		String json = objectToJson(pageBean);
		return json;
	}

	@Override
	public int findRptAssetsBalanceTotalNum(Map<String, Object> paramMap) {
		int totalNum = relationDbTemplate.selectOne("rptAssetsBalance.findRptAssetsBalanceTotalNum");
		return totalNum;
	}

	@Override
	public List<RptAssetsBalance> findAllRptAssetsBalance(Map<String, Object> paramMap) {
		List<RptAssetsBalance> list = relationDbTemplate.selectList("rptAssetsBalance.findAllRptAssetsBalance");
		return list;
	}

	@Override
	public String findRptSalesDetailListByPage(Map<String, Object> paramMap) {
		/*String begin_buy_date = "";
		if(){
			begin_buy_date = String.valueOf(paramMap.get("begin_buy_date"));
		}
		String end_buy_date = "";
		if(paramMap.get("end_buy_date") != null){
			end_buy_date = String.valueOf(paramMap.get("end_buy_date"));
		}*/
		/*if (begin_buy_date == null || "".equals(begin_buy_date) || "null".equals(begin_buy_date)) {
			begin_buy_date = "1970-01-01";
		} else {*/
		if (paramMap.get("begin_buy_date") != null  && !"".equals(String.valueOf(paramMap.get("begin_buy_date")))){
			String begin_buy_date = String.valueOf(paramMap.get("begin_buy_date"));
			begin_buy_date = begin_buy_date + " 00:00:00";
			paramMap.put("begin_buy_date", begin_buy_date);
		}
		//}
		/*if (end_buy_date == null || "".equals(end_buy_date) || "null".equals(end_buy_date)) {
			end_buy_date = "2070-01-01";
		} else {*/
		if (paramMap.get("end_buy_date") != null  && !"".equals(String.valueOf(paramMap.get("end_buy_date")))){
			String end_buy_date = String.valueOf(paramMap.get("end_buy_date"));
			end_buy_date = end_buy_date + " 23:59:59";
			paramMap.put("end_buy_date", end_buy_date);
		}
		//}
		PageBean<RptAssetsBalance> pageBean = relationDbTemplate.selectListForPage(
				"rptSalesDetail.findRptSalesDetailListByPage", "rptSalesDetail.findRptSalesDetailTotalNum", paramMap);
		String json = objectToJson(pageBean);
		return json;
	}

	@Override
	public int findRptSalesDetailTotalNum(Map<String, Object> paramMap) {
		String begin_buy_date = String.valueOf(paramMap.get("begin_buy_date"));
		String end_buy_date =  String.valueOf(paramMap.get("end_buy_date"));
		/*if (begin_buy_date == null || "".equals(begin_buy_date) || "null".equals(begin_buy_date)) {
			begin_buy_date = "1970-01-01";
		} else {*/
		if (begin_buy_date != null && !"".equals(begin_buy_date) && !"null".equals(begin_buy_date)){
			begin_buy_date = begin_buy_date + " 00:00:00";
		}
		//}
		/*if (end_buy_date == null || "".equals(end_buy_date) || "null".equals(end_buy_date)) {
			end_buy_date = "2070-01-01";
		} else {*/
		if (end_buy_date != null && !"".equals(end_buy_date) && !"null".equals(end_buy_date)){
			end_buy_date = end_buy_date + " 23:59:59";
		}
		//}
		int totalNum = relationDbTemplate.selectOne("rptSalesDetail.findRptSalesDetailTotalNum");
		return totalNum;
	}

	@Override
	public List<RptSalesDetail> findAllRptSalesDetailByBuyDate(Map<String, Object> paramMap) {
		List<RptSalesDetail> list = relationDbTemplate.selectList("rptSalesDetail.findAllRptSalesDetailByBuyDate",
				paramMap);
		return list;
	}

	/**
	 * 活期分页查询列表
	 *
	 * @param paramMap
	 * @return
	 */
	@Override
	public String findRptCurrentSalesDetailListByPage(Map<String, Object> paramMap) {
		if (paramMap.get("begin_buy_date") != null  && !"".equals(String.valueOf(paramMap.get("begin_buy_date")))){
			String begin_buy_date = String.valueOf(paramMap.get("begin_buy_date"));
			begin_buy_date = begin_buy_date + " 00:00:00";
			paramMap.put("begin_buy_date", begin_buy_date);
		}
		if (paramMap.get("end_buy_date") != null  && !"".equals(String.valueOf(paramMap.get("end_buy_date")))){
			String end_buy_date = String.valueOf(paramMap.get("end_buy_date"));
			end_buy_date = end_buy_date + " 23:59:59";
			paramMap.put("end_buy_date", end_buy_date);
		}
		PageBean<RptCurrentSalesDetail> pageBean = relationDbTemplate.selectListForPage("rptCurrentSalesDetail.findRptCurrentSalesDetailListByPage", "rptCurrentSalesDetail.findRptCurrentSalesDetailTotalNum", paramMap);
		String json = objectToJson(pageBean);
		return json;
	}

	/**
	 * 总数
	 *
	 * @param paramMap
	 * @return
	 */
	@Override
	public int findRptCurrentSalesDetailTotalNum(Map<String, Object> paramMap) {
		String begin_buy_date = String.valueOf(paramMap.get("begin_buy_date"));
		String end_buy_date =  String.valueOf(paramMap.get("end_buy_date"));

		if (begin_buy_date != null && !"".equals(begin_buy_date) && !"null".equals(begin_buy_date)){
			begin_buy_date = begin_buy_date + " 00:00:00";
		}

		if (end_buy_date != null && !"".equals(end_buy_date) && !"null".equals(end_buy_date)){
			end_buy_date = end_buy_date + " 23:59:59";
		}
		int totalNum = relationDbTemplate.selectOne("rptCurrentSalesDetail.findRptCurrentSalesDetailTotalNum");
		return totalNum;
	}


	/**
	 * 活期销售明细查询列表
	 *
	 * 根据购买时间，用于excel导出
	 *
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<RptCurrentSalesDetail> findAllRptCurrentSalesDetailByBuyDate(Map<String, Object> paramMap) {
		List<RptCurrentSalesDetail> list = relationDbTemplate.selectList("rptCurrentSalesDetail.findAllRptCurrentSalesDetailByBuyDate",
				paramMap);
		return list;
	}

}
