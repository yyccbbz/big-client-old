<!DOCTYPE html>
<%@ page language='java' pageEncoding='UTF-8' contentType='text/html;charset=UTF-8' %>
<html lang="en">
	<head>
   		<title>外部拓展客户</title>
   		<jsp:include  page="/pageStatic/evergrandeBase.jsp"/>
	</head>
	<body>
	    <link href="${localProjectName}/pageCss/sys/common/dataTable.css?v=${revision}" rel="stylesheet">
		<script src="${localProjectName}/pageJs/report/extUserViewList.js?v=${revision}"></script>
		<!-- 弹出提示框 start -->
		<link href="${localProjectName}/pageCss/modal-div.css?v=${revision}" rel="stylesheet">
		<script src="${localProjectName}/pageJs/base/modal-div.js?v=${revision}"></script>
		<script type="text/javascript">
			function alertUpload(){
				//alert("正在上传中，请稍候。。。");
				$('#uploadAlert').text("正在上传中，请稍候。。。");
			}
		</script>

		<!-- 弹出提示框 end -->
		<div id="searchDiv" class="title-search-div" style="display:none">
			<%--<input type="hidden" id="order_id" name="order_id" value="${order_id}" />--%>
			<%--<input type="hidden" id="order_type" name="order_type" value="${order_type}" />--%>
		</div>
		<input type="hidden" id="currPage" name="currPage" value="${currPage}" />
		<div id="titleDiv" class="body-title">
            外部拓展客户管理
			<%-- 此处为搜索框位置 --%>
			<div id="searchDiv" class="title-search-div" style="">
				<input id="search_context" class="form-control contextInput" type="text" value="" placeholder="请输入客户姓名或手机号" >
				<label id="seachLabel" class="input_lable"/>
			</div>
		</div>
		<div id="splitDiv" class="split_title"></div><!-- title和内容页的分割线 -->
		<div id="operationDiv" class="operation_div" >
			<ul class="first">
				<form style="width: 600px" action="<%=request.getContextPath()%>/report/ext/upload.do" id="fileForm" method="post" enctype="multipart/form-data" onsubmit="return check()">
					<input style="display:inline-block;width: 300px" class="btn btn-active"  id="file" type="file" name="file" />
					<button id="excel_button" type="submit" onclick="alertUpload();">上传EXCEL</button>
					<span id="uploadAlert" style="color:red;"></span>
				</form>
			</ul>
			<%--<ul class="first">
				<button id="excel_button" type="button" class="btn btn-active" style="width:100px" onclick="clickAddBtnIn();"  data-toggle="button">导入Excel</button>
			</ul>--%>

			<ul class="first">
				<button type="button" class="btn btn-active" style="width:100px" onclick="clickAddBtnAdd();"  data-toggle="button">添加用户</button>
			</ul>
			<%--<ul class="first">
				<button type="button" class="btn btn-active" style="width:100px" onclick="clickAddBtnEdit();"  data-toggle="button">修改用户</button>
			</ul>--%>
			<ul class="first">
				<button type="button" class="btn btn-active" style="width:100px" onclick="clickAddBtnOut();"  data-toggle="button">导出到Excel</button>
			</ul>
		</div><!-- operationDiv end 操作按钮DIV -->

		<div class="table-responsive data-list-table-div">
            <table class="table-hover evergrande-table"> <!-- table size and style are aligned in "evergrande-table" -->
                <thead>
                    <tr class="thread-tr">
                        <%--<th><span>客户ID</span></th>--%>
                        <th><span>手机号码</span></th>
                        <th><span>系统内姓名</span></th>
                        <th><span>是否注册</span></th>
                        <th><span>注册日期</span></th>
                        <th><span>是否实名</span></th>
                        <th><span>是否绑卡</span></th>
                        <th><span>是否有过交易</span></th>
                        <th><span>扫码推荐人</span></th>
                        <th><span>返利失效日期</span></th>
                        <%--<th><span>创建时间</span></th>--%>
                        <%--<th><span>修改时间</span></th>--%>
                        <th><span>操作</span></th>
                    </tr>
                </thead>
				<tbody id="tbody"></tbody>
			</table>

			<div id="paginationDiv" class="pageit_div"></div>
		</div>
		<jsp:include  page="/pageStatic/evergrandeBaseOnload.jsp"/>
	</body>
</html>
			