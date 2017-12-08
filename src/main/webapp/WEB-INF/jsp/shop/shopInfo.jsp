<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/basepath.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<link href="${PATH}/js/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	<link href="${PATH}/css/bootstrap-theme.min.css" rel="stylesheet">
	<link href="${PATH}/css/bootstrap.min.css" rel="stylesheet">
	<link href="${PATH }/easyui/themes/default/easyui.css" type="text/css" rel="stylesheet">
	<link href="${PATH }/easyui/themes/icon.css" type="text/css" rel="stylesheet">
</head>
<body>
	<div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	    <h4 class="modal-title" id="exampleModalLabel">店铺信息</h4>
	</div>
	<div class="modal-body">
		<form id="shopInfo" name="shopInfo" action="#" method="post">
			<div class="form-group">
				<label for="addressText" class="control-label">地址:</label>
				<input type="text" class="form-control" id="addressText" name="addressText" value="${shop.addressText}" disabled/>
				<label for="description" class="control-label">简述:</label>
				<textarea style="height: 100px;" class="form-control"
						  id="description" name="description" disabled>${shop.description }</textarea>
			</div>
		</form>
	</div>
	<div class="modal-footer">
	    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	</div>
</body>
<script src="${PATH}/js/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${PATH}/js/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="${PATH }/easyui/jquery.easyui.min.js"></script>
<script src="${PATH }/easyui/locale/easyui-lang-zh_CN.js"></script>
</html>