<!DOCTYPE html>
<%@ page language='java' pageEncoding='UTF-8' contentType='text/html;charset=UTF-8' %>
<html lang="en">
<head>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>大客户中心系统</title>
    <meta name="description" content="Source code generated using layoutit.com">
    <meta name="author" content="LayoutIt!">
</head>
<body>
<%@ include file="/pageStatic/evergrandeBase.jsp" %>
<script src="${localProjectName}/utils/bootstrap/js/bootstrap.min.js?v=${revision}"></script>
<script src="${localProjectName}/pageJs/main.js?v=${revision}"></script>
<div id="rootDiv"
     style="width:100%;height:97%;background: url(/big-client/images/insidepage_bg.png) no-repeat;background-size:100%;">
    <div id="showDiv" style="width:100%;height:100%;max-height:842px;margin:auto;">
        <div id="titleDiv" class="title_div_style">
            <span style="display:inline-block;font-size:22px;font-style:bold;color:#ffffff;margin-right:16px">大客户中心系统</span>
            <!-- <font class="chinese_saying_style">大客户中心系统</font> -->
            <div style="float:right;font-size:14px;font-family:宋体;margin-right:26px">
                <a href="${ctx}/sys/logout.do" style="cursor:pointer;color:#999999;">
                    <label class="glyphicon glyphicon-off" style="cursor:pointer;color:#999999;"></label>
                    <span style="cursor:pointer;color:#999999;">退出</span>
                </a>
                <span style="color:#999999;">&nbsp;<c:out value="${user.user_real_nm}"/></span>
            </div>
        </div>
        <div style="width:15%;height:92%;float:left;background-color:#313743;">
            <ul id="main-nav" class="nav nav-stacked" style="margin:0px;width:100%;background:#313743;">
                <c:forEach items="${menuList }" var="menu" varStatus="status">
                    <c:if test="${menu.menu_level==1 }">
                        <li id="${menu.menu_url}LI" <c:if test="${status.index == 0}">style="border-top:0px"</c:if>>
                            <a href="#${menu.menu_url}" id="${menu.menu_url }A" class="nav-header collapsed"
                               onclick="firstMenuClick('${menu.menu_url}','${menu.menu_ch_name}','${menu.menu_img_url}');"
                               data-toggle="collapse">
                                <label class="${menu.menu_img_url}"></label>
                                <i><c:out value="${menu.menu_ch_name}"/></i>
                                <span style="float:right"><img src="${baseHostUrl}/images/menuDown.png"></span>
                            </a>
                            <ul id="${menu.menu_url }" class="nav nav-list collapse secondmenu" style="height: 0px;">
                                <c:forEach items="${menuList }" var="submenu">
                                    <c:if test="${submenu.parent_menu_id==menu.menu_id }">
                                        <li id="subli_${submenu.menu_id}"
                                            onclick="onclickSubMenu('subli_${submenu.menu_id}');">
                                            <a href="#" onclick="openPageByUrl('${submenu.menu_url}');">
                                                <label class="${submenu.menu_img_url}"></label>
                                                <i><c:out value="${submenu.menu_ch_name }"/></i>
                                            </a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:if>
                </c:forEach>
                <c:if test="${fn:length(menuList) > 0}">
                    <li id="emptyLI" style="border-bottom:0px;"></li>
                </c:if>
            </ul>
        </div>
        <div id="mainBodyDiv" style="width:85%;height:91.5%;float:left;">
            <c:choose>
                <c:when test="${user.role_id == 1}">
                    <iframe id="mainPageIfame" frameborder="0" style="width:100%;height:100%;background-color:#eff3f6;"
                            src="${baseHostUrl}/sys/user/main.do"></iframe>
                </c:when>
                <c:otherwise>
                    <iframe id="mainPageIfame" frameborder="0" style="width:100%;height:100%;background-color:#eff3f6;"
                            src="${baseHostUrl}/report/ext/extList.do">
                    </iframe>
                </c:otherwise>
            </c:choose>
        </div>
    </div><!-- showDiv end -->
</div>
</body>
</html>