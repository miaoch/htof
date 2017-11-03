<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../../common/basepath.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>用户列表</title>
<link href="${PATH}/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="${PATH}/css/bootstrap.min.css" rel="stylesheet">
<link href="${PATH}/js/sweetalert/css/my-sweet-alert.css" rel="stylesheet" >
<script src="${PATH}/js/jquery-1.11.0.min.js"></script>
<script src="${PATH}/js/bootstrap/bootstrap.min.js"></script>
<script src="${PATH}/js/sweetalert/js/sweet-alert.js"></script>
<style type="text/css">
a, a:HOVER, a:FOCUS {
	text-decoration: none;
}
.table-striped>tbody>tr.active1 {
	background: #5dd4ed ;
}
</style>
</head>
<body>
<div class="panel panel-default" style="margin: 1px;">
  <div class="panel-heading">${cityname }：(${currentid })
  	<div style="float: right">
  		<a href="#" id="add">
           <i class="glyphicon glyphicon-plus"></i>新增&nbsp;
       	</a>
		<a href="#" id="modifyinfo">
			<i class="glyphicon glyphicon-pencil"></i>修改信息&nbsp;
       	</a>
		<a href="#" id="modifyrole">
			<i class="glyphicon glyphicon-pencil"></i>修改权限&nbsp;
       	</a>
       	<a href="#" id="remove">
           <i class="glyphicon glyphicon-trash"></i>删除
       	</a>
  	</div>
  </div>
  <table class="table table-striped table-bordered table-hover usertable">
  	<thead>
      <tr>
          <th>用户名</th>
          <th>密码</th>
          <th>真实姓名</th>
          <th>联系电话</th>
          <th>电子邮箱</th>
          <!-- <th>用户标识</th> -->
      </tr>
	</thead>
	<tbody>
  	<c:forEach var="record" items="${page.recordList}">
		<tr>
		   <input type="hidden" name="id" value="${record.id}"/>
	       <td>${record.name }</td>
	       <td>${record.password }</td>
	       <td>${record.realname }</td>
	       <td>${record.telephone }</td>
	       <td>${record.email }</td>
	       <%-- <td>${record.flag }</td> --%>
	   </tr>
   </c:forEach>
   </tbody>
  </table>
</div>
<%@include file="../../common/page.jsp" %>
<%@include file="../../common/modalinfo.jsp" %>
<script type="text/javascript">
var id;
$(function(){
	$('.table tbody tr').click(function(){
		id=$(this).children(1).val();
		$('.table tbody tr').removeClass('active1');
		$(this).addClass('active1');
	});
});
$('#add').click(function() {
	$("#detail").modal({
	    remote: "unitadd?parentid=${parentid}"
	});
});
$('#modifyinfo').click(function() {
	if (id == undefined) {
		swal("请选择一条记录！");
		return;
	}
	$("#detail").modal({
	    remote: "unitedit?currentid=" + id
	});
});
$('#modifyrole').click(function() {
	if (id == undefined) {
		swal("请选择一条记录！");
		return;
	}
	window.parent.updaterole(id);
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
