/**
 * 初始化页面元素大小
 */
var coloneTgNum = 0;
var tagMainBodyHeight = 0;
var bodyHeight = $(document).height() ;//整个BODY高度
$(document).ready(function(){
	//titleDiv高度100px;detail-info高度70px;indv-info占剩余的1/5	
	var otherHeight =  bodyHeight-100;//去掉title和第一个栏位后剩下的位置
	otherHeight = otherHeight-70;//去掉活动输入框后剩余
	
	var tagMarginTopHeight = otherHeight*0.05;//标签块距离上一DIV的距离
	tagMainBodyHeight = otherHeight*0.85;//标签块DIV高度
	var campOperBodyTopHeight = otherHeight*0.1*0.2;
	var campOperBodyHeight = otherHeight*0.1*0.8;//活动操作按钮DIV高度
	
	$("#tag-main-body").css("margin-top",tagMarginTopHeight);
	$("#tag-main-body").css("height",tagMainBodyHeight);
	$("#operation_body").css("margin-top",campOperBodyTopHeight);
	$("#operation_body").css("height",campOperBodyHeight);
	$("#msg_body").mCustomScrollbar({});
	
	initDataFunction('/camp/campView/listTagData.do',null,getTagListData);//获取渠道下拉选数据、活动产业下拉选数据
	
	initDataFunction('/camp/campView/indsAdChnl.do',null,initIndsAdChnl);//初始化标签信息
	getTagGrpData();
	
	$("#submitCamp").click(function(){
		if(checkLoading()){
			showLoading();
			return ;
		}
		
		var campNmValue = $("#camp_nm").val();
		if(containSpecial(campNmValue)){
			var param = new Object();
			param['hide_back_btn'] = true;
			createModalAlter("活动名称需由汉字、数字、下划线、减号组成",'取消','确定',null,param);
		}
		
		var checkResult = checkNotNull();
		if(!checkResult){
			return ;
		}
		
		//检查日期大小关系
		var startDate= new Date($("#camp_start_dt").val());
		var endDate = new Date($("#camp_end_dt").val());
		if(endDate<startDate){
			var param = new Object();
			param['hide_back_btn'] = true;
			createModalAlter("开始时间大于结束时间，请重新设置",'取消','确定',null,param);
			return;
		}
		
		//检查模板中参数值是否过长,最长限制为70
//		var allParaValus = $(".paraValue");
//		var notAllowParams = "";
//		$.each(allParaValus,function(i,paraValue){
//			if($(paraValue).val().length>70){
//				var paramNm = $(paraValue).parent().pre().find("input");
//			}
//		});
		
		var checkResult = checkTmp();//检查输入项是否合法
		
		if(checkResult){//输入项合法,更新活动状态为:"提交审批"
			$("#camp_status_cd").val("02");
			var showMsg = "<div style='width:90%;height:90%;margin-left:5%;margin-right:5%;margin-top:20%;line-height:20px;font-size:15px;text-align:center;'>活动提交将进入审批流程，不可再被修改！是否确定提交？</div>";
//			createModalAlter(showMsg,'取消','确定',submitCamp);
			var param = new Object();
			createModalAlter(showMsg,'取消','确定',submitCamp,param,null);
		}
//		var showMsg = "<div style='margin-left:10px;margin-right:10px;line-height:20px;'>提交活动后活动将自动保存,审批通过后不可再被修改！是否确定提交？</div>";
		
	});
	
	$("#paramHelp").mouseover(function(){
		var param = new Object();
		param['hide_back_btn'] = true;
		var msgCont = "短信参数有两种,参数名称为可选项,参数值为必输入项:<br/>" +
				"1 )&nbsp;固定值参数<BR/>" +
				"2 )&nbsp;变量值参数,如username,请在填写参数值的时候使用#号引用起来<br/>" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对于变量值参数,现在固定两个字段,以后有可能增加:<br/>" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用户名：#username#<br/>" +
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前积分：#point_current#";
		var showMsg = "<div style='width:90%;height:90%;margin-left:5%;margin-right:5%;margin-top:0;line-height:20px;font-size:15px;text-align:left;'>"+msgCont+"</div>";
		createModalAlter(showMsg,'取消','确定',null,param);
//		alert("我去");
	});
	
	//短信模板ID变化时去后台拿短信模板内容
	$("#sms_tmp_id").change(function(){
		getMsgContent();
	});
		
	var uom = new Date(new Date()-0+1*86400000);  
	var nowMonth = uom.getMonth()+1;
	if(nowMonth<10){
		nowMonth = "0"+nowMonth;
	}
	
	var todayDate = new Date();
	
	var nowDay = todayDate.getDate();
	if(nowDay<10){
		nowDay = "0"+nowDay;
	}
	var nowDateStr = uom.getFullYear() + "-" + nowMonth + "-" + nowDay;  
//	alert(nowDateStr);
	$("#camp_start_dt").attr("data-date",nowDateStr);
	$("#camp_start_dt").datetimepicker({
//		format: "yyyy-MM-dd",
//        autoclose: true,//参数在设置changeDate事件后不好使,需要手动关闭$('#startDateLabel').datetimepicker('hide');
//        language : 'zh-CN',
//        minView : 2,
//        todayBtn: true,
//        todayHighlight : 1,
        
        format : 'yyyy-mm-dd hh:ii',
		language : 'zh-CN',
		startDate:nowDateStr,
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		startView : 2,
		minView : 0,
		todayBtn: false,
		forceParse : 0
	});
	
	$("#camp_end_dt").attr("data-date",nowDateStr);
	$("#camp_end_dt").datetimepicker({
		format : 'yyyy-mm-dd hh:ii',
		language : 'zh-CN',
		startDate:nowDateStr,
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		startView : 2,
		minView : 0,
		todayBtn: false,
		forceParse : 0
    });
	
});

