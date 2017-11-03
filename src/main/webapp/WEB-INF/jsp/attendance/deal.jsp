<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="util" uri="/util"%>
<%@ include file="../../common/basepath.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>考勤管理</title>
<link href="${PATH}/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="${PATH}/css/bootstrap.min.css" rel="stylesheet">
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
	      <a class="navbar-brand" href="#">采集人员考勤管理</a>
	    </div>
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <util:role role="PUBINFO_KQJL">
		      <!-- <ul class="nav navbar-nav">
		        <li><a href="deal/detail" target="content" id="dkmx">打卡明细</a></li>
		      </ul> -->
		      <ul class="nav navbar-nav">
		        <li><a href="deal/result" target="content" id="kqjg">考勤结果</a></li>
		      </ul>
	      </util:role>
	      <util:role role="PUBINFO_GRKQB">
		      <ul class="nav navbar-nav">
		        <li><a href="deal/attendanceStatistics" target="content" id="grkqtj">个人考勤统计</a></li>
		      </ul>
	      </util:role>
	    </div>
	  </div>
	</nav>
	<!-- 主体 -->
	<div id="main" class="embed-responsive">
  		<iframe id="content" name="content" class="embed-responsive-item" src="deal/result" style="height:100%"></iframe>
	</div>
<%@include file="../../common/modalinfo.jsp" %>	
<script src="${PATH}/js/jquery-1.11.0.min.js"></script>
<script src="${PATH}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(".navbar-nav li").click(function(){
        $(".navbar-nav li").removeClass("active");
        $(this).addClass("active");
    });
    var height=$(window).height()-62;
    $("#main").height(height);
    $("#modify").click(function() {
		$("#detail").modal({
		    remote: "manage/loginunitedit?currentid=${sessionScope.user.id}"
		});
	});
    var label = document.getElementById("bs-example-navbar-collapse-1").getElementsByTagName("a")[0];
	if (label) {
		label.click();
	}
});
</script>
</body>
</html> 
