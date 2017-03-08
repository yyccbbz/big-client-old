<!DOCTYPE html>
<%@ page language='java' pageEncoding='UTF-8' contentType='text/html;charset=UTF-8' %>
<html lang="en">
	<head>
   		<title>Bootstrap 实例 - 响应式表格</title>
   		<jsp:include  page="/pageStatic/evergrandeBase.jsp"/> 
	</head>
	<body>
	    <link href="${localProjectName}/pageCss/sys/common/dataTable.css?v=${revision}" rel="stylesheet">
		<script src="${localProjectName}/pageJs/report/rptAssetsBalanceViewList.js?v=${revision}"></script>
		<!-- 弹出提示框 start -->
		<link href="${localProjectName}/pageCss/modal-div.css?v=${revision}" rel="stylesheet">
		<script src="${localProjectName}/pageJs/base/modal-div.js?v=${revision}"></script>
		<!-- 弹出提示框 end -->
		<div id="searchDiv" class="title-search-div" style="display:none">
			<%-- <input type="hidden" id="order_id" name="order_id" value="${order_id}" />
			<input type="hidden" id="order_type" name="order_type" value="${order_type}" /> --%>
		</div>
		<input type="hidden" id="currPage" name="currPage" value="${currPage}" />
		<div id="titleDiv" class="body-title">
			资产余额表
			<div id="searchDiv" class="title-search-div" style="">
				<input id="search_context" class="form-control contextInput" type="text" value="" placeholder="请输入客户姓名或手机号" >
				<label id="seachLabel" class="input_lable"/>
			</div>
		</div>
		<div id="splitDiv" class="split_title"></div><!-- title和内容页的分割线 -->
		<div id="operationDiv" class="operation_div" >
			<ul class="first">   	
				<button type="button" class="btn btn-active" style="width:100px" onclick="exportExcel();"  data-toggle="button">导出到Excel</button>
			</ul>
		</div><!-- operationDiv end 操作按钮DIV -->
		<div class="table-responsive data-list-table-div">
			<table class="table-hover evergrande-table"> <!-- table size and style are aligned in "evergrande-table" -->
				<thead>
					<tr class="thread-tr">
						<th><span>客户姓名</span></th>
						<th><span>注册手机</span></th>
						<th><span>注册日期</span></th>
						<th><span>当前AUM（资产总额）</span></th>
						<th><span>AUM时间点</span></th>
					</tr>
				</<thead>
				<tbody id="tbody"></tbody>
			</table>

			<div id="paginationDiv" class="pageit_div"></div>
		</div>
		<jsp:include  page="/pageStatic/evergrandeBaseOnload.jsp"/>
	</body>
</html>
			