var setting = {//ZTREE参数
	check: {
		enable: true//是否开启checkbox复选框
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	view:{
		showLine:false,//是否显示节点间连接线
		showIcon:false//是否显示节点前图标
	},
	callback: {
		onCheck: checkTagBefore//checkbox选中事件
	}
};

/**
 * 获取当前活动已保存标签信息
 */
function getTagGrpData(){
	
	$("#selected-tag").mCustomScrollbar({});
}

/**
 * 获取系统现有标签&收藏标签数据
 * @param tagListData
 */
function getTagListData(tagListData){
	
	$("#tag-market").show();
	$("#tag-fav").hide();
	$("#tagMarketSpan").css("font-weight","bold");
	
	$("#tagFavSpan").click(function(){
		$("#tag-fav").show();
		$("#tagFavSpan").css("font-weight","bold");
		$("#tag-market").hide();
		$("#tagMarketSpan").css("font-weight","");
	});
	
	$("#tagMarketSpan").click(function(){
		$("#tag-fav").hide();
		$("#tagFavSpan").css("font-weight","");
		$("#tag-market").show();
		$("#tagMarketSpan").css("font-weight","bold");
	});
	
	$("#addTagGrpLb").click(function(){
		addTagGrp();
		clearCheckNode();
	});
	
	initFavList(tagListData.favTagList);
	initMarketList(tagListData.marketTagList);
	
	$("#tag-fav").mCustomScrollbar({});
	$("#tag-market").mCustomScrollbar({});
}

function initFavList(favListData){
	var nodesArray = new Array();
	if(favListData!=null&&favListData.length>0){}
	else return;
	$.each(favListData,function(i,tagData){
		var tagId = tagData.tagId;
		var tagNm = tagData.tagNm;
		var parTagId = tagData.parTagId;
		var tagCtgyNm = tagData.tagCtgyNm;
		
		var isLastNode = tagId.toString().indexOf('prt_');
		if(isLastNode<0){//标签节点
			var parTagIdLength = parTagId.length;
			var realTagCtgyId = parTagId.substring(4,parTagIdLength);
			nodesArray.push({id:tagId, pId:parTagId, name:tagNm,tagCtgyId:realTagCtgyId,tagCtgyNm:tagCtgyNm});
		}else{//标签类别节点
			nodesArray.push({id:tagId, pId:parTagId, name:tagNm, open:true,nocheck:true});
		}
		
	});
		
	$.fn.zTree.init($("#tag-fav-tree"), setting, nodesArray);
	var zTree = $.fn.zTree.getZTreeObj("tag-fav-tree");
	zTree.expandAll(false); 
	type = { "Y":"ps", "N":"ps"};
	zTree.setting.check.chkboxType = type;
}

function initMarketList(marketListData){
	var nodesArray = new Array();
	
	$.each(marketListData,function(i,tagData){
		var tagId = tagData.tagId;
		var tagNm = tagData.tagNm;
		var parTagId = tagData.parTagId;
		var tagCtgyNm = tagData.tagCtgyNm;
		
		var isLastNode = tagId.toString().indexOf('prt_');
		if(isLastNode<0){//标签节点
			var parTagIdLength = parTagId.length;
			var realTagCtgyId = parTagId.substring(4,parTagIdLength);
			nodesArray.push({id:tagId, pId:parTagId, name:tagNm,tagCtgyId:realTagCtgyId,tagCtgyNm:tagCtgyNm});
		}else{//标签类别节点
			nodesArray.push({id:tagId, pId:parTagId, name:tagNm, open:true,nocheck:true});
		}
		
	});
		
	$.fn.zTree.init($("#tag-market-tree"), setting, nodesArray);
	var zTree_all = $.fn.zTree.getZTreeObj("tag-market-tree");
	zTree_all.expandAll(false); 
	type_all = { "Y":"ps", "N":"ps"};
	zTree_all.setting.check.chkboxType = type_all;
}



function initCampList( campDataList ){

	/*
	var nodesArray = new Array();

	$.each(campDataList,function(i, campData){
		var tagId = tagData.tagId;
		var tagNm = tagData.tagNm;
		var parTagId = tagData.parTagId;
		var tagCtgyNm = tagData.tagCtgyNm;

		nodesArray.push( {id:tagId, pId:parTagId, name:tagNm, tagCtgyId:realTagCtgyId,tagCtgyNm:tagCtgyNm} );

	});

	$.fn.zTree.init($("#tag-market-tree"), setting, nodesArray);
	var zTree_all = $.fn.zTree.getZTreeObj("tag-market-tree");
	zTree_all.expandAll(false);
	type_all = { "Y":"ps", "N":"ps"};
	zTree_all.setting.check.chkboxType = type_all;

	*/
}


function checkTagBefore(event, treeId, treeNode){
	var param = new Object();
	param["event"] = event;
	param["treeId"] = treeId;
	param["treeNode"] = treeNode;
	var checked = treeNode.checked;
	
	checkTag(param);
	
//	if(checked){
//		createModalAlter("确认将标签加入到活动中？",'取消','确定',checkTag,param);
//	}else{
//		createModalAlter("确认将标签从活动中移除？",'取消','确定',checkTag,param);
//	}
	
}

function checkTag(param){
	//tag-market-tree tag-fav-tree
	var event = param.event;
	var treeId = param.treeId;
	var treeNode = param.treeNode;
	var hasAppend = $("#finalTagGrp").attr("hasAppend");
	var checked = treeNode.checked;
	if(checked){
		addNodeToTagPlace(treeId, treeNode);
	}else{
		removeNodeFromTagPlace(treeId, treeNode);
	}
	
	//根据勾选标签实时计算命中人群数
	sumIndvNum();

}

function addTagGrp(){
	var tags = $("#finalTagGrp ul li");
	if(tags.length>0){//已选标签
		var  finalTagGrpHtml = $("#finalTagGrp").html();
		$("#tag_grp_div_1").append("<table id='tags_grp_tab_"+coloneTgNum+"' class='tag_grp_tab' ><tr >" +
				"<td><div id='tags_grp_"+coloneTgNum+"' class='tag_grp_info'>"+finalTagGrpHtml+"</div></td>" +
				"<td><label class='sprite-img-second' style='background-position:-102px -26px;margin-top:10%;' onclick=removeTagGrp('"+coloneTgNum+"')></label>	</td>" +
				"</tr></table>");
		
		$("#finalTagGrp ul").remove();
		
		coloneTgNum++;
		
		//将新增标签组之前的所有勾选节点置为不可变状态
//		var treeObj = $.fn.zTree.getZTreeObj("tag-fav-tree");
//		var allCheckObjs = treeObj.getCheckedNodes(true);
//		$.each(allCheckObjs,function(i,checkNode){
//			checkNode.chkDisabled = true;
//			treeObj.updateNode(checkNode);
//		});
//		
//		var otherTreeObj = $.fn.zTree.getZTreeObj("tag-market-tree");
//		var otherAllCheckObjs = otherTreeObj.getCheckedNodes(true);
//		$.each(otherAllCheckObjs,function(i,checkNode){
//			checkNode.chkDisabled = true;
//			otherTreeObj.updateNode(checkNode);
//		});
		
	}else{//还未选择任何标签
		var param = new Object();
		param['hide_back_btn'] = true;
		createModalAlter("当前未选择任何标签,请在当前标签集中选择标签",'取消','确定',null,param);
	}
}

function clearCheckNode(){
	//将新增标签组之前的所有勾选节点置为不可变状态
	var treeObj = $.fn.zTree.getZTreeObj("tag-fav-tree");
	if(treeObj!=null){
		var allCheckObjs = treeObj.getCheckedNodes(true);
		if(allCheckObjs!=null){
			$.each(allCheckObjs,function(i,checkNode){
				checkNode.checked = false;
				treeObj.updateNode(checkNode);
			});
		}
	}
	
	var otherTreeObj = $.fn.zTree.getZTreeObj("tag-market-tree");
	if(otherTreeObj!=null){
		var otherAllCheckObjs = otherTreeObj.getCheckedNodes(true);
		if(otherAllCheckObjs!=null){
			$.each(otherAllCheckObjs,function(i,checkNode){
				checkNode.checked = false;
				otherTreeObj.updateNode(checkNode);
			});
		}
		
	}
}

//function checkNodeDisabled(){
//	//将新增标签组之前的所有勾选节点置为不可变状态
//	var treeObj = $.fn.zTree.getZTreeObj("tag-fav-tree");
//	if(treeObj!=null){
//		var allCheckObjs = treeObj.getCheckedNodes(true);
//		if(allCheckObjs!=null){
//			$.each(allCheckObjs,function(i,checkNode){
//				checkNode.chkDisabled = true;
//				treeObj.updateNode(checkNode);
//			});
//		}
//	}
//	
//	var otherTreeObj = $.fn.zTree.getZTreeObj("tag-market-tree");
//	if(otherTreeObj!=null){
//		var otherAllCheckObjs = otherTreeObj.getCheckedNodes(true);
//		if(otherAllCheckObjs!=null){
//			$.each(otherAllCheckObjs,function(i,checkNode){
//				checkNode.chkDisabled = true;
//				otherTreeObj.updateNode(checkNode);
//			});
//		}
//		
//	}
//}


function removeTagGrp(tgGrpId){
	var tagGrpDivId = "tags_grp_"+tgGrpId;
	var tagGrpTabId = "tags_grp_tab_"+tgGrpId;
	var allCheckTags = $("#"+tagGrpDivId+" span");
	//将之前已选中的标签修改为可选&未选中状态(之前已经在其他方法中置为不可选&选中状态)
	$.each(allCheckTags,function(i,checkTag){
//		alert($(checkTag).attr("id")+","+$(checkTag).text());
		var noCheckTagId = $(checkTag).attr("id");
		var treeObj = $.fn.zTree.getZTreeObj("tag-fav-tree");
		if(treeObj!=null){
			var node = treeObj.getNodeByParam("id", noCheckTagId, null);
			if(node!=null){
				node.chkDisabled=false;
				node.checked=false;
				treeObj.updateNode(node);
			}
		}
		
		var otherTreeObj = $.fn.zTree.getZTreeObj("tag-market-tree");
		if(otherTreeObj!=null){
			var otherNode = otherTreeObj.getNodeByParam("id", noCheckTagId, null);
			if(otherNode!=null){
				otherNode.chkDisabled=false;
				otherNode.checked=false;
				otherTreeObj.updateNode(otherNode);
			}
		}
	});
	
	//删除标签组在页面的数据
	$("#"+tagGrpTabId).remove();
	sumIndvNum();
}

/**
 * 当在标签市场或者标签收藏中选中某标签后，需要将另外一个树中的标签也置为已选状态
 */
function checkOtherTree(treeId,tagId,checked){
	var otherTreeId = null;
	if(treeId=='tag-fav-tree'){
		otherTreeId = "tag-market-tree";
	}else{
		otherTreeId = "tag-fav-tree";
	}
	var treeObj = $.fn.zTree.getZTreeObj(otherTreeId);
	if(treeObj!=null){
		var checkNode = treeObj.getNodeByParam("id", tagId, null);
		if(checkNode!=null){
			checkNode.checked = checked;
			treeObj.updateNode(checkNode);
		}
	}
	
	
	
}

/**
 * 根据当前勾选的所有标签实时计算命中人群数
 */
function sumIndvNum(){
	var allSelectTagGrp = $(".check-info-list-ul");//取标签,按组取
	var tagGrpArray = new Array();
	$.each(allSelectTagGrp,function(i,selectTag){//每个循环是一组标签
		var allTags = $(selectTag).find("span");
		var tagGrpArraySeq = new Array();
		$.each(allTags,function(s,tagData){
			tagGrpArraySeq.push({"tagId":tagData.id,"tagCtgyId":$(tagData).attr("tagctgyid")});
		});
		tagGrpArray.push(tagGrpArraySeq);
	});
	jQuery.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : postUrl + '/camp/campView/sumIndvNum.do',
		data : JSON.stringify(tagGrpArray),
		dataType : 'json',
		success : function(data) {
			$("#indv_num").text(data);
			$("#indv_num_hid").val(data);
		},
		error : function(data) {
			alert("error");
		}
	});
}

