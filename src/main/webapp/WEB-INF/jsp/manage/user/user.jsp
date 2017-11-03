<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../../common/basepath.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>用户管理</title>
<link href="${PATH }/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="${PATH }/css/bootstrap.min.css" rel="stylesheet">
<link href="${PATH }/easyui/themes/default/easyui.css" type="text/css" rel="stylesheet">
<link href="${PATH }/easyui/themes/icon.css" type="text/css" rel="stylesheet">
<style type="text/css">
.col-md-9{
	padding-left: 0px;
}
.panel-body {
    border-color: transparent;
}
</style>
</head>
<body>
	<!-- 主体 -->
	<div class="container-fluid">
	  <div class="row">
  		<div class="col-md-3">
			<div class="panel panel-success">
			  <div class="panel-heading">用户目录</div>
			  <div class="panel-body">
			    <iframe id="usertree" name="usertree" frameborder="0" src="usertree" style="width:100%;height:100%;"></iframe>
			  </div>
			</div>
		</div>
	    <div class="col-md-9">
	    	<div class="panel panel-success">
			  <div class="panel-heading">用户列表</div>
			  <div class="panel-body">
			    <iframe id="userlist" name="userlist" frameborder="0" src="userlist?id=1" style="width:100%;height:100%;"></iframe>
			  </div>
			</div>
	    </div>
	  </div>
	</div>
<div id="win"></div>   
<script src="${PATH }/js/jquery-1.11.0.min.js"></script>
<script src="${PATH }/js/bootstrap/bootstrap.min.js"></script>
<script src="${PATH }/easyui/jquery.easyui.min.js"></script>
<script src="${PATH }/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(".panel-success").height($(document).height()-22);
	$("#usertree").height($(".panel-success").height()-70);
	$("#userlist").height($(".panel-success").height()-70);
	
});
function updaterole(nodeid) {
	$("#win").window({    
   	    title: '设置权限',    
   	    width: 400,    
   	    height: 500,    
   	    modal: true,
   	 	collapsible:false,
	    minimizable:false,
	    maximizable:false,
	    resizable:false
   	}); 
	$("#win").window("refresh", "../user/roleselect?id=" + nodeid);  
}
</script>
</body>
</html> 
