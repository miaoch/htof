<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="util" uri="/util"%>
<%@ include file="../../../common/basepath.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>用户列表</title>
<link href="${PATH}/js/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="${PATH}/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="${PATH}/css/bootstrap.min.css" rel="stylesheet">
<link href="${PATH}/js/sweetalert/css/my-sweet-alert.css" rel="stylesheet" >
<script src="${PATH}/js/jquery-1.11.0.min.js"></script>
<script src="${PATH}/js/bootstrap/bootstrap.min.js"></script>
<script src="${PATH}/js/sweetalert/js/sweet-alert.js"></script>
<script src="${PATH}/js/manage/userlist.js"></script>
<style type="text/css">
a, a:HOVER, a:FOCUS {
	text-decoration: none;
}
</style>
</head>
<body>
<div class="panel panel-default" style="margin: 1px;">
  <div class="panel-heading">
	  	<form id="searchform" class="form-inline" role="form" action="result" method="get">
			<div class="form-group">
				<input type="text" class="form-control" name="from" value="${from }" placeholder="起始日期" readonly="readonly">
				&nbsp;至&nbsp;
				<input type="text" class="form-control" name="to" value="${to }" placeholder="结束日期" readonly="readonly">
				<select class="form-control" name="state" style="margin-left: 10px">
					<option value="">选择状态</option>
					<option value="0">正常</option>
					<option value="1">迟到</option>
					<option value="2">早退</option>
					<option value="3">迟到早退</option>
					<option value="4">旷工</option>
					<option value="5">请假</option>
				</select>
			</div>
			<button type="submit" class="btn btn-default" style="margin-left: 10px">搜&nbsp;索</button>
		</form>
  	</div>
  </div>
  <div class="panel-heading">${sessionScope.patroler.cardid }：(${sessionScope.patroler.name })
  	<a href="#" onclick="export_excel();" style="float: right">
		<i class="glyphicon glyphicon-cloud-download"></i>&nbsp;导出excel&nbsp;&nbsp;
	</a>
  </div>
  <table class="table table-striped table-bordered table-hover usertable">
  	<thead>
      <tr>
          <th>日期</th>
          <th>时段</th>
          <th>总状态</th>
          <th>签到时间</th>
          <th>签退时间</th>
          <th>签到状态</th>
          <th>签退状态</th>
      </tr>
	</thead>
	<tbody>
  	<c:forEach var="record" items="${page.recordList}">
		<tr>
		   <input type="hidden" name="id" value="${record.id}"/>
	       <td>${record.datetime }</td>
           <td><util:parseState mapName="TIMEMAP" state="${record.type}"/></td>
	       <td><util:parseState mapName="ATTENDANCESTATEMAP" state="${record.state }" redKeys="1,2,3,4,5"/></td>
	       <td><util:parseDate value="${record.attendance.signInTime }" /></td>
	       <td><util:parseDate value="${record.attendance.signOutTime }" /></td>
	       <td><util:parseState mapName="SIGNINMAP" state="${record.attendance.onLateFlag}" defaultValue="未签" redKeys="1"/></td>
	       <td><util:parseState mapName="SIGNOUTMAP" state="${record.attendance.onEarlyFlag}" defaultValue="未签" redKeys="1"/></td>
	   </tr>
   </c:forEach>
   </tbody>
  </table>
</div>
<%@include file="../../../common/page.jsp" %>
<%@include file="../../../common/modalinfo.jsp" %>
<script src="${PATH}/js/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${PATH}/js/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
$("input[name='from']").datetimepicker({
	language:'zh-CN',
    format:'yyyy-mm-dd',
    autoclose:true,
    startView:2,
    minView:2,
});
$("input[name='to']").datetimepicker({
	language:'zh-CN',
    format:'yyyy-mm-dd',
    autoclose:true,
    startView:2,
    minView:2,
});
$('#add').click(function() {
	$("#detail").modal({
	    remote: "unitadd?parentid=${parentid}&parentflag=${parentflag}"
	});
});
$('#modify').click(function() {
	if (id == undefined) {
		swal("请选择一条记录！");
		return;
	}
	$("#detail").modal({
	    remote: "unitedit?currentid=" + id
	});
});
$('#remove').click(function() {
	if (id == undefined) {
		swal("请选择一条记录！");
		return;
	}
	swal({
		title: "确认删除?",
		text: "",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: "#DD6B55",
		confirmButtonText: "删除！",
		cancelButtonText: "取消！",
		closeOnConfirm: false,
		closeOnCancel: true,
		imageUrl:"",
	},
	function(isConfirm){
		if (isConfirm) {
			$.get("unitdelete?id=" + id, function(data){
				swal({
					title: "删除成功！",
					text: "",
					type: "success",
				}, function(){
					window.location.reload();
				});
			});
		}
	});
});
function export_excel() {
	$.ajax({
		type:"POST",
		url:"../../util/exportGRKQBExcel",
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
</script>
</body>
</html> 