function addOrUpdateSaveCamp(isSubmit){
	
	var param = new Array();
	param.push({"camp_id":$("#camp_id").val()});
	param.push({"camp_nm":$("#camp_nm").val()});
	param.push({"camp_desc":$("#camp_nm").val()});
	param.push({"start_dt":$("#camp_start_dt").val()});
	param.push({"end_dt":$("#camp_end_dt").val()});
	param.push({"indv_num":$("#indv_num_hid").val()});
	param.push({"camp_chnl_cd":$("#camp_chnl_cd").val()});
	param.push({"camp_inds_cd":$("#camp_inds_cd").val()});
	
//	param.push({"camp_templt_id":$("#camp_templt_id").val()});
//	param.push({"camp_status_cd":$("#camp_status_cd").val()});
//	param.push({"user_id":$("#user_id").val()});
//	param.push({"created_ts":$("#created_ts").val()});
//	param.push({"updated_ts":$("#updated_ts").val()});
	if($("#camp_status_cd").val()!=null&&$("#camp_status_cd").val()!=''){
		param.push({"camp_status_cd":$("#camp_status_cd").val()});
	}
	
	//取已选标签:
	var tagGrpArray = new Array();
	var allSelectTagGrp = $(".check-info-list-ul");//取标签,按组取
	$.each(allSelectTagGrp,function(i,selectTag){
		var allTags = $(selectTag).find("span");
		$.each(allTags,function(s,tagData){
			if(tagGrpArray[i]==null)
				tagGrpArray[i] = new Array();
			tagGrpArray[i].push({"tag_id":tagData.id,"tag_ctgy_id":$(tagData).attr("tagctgyid")});
		});
	});
	//取短信模板配置数据
	var sms_tmp_id = $("#sms_tmp_id").val();
	var sms_datas = new Object();
	if(sms_tmp_id!=null&&sms_tmp_id!=''){
		sms_datas['sms_tmp_id'] = sms_tmp_id;
		var msg_conent = $("#msg_conent_ta").val();
		sms_datas['msg_conent'] = msg_conent;
		//获取参数数据
		var msgParamTrs = $("#msg_params tr");
		var msgParamArray = new Array();
		$.each(msgParamTrs,function(i,msgParamTr){
//			alert($(msgParamTr).attr("id"));
			if('addParamTr'==$(msgParamTr).attr("id")){
				return true;
			}
			var msgParamTds = $(msgParamTr).find("input");
			if(msgParamTds.length==2){
				var paramName = $(msgParamTds[0]).val();
//				if(paramName!=null&&paramName!=''){//参数名称可以为空
					var paramValue = $(msgParamTds[1]).val();
//					alert(paramName+":"+paramValue);
					msgParamArray.push({"paramName":paramName,"paramValue":paramValue});
//				}
			}
		});
//		alert(JSON.stringify(msgParamArray));
		sms_datas['msg_params'] = msgParamArray;
	}
//	alert(JSON.stringify(sms_datas));
//	if(true)
//		return;
	var jsonObj = new Object();
	jsonObj = {"camp_id":$("#camp_id").val(),
			   "camp_nm":$("#camp_nm").val(),
			   "camp_desc":$("#camp_nm").val(),
			   "start_dt":$("#camp_start_dt").val(),
			   "end_dt":$("#camp_end_dt").val(),
			   "camp_chnl_cd":$("#camp_chnl_cd").val(),
			   "camp_status_cd":$("#camp_status_cd").val(),
			   "camp_inds_cd":$("#camp_inds_cd").val(),
			   "indv_num":$("#indv_num_hid").val(),
			   "camp_templt_id":sms_tmp_id,
			   "tag_grp":tagGrpArray,
			   "sms_tmp":sms_datas};
//	initDataFunction('/evt/evtView/addSave.do',param,saveCallback);
	showLoading();
	
	jQuery.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : postUrl + '/camp/campMag/addSave.do',
		data : JSON.stringify(jsonObj),
		dataType : 'json',
		beforeSend: function () {
//			showLoading();
		},
		success : function(data) {
			setTimeout(function () { 
				removeLoading();
				if(isSubmit){
					var param = new Object();
					param['hide_back_btn'] = true;
					createModalAlter("提交成功",'取消','确定',backToList,param);
					
				}else{
					var param = new Object();
					param['hide_back_btn'] = true;
					createModalAlter("暂存成功",'取消','确定',backToEdit,param);
					
				}
			},500);
			
			
		}
		
	});
	
	
}

