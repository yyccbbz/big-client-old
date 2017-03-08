<!DOCTYPE html>
<%@ page language='java' pageEncoding='UTF-8' contentType='text/html;charset=UTF-8' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>平台分配客户添加</title>
    <jsp:include page="/pageStatic/evergrandeBase.jsp"/>
    <%@ include file="/pageStatic/taglibs.jsp" %>
</head>
<body>
<link href="${localProjectName}/pageCss/sys/user/userAddEdit.css?v=${revision}" rel="stylesheet">
<script src="${localProjectName}/pageJs/report/plaUserViewList.js?v=${revision}"></script>
<script src="${localProjectName}/utils/encode/blowfish.js?v=${revision}"></script>
<script src="${localProjectName}/pageJs/sys/encodePwd.js?v=${revision}"></script>
<script src="${localProjectName}/pageJs/report/plaUserAddEdit.js?v=${revision}"></script>
<%--日期插件--%>
<script language="javascript" type="text/javascript" src="${localProjectName}/pageJs/report/My97DatePicker/WdatePicker.js"></script>

<div id="titleDiv" class="body-title">
    平台分配客户管理 <span class="secord-title"> > </span>
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
<%--//CREATE TABLE `rpt_to_assigned_customers` (
//        `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
//        `user_name` varchar(30) NOT NULL DEFAULT '' COMMENT '客户名称',
//        `mobile_no` varchar(30) NOT NULL DEFAULT '' COMMENT '注册手机号',
//        `reg_date` date NOT NULL DEFAULT '0000-00-00' COMMENT '注册日期',
//        `id_card_no` varchar(30) NOT NULL DEFAULT '' COMMENT '身份证号',
//        `is_staff` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否员工',
//        `assets` int(11) NOT NULL DEFAULT '0' COMMENT '资产金额',
//        `gender` tinyint(4) NOT NULL DEFAULT '0' COMMENT '性别：1：男；0：女',
//        `age` tinyint(4) NOT NULL DEFAULT '0' COMMENT '年龄',
//        `birthday` date NOT NULL DEFAULT '0000-00-00' COMMENT '出生日期',
//        `invite_user` varchar(30) NOT NULL DEFAULT '' COMMENT '邀请人',
//        `invite_user_mobile` varchar(30) NOT NULL DEFAULT '' COMMENT '邀请人手机',
//        `invite_user_is_staff` tinyint(4) NOT NULL,
//        `create_ts` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
//        `update_ts` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',--%>
            <tr>
                <td>手机号码</td>
                <td><input type="text" class="form-control" id="mobile_no" name="mobile_no" value="${editUser.mobile_no}"/></td>
            </tr>

            <c:if test="${action == 'add'}">
                <tr>
                    <td style="width:30%;">客户名称</td>
                    <td style="text-align:left;width:70%;">
                        <input id="user_name" name="user_name" class="form-control" type="text" value="${editUser.user_name}">
                    </td>
                </tr>
                <tr>
                    <td>注册日期</td>
                    <td><input id="reg_date" name="reg_date" type="text" class="form-control" onClick="WdatePicker()" value="${editUser.reg_date}" /></td>
                </tr>
                <tr>
                    <td style="width:30%;">身份证号</td>
                    <td style="text-align:left;width:70%;">
                        <input id="id_card_no" name="id_card_no" class="form-control" type="text" value="${editUser.id_card_no}">
                    </td>
                </tr>
                <tr>
                    <td>是否员工</td>
                    <td>
                        <span class="form-control">
                            <input type="radio" name="is_staff" value="1" /> 是
                            <input type="radio" name="is_staff" value="0" /> 否
                        </span>
                    </td>
                </tr>
                <tr>
                    <td style="width:30%;">资产金额</td>
                    <td style="text-align:left;width:70%;">
                        <input id="assets" name="assets" class="form-control" type="text" value="${editUser.assets}">
                    </td>
                </tr>
                <tr>
                    <td>性别</td>
                    <td>
                        <span class="form-control">
                            <input type="radio" name="gender" value="1" /> 男
                            <input type="radio" name="gender" value="0" /> 女
                        </span>
                    </td>
                </tr>
                <tr>
                    <td style="width:30%;">年龄</td>
                    <td style="text-align:left;width:70%;">
                        <input id="age" name="age" class="form-control" type="text" value="${editUser.age}">
                    </td>
                </tr>
                <tr>
                    <td>出生日期</td>
                    <td><input id="birthday" name="birthday" type="text" class="form-control" onClick="WdatePicker()" value="${editUser.birthday}" /></td>
                </tr>
                <tr>
                    <td style="width:30%;">邀请人</td>
                    <td style="text-align:left;width:70%;">
                        <input id="invite_user" name="invite_user" class="form-control" type="text" value="${editUser.invite_user}">
                    </td>
                </tr>
                <tr>
                    <td>邀请人手机号</td>
                    <td><input type="text" class="form-control" id="invite_user_mobile" name="invite_user_mobile" value="${editUser.invite_user_mobile}"/></td>
                </tr>
                <tr>
                    <td>邀请人是否员工</td>
                    <td>
                        <%--<input id="is_real_name" name="is_real_name" type="text" class="form-control"/>--%>
                        <span class="form-control">
                            <input type="radio" name="invite_user_is_staff" value="1" /> 是
                            <input type="radio" name="invite_user_is_staff" value="0" /> 否
                        </span>
                    </td>
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
			