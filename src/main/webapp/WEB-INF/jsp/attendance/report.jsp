<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../common/basepath.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="util" uri="/util" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>考勤管理</title>
<link href="${PATH }/easyui/themes/default/easyui.css" type="text/css" rel="stylesheet">
<link href="${PATH }/easyui/themes/icon.css" type="text/css" rel="stylesheet">
<link href="${PATH}/js/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="${PATH}/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="${PATH}/css/bootstrap.min.css" rel="stylesheet">
<link href="${PATH}/css/main.css" rel="stylesheet" />
<link href="${PATH}/js/sweetalert/css/my-sweet-alert.css" rel="stylesheet" >
<script src="${PATH}/js/jquery-1.11.0.min.js"></script>
<script src="${PATH}/js/bootstrap/bootstrap.min.js"></script>
<script src="${PATH}/js/pubinfo.js"></script>
<script src="${PATH}/js/sweetalert/js/sweet-alert.js"></script>
<style type="text/css">
.section a{
	color: #3AC5E2;
}
</style>
</head>
<body>
<div class="section">
	<h4>
		采集人员月报表
		<p>
			<a href="#" onclick="export_excel();">
				<i class="glyphicon glyphicon-cloud-download"></i>&nbsp;导出excel&nbsp;&nbsp;
			</a>
			<a href="reportChart" id="chart">
				<i class="glyphicon glyphicon-signal"></i>&nbsp;查看图表&nbsp;&nbsp;
			</a>
		</p>
	</h4>
	
	<form id="searchform" class="form-inline" role="form" action="report" method="get" style="margin: 5px 0px 0px 14px;">
		<div class="form-group">
			<label for="search">按月:</label>
			<input type="text" class="form-control" id="search" name="date" value="${date }" placeholder="日期" readonly="readonly">
		</div>
		<button type="submit" class="btn btn-default">搜&nbsp;索</button>
	</form>
	<form id="searchform2" class="form-inline" role="form" action="reportByDay" method="get" style="margin: 5px 0px 0px 14px;">
		<div class="form-group">
			<label for="from">按日:</label>
			<input type="text" class="form-control" id="from" name="from" value="${from }" placeholder="开始日期" readonly="readonly">
			<label for="to">到</label>
			<input type="text" class="form-control" id="to" name="to" value="${to }" placeholder="结束日期" readonly="readonly">
		</div>
		<button type="button" class="btn btn-default" onclick="submit_day();">搜&nbsp;索</button>
	</form>
	<div class="table">
		<ul class="tableList tableList2">
			<li class="citytitle">
				<em style="width:30%">姓名</em>
				<span style="width:10%">考勤天数</span>
				<span style="width:10%">正常天数</span>
				<span style="width:10%">迟到天数</span>
				<span style="width:10%">早退天数</span>
				<span style="width:10%">迟到早退天数</span>
				<span style="width:10%">旷工天数</span>
				<span style="width:10%">请假天数</span>
			</li>
			<c:forEach var="record" items="${page.recordList}">
				<li>
					<em style="width:30%">
						<c:out value="${record.patrolername}"/>
					</em>
					<span style="width:10%">
						<c:out value="${record.attendanceDays}"/>
					</span>
					<span style="width:10%" ondblclick="queryDetails('${record.ontimeDays}', '${record.patrolerid}', '0')">
						<c:out value="${record.ontimeDays}"/>
					</span>
					<span style="width:10%" ondblclick="queryDetails('${record.lateDays}', '${record.patrolerid}', '1')">
						<c:out value="${record.lateDays}"/>
					</span>
					<span style="width:10%" ondblclick="queryDetails('${record.earlyDays}', '${record.patrolerid}', '2')">
						<c:out value="${record.earlyDays}"/>
					</span>
					<span style="width:10%" ondblclick="queryDetails('${record.lateEarlyDays}', '${record.patrolerid}', '3')">
						<c:out value="${record.lateEarlyDays}"/>
					</span>
					<span style="width:10%" ondblclick="queryDetails('${record.absenceDays}', '${record.patrolerid}', '4')">
						<c:out value="${record.absenceDays}"/>
					</span>
					<span style="width:10%" ondblclick="queryDetails('${record.leaveDays}', '${record.patrolerid}', '5')">
						<c:out value="${record.leaveDays}"/>
					</span>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<%@include file="../../common/pagination.jsp" %>
<script src="${PATH}/js/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${PATH}/js/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="${PATH }/easyui/jquery.easyui.min.js"></script>
<script src="${PATH }/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
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
$(function(){
	$('.spantip').tooltip();
});
function submit_day() {
	var from = $("#from").val();
	var to = $("#to").val();
	if (from == "" || to == "" || from > to) {
		$.messager.alert("错误", "请填写正确的开始结束日期！");
		return false;
	}
	$('#searchform2')[0].submit();
}
function export_excel() {
	var date = "${date}";
	var from = "${from}";
	var to = "${to}";
	var url = (date!=""?"../util/exportKQTJExcelByMonth":"../util/exportKQTJExcelByDays");
	$.ajax({
		type:"POST",
		url:url,
		data:{"date":date, "from":from, "to":to},
		async:false,
		success: function(result) {
			if (result == "error") {
				$.messager.alert("错误", "导出失败,请联系开发人员！");
			} else {
				var elemIF = document.createElement("iframe");   
	            elemIF.src = result;
	            elemIF.style.display = "none";   
	            document.body.appendChild(elemIF);
			}
		},
	});
}
function queryDetails(count, pid, state) {
	if (count != 0) {
		window.open("queryDetails?date=${date}&from=${from}&to=${to}&pid=" + pid + "&state=" + state);
	}
}
</script>
</body>
</html> 