function backToList(){
	var sendParam = new Array();
	newPageByPost(sendParam,'/camp/campView/main.do');
}

function backToEdit(){
	var sendParam = new Array();
	sendParam.push({id:"camp_id",value:$("#camp_id").val()});
	newPageByPost(sendParam,'/camp/campView/evtEdit.do');
}

function saveCallback(){
	var param = new Object();
	param['hide_back_btn'] = true;
	createModalAlter("新增成功",'取消','确定',null,param);
}

/**
 * 提交活动,先校验短信模板是否设置,之后保存,最后提交
 */
function submitCamp(){	
	
	addOrUpdateSaveCamp(true);
}

function saveCamp(){
	if(checkLoading()){
		showLoading();
		return ;
	}
	
	var campNmValue = $("#camp_nm").val();
	if(containSpecial(campNmValue)){
		var param = new Object();
		param['hide_back_btn'] = true;
		createModalAlter("活动名称需由汉字、数字、下划线、减号组成",'取消','确定',null,param);
		return ;
	}
	
	var checkResult = checkNotNull();
	if(!checkResult){
		return ;
	}
	
	//检查日期大小关系
	var startDate= new Date($("#camp_start_dt").val());
	var endDate = new Date($("#camp_end_dt").val());
	if(endDate<startDate){
		var param = new Object();
		param['hide_back_btn'] = true;
		createModalAlter("开始时间大于结束时间，请重新设置",'取消','确定',null,param);
		return;
	}
	
	addOrUpdateSaveCamp(false);
}

