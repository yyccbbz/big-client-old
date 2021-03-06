<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/pageStatic/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="shortcut icon" href="${ctx}/images/favicon.ico" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>大客户中心系统</title>
<%
	String base_path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	request.setAttribute("server_name", request.getContextPath());
	request.setAttribute("baseHostUrl", request.getContextPath());
	request.setAttribute("localProjectName", request.getContextPath());
%>
		<style type="text/css">
			html {
				height: 100%;
				margin: 0;
			}
			body {
				height: 100%;
				margin: 0px;
				font-family: 'Microsoft YaHei';
			}
			.leftMargin {
				margin-left: 30px;
				float: left; 
				border: 1px solid red;
			}
		</style>
		<script>
			var baseHostUrl = '${baseHostUrl}';
			var postUrl = baseHostUrl;
			var localProjectName = '${localProjectName}';
		</script>
		<script src="${localProjectName}/utils/jquery/jquery.min.js?v=${revision}"></script>
		<script src="${localProjectName}/utils/jquery/plugin/masonry.pkgd.min.js?v=${revision}"></script>
		<script src="${localProjectName}/utils/jquery/plugin/jquery.sparkline.min.js?v=${revision}"></script>
		<script src="${localProjectName}/utils/echarts3.0.2/echarts.min.js?v=${revision}"></script>
		<script src="${localProjectName}/utils/echarts/js/echarts.js?v=${revision}"></script>
		<script src="${localProjectName}/pageJs/base/extendPagination.js?v=${revision}"></script>
		<script src="${localProjectName}/pageJs/base/evergrande_public.js?v=${revision}"></script>
		<script src="${localProjectName}/utils/bootstrap/js/bootstrap.min.js?v=${revision}"></script>
		<script src="${localProjectName}/pageJs/base/utils/loading.js?v=${revision}"></script>
		<link href="${localProjectName}/utils/bootstrap/css/bootstrap.css?v=${revision}" rel="stylesheet">
		<link href="${localProjectName}/pageCss/checkBox/style.css?v=${revision}" rel="stylesheet">
		<link href="${localProjectName}/pageCss/evergrande-body-common.css?v=${revision}" rel="stylesheet">
		<link href="${localProjectName}/pageCss/menu.css?v=${revision}" rel="stylesheet">
		<link href="${localProjectName}/pageCss/main-body.css?v=${revision}" rel="stylesheet">
		<link href="${localProjectName}/utils/bootstrap/css/bootstrap-multiselect.css?v=${revision}" rel="stylesheet" media="screen">
		<link href="${localProjectName}/pageCss/tablist.css?v=${revision}" rel="stylesheet" media="screen" />
		<link href="${localProjectName}/pageCss/evergrande-table.css?v=${revision}" rel="stylesheet" media="screen" />
		<mce:script type="text/javascript">
		</mce:script>
		</head>
		<script>
			window.onload=function(){
				var bodyHeight = $("body").height();
			}
		</script>
</html>
