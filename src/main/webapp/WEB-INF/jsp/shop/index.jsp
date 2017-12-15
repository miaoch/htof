<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../common/basepath.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>旺克士后台管理平台</title>
<link href="${PATH}/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="${PATH}/css/bootstrap.min.css" rel="stylesheet">
<link href="${PATH}/fonts/awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="${PATH}/css/main.css" rel="stylesheet" />
<style type="text/css">
.title ul a {
	height: 72px;
	line-height: 72px;
	font-size: 16px;
	color: #fff;
	opacity: 0.6;
	filter: alpha(opacity=60);
	padding: 0 20px;
	font-weight: bold;
}
.main .aside{
	width: 270px;
}
.main .section{
	width: 1000px;
}
.userwelcome{
    width: 180px;
    height: 22px;
    text-align: center;
    margin-top: 30px;
    margin-right: 10px;
    float: right;
    color: white;
}
</style>
</head>
<body>
	<div class="title">
		<h2>旺克士后台管理平台&nbsp;-</h2><h4 style="line-height: 72px;padding-left: 0px;">&nbsp;店铺管理</h4>
		<ul>
			<li><a href="javascript:window.close();">退出</a></li>
		</ul>
		<div class="userwelcome">
				欢迎用户：${sessionScope.user.realname}
		</div>
	</div>
	<div class="main">
		<div class="aside" style="padding:0">
			<div class="list-group" id="label">
				<a href="shop/getShopList" target="listFrame" class="list-group-item">
					<i class="glyphicon glyphicon-th-list"></i>&nbsp;&nbsp;店铺概况
				</a>
				<c:forEach var="shop" items="${shopList}">
					<a href="shop/vfoodList?shopId=${shop.id}" target="listFrame" class="list-group-item">
						<i class="glyphicon glyphicon-th-list"></i>&nbsp;&nbsp;${shop.name}
					</a>
				</c:forEach>
			</div>
		</div>
		<div class="section">
			<iframe id="listFrame" name="listFrame" frameborder="0" src="" style="width:100%;height:100%;"></iframe>
		</div>
	</div>
<script src="${PATH}/js/jquery-1.11.0.min.js"></script>
<script src="${PATH}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$(".list-group a").click(function(){
        $(".list-group a").removeClass("list-group-item-success");
        $(this).addClass("list-group-item-success");
    });
	var label = document.getElementById("label").getElementsByTagName("a")[0];
	if (label) {
		label.click();
	}
});
</script>
</body>
</html> 