/**
 * 检查短信模板是否设置,只校验短信模板ID,因为模板的参数可能为空.所以不做校验
 */
function checkTmp(){
	var sms_tmp_id = $("#sms_tmp_id").val();
	if(sms_tmp_id==null||sms_tmp_id==''){
		var param = new Object();
		param['hide_back_btn'] = true;
		createModalAlter('渠道模板未设置','取消','确定',null,param);
		return false;
	}
	return true;
	
}

function showMsgMadol(){
	//检查是否设置了渠道,未设置渠道不允许设置模板
	var campChnlCdVal = $("#camp_chnl_cd").val();
	if(campChnlCdVal==null||campChnlCdVal==''){
		var param = new Object();
		param['hide_back_btn'] = true;
		createModalAlter("请设置活动渠道",'取消','确定',null,param);
		return;
	}
	
	$("#templateModal").modal({backdrop:false,show:true});
}


function show_deDuplicate_config_dialog() {

	alert("TODO:");

}

function addNodeToTagPlace(treeId, treeNode){
	var tagCtgyId = treeNode.pId;
	var tagCtgyNm = treeNode.tagCtgyNm;
	var tagNm =  treeNode.name;
	var tagId = treeNode.id;
	var checked = treeNode.checked;
	var realTagCtgyId = treeNode.tagCtgyId;
//	alert(realTagCtgyId);
	var tags = $("#finalTagGrp ul li");
	if(tags.length>0){//已选标签
		var ctgyObj = $("#finalTagGrp ul #tagCtgyId_"+tagCtgyId);
		if(ctgyObj.length>0){
			var labelObj = $("#finalTagGrp ul #tagCtgyId_"+tagCtgyId+" label");
			labelObj.before("<span class='tagSpan' tagCtgyId='"+realTagCtgyId+"' id='"+tagId+"'>,"+tagNm+"</span>");
//			ctgyObj.append("<span class='tagSpan' tagCtgyId='"+realTagCtgyId+"' id='"+tagId+"'>,"+tagNm+"</span>");
		}else{
			var ulObj = $("#finalTagGrp ul");
			ulObj.append("<li class='check-info-list-ul-li' id='tagCtgyId_"+tagCtgyId+"'>"+tagCtgyNm+":"+"<span class='tagSpan' tagCtgyId='"+realTagCtgyId+"' id='"+tagId+"'>"+tagNm+"</span>" +
					"<label onclick='removeTagCtgyBefore(this)' class='glyphicon glyphicon-remove remove_label'></label></li>");
		}
	}else{
		var ulObj = $("<ul></ul>");
		ulObj.attr("class","check-info-list-ul");
		ulObj.attr("id","tagCtgyId_ul_"+tagCtgyId);
		ulObj.append("<li class='check-info-list-ul-li' id='tagCtgyId_"+tagCtgyId+"'>"+tagCtgyNm+":"+"<span class='tagSpan' tagCtgyId='"+realTagCtgyId+"' id='"+tagId+"'>"+tagNm+"</span>" +
				"<label onclick='removeTagCtgyBefore(this)' class='glyphicon glyphicon-remove remove_label'></label></li>");
		
		$("#finalTagGrp").append(ulObj);
		$("#finalTagGrp").attr("hasAppend","true");
	}
	
//	alert($("#finalTagGrp").html());
	
//	alert($("#finalTagGrp").html());
	checkOtherTree(treeId,tagId,checked);//操作另外一个树
}

