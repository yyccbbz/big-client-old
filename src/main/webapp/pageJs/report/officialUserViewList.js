var currPage=1;
var order_id="user_id";
var order_type="asc";

$(document).ready(function() {
	// var currPage = $("#currPage").val();
	// showPageForSplit('/report/off/findOfficialUserListByPage.do',listData,currPage);
	var currPage = $("#currPage").val();
	refreshPage(currPage);
	$("#seachLabel").click(function(){
		currPage = 1;
		refreshPage(currPage);
	});
});

function refreshPage(currPage){
	isInitPage = true;
	showPageForSplit('/report/off/findOfficialUserListByPage.do',listData,currPage);
}


function clickAddBtnAdd() {
	var currPage = $("#currPage").val();
	// var order_id = $("#order_id").val();
	// var order_type = $("#order_type").val();
	// window.location = postUrl + '/report/ext/extUserAddEdit.do?action=add&currPage='
	// 		+ currPage + "&order_id=" + order_id + "&order_type=" + order_type;
	window.location = postUrl + '/report/off/officialUserAddEdit.do?action=add&currPage='+ currPage ;
}

function clickAddBtnIn() {
	var currPage = $("#currPage").val();
	// var order_id = $("#order_id").val();
	// var order_type = $("#order_type").val();
	// window.location = postUrl + '/report/ext/extUserAddEdit.do?action=add&currPage='
	// 		+ currPage + "&order_id=" + order_id + "&order_type=" + order_type;
	window.location = postUrl + '/report/off/.do?action=add&currPage='+ currPage ;
}

function clickAddBtnOut() {
	var currPage = $("#currPage").val();
	// var order_id = $("#order_id").val();
	// var order_type = $("#order_type").val();
	// window.location = postUrl + '/report/ext/extUserAddEdit.do?action=add&currPage='
	// 		+ currPage + "&order_id=" + order_id + "&order_type=" + order_type;
	window.location = postUrl + '/report/off/export_officialUserList.do?action=add&currPage='+ currPage ;
}

//onsubmit事件无法触发，使用绑定按钮点击事件代替
function check() {
	// alert("check");
	var excel_file = $("#file").val();
	if (excel_file == "" || excel_file.length == 0) {
		alert("请选择文件路径！");
		return false;
	} else {
		return true;
	}
}

function alertUpload(){
	alert("正在上传中，请稍候。。。");
	$('#uploadAlert').text("正在上传中，请稍候。。。");
}


/**
 * 获取用户列表数据
 */
function listData(pageDataObj) {
	var tbody = $("#tbody");
	var webAppRoot = "/" + window.location.pathname.substr(1).split('/')[0];
	currPage= pageDataObj.currPage;//当前页号
	$("#currPage").val(currPage);
	/*order_id = $("#order_id").val();
	order_type = $("#order_type").val();
	$("#order_id").val(order_id);
	$("#order_type").val(order_type);*/
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
		var str_update_time = dataList[i].str_update_time;
		if(str_update_time == null){
			str_update_time = "";
		}
		var active_ind = dataList[i].active_ind;*/
		var trObj = $("<tr></tr>");
		if (i % 2 != 0) {
			trObj.attr("class", "active");
		}
		/** private int id;                     //自增id
		 private String user_name;           //客户名称(系统内名称)
		 private String mobile_no;           //手机号(注册手机号)
		 private int report_or_allot;        //上报/分配；上报：1；分配：2
		 private Date report_allot_date;     //上报分配时间
		 private String format_report_allot_date;
		 private String invest_adviser;      //投资顾问
		 private String customer_ids;        //客户标识
		 private Date create_ts;             //创建时间
		 private String format_create_ts;
		 private Date update_ts;             //修改时间
		 private String format_update_ts;*/
		// trObj.append("<td class='td_padding_top'>" + dataList[i].id + "</td>");
		/**校验字段*/
		var mobile_no = "";
		if (dataList[i].mobile_no){
			mobile_no = dataList[i].mobile_no;
		}
		trObj.append("<td class='td_padding_top'>" + mobile_no + "</td>");
		/**校验字段*/
		var report_or_allot = "";
		if (dataList[i].report_or_allot && dataList[i].report_or_allot.valueOf() == "1"){
			report_or_allot = "上报";
		} else if(dataList[i].report_or_allot && dataList[i].report_or_allot.valueOf() == "2"){
			report_or_allot = "分配";
		}
		trObj.append("<td class='td_padding_top'>" + report_or_allot + "</td>");
		/**校验字段*/
		var report_allot_date = "";
		if (dataList[i].report_allot_date){
			report_allot_date = dataList[i].report_allot_date;
		}
		trObj.append("<td class='td_padding_top'>" + report_allot_date + "</td>");
		/**校验字段*/
		var invest_adviser = "";
		if (dataList[i].invest_adviser){
			invest_adviser = dataList[i].invest_adviser;
		}
		trObj.append("<td class='td_padding_top'>" + invest_adviser + "</td>");
		/**校验字段*/
		var customer_ids = "";
		if (dataList[i].customer_ids){
			customer_ids = dataList[i].customer_ids;
		}
		trObj.append("<td class='td_padding_top'>" + customer_ids + "</td>");
		console.log(dataList[i].id);
		trObj.append("<td class='td_padding_top' field='operate'><a name='delete' href='#' onclick='submitDel(" + dataList[i].id + ")'>删除</a></td>");
		tbody.append(trObj);
	}

}

