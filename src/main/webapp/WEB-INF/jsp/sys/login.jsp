<!DOCTYPE html>
<%@ page language='java' pageEncoding='UTF-8' contentType='text/html;charset=UTF-8' %>
<%@ include file="/pageStatic/evergrandeBase.jsp" %>
<html lang="en">
	<head>
    	<title>大客户中心系统</title>
    	<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="${ctx }/pageCss/login.css?v=${revision}" rel="stylesheet">
		<link href="${ctx }/pageCss/font-awesome/font-awesome.css?v=${revision}" rel="stylesheet">

		<script src="${localProjectName}/utils/encode/blowfish.js?v=${revision}"></script>
		<script src="${localProjectName}/pageJs/sys/encodePwd.js?v=${revision}"></script>
		<script src="${localProjectName}/pageJs/sys/login.js?v=${revision}"></script>
  	</head>
  	<!-- background-position:0px 0px;background-size:100%; -->
  	<body style="overflow:hidden;background: url(${localProjectName }/images/login_bg.png) no-repeat;text-align:center;background-position:0px -15em;" onkeypress="if (event.keyCode == 13) login();">
  		<div style="width:100%;height:100%;border:0px solid red;">
  			<form id="loginForm">
	  			<div id="logInfoDiv" style="border:0px solid black;width:735px;height:735px;position:absolute;no-repeat;">
	  				<div class="controls" style="margin-top:180px;width:230px;margin-left:250px;">
	  					<span style="color:black;font-size:21px">大客户中心系统</span><br/><br/><br/>
						<input id="username" name="username" type="text" style="height:50px;" class="form-control" placeholder="用户名">
					</div>
					<div class="controls" style="margin-top:30px;width:230px;margin-left:250px;">
						<input id="password" name="password" style="height:50px;" class="form-control" type="password" placeholder="密码"/>
					</div>
					<div class="controls" style="border:0px solid blue;height:40px;width:735px;text-align:center">
						<img alt="" src="${localProjectName }/images/gth.png" id="gthpng" name="gthpng"/>&nbsp;<span id="pwdmsg"></span>
					</div>
					<div class="controls" style="width:230px;height:60px;margin-left:250px;">
						<label  id="loginImg" class="loginPic" onclick="login()"></label>
					</div>
					
	  			</div>
  			</form>
  		</div>
  	</body>
</html>