function removeNodeFromTagPlace(treeId, treeNode){
	var tagCtgyId = treeNode.pId;
	var tagId = treeNode.id;
	var checked = treeNode.checked;
	
	var allSpan = $("#finalTagGrp ul #tagCtgyId_"+tagCtgyId+" span");
	
	if(allSpan.length==1){
		$("#finalTagGrp ul #tagCtgyId_"+tagCtgyId).remove();
		var liCnt = $("#finalTagGrp ul li").length;
		if(liCnt==0){
			$("#finalTagGrp ul").remove();
		}
		
	}else{
		var tagObj = $("#finalTagGrp ul #tagCtgyId_"+tagCtgyId+" #"+tagId);
		if(tagObj.length>0)
			tagObj.remove();
		var firstSpan = $("#finalTagGrp ul #tagCtgyId_"+tagCtgyId+" span:eq(0)");
		var spanText = firstSpan.text();
		spanText = spanText.replace(',','');
		firstSpan.text(spanText);
	}

	checkOtherTree(treeId,tagId,checked);//操作另外一个树
	
	
}

/**
 * 短信模板参数设置
 */
function msgAddParam(maxlength){
	
	var trObj = $("<tr class='msg_params_tr'></tr>");
	var firstTd = $("<td class='msg_params_td' style='width:50%;'></td>");
	var secondTd = $("<td class='msg_params_td' style='width:50%;'></td>");
//	var thrdTd = $("<td class='msg_params_td' style='width:8%;padding-left:4%;'></td>");
	
	firstTd.append("<input style='margin-right:5%;' type='text' class='form-control'/>");
	secondTd.append("<input style='margin-left:5%;' type='text' class='form-control  paraValue' maxValuelength='"+maxlength+"'/>");
//	thrdTd.append("<label class='sprite-img-second' style='background-position:-102px -26px;float:left;' onclick='msgRemoveParam(this)'></label>");
	
	trObj.append(firstTd);
	trObj.append(secondTd);
//	trObj.append(thrdTd);
	
	
	$("#msg_params table").append(trObj);
	
}

function msgRemoveParam(thisObj){
//	alert($(thisObj).attr("class"));
	$(thisObj).parent().parent().remove();
}

function viewTestMsg(){
	//判断测试手机号是否为空
	var test_nums = $("#test_nums").val();
	if(test_nums==null||test_nums==''){
		var param = new Object();
		param['hide_back_btn'] = true;
		createModalAlter("请填写测试手机号",'取消','确定',null,param);
		return;
	}else{
		var numsArray = test_nums.split(',');
		var wrongNums = "";
		$.each(numsArray,function(i,numVal){
			
			var numVal=numVal.replace(/\ +/g,"");//去掉空格
			numVal=numVal.replace(/[ ]/g,"");    //去掉空格
			numVal=numVal.replace(/[\r\n]/g,"");//去掉回车换行
			if(numVal.length!=11){
				if(wrongNums.length>0){
					wrongNums = wrongNums+ "<br>" + numVal;
				}else{
					wrongNums = "<br>" +numVal;
				}
				
			}
		});
		if(wrongNums!=null&&wrongNums!=''){
			var param = new Object();
			param['hide_back_btn'] = true;
			var showMsg = "<div style='width:90%;height:90%;margin-left:5%;margin-right:5%;margin-top:20%;line-height:20px;font-size:15px;text-align:center;'>手机号错误:"+wrongNums+"</div>";
			createModalAlter(showMsg,'取消','确定',null,param);
			return;
		}
	}
	
	//判断参数是否为空
	var paramValues = $("#msg_params table .paraValue");
	if(paramValues!=null&&paramValues.length>0){
		var msgContent = "";
		$.each(paramValues,function(i,paramValue){
			//maxValuelength
			
			var maxValuelength = $(paramValue).attr("maxValuelength");
			var parmVal = $(paramValue).val();
			
			if(parmVal==null||parmVal==''){//判断短信参数内容是否为空
				msgContent = msgContent+"第"+(i+1)+"个参数为空,请输入内容<br/>";
			}else{//参数不为空,判断字符串长度,如果字符串为变量参数($username$ $point_current$),不校验长度
				var noNeedLength = parmVal.indexOf("#");
				if(noNeedLength>=0){//目前变量参数只支持两个:$username$ $point_current$
					if(parmVal.indexOf('#username#')!=0&&parmVal.indexOf('#point_current#')!=0){
						msgContent = msgContent+"第"+(i+1)+"个参数非法,变量参数只支持:#username# #point_current#<br/>";
					}
				}else{
					if(parmVal.length>maxValuelength){
						msgContent = msgContent+"第"+(i+1)+"个参数的值过长,最大长度:"+maxValuelength+",当前长度:"+parmVal.length+"<br/>";
					}
				}
			}
			
		});
		if(msgContent!=null&&msgContent!=''){
			var param = new Object();
			param['hide_back_btn'] = true;
			var showMsg = "<div style='width:90%;height:90%;margin-left:5%;margin-right:5%;margin-top:20%;line-height:20px;font-size:15px;text-align:center;'>"+msgContent+"</div>";
			createModalAlter(showMsg,'取消','确定',null,param,null);
			return ;
		}
	}
	
	//取短信模板配置数据
	var sms_tmp_id = $("#sms_tmp_id").val();
	var sms_datas = new Object();
	if(sms_tmp_id!=null&&sms_tmp_id!=''){
		sms_datas['sms_tmp_id'] = sms_tmp_id;
		//获取参数数据
		var msgParamTrs = $("#msg_params tr");
		var msgParamArray = new Array();
		$.each(msgParamTrs,function(i,msgParamTr){
			var msgParamTds = $(msgParamTr).find("input");
			if(msgParamTds.length==2){
				var paramName = $(msgParamTds[0]).val();
//				if(paramName!=null&&paramName!=''){//参数名称可以为空
					var paramValue = $(msgParamTds[1]).val();
//					alert(paramName+":"+paramValue);
					msgParamArray.push({"paramName":paramName,"paramValue":paramValue});
//				}
			}
		});
		sms_datas['msg_params'] = msgParamArray;
	}
//	alert(JSON.stringify(sms_datas));
	initDataFunctionByJson('/camp/campMag/viewTestMsg.do',sms_datas,viewAndTest);
//	initDataForText('/camp/campMag/viewTestMsg.do',null,viewAndTest);
}

