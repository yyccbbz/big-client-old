package com.evergrande.report.controller;

import com.evergrande.base.controller.BaseController;
import com.evergrande.report.entity.RptAssetsBalance;
import com.evergrande.report.entity.RptCurrentSalesDetail;
import com.evergrande.report.entity.RptSalesDetail;
import com.evergrande.report.service.ExportService;
import com.evergrande.report.service.ReportService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("report")
public class ReportController extends BaseController {

	@Autowired
	private ExportService exportService;

	@Autowired
	private ReportService reportService;

	@RequestMapping("assets_balance_main")
	public String assets_balance_main(HttpServletRequest request) {
		String strCurrPage = request.getParameter("currPage");
		int currPage = 1;
		if (strCurrPage != null) {
			currPage = Integer.parseInt(strCurrPage);
		}
		request.setAttribute("currPage", currPage);
		return "report/assets_balance_main";
	}

	@RequestMapping("findRptAssetsBalanceListByPage")
	@ResponseBody
	public String findRptAssetsBalanceListByPage(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> paramMap = initRequestParam(request);
		String resultJson = reportService.findRptAssetsBalanceListByPage(paramMap);
		return resultJson;
	}

	@RequestMapping("export_assets_balance")
	public void export_assets_balance(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> paramMap = initRequestParam(request);
		List<RptAssetsBalance> list = reportService.findAllRptAssetsBalance(paramMap);
		XSSFWorkbook wb = exportService.export_assets_balance(list);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=assets_balance_report.xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	/**销售明细（定期）*/

	/**
	 * 页面跳转
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("sales_detail_main")
	public String sales_detail_main(HttpServletRequest request) {
		String strCurrPage = request.getParameter("currPage");
		int currPage = 1;
		if (strCurrPage != null) {
			currPage = Integer.parseInt(strCurrPage);
		}
		request.setAttribute("currPage", currPage);
		return "report/sales_detail_main";
	}

	/**
	 * 分页条件查询
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("findRptSalesDetailListByPage")
	@ResponseBody
	public String findRptSalesDetailListByPage(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> paramMap = initRequestParam(request);
		String resultJson = reportService.findRptSalesDetailListByPage(paramMap);
		return resultJson;
	}


	/**
	 * excel 导出文件
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("export_sales_detail")
	public void exportSalesDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String begin_buy_date = request.getParameter("begin_buy_date");
		String end_buy_date = request.getParameter("end_buy_date");
		/*if (begin_buy_date == null || "".equals(begin_buy_date)) {
			begin_buy_date = "1970-01-01";
		} else {*/
		if (begin_buy_date != null && !"".equals(begin_buy_date)){
			begin_buy_date = begin_buy_date + " 00:00:00";
		}

		if (end_buy_date != null && !"".equals(end_buy_date)){
			end_buy_date = end_buy_date + " 23:59:59";
		}
		map.put("begin_buy_date", begin_buy_date);
		map.put("end_buy_date", end_buy_date);
		List<RptSalesDetail> list = reportService.findAllRptSalesDetailByBuyDate(map);
		XSSFWorkbook wb = exportService.exportSalesDetail(list);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=sales_detail_report.xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	/**销售明细（活期）*/

	/**
	 * 页面跳转
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("current_sales_detail")
	public String current_sales_detail(HttpServletRequest request) {
		String strCurrPage = request.getParameter("currPage");
		int currPage = 1;
		if (strCurrPage != null) {
			currPage = Integer.parseInt(strCurrPage);
		}
		request.setAttribute("currPage", currPage);
		return "report/current_sales_detail";
	}

	/**
	 * 分页条件查询
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("findRptCurrentSalesDetailListByPage")
	@ResponseBody
	public String findRptCurrentSalesDetailListByPage(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> paramMap = initRequestParam(request);
		String resultJson = reportService.findRptCurrentSalesDetailListByPage(paramMap);
		return resultJson;
	}

	/**
	 * 导出excel文件
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("export_current_sales_detail")
	public void exportCurrentSalesDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String begin_buy_date = request.getParameter("begin_buy_date");
		String end_buy_date = request.getParameter("end_buy_date");
		/*if (begin_buy_date == null || "".equals(begin_buy_date)) {
			begin_buy_date = "1970-01-01";
		} else {*/
		if (begin_buy_date != null && !"".equals(begin_buy_date)){
			begin_buy_date = begin_buy_date + " 00:00:00";
		}
		if (end_buy_date != null && !"".equals(end_buy_date)){
			end_buy_date = end_buy_date + " 23:59:59";
		}
		map.put("begin_buy_date", begin_buy_date);
		map.put("end_buy_date", end_buy_date);
		List<RptCurrentSalesDetail> list = reportService.findAllRptCurrentSalesDetailByBuyDate(map);
		XSSFWorkbook wb = exportService.exportCurrentSalesDetail(list);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=current_sales_detail_report.xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}
}
