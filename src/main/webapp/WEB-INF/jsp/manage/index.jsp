<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../common/basepath.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>后台管理</title>
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
.navbar {
    margin-bottom: 10px;
}
</style>
</head>
<body>
	<!-- 导航 -->
	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="#">采集人员管理平台后台系统</a>
	    </div>
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
	        <li><a href="manage/user" target="content" id="yhgl">用户管理</a></li>
	      </ul>
	      <ul class="nav navbar-nav navbar-right">
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">个人中心<span class="caret"></span></a>
	          <ul class="dropdown-menu">
	            <li><a href="#" id="modify">信息修改</a></li>
	            <li role="separator" class="divider"></li>
	            <li><a href="logout">退出</a></li>
	          </ul>
	        </li>
	      </ul>
	    </div>
	  </div>
	</nav>
	<!-- 主体 -->
	<div id="main" class="embed-responsive">
  		<iframe id="content" name="content" class="embed-responsive-item" src="manage/user" style="height:100%"></iframe>
	</div>
<%@include file="../common/modalinfo.jsp" %>	
<script src="js/jquery-1.11.0.min.js"></script>
<script src="js/bootstrap/bootstrap.min.js"></script>
<%@include file="../common/modalinfo.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	$(".navbar-nav li").click(function(){
        $(".navbar-nav li").removeClass("active");
        $(this).addClass("active");
    });
    var height=$(window).height()-62;
    $("#main").height(height);
    $('#modify').click(function() {
		$("#detail").modal({
		    remote: "manage/loginunitedit?currentid=${sessionScope.user.id}"
		});
	});
});
</script>
</body>
</html> 