function viewAndTest(msgCont){
	var contentVal = msgCont.msgStr;
	if(contentVal==null||contentVal==''){
		var param = new Object();
		param['hide_back_btn'] = true;
		createModalAlter("未找到相应的短信模板",'','确定',null,param);
		return;
	}
	
	var msgText = "<div style='width:90%;height:90%;margin-left:5%;margin-right:5%;margin-top:5%;line-height:20px;font-size:15px;text-align:left;'>"+msgCont.msgStr+"</div>";
	var param = new Object();
	createModalAlter(msgText,'取消','发送',sendTestMsg,param,'短信预览');
}

function sendTestMsg(){
	//取短信模板配置数据
	var sms_tmp_id = $("#sms_tmp_id").val();
	
	var sms_datas = new Object();
	if(sms_tmp_id!=null&&sms_tmp_id!=''){
		sms_datas['sms_tmp_id'] = sms_tmp_id;
		
		//测试手机号
		var test_nums = $("#test_nums").val();
		sms_datas['test_nums'] = test_nums;
		//获取参数数据
		var msgParamTrs = $("#msg_params tr");
		var msgParamArray = new Array();
		$.each(msgParamTrs,function(i,msgParamTr){
			var msgParamTds = $(msgParamTr).find("input");
			if(msgParamTds.length==2){
				var paramName = $(msgParamTds[0]).val();
//				if(paramName!=null&&paramName!=''){//参数名称可以为空
					var paramValue = $(msgParamTds[1]).val();
//					alert(paramName+":"+paramValue);
					msgParamArray.push({"paramName":paramName,"paramValue":paramValue});
//				}
			}
		});
		sms_datas['msg_params'] = msgParamArray;
	}
	initDataFunctionByJson('/camp/campMag/sendTestMsg.do',sms_datas,sendTestMsgResult);
//	initDataForText('camp/campMag/sendTestMsg.do',null,sendTestMsgResult);
}

function sendTestMsgResult(repData){
	var param = new Object();
	param['hide_back_btn'] = true;
	var sendResultStr = "";
	if(repData!=null&&repData.length>0){
		$.each(repData,function(i,resultDt){
			var phoneNo = resultDt.phoneNo;
			var sendMsg = resultDt.sendMsg;
			if(sendResultStr==''){
				sendResultStr = "<br>手机号:"+phoneNo+" 发送结果:"+sendMsg;
			}else{
				sendResultStr = sendResultStr + "<br>"+ phoneNo+":"+sendMsg;
			}
		});
	}
	
	var showMsg = "<div style='width:90%;height:90%;margin-left:5%;margin-right:5%;margin-top:20%;line-height:20px;font-size:15px;text-align:center;'>"+sendResultStr+"</div>";
	createModalAlter(showMsg,'取消','确定',null,param,null);
	
//	createModalAlter("消息发送成功",'取消','确定',null,param);
}	


/**
 * 清楚短信模板内容
 */
function clearSmsTemp(){
	$("#sms_tmp_id").val("");
	$("#msg_conent_ta").val("");
	$("#test_nums").val("");
	var msgParamTrs = $("#msg_params tr");
	$("#addParamTr").show();
	var msgParamArray = new Array();
	$.each(msgParamTrs,function(i,msgParamTr){
		var msgParamTds = $(msgParamTr).find("input");
		
		if($(msgParamTds[0]).attr("disabled")==null&&i>1){
			$(msgParamTr).remove();
		}else{
			$(msgParamTds[0]).val("");
			$(msgParamTds[1]).val("");
		}
	});
	
}

function initIndsAdChnl(jsonData){
	var chnlDataList = jsonData.chnlDataList;
	var indsDataList = jsonData.indsDataList;
	
	$.each(chnlDataList,function(i,chnlData){
		var chnlCd = chnlData.chnlCd;
		var chnlNm = chnlData.chnlNm;
		$("#camp_chnl_cd").append("<option value='"+chnlCd+"'>"+chnlNm+"</option>");
	});
	
	$.each(indsDataList,function(i,indsData){
		var indsCd = indsData.indsCd;
		var indsNm = indsData.indsNm;
		$("#camp_inds_cd").append("<option value='"+indsCd+"'>"+indsNm+"</option>");
	});
	
}

