
var colorArray =  ['#416FA7','#FFCC33','#6CABF6','#ff7f50','#87cefa', '#da70d6', '#32cd32', '#6495ed', 
                   '#ff69b4', '#ba55d3', '#cd5c5c', '#ffa500', '#40e0d0', 
                   '#1e90ff', '#ff6347', '#7b68ee', '#00fa9a', '#ffd700', 
                   '#6b8e23', '#ff00ff', '#3cb371', '#b8860b', '#30e0e0'];

var bodyHeight = $(document).height() ;//整个BODY高度
$(document).ready(function(){
	//titleDiv高度100px;detail-info高度70px;indv-info占剩余的1/5
	var otherHeight =  bodyHeight-170;//去掉title和第一个栏位后剩下的位置
	var indvInfoHeight = otherHeight/4*0.75;//发送情况表格占剩下的1/4
	var labelInfoHeight = otherHeight/4*2.65;//标签详情占剩下的2.5/4
	var operationHeight = otherHeight/4*0.3;//曹组欧按钮占剩下的0.5/4
	//发送情况表格
	$("#indv-info").attr("class","indv-info");
	$("#indv-info").css("height",indvInfoHeight+"px");
	$("#indv-info span").css("margin-top",indvInfoHeight/7*1+"px");//发送情况title
	$("#indv-info span").css("height",indvInfoHeight/7*2+"px");
	//$("#indv-info table").css("margin-top",indvInfoHeight/7+"px");
	$("#indv-info table").css("height",indvInfoHeight/7*4+"px");
	
	//标签详情栏位
	$("#label-info").css("margin-top",labelInfoHeight*0.1+"px");
	$("#label-info").css("height",labelInfoHeight*0.9+"px");
	$("#labelPieDiv_1").css("height",labelInfoHeight*0.9*0.8+"px");
	
	
	$("#operation_body").css("height",operationHeight+"px");
	
	
	
	var param = new Array();
//		$("#campId").val();
	param.push({id:"campId",value:$("#campId").val()});
	//初始化页面活动相关信息
	initDataFunction('/camp/campView/getDetailData.do',param,campPageData);
	
	//初始化短信投放情况数据
	initDataFunction('/camp/campView/sumSendData.do',param,sumSendData);
	
	//初始化 已选标签数据
	initDataFunction('/camp/campView/getTagDetailData.do',param,tagPageData);
	
	
	initDataFunction('/camp/campView/listTagValiData.do',param,listTagValiData);
//		initLabelPie();
	
	var isMa = $("#isMa").val();
	if(isMa=='false'){
		$("#sbmit_button").remove();
	}
	
	initHelpInfo();
	
	$("#tempIconId").click(function(){
		var campId = $("#campId").val();
		var param = new Array();
		param.push({"id":"campId","value":campId}) ;
		initDataFunction('/camp/campView/getCampTmpData.do',param,showMsgMadol);
	});
	
	$("#msg_body").mCustomScrollbar({});
});
	
/**
 * 活动信息初始化
 * @param dataFromService
 */
function campPageData(dataFromService){
	var campId = dataFromService.campId;
	var campNm = dataFromService.campNm;
	var startDt = dataFromService.startDt;
	var endDt = dataFromService.endDt;
	var campChnlNm = dataFromService.campChnlNm ;
	var status = dataFromService.status;
	var statusNm = dataFromService.statusNm;
	var hitCount = dataFromService.hitCount;
//	alert(hitCount);
	$("#hit_info_span").text(hitCount);
	$("#detail-title-foot").append(campId);
	if(campNm.length>15){
		$("#detail-info-ul li:nth-child(1)").append(campNm.substring(0,12)+"...");
		$("#detail-info-ul li:nth-child(1)").attr("title",campNm);
	}else{
		$("#detail-info-ul li:nth-child(1)").append(campNm);
	}
	$("#detail-info-ul li:nth-child(2)").append(statusNm);
	$("#detail-info-ul li:nth-child(3)").append(startDt);
	$("#detail-info-ul li:nth-child(4)").append(endDt);
	$("#detail-info-ul li:nth-child(5) span").before(campChnlNm);
	
	if(status!='02'){
		$("#sbmit_button").remove();
	}
	
}

