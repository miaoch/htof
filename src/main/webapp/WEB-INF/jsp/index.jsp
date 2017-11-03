<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="common/basepath.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>采集人员管理平台业务系统</title>
<link href="${PATH}/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="${PATH}/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${PATH}/css/main.css" />
</head>
<body>
	<div class="header clear">
		<h1>旺克士外卖平台</h1>
		<div class="user">
			<a href="${PATH}/logout" data-toggle="tooltip" data-placement="bottom" title="退出">
                <i class="glyphicon glyphicon-log-out"></i>
            </a>
		</div>
		<div class="header welcome">欢迎用户：${sessionScope.user.realname}</div>
	</div>
	<div class="content pubinfo">
		<div class="module">
			<div class="m1"></div>
			<div class="m2"></div>
			<div class="m3"></div>
			<div class="m4"></div>
			<div class="m5"></div>
			<div class="m6"></div>
			<div class="m7"></div>
			<div class="m8"></div>
		</div>
	</div>
	<div class="footer">涵哥制造</div>
	<script src="${PATH}/js/jquery-1.11.0.min.js"></script>
	<script src="${PATH}/js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function () { $("[data-toggle='tooltip']").tooltip(); });
	</script>
</body>
</html>
