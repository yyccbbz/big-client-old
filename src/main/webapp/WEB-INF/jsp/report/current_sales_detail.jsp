<!DOCTYPE html>
<%@ page language='java' pageEncoding='UTF-8' contentType='text/html;charset=UTF-8' %>
<html lang="en">
<head>
    <title>活期产品销售明细</title>
    <jsp:include page="/pageStatic/evergrandeBase.jsp"/>
</head>
<body>
<link href="${localProjectName}/pageCss/sys/common/dataTable.css?v=${revision}" rel="stylesheet">
<script src="${localProjectName}/pageJs/report/currentSalesDetailViewList.js?v=${revision}"></script>
<!-- 弹出提示框 start -->
<link href="${localProjectName}/pageCss/modal-div.css?v=${revision}" rel="stylesheet">
<script src="${localProjectName}/pageJs/base/modal-div.js?v=${revision}"></script>
<script language="javascript" type="text/javascript"
        src="${localProjectName}/pageJs/report/My97DatePicker/WdatePicker.js"></script>
<!-- 弹出提示框 end -->
<div id="searchDiv" class="title-search-div" style="display:none">
    <%-- <input type="hidden" id="order_id" name="order_id" value="${order_id}" />
    <input type="hidden" id="order_type" name="order_type" value="${order_type}" /> --%>
</div>
<input type="hidden" id="currPage" name="currPage" value="${currPage}"/>
<div id="titleDiv" class="body-title">
    销售明细表【活期】
    <div id="searchDiv" class="title-search-div" style="">
        <input id="search_context" class="form-control contextInput" type="text" value="" placeholder="请输入客户姓名或手机号">
        <label id="seachLabel" class="input_lable">
    </div>
</div>
<div id="splitDiv" class="split_title"></div><!-- title和内容页的分割线 -->
<div id="operationDiv" class="operation_div">
    <ul class="first">
        <button type="button" class="btn btn-active" style="width:100px" onclick="exportCurrentExcel();" data-toggle="button">
            导出到Excel
        </button>
        <label class="date-label">起始申购日期：</label>
        <input id="begin_buy_date" name="begin_buy_date" type="text" class="date-input-text" onClick="WdatePicker()"
               placeholder="起始购买日期" style="width:137px"/>
        <label class="date-label">截止申购日期：</label>
        <input id="end_buy_date" name="end_buy_date" type="text" class="date-input-text" onClick="WdatePicker()"
               placeholder="截止购买日期" style="width:137px"/>
        <button type="button" id="queryBtn" class="btn btn-active" style="width:73px" data-toggle="button">查询</button>
    </ul>
</div><!-- operationDiv end 操作按钮DIV -->
<div class="table-responsive data-list-table-div">
    <table class="table-hover evergrande-table"> <!-- table size and style are aligned in "evergrande-table" -->
        <thead>
        <tr class="thread-tr">
            <th><span>客户姓名</span></th>
            <th class="td_mobile_width"><span>手机号码</span></th>
            <th><span>上报/分配</span></th>
            <th><span>上报/分配日期</span></th>
            <th><span>投资顾问</span></th>
            <th><span>客户标识</span></th>
            <th><span>基础产品名称</span></th>
            <th><span>申购金额</span></th>
            <th><span>申购日期</span></th>
        </tr>
        </thead>
        <!-- table size and style are aligned in "evergrande-table" -->
        <tbody id="tbody"></tbody>
    </table>

    <div id="paginationDiv" class="pageit_div"></div>
</div>
<jsp:include page="/pageStatic/evergrandeBaseOnload.jsp"/>
</body>
</html>
			