function sumSendData(dataFromService){
	var indvNum = dataFromService.indvNum;
	var sendNum = dataFromService.sendNum;
	var sendPcnt = dataFromService.sendPcnt;
	var repNum = dataFromService.repNum;
	var repPcnt = dataFromService.repPcnt;
	var replyNum = dataFromService.replyNum;
	var replyPcnt = dataFromService.replyPcnt;
	$("#send-info-val #indvNum").text(indvNum);
	$("#send-info-val #sendNum").text(sendNum);
	$("#send-info-val #sendPcnt").text(sendPcnt);
	$("#send-info-val #repNum").text(repNum);
	$("#send-info-val #repPcnt").text(repPcnt);
	$("#send-info-val #replyNum").text(replyNum);
	$("#send-info-val #replyPcnt").text(replyPcnt);
}

function tagPageData(dataFromService){
	if(dataFromService==null)
		return;
	var childrens = $("#tag-list");//使用美化滑块时会在需要被美化的对象中套一层DIV，之后将原对象中的内容放到这个div中。所以会导致原DIV中CSS失效,并且向对象中追加内容也需要将滑块DIV开率进去
	var tagGrpLst = dataFromService.tagGrpLst;
//	var tagHitCnt = dataFromService.tagHitCnt;
//	$("#hit_info_span").text(tagHitCnt);
	$.each(tagGrpLst,function(i,tagGrpData){
		var tagGrpSeq = tagGrpData.tagGrpSeq;//标签组序号
		var tagGrpDetail = tagGrpData.tagGrpDetail;//标签组内标签类别&标签信息
		
		var tagUl = $("<ul></ul>");
		tagUl.attr("class","check-info-list-ul");
		if(i==0){
			tagUl.attr("style",'margin-top:20px;');
		}
		
		$.each(tagGrpDetail,function(s,tagCtgyData){
			var tagCtgyId = tagCtgyData.tagCtgyId;
			var tagCtgyNm = tagCtgyData.tagCtgyNm;
			var tagDetail = tagCtgyData.tagDetail;
			var str = tagCtgyNm;
			$.each(tagDetail,function(q,tagData){
				var tagId = tagData.tagId;
				var tagNm = tagData.tagNm;
				if(q==0)
					str = str+":"+tagNm;
				else
					str = str+","+tagNm;
			});
			var liObj = $("<li ></li>");
			liObj.attr("class","check-info-list-ul-li");
			liObj.append(str);
			tagUl.append(liObj);
		});
		tagUl.append("<hr class='check-info-list-ul-li'/>");
		childrens.append(tagUl);
	});
	
	$("#tag-list").mCustomScrollbar({});
	
}

