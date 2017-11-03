<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../common/basepath.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>综合监管分析平台</title>
<link rel="stylesheet" type="text/css" href="${PATH}/css/main.css"/>
<link href="${PATH }/easyui/themes/default/easyui.css" type="text/css" rel="stylesheet">
<link href="${PATH }/easyui/themes/icon.css" type="text/css" rel="stylesheet">
<link href="${PATH}/js/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="${PATH}/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="${PATH}/css/bootstrap.min.css" rel="stylesheet">
<link href="${PATH}/js/sweetalert/css/my-sweet-alert.css" rel="stylesheet" >
<script src="${PATH}/js/jquery-1.11.0.min.js"></script>
<script src="${PATH}/js/bootstrap/bootstrap.min.js"></script>
<script src="${PATH}/js/pubinfo.js"></script>
<script src="${PATH}/js/sweetalert/js/sweet-alert.js"></script>
<style type="text/css">
	.chart .left {
		width: 491px;
		height: 540px;
		float: left;
		border-radius: 5px;
		box-sizing: border-box;
		border: 1px solid white;
	}
	
	.chart .right {
		width: 491px;
		height: 540px;
		float: right;
		border-radius: 5px;
		box-sizing: border-box;
		border: 1px solid white;
	}
</style>
</head>
<body>
<div class="section">
	<h4>
		采集人员月报表
		<p>
			<a href="report" id="chart">
				<i class="glyphicon glyphicon-th"></i>&nbsp;查看报表&nbsp;&nbsp;
			</a>
		</p>
	</h4>
	
	<form id="searchform" class="form-inline" role="form" action="report" method="get" style="margin: 5px 0px 0px 14px;">
		<div class="form-group">
			<label for="search">按月:</label>
			<input type="text" class="form-control" id="search" name="date" value="${date }" placeholder="日期" readonly="readonly">
		</div>
		<button type="button" class="btn btn-default" onclick="submit_month();">图&nbsp;表</button>
	</form>
	<form id="searchform2" class="form-inline" role="form" action="reportByDay" method="get" style="margin: 5px 0px 0px 14px;">
		<div class="form-group">
			<label for="from">按日:</label>
			<input type="text" class="form-control" id="from" name="from" value="${from }" placeholder="开始日期" readonly="readonly">
			<label for="to">到</label>
			<input type="text" class="form-control" id="to" name="to" value="${to }" placeholder="结束日期" readonly="readonly">
		</div>
		<button type="button" class="btn btn-default" onclick="submit_day();">图&nbsp;表</button>
	</form>
	<div class="chart">
		<div class="left">
			<iframe frameborder="0" id="leftchart" src="leftchart" scrolling="no" style="width:100%;height:100%;"></iframe>
		</div>
		<div class="right">
			<iframe frameborder="0" id="rightchart" src="rightchart" scrolling="no" style="width:100%;height:100%;"></iframe>
		</div>
	</div>
</div>
<script src="${PATH}/js/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${PATH}/js/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="${PATH }/easyui/jquery.easyui.min.js"></script>
<script src="${PATH }/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var dateStr = $("#search").val();
	if (dateStr != null && dateStr != "") {
		submit_month();
	}
});
$("#search").datetimepicker({
	language:'zh-CN',
    format:'yyyy-mm',
    autoclose:true,
    startView:3,
    minView:3,
});
$("#from").datetimepicker({
	language:'zh-CN',
    format:'yyyy-mm-dd',
    autoclose:true,
    startView:2,
    minView:2,
});
$("#to").datetimepicker({
	language:'zh-CN',
    format:'yyyy-mm-dd',
    autoclose:true,
    startView:2,
    minView:2,
});
function submit_month() {
	$("#from").val("");
	$("#to").val("");
	var dateStr = $("#search").val();
	if (dateStr == null || dateStr == "") {
		$.messager.alert("错误", "请填写正确的月份！");
		return false;
	}
	$.ajax({
        url:"chartByMonth",
        data:{"dateStr":dateStr},
        type:"POST",
        success: function (result) {
        	var json = JSON.parse(result);
        	$("#leftchart")[0].contentWindow.refresh(json.leftchart);
        	$("#rightchart")[0].contentWindow.refresh(json.rightchart);
        }
    });
}
function submit_day() {
	var from = $("#from").val();
	var to = $("#to").val();
	if (from == "" || to == "" || from > to) {
		$.messager.alert("错误", "请填写正确的开始结束日期！");
		return false;
	}
	$("#search").val("");
	$.ajax({
        url:"chartByDay",
        data:{"from":from,"to":to},
        type:"POST",
        success: function (result) {
        	var json = JSON.parse(result);
        	$("#leftchart")[0].contentWindow.refresh(json.leftchart);
        	$("#rightchart")[0].contentWindow.refresh(json.rightchart);
        }
    });
}
</script>
</body>
</html>
