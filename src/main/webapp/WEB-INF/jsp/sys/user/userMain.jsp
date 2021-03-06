<!DOCTYPE html>
<%@ page language='java' pageEncoding='UTF-8' contentType='text/html;charset=UTF-8' %>
<html lang="en">
	<head>
   		<title>Bootstrap 实例 - 响应式表格</title>
   		<jsp:include  page="/pageStatic/evergrandeBase.jsp"/> 
	</head>
	<body>
	    <link href="${localProjectName}/pageCss/sys/common/dataTable.css?v=${revision}" rel="stylesheet">
		<script src="${localProjectName}/pageJs/sys/user/userViewList.js?v=${revision}"></script>
		<!-- 弹出提示框 start -->
		<link href="${localProjectName}/pageCss/modal-div.css?v=${revision}" rel="stylesheet">
		<script src="${localProjectName}/pageJs/base/modal-div.js?v=${revision}"></script>
		<!-- 弹出提示框 end -->
		<div id="searchDiv" class="title-search-div" style="display:none">
			<input type="hidden" id="order_id" name="order_id" value="${order_id}" />
			<input type="hidden" id="order_type" name="order_type" value="${order_type}" />
		</div>
		<input type="hidden" id="currPage" name="currPage" value="${currPage}" />
		<div id="titleDiv" class="body-title">
			用户管理
		</div>
		<div id="splitDiv" class="split_title"></div><!-- title和内容页的分割线 -->
		<div id="operationDiv" class="operation_div" >
			<ul class="first">   	
				<button type="button" class="btn btn-active" style="width:100px" onclick="clickAddBtn();"  data-toggle="button">新建用户</button>
			</ul>
		</div><!-- operationDiv end 操作按钮DIV -->
		<div class="table-responsive data-list-table-div">
			<table class="table table-hover evergrande-table"> <!-- table size and style are aligned in "evergrande-table" -->
				<thead>
				<tr id="thread-tr">
					<th class="title_td_css td_user_id">
						<span id="user_id" class='glyphicon'>ID</span>
					</th>
					<th class="title_td_css td_user_name">
						<span id="user_nm" class='glyphicon'>用户名</span>
					</th>
					<th class="title_td_css td_user_realname">
						<span id="user_real_nm" class='glyphicon'>真实姓名</span>
					</th>
					<th class="title_td_css td_user_creator">
						<span id="create_name" class='glyphicon'>创建者</span>
					</th>
					<th class="title_td_css td_user_date_and_time">
						<span id="create_ts" class='glyphicon'>创建时间</span>
					</th>
					<th class="title_td_css td_user_modifier">
						<span id="update_name" class='glyphicon'>修改者</span>
					</th>
					<th class="title_td_css td_user_date_and_time">
						<span id="update_ts" class='glyphicon'>修改时间</span>
					</th>
					<th class="title_td_css td_user_operation" style="text-align:center;">
						<span class='glyphicon' style="cursor:default;">操作</span>
					</th>
				</tr>
				</thead>
			<!-- </table>

			<table class="table table-hover evergrande-table" id="user_main_table"> -->
				<!-- table size and style are aligned in "evergrande-table" -->
				<tbody id="tbody"></tbody>
			</table>

			<div id="paginationDiv" class="pageit_div"></div>
		</div>
		<jsp:include  page="/pageStatic/evergrandeBaseOnload.jsp"/>
	</body>
</html>
			