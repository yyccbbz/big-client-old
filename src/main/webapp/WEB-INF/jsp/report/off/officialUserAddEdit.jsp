<!DOCTYPE html>
<%@ page language='java' pageEncoding='UTF-8' contentType='text/html;charset=UTF-8' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>外部拓展用户添加</title>
    <jsp:include page="/pageStatic/evergrandeBase.jsp"/>
    <%@ include file="/pageStatic/taglibs.jsp" %>
</head>
<body>
<link href="${localProjectName}/pageCss/sys/user/userAddEdit.css?v=${revision}" rel="stylesheet">
<script src="${localProjectName}/pageJs/report/extUserViewList.js?v=${revision}"></script>
<script src="${localProjectName}/utils/encode/blowfish.js?v=${revision}"></script>
<script src="${localProjectName}/pageJs/sys/encodePwd.js?v=${revision}"></script>
<script src="${localProjectName}/pageJs/report/extUserAddEdit.js?v=${revision}"></script>
<%--日期插件--%>
<script language="javascript" type="text/javascript" src="${localProjectName}/pageJs/report/My97DatePicker/WdatePicker.js"></script>

<div id="titleDiv" class="body-title">
    外部拓展客户管理 <span class="secord-title"> > </span>
    <c:choose>
        <c:when test="${action == 'edit'}"><span class="secord-title">编辑用户</span></c:when>
        <c:otherwise><span class="secord-title">新建用户</span></c:otherwise>
    </c:choose>
</div>

<div id="splitDiv" class="split_title"><!-- title和内容页的分割线START-----splitDiv -->
</div>    <!-- title和内容页的分割线END-----splitDiv -->

<div id="centerdiv" class="contend_div">
    <form id="addEditUserForm">
        <input type="hidden" id="action" name="action" value="${action}"/>
        <input type="hidden" id="currPage" name="currPage" value="${currPage}"/>
        <input type="hidden" id="edit_user_id" name="edit_user_id" value="${editUser.user_id}"/>
        <%--<input type="hidden" id="order_id" name="order_id" value="${order_id}"/>--%>
        <%--<input type="hidden" id="order_type" name="order_type" value="${order_type}"/>--%>
        <div class="error-message" id="uperror" style="display:none">
            <span id="pwdmsg" class="error-text"></span>
        </div>
        <table id="center-tab" class="center-tab-css">

            <!-- tr style="width:100%;height:15%;">
                <td style="width:30%;">登陆验证</td>
                <td style="width:70%;"><input id="username" class="form-control" type="text" value=""  ></td>
            </tr -->

            <tr>
                <td>手机号码</td>
                <td><input type="text" class="form-control" id="mobile_no" name="mobile_no"
                           value="${editUser.mobile_no}"/></td>
            </tr>

            <c:if test="${action == 'add'}">
                <tr>
                    <td style="width:30%;">系统内姓名</td>
                    <td style="text-align:left;width:70%;"><input id="user_name" name="user_name" class="form-control"
                                                                  type="text" value="${editUser.user_name}"></td>
                </tr>
                <tr>
                    <td>是否注册</td>
                    <td>
                        <%--<input id="is_reg" name="is_reg" type="text" class="form-control"/>--%>
                        <span class="form-control">
                            <input type="radio" name="is_reg" value="1" /> 是
                            <input type="radio" name="is_reg" value="0" /> 否
                        </span>
                    </td>

                </tr>
                <tr>
                    <td>注册日期</td>
                    <td><input id="reg_date" type="text" class="form-control" onClick="WdatePicker()"/></td>
                </tr>
                <tr>
                    <td>是否实名</td>
                    <td>
                        <%--<input id="is_real_name" name="is_real_name" type="text" class="form-control"/>--%>
                        <span class="form-control">
                            <input type="radio" name="is_real_name" value="1" /> 是
                            <input type="radio" name="is_real_name" value="0" /> 否
                        </span>
                    </td>
                </tr>
                <tr>
                    <td>是否绑卡</td>
                    <td>
                        <%--<input id="is_tie_card" name="is_tie_card" type="text" class="form-control"/>--%>
                        <span class="form-control">
                            <input type="radio" name="is_tie_card" value="1" /> 是
                            <input type="radio" name="is_tie_card" value="0" /> 否
                        </span>
                    </td>
                </tr>
                <tr>
                    <td>是否有过交易</td>
                    <td>
                        <%--<input id="is_trans" name="is_trans" type="text" class="form-control"/>--%>
                        <span class="form-control">
                            <input type="radio" name="is_trans" value="1" /> 是
                            <input type="radio" name="is_trans" value="0" /> 否
                        </span>
                    </td>
                </tr>
                <tr>
                    <td>扫码推荐人</td>
                    <td><input id="scan_code_rec" name="scan_code_rec" type="text" class="form-control"/></td>
                </tr>
                <tr>
                    <td>返利失效日期</td>
                    <td><input id="rebate_expire_date" type="text" class="form-control" onClick="WdatePicker()"/></td>
                </tr>

            </c:if>

        </table>
    </form>
</div>

<div id="btn-div" class="contend_div">
    <table id="btn-tab">
        <tr>
            <td style="width:30%;">
            </td>
            <td style="width:50%;height:100%;text-align:left;">
                <button id="saveUserBtn" name="saveUserBtn" type="button" class="btn save_btn" onclick="saveUser()">保存
                </button>
            <td style="width:20%;height:100%;text-align:right;">
                <a href="#" onclick="clickReturnBtn()">返回</a>
            </td>
            </td>
        </tr>
    </table>
</div>
<jsp:include page="/pageStatic/evergrandeBaseOnload.jsp"/>
</body>
</html>
			