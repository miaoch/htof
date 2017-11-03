<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>附件管理</title>
<link href="css/bootstrap-theme.min.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="fonts/awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet" />
<link href="js/sweetalert/css/my-sweet-alert.css" rel="stylesheet" >
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="js/sweetalert/js/sweet-alert.js"></script>
<style type="text/css">
.title {
	width: 1300px;
	margin: 0 auto;
	height: 48px;
	background: #3ac5e2;
}
.main{
	height: auto !important;
	min-height: 598px;
}
.container{
	width: 1280px;
	height: auto !important;
}
.jumbotron{
	min-height: 510px;
	height: auto !important;
    margin-bottom: 0px;
}
.thumbnail i{
    font-size: 100px;
    display: block;
    margin: 15px 80px;
}
.caption p{
	margin-top: 15px;
	text-align: center;
}
.caption h3{
    text-align: center;
}
</style>
<script type="text/javascript">
</script>
</head>
<body>
	<div class="title">
		<h4 id="d1">附件管理</h4>
	</div>
	<div class="main">
		<div class="container">
			<nav class="navbar navbar-default" role="navigation">
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<form class="navbar-form navbar-left" role="search" id="mediaForm" action="mediasave" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="${id }"/>
						请选择附件：
						<div class="form-group">
							<input type="file" id="exampleInputFile" name="uploadFile">
						</div>
						<button type="button" class="btn btn-default" onclick="sendfile()">提交</button>
					</form>
				</div>
			</nav>
			<div class="jumbotron">
				<div class="container-fluid">
					<div class="row">
						<!-- 循环 -->
						<c:forEach var="record" items="${medialist}">
						<div class="col-md-3">
						    <div class="thumbnail">
						    	<i class="fa fa-file-${record.mediatype }-o" style="font-size: 100px;"></i>
								<div class="caption">
									<h3>${record.medianame }</h3>
									<p>
										<a href="${record.mediapath }/${record.storename }" target="_blank" class="btn btn-primary" role="button">下载</a>
							        	<a href="javascript:remove('${id }', '${record.id }');" class="btn btn-primary" role="button">删除</a>
							        </p>
								</div>
						    </div>
					  	</div>
					  	</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="alertinfo.jsp" %>
<script type="text/javascript">
function sendfile(){
	var f = document.getElementById("exampleInputFile").files;
	if(f[0].size > 20971520){
		swal("大小超出20M，请分批上传！");
		return;
	}
	$('#mediaForm')[0].submit();
}

function remove(id, mediaid){
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
			window.location.href="mediadelete?id="+id+'&mediaid='+mediaid;
		}
	});
}
$(function(){
	if("${param.info}" == "projectinfo"){
		$("#d1").html("附件上传（立项、可研、初设及环评、环保验收、项目验收批复文件扫描件或照片）");
	}
	else if("${param.competent}" == "competent"){
		$("#d1").html("上传物价局污水处理费批复文件");
	}
	
	
})
</script>	
</body>
</html>