function listTagValiData(backData){
	var otherHeight =  bodyHeight-170;//去掉title和第一个栏位后剩下的位置
	var labelInfoHeight = otherHeight/4*2.65;//标签详情占剩下的2.5/4
	var allCnt = backData.allCnt;
	var tagCtgyList = backData.tagCtgyList;
	var tagCtgyLength = tagCtgyList.length;
//	$("#hit_info_span").text(allCnt);
	var allWidth = tagCtgyLength*(labelInfoHeight);
	
	$("#allPiePrtDiv").css("width",(allWidth+100)+"px");
	
	//标签详情栏位
	$("#label-info").css("margin-top",labelInfoHeight*0.1+"px");
	$("#label-info").css("height",labelInfoHeight*0.9+"px");
//	$("#labelPieDiv_1").css("height",labelInfoHeight*0.9*0.8+"px");
	
	require.config({
		paths : {
			echarts : localProjectName+'/utils/echarts/js'
		}
	});
	// 使用
	require([ 'echarts', 
	          'echarts/chart/pie'
	], function(ec) {
		var echartsEc = ec;
		var hasShwTag = new Object();
		$.each(tagCtgyList,function(i,tagCtgyData){
			var tagCtgyId = tagCtgyData.tagCtgyId;
//			var tagCodiCnt = tagCtgyData.tagCodiCnt;
			var tagCtgyNm = tagCtgyData.tagCtgyNm;
			var tagList = tagCtgyData.tagList;
			
			var pieDataArray = new Array();
			var otherCount = allCnt;
			var sNumTmp = 0;
			$.each(tagList,function(s,tagData){
				var tagId = tagData.tagId;
				if(hasShwTag[tagId]!=null){
//					alert(tagData.tagNm);
					return true;
				}else{
//					alert("go on");
					hasShwTag[tagId]=tagId;
				}
				
				var tagNm = tagData.tagNm;
				var tagCodiCnt = tagData.tagCodiCnt;
				if(tagCodiCnt!=null&&tagCodiCnt>0){
					otherCount = otherCount-tagCodiCnt;
				}
				var floatvar = tagCodiCnt/allCnt*100;
				
				var f_x = parseFloat(floatvar);
				f_x = Math.round(floatvar*100)/100;
//				var showNm = tagNm+":"+f_x.toFixed(2)+"%";
				var showNm = tagNm;
				pieDataArray.push({value:tagCodiCnt, name:showNm, itemStyle : {normal : {color : colorArray[s]}}});
//				pieDataArray.push({value:tagCodiCnt, name:tagNm});
				sNumTmp = s;
			});
			
			var floatvar = otherCount/allCnt*100;
			
			var f_x = parseFloat(floatvar);
			f_x = Math.round(floatvar*100)/100;
//			var showNm = "其他:"+f_x.toFixed(2)+"%";
			var showNm = "其他:";
			pieDataArray.push({value:otherCount, name:showNm, itemStyle : {normal : {color : colorArray[++sNumTmp]}}});
//			pieDataArray.push({value:otherCount, name:'其他'});
//			alert(JSON.stringify(pieDataArray));
			$("#startDiv").after("<div id='labelPieDiv_"+i+"' style='float:left;'></div>");
			$("#labelPieDiv_"+i).css("height",labelInfoHeight*0.9*0.8+"px");
			$("#labelPieDiv_"+i).css("width",allWidth/(tagCtgyList.length)+10);
			var myChart = ec.init(document.getElementById('labelPieDiv_'+i));
			var ecConfig = require('echarts/config');
			
			option = {
					tooltip : {
				        show: true,
				        showDelay:500,
//				        showContent:true,
				        formatter: "{b} : {c} ({d}%)"
				    },
					series : [
					          {
					              name:'访问来源',
					              type:'pie',
					              radius : ['35%', '60%'],
					              center : ['50%', '50%'],
					              startAngle : 0,
					              itemStyle : {
					                  normal : {
					                      label : {
					                          show : false
					                      },
					                      labelLine : {
					                          show : false
					                      }
					                  },
					                  emphasis : {
					                      label : {
					                          show : false,
					                          position : 'center',
					                          formatter : showNm+"{d}%",
					                          textStyle : {
					                              color : 'red',
					                              fontSize : '10',
					                              fontFamily : '微软雅黑',
					                              fontWeight : 'bold'
					                          }
					                      }
					                  }
					              },
					              data:pieDataArray
					          },
					          {
					              name:'标签类别',
					              type:'pie',
					              radius : [0, '35%'],
					              center : ['50%', '50%'],
					              itemStyle : {
					                  normal : {
					                	  color : '#2B313A',
					                      label : {
					                    	  show : true,
	                                          position : 'center'
					                      },
					                      labelLine : {
					                          show : false
					                      }
					                  },
					                  emphasis : {
					                      label : {
					                          show : false,
					                          position : 'center',
					                          textStyle : {
					                              fontSize : '30',
//					                              color:'white',
					                              fontWeight : 'bold'
					                          }
					                      }
					                  }
					              },
					              data:[
					                  {value:100, name:tagCtgyNm}
					              ]
					          }
					      ]
				};
			myChart.setOption(option);
		});
		
	});
	
//	
//	$.each(tagCtgyList,function(i,tagCtgyData){
//		var tagCtgyId = tagCtgyData.tagCtgyId;
//		var tagCodiCnt = tagCtgyData.tagCodiCnt;
//		var tagList = tagCtgyData.tagList;
//		
//		$("#startDiv").after("<div id='labelPieDiv_'"+i+" style='float:left;'></div>");
//		$("#labelPieDiv_"+i).css("height",labelInfoHeight*0.9*0.8+"px");
//		$("#labelPieDiv_"+i).css("width","100%");
//		
//	});

}

