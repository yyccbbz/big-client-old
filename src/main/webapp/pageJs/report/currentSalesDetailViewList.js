var currPage=1;

$(document).ready(function() {
	var currPage = $("#currPage").val();
	refreshPage(currPage);
	$("#seachLabel").click(function(){
		currPage = 1;
		$("#begin_buy_date").val("");
		$("#end_buy_date").val("");
		refreshPage(currPage);
	});
	$("#queryBtn").click(function(){
		currPage = 1;
		$("#search_context").val("");
		refreshPage(currPage);
	});
});

function clickAddBtn() {
	var currPage = $("#currPage").val();
	var order_id = $("#order_id").val();
	var order_type = $("#order_type").val();
	window.location = postUrl + '/sys/user/userAddEdit.do?action=add&currPage='
			+ currPage + "&order_id=" + order_id + "&order_type=" + order_type;
}

/**
 * 获取用户列表数据
 */
function listData(pageDataObj) {
	var tbody = $("#tbody");
	var webAppRoot = "/" + window.location.pathname.substr(1).split('/')[0];
	currPage= pageDataObj.currPage;//当前页号
	$("#currPage").val(currPage);
	var pageSize= pageDataObj.pageSize;//当前页面显示条数
	var totalNum= pageDataObj.totalNum;//数据总数
	var dataList= pageDataObj.list;//当前页面元素集
	tbody.empty();
	if (dataList != null && dataList.length > 0) {
	} else {
		return;
	}
	for (var i = 0; i < dataList.length; i++) {
		var trObj = $("<tr></tr>");
		if (i % 2 != 0) {
			trObj.attr("class", "active");
		}
		trObj.append("<td>" + dataList[i].user_name + "</td>");
		trObj.append("<td>" + dataList[i].mobile_no + "</td>");
		trObj.append("<td>" + dataList[i].str_report_allot + "</td>");
		trObj.append("<td>" + dataList[i].format_report_allot_date + "</td>");
		trObj.append("<td>" + dataList[i].invest_adviser + "</td>");
		trObj.append("<td>" + dataList[i].customer_ind + "</td>");
		trObj.append("<td>" + dataList[i].basic_product_name + "</td>");
		trObj.append("<td>" + dataList[i].invest_amount + "</td>");
		trObj.append("<td>" + dataList[i].format_buy_date + "</td>");
		tbody.append(trObj);
	}

}

function exportCurrentExcel(){
	var begin_buy_date = $("#begin_buy_date").val();
	var end_buy_date = $("#end_buy_date").val();
    location.href = postUrl +"/report/export_current_sales_detail.do?begin_buy_date="+begin_buy_date+"&end_buy_date="+end_buy_date;
} 

function refreshPage(currPage){
	isInitPage = true;
	//var begin_buy_date = $("#begin_buy_date").val();
	//var end_buy_date = $("#end_buy_date").val();
	showPageForSplit('/report/findRptCurrentSalesDetailListByPage.do',listData,currPage);
}
