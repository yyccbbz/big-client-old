var currPage=1;
var order_id="user_id";
var order_type="asc";

$(document).ready(function() {
	// var currPage = $("#currPage").val();
	// showPageForSplit('/report/pla/findAssignUserListByPage.do',listData,currPage);
	var currPage = $("#currPage").val();
	refreshPage(currPage);
	$("#seachLabel").click(function(){
		currPage = 1;
		refreshPage(currPage);
	});
});

function refreshPage(currPage){
	isInitPage = true;
	showPageForSplit('/report/pla/findAssignUserListByPage.do',listData,currPage);
}

function clickAddBtnAdd() {
	var currPage = $("#currPage").val();
	// var order_id = $("#order_id").val();
	// var order_type = $("#order_type").val();
	// window.location = postUrl + '/report/ext/extUserAddEdit.do?action=add&currPage='
	// 		+ currPage + "&order_id=" + order_id + "&order_type=" + order_type;
	window.location = postUrl + '/report/pla/assignUserAddEdit.do?action=add&currPage='+ currPage ;
}

function clickAddBtnIn() {
	var currPage = $("#currPage").val();
	// var order_id = $("#order_id").val();
	// var order_type = $("#order_type").val();
	// window.location = postUrl + '/report/ext/extUserAddEdit.do?action=add&currPage='
	// 		+ currPage + "&order_id=" + order_id + "&order_type=" + order_type;
	window.location = postUrl + '/report/pla/.do?action=add&currPage='+ currPage ;
}

function clickAddBtnOut() {
	var currPage = $("#currPage").val();
	// var order_id = $("#order_id").val();
	// var order_type = $("#order_type").val();
	// window.location = postUrl + '/report/ext/extUserAddEdit.do?action=add&currPage='
	// 		+ currPage + "&order_id=" + order_id + "&order_type=" + order_type;
	window.location = postUrl + '/report/pla/export_assignUserList.do?action=add&currPage='+ currPage ;
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

		trObj.append("<td class='td_padding_top'>" + dataList[i].user_name + "</td>");
		trObj.append("<td class='td_padding_top td_mobile_width'>" + dataList[i].mobile_no + "</td>");
		trObj.append("<td class='td_padding_top td_date_width'>" + dataList[i].reg_date + "</td>");
		trObj.append("<td class='td_padding_top td_id_width'>" + dataList[i].id_card_no + "</td>");
		var is_staff = "";
		if (dataList[i].is_staff.valueOf() == "0"){
			is_staff = "否";
		} else if (dataList[i].is_staff.valueOf() == "1"){
			is_staff = "是";
		}
		trObj.append("<td class='td_padding_top'>" + is_staff + "</td>");
		trObj.append("<td class='td_padding_top'>" + dataList[i].assets + "</td>");
		var gender = "";
		if (dataList[i].gender.valueOf() == "0"){
			gender = "女";
		} else if (dataList[i].gender.valueOf() == "1") {
			gender = "男";
		}
		trObj.append("<td class='td_padding_top'>" + gender + "</td>");
		trObj.append("<td class='td_padding_top'>" + dataList[i].age + "</td>");
		/**校验字段*/
		var birthday = "";
		if (dataList[i].birthday) {
			birthday = dataList[i].birthday;
		}
		trObj.append("<td class='td_padding_top td_date_width'>" + birthday + "</td>");
		/**校验字段*/
		var invite_user = "";
		if (dataList[i].invite_user) {
			invite_user = dataList[i].invite_user;
		}
		trObj.append("<td class='td_padding_top'>" + invite_user + "</td>");
		/**校验字段*/
		var invite_user_mobile = "";
		if (dataList[i].invite_user_mobile) {
			invite_user_mobile = dataList[i].invite_user_mobile;
		}
		trObj.append("<td class='td_padding_top td_mobile_width'>" + invite_user_mobile + "</td>");
		/**校验是否员工字段*/
		var invite_user_is_staff = "";
		if (dataList[i].invite_user_is_staff.valueOf() == "0"){
			invite_user_is_staff = "否";
		} else if (dataList[i].invite_user_is_staff.valueOf() == "1") {
			invite_user_is_staff = "是";
		}
		trObj.append("<td class='td_padding_top'>" + invite_user_is_staff + "</td>");
		tbody.append(trObj);
	}

}

function submitDel(delete_user_id){
	createModalAlter('<div style="width:90%;height:90%;margin-left:5%;margin-right:5%;margin-top:9%;line-height:37px;text-align:center;">删除后，数据将不可恢复。<br/>确定要删除此用户吗？</div>','取消','确认',afterDelUser,delete_user_id);
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