/**编辑用户*/
function editUser(id) {
	var currPage = $("#currPage").val();
	// var order_id = $("#order_id").val();
	// var order_type = $("#order_type").val();
	window.location = postUrl + "/report/off/extUserAddEdit.do?action=edit" + "&id=" + id + "&currPage=" + currPage + "&order_id=" + order_id + "&order_type=" + order_type;
}

/**删除用户*/
function deleteUser(id) {
	// console.log(id);
	var params = new Object();
	params = {hide_back_btn: true};
	var tbody_tr_length = $("#user_main_table tbody tr").length;
	if (tbody_tr_length == 1 && currPage > 1) {
		currPage = currPage - 1;
	}
	jQuery.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: postUrl + '/report/off/deleteUserById.do?currPage=' + currPage + '&id=' + id,
		data: '',
		//dataType : 'json',
		success: function (data) {
			//alert("删除成功");
			createModalAlter('删除成功', '取消', '确认', afterCancelBtn, params);
			showPageForSplit('/report/off/findOfficialUserListByPage.do', listData, currPage);
		},
		error: function (data) {
			//alert("error");
			createModalAlter('error', '取消', '确认', afterCancelBtn, params);
		}
	});

}


function submitDel(id){
	// console.log(id);
	createModalAlter('<div style="width:90%;height:90%;margin-left:5%;margin-right:5%;margin-top:9%;line-height:37px;text-align:center;">删除后，数据将不可恢复。<br/>确定要删除此用户吗？</div>', '取消', '确认', deleteUser, id);
}

function afterDelUser(delete_user_id){
	var params = new Object(); 
	params = {hide_back_btn : true};
	var tbody_tr_length = $("#user_main_table tbody tr").length;
	if(tbody_tr_length == 1 && currPage > 1){
		currPage = currPage - 1;
	}
	jQuery.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : postUrl + '/sys/user/deleteUserById.do?currPage='+currPage+'&delete_user_id='+delete_user_id,
		data : '',
		//dataType : 'json',
		success : function(data) {
			//alert("删除成功");
			createModalAlter('删除成功','取消','确认',afterCancelBtn,params);
			showPageForSplit('/sys/user/findUserListByPageNoAdmin.do',listData,currPage);
		},
		error : function(data) {
			//alert("error");
			createModalAlter('error','取消','确认',afterCancelBtn,params);
		}
	});
}

function submitActiveInd(user_id, active_ind){
	var param = new Object(); 
	param = {user_id : user_id,active_ind : active_ind};
	if(active_ind == 1) {
		createModalAlter('是否激活此用户？','取消','确认',afterActivelUser,param);
	} else {
		createModalAlter('是否禁用此用户？','取消','确认',afterActivelUser,param);
	}
}

function afterActivelUser(param){
	var promptParams = new Object(); 
	promptParams = {hide_back_btn : true};
	jQuery.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : postUrl + '/sys/user/modifyUserActiveInd.do?active_ind='+param.active_ind+'&currPage='+currPage+'&user_id='+param.user_id,
		data : '',
		//dataType : 'json',
		success : function(data) {
			if(param.active_ind == 1){
				//alert("激活成功");
				createModalAlter('激活成功','取消','确认',afterCancelBtn,promptParams);
				showPageForSplit('/sys/user/findUserListByPageNoAdmin.do',listData,currPage);
			} else {
				//alert("禁用成功");
				createModalAlter('禁用成功','取消','确认',afterCancelBtn,promptParams);
			}
			showPageForSplit('/sys/user/findUserListByPageNoAdmin.do',listData,currPage);
		},
		error : function(data) {
			//alert("error");
			createModalAlter('error','取消','确认',afterCancelBtn,promptParams);
		}
	});
}

function afterCancelBtn(){
}


function orderList(orderColumn){

	update_Table_With_orderByColumn_Icon(orderColumn);

	showPageForSplit('/sys/user/findUserListByPageNoAdmin.do', listData, currPage);
}