function removeTagCtgyBefore(thisObj){
	var param = new Object();
	param["thisObj"] = thisObj;
	createModalAlter("确认将标签从活动中移除？",'取消','确定',removeTagCtgy,param);
}

function removeTagCtgy(param){
//	alert("removeTagCtgy is running");
	var thisObj = param.thisObj;
	var spanObjs = $(thisObj).parent().find("span");
	$.each(spanObjs,function(i,spanObj){
		var tagId = $(spanObj).attr("id");
		
		var treeMarketObj = $.fn.zTree.getZTreeObj("tag-market-tree");
		var treeFavObj = $.fn.zTree.getZTreeObj("tag-fav-tree");
		
		if(treeMarketObj!=null){
			var checkNode = treeMarketObj.getNodeByParam("id", tagId, null);
			if(checkNode!=null){
				checkNode.checked = false;
				checkNode.chkDisabled = false;
				treeMarketObj.updateNode(checkNode);
			}
		}
		
		if(treeFavObj!=null){
			var checkNode = treeFavObj.getNodeByParam("id", tagId, null);
			if(checkNode!=null){
				checkNode.checked = false;
				checkNode.chkDisabled = false;
				treeFavObj.updateNode(checkNode);
			}
		}
		
	});
//	alert("each is end running");
	var divObj = $(thisObj).parent().parent().parent();//找到标签组最外层DIV
//	alert(divObj.html());
	var divId = $(divObj).attr("id");

	var liSonForUl = $(thisObj).parent().parent().find("li");

	if(liSonForUl.length==1){
		$(thisObj).parent().parent().remove();
		return;
	}
	
	$(thisObj).parent().remove();

	var liObjs = divObj.find("li");
	
	if(liObjs.length==0&&divId!='finalTagGrp'){
		divObj.parent().parent().remove();//删除整个TR
	}
	
	sumIndvNum();
}

/**
 * 获取短信模板内容
 */
function getMsgContent(){
	var smsTmpId = $("#sms_tmp_id").val();//短信模板ID
	if(smsTmpId==null||smsTmpId==''){
		var param = new Object();
		param['hide_back_btn'] = true;
		createModalAlter("请输入短信模板ID",'','确定',null,param);
		return;
	}
	var param = new Array();
	param.push({id:"sms_tmp_id",value:smsTmpId});
	initDataFunction('/camp/campMag/getSmsCont.do',param,initMsgTmpInfo);//获取渠道下拉选数据、活动产业下拉选数据
}

/**
 * 将取到的短信模板内容初始化到页面:短信模板内容,参数信息
 * @param backData
 */
function initMsgTmpInfo(backData){
//	alert(JSON.stringify(backData));
	$("#msg_conent_ta").val("");
	var contentVal = backData.model_content;
	if(contentVal==null||contentVal==''){
		var param = new Object();
		param['hide_back_btn'] = true;
		createModalAlter("未找到相应的短信模板",'','确定',null,param);
		return;
	}
	var paramList = backData.paramList;
//	alert(JSON.stringify(paramList));
	if(paramList!=null){
		$("#addParamTr").hide();
		$(".msg_params_tr").remove();
		$.each(paramList,function(i,param){
			var paramLength = param.length;
			msgAddParam(paramLength);
//			alert(paramLength);
		});
	}else{
		$("#addParamTr").show();
		$(".msg_params_tr").remove();
	}
	$("#msg_conent_ta").val(contentVal);//初始化短信模板内容textarea
	
}

/**
 * 检查短信模板输入项合法性
 */
function checkSmsTemp(){
	var smsTmpId = $("#sms_tmp_id").val();//短信模板ID
	if(smsTmpId==null||smsTmpId==''){
		var param = new Object();
		param['hide_back_btn'] = true;
		createModalAlter("请输入短信模板ID",'','确定',null,param);
		return;
	}
	
	var paramValues = $("#msg_params table .paraValue");
	if(paramValues!=null&&paramValues.length>0){
		var msgContent = "";
		$.each(paramValues,function(i,paramValue){
			//maxValuelength
			
			var maxValuelength = $(paramValue).attr("maxValuelength");
			var parmVal = $(paramValue).val();
			
			if(parmVal==null||parmVal==''){//判断短信参数内容是否为空
				msgContent = msgContent+"第"+(i+1)+"个参数为空,请输入内容<br/>";
			}else{//参数不为空,判断字符串长度,如果字符串为变量参数($username$ $point_current$),不校验长度
				var noNeedLength = parmVal.indexOf("#");
				if(noNeedLength>=0){//目前变量参数只支持两个:$username$ $point_current$
					if(parmVal.indexOf('#username#')!=0&&parmVal.indexOf('#point_current#')!=0){
						msgContent = msgContent+"第"+(i+1)+"个参数非法,变量参数只支持:#username# #point_current#<br/>";
					}
				}else{
					if(parmVal.length>maxValuelength){
						msgContent = msgContent+"第"+(i+1)+"个参数的值过长,最大长度:"+maxValuelength+",当前长度:"+parmVal.length+"<br/>";
					}
				}
			}
			
		});
		if(msgContent!=null&&msgContent!=''){
			var param = new Object();
			param['hide_back_btn'] = true;
			var showMsg = "<div style='width:90%;height:90%;margin-left:5%;margin-right:5%;margin-top:20%;line-height:20px;font-size:15px;text-align:center;'>"+msgContent+"</div>";
			createModalAlter(showMsg,'取消','确定',null,param,null);
			return ;
		}
	}
	$('#templateModal').modal('hide');
}
