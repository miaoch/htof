<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../../../common/basepath.jsp" %>
<%@ taglib prefix="util" uri="/util" %>
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
  	<form id="searchform" class="form-inline" role="form" action="detail" method="get">
		<div class="form-group">
			<input type="text" class="form-control" name="date" value="${date }" placeholder="日期" readonly="readonly">
			<select class="form-control" name="state" style="margin-left: 10px">
				<option value="">选择状态</option>
				<option value="0">正常</option>
				<option value="1">迟到</option>
				<option value="2">早退</option>
			</select>
		</div>
		<button type="submit" class="btn btn-default" style="margin-left: 10px">搜&nbsp;索</button>
	</form>
  </div>
  <div class="panel-heading">${sessionScope.patroler.cardid }：(${sessionScope.patroler.name })</div>
  <table class="table table-striped table-bordered table-hover usertable">
  	<thead>
      <tr>
          <th>日期</th>
          <th>时段</th>
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
	       <td>${record.attDate }</td>
	       <td><util:parseState mapName="TIMEMAP" state="${record.mission.hour}"/></td>
	       <td><util:parseDate value="${record.signInTime }" /></td>
	       <td><util:parseDate value="${record.signOutTime }" /></td>
	       <td><util:parseState mapName="SIGNINMAP" state="${record.onLateFlag}" defaultValue="未签"/></td>
	       <td><util:parseState mapName="SIGNOUTMAP" state="${record.onEarlyFlag}" defaultValue="未签"/></td>
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
$("input[name='date']").datetimepicker({
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
</script>
</body>
</html> 