function initLabelPie(){
	require.config({
		paths : {
			echarts : localProjectName+'/utils/echarts/js'
		}
	});
	// 使用
	require([ 'echarts', 
	          'echarts/chart/pie'
	], function(ec) {
		// 基于准备好的dom，初始化echarts图表
		echartsEc = ec;
		
		for(var i=0;i<2;i++){
			var myChart = ec.init(document.getElementById('labelPieDiv_'+(i+1)));
			var ecConfig = require('echarts/config');
			option = {
					series : [
					          {
					              name:'访问来源',
					              type:'pie',
					              radius : ['35%', '60%'],
					              center : ['50%', '50%'],
					              startAngle : 0,
					              itemStyle : {
					                  normal : {
					                      label : {
					                          show : false
					                      },
					                      labelLine : {
					                          show : false
					                      }
					                  },
					                  emphasis : {
					                      label : {
					                          show : false,
					                          position : 'center',
					                          textStyle : {
					                              fontSize : '30',
					                              fontWeight : 'bold'
					                          }
					                      }
					                  }
					              },
					              data:[
					                  {value:335, name:'直接访问', itemStyle : {normal : {color : '#416FA7'}}},
					                  {value:310, name:'邮件营销', itemStyle : {normal : {color : '#FFCC33'}}},
					                  {value:234, name:'联盟广告', itemStyle : {normal : {color : '#6CABF6'}}}
					              ]
					          },
					          {
					              name:'标签类别',
					              type:'pie',
					              radius : [0, '35%'],
					              center : ['50%', '50%'],
					              itemStyle : {
					                  normal : {
					                	  color : '#2B313A',
					                      label : {
					                          show : false
					                      },
					                      labelLine : {
					                          show : false
					                      }
					                  },
					                  emphasis : {
					                      label : {
					                          show : false,
					                          position : 'center',
					                          textStyle : {
					                              fontSize : '30',
					                              fontWeight : 'bold'
					                          }
					                      }
					                  }
					              },
					              data:[
					                  {value:335, name:'直接访问'}
					              ]
					          }
					      ]
				};
				  
			myChart.setOption(option);
		}
		
		
//		var myChart2 = ec.init(document.getElementById('labelPieDiv_2'));
////		var ecConfig2 = require('echarts/config');
//		option2 = {
//				series : [
//				          {
//				              name:'访问来源',
//				              type:'pie',
//				              radius : ['35%', '60%'],
//				              center : ['50%', '50%'],
//				              startAngle : 0,
//				              itemStyle : {
//				                  normal : {
//				                      label : {
//				                          show : false
//				                      },
//				                      labelLine : {
//				                          show : false
//				                      }
//				                  },
//				                  emphasis : {
//				                      label : {
//				                          show : false,
//				                          position : 'center',
//				                          textStyle : {
//				                              fontSize : '30',
//				                              fontWeight : 'bold'
//				                          }
//				                      }
//				                  }
//				              },
//				              data:[
//				                  {value:335, name:'直接访问', itemStyle : {normal : {color : '#416FA7'}}},
//				                  {value:310, name:'邮件营销', itemStyle : {normal : {color : '#FFCC33'}}},
//				                  {value:234, name:'联盟广告', itemStyle : {normal : {color : '#6CABF6'}}}
//				              ]
//				          },
//				          {
//				              name:'访问来源',
//				              type:'pie',
//				              radius : [0, '35%'],
//				              center : ['50%', '50%'],
//				              itemStyle : {
//				                  normal : {
//				                	  color : '#2B313A',
//				                      label : {
//				                          show : false
//				                      },
//				                      labelLine : {
//				                          show : false
//				                      }
//				                  },
//				                  emphasis : {
//				                      label : {
//				                          show : false,
//				                          position : 'center',
//				                          textStyle : {
//				                              fontSize : '30',
//				                              fontWeight : 'bold'
//				                          }
//				                      }
//				                  }
//				              },
//				              data:[
//				                  {value:335, name:'直接访问'}
//				              ]
//				          }
//				      ]
//			};
//		
//		myChart2.setOption(option2);
	});
}

function passCamp(){
	var param = new Array();
	param.push({id:"camp_status_cd",value:"03"});
	param.push({id:"camp_id",value:$("#campId").val()});
	initDataFunction('/camp/campView/workflowCamp.do',param,passResult);
}

function passResult(){
//	alert("审批通过成功！");
//	var param = new Array();
////	param.push({id:"camp_id",value:$("#campId").val()});
//	newPageByPost(param,'/camp/campView/main.do');
	
	var param = new Object();
	param['hide_back_btn'] = true;
	createModalAlter("审批通过成功",'取消','确定',backToList,param);
	
}

function rejectCamp(){
	var param = new Array();
	param.push({id:"camp_status_cd",value:"10"});
	param.push({id:"camp_id",value:$("#campId").val()});
	initDataFunction('/camp/campView/workflowCamp.do',param,rejectResult);
}

function rejectResult(){
	var param = new Object();
	param['hide_back_btn'] = true;
	createModalAlter("驳回成功",'取消','确定',backToList,param);
	
}

function backToList(){
	var param = new Array();
//	param.push({id:"camp_id",value:$("#campId").val()});
	newPageByPost(param,'/camp/campView/main.do');
}

