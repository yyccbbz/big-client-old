var currPage=1;

$(document).ready(function() {
	var currPage = $("#currPage").val();
	refreshPage(currPage);
	$("#seachLabel").click(function(){
		currPage = 1;
		refreshPage(currPage);
	});
});

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
		/*var user_id = dataList[i].user_id;
		var user_nm = dataList[i].user_nm;
		var group_name = dataList[i].group_name;
		if(group_name == null){
			group_name = "";
		}
		var user_real_nm = dataList[i].user_real_nm;
		var create_name = dataList[i].create_name;
		if(create_name == null){
			create_name = "";
		}
		var str_create_time = dataList[i].str_create_time;
		if(str_create_time == null){
			str_create_time = "";
		}
		var update_name = dataList[i].update_name;
		if(update_name == null){
			update_name = "";
		}
		var active_ind = dataList[i].active_ind;*/
		var trObj = $("<tr></tr>");
		if (i % 2 != 0) {
			trObj.attr("class", "active");
		}
		trObj.append("<td>" + dataList[i].user_name + "</td>");
		trObj.append("<td>" + dataList[i].mobile_no + "</td>");
		trObj.append("<td>" + dataList[i].format_reg_date + "</td>");
		trObj.append("<td>" + dataList[i].assets_total + "</td>");
		trObj.append("<td>" + dataList[i].format_aum_time + "</td>");
		tbody.append(trObj);
	}

}

function exportExcel(){    
    location.href = postUrl +"/report/export_assets_balance.do";    
} 

function refreshPage(currPage){
	isInitPage = true;
	showPageForSplit('/report/findRptAssetsBalanceListByPage.do',listData,currPage);
}