function initHelpInfo(){
	var param = new Object();
	param['hide_back_btn'] = true;
	$("#sendNumHelp").mouseover(function(){
		var msgCont = "推送成功数量:<br/>从营销平台成功推送到短信运营商处的短信数量";
		var showMsg = "<div class='helper-msg'>"+msgCont+"</div>";
		createModalAlter(showMsg,'取消','确定',null,param);
	});
	$("#sendPcntHelp").mouseover(function(){
		var msgCont = "推送成功占比:<br/>推送成功数量/活动总人数";
		var showMsg = "<div class='helper-msg'>"+msgCont+"</div>";
		createModalAlter(showMsg,'取消','确定',null,param);
	});
	$("#repNumHelp").mouseover(function(){
		var msgCont = "成功发送数量:<br/>短信运营商发送成功的数量";
		var showMsg = "<div class='helper-msg'>"+msgCont+"</div>";
		createModalAlter(showMsg,'取消','确定',null,param);
	});
	$("#repPcntHelp").mouseover(function(){
		var msgCont = "成功发送占比:<br/>成功发送数量/活动总人数";
		var showMsg = "<div class='helper-msg'>"+msgCont+"</div>";
		createModalAlter(showMsg,'取消','确定',null,param);
	});
	$("#replyNumHelp").mouseover(function(){
		var msgCont = "响应短信数量:<br/>有上下行短信类型,有回复的客户数量";
		var showMsg = "<div class='helper-msg'>"+msgCont+"</div>";
		createModalAlter(showMsg,'取消','确定',null,param);
	});
	$("#replyPcntHelp").mouseover(function(){
		var msgCont = "响应短信占比:<br/>响应短信数量/活动总人数";
		var showMsg = "<div class='helper-msg'>"+msgCont+"</div>";
		createModalAlter(showMsg,'取消','确定',null,param);
	});
}

/**
 * 显示短信模板内容
 */
function showMsgMadol(tmpData){
	$("#nullTr").remove();
	var sms_tmp_id = tmpData.sms_tmp_id;
	if(sms_tmp_id!=null){
		var model_content = tmpData.model_content;
		$("#sms_tmp_id").val(sms_tmp_id);
		$("#msg_conent_ta").val(model_content);
		var msg_params = tmpData.msg_params;
		if(msg_params!=null&&msg_params.length>0){
			$.each(msg_params,function(i,param){
				var paramName = param.paramName;
				var paramValue = param.paramValue;
				if(paramValue==null&&i==0){
					var trObj = $("<tr id='nullTr'></tr>");
					trObj.append("<td class='msg_params_td' style='width:50%;'><input style='height:15px;background-color:#F7F7F7;margin-right:5%;' disabled='disabled' type='text' class='form-control'/></td>");
					trObj.append("<td class='msg_params_td' style='width:50%;'><input style='height:15px;background-color:#F7F7F7;margin-left:5%;' disabled='disabled' type='text' class='form-control'/></td>");
					$("#msg_params table").append(trObj);
				}else{
					$("#nullTr").remove();
					var trObj = $("<tr class='msg_params_tr'></tr>");
					trObj.append("<td class='msg_params_td' style='width:46%;'><input style='margin-right:5%;' disabled='disabled' type='text' value='"+paramName+"' class='form-control'/></td>");
					trObj.append("<td class='msg_params_td' style='width:46%;'><input style='margin-left:5%;' disabled='disabled' type='text'  value='"+paramValue+"' class='form-control'/></td>");
					$("#msg_params table").append(trObj);
				}
			
			});
		}else{
			var trObj = $("<tr id='nullTr'></tr>");
			trObj.append("<td class='msg_params_td' style='width:50%;'><input style='height:15px;background-color:#F7F7F7;margin-right:5%;' disabled='disabled' type='text' class='form-control'/></td>");
			trObj.append("<td class='msg_params_td' style='width:50%;'><input style='height:15px;background-color:#F7F7F7;margin-right:5%;' disabled='disabled' type='text' class='form-control'/></td>");
			$("#msg_params table").append(trObj);
//			<tr id="addParamTr" style="">
//				<td class="msg_params_td" style="width:50%;"><input style="height:15px;background-color:#F7F7F7;margin-right:5%;" disabled=disabled type="text" class="form-control"/></td>
//				<td class="msg_params_td" style="width:50%;"><input style="height:15px;background-color:#F7F7F7;margin-left:5%;" disabled=disabled type="text" class="form-control"/></td>
//			</tr>
		}
		$("#templateModal").modal({backdrop:false,show:true});
	}else{
		var param = new Object();
		param['hide_back_btn'] = true;
		createModalAlter("当前活动未设置短信模板",'取消','确定',null,param);
	}
	
}