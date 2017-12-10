<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/basepath.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<link href="${PATH}/css/bootstrap-theme.min.css" rel="stylesheet">
	<link href="${PATH}/css/bootstrap.min.css" rel="stylesheet">
	<link href="${PATH }/easyui/themes/icon.css" type="text/css" rel="stylesheet">
</head>
<body>
	<div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	    <h4 class="modal-title" id="exampleModalLabel">设置成本价</h4>
	</div>
	<div class="modal-body">
		<form id="priceForm" name="priceForm" action="setPrice" method="post">
			<input type="text" hidden id="vfoodId" name="vfoodId" value="${vfood.id}"/>
			<input type="text" hidden id="shopId" name="shopId" value="${shopId}"/>
			<input type="text" hidden id="curPage" name="curPage" value="${curPage}"/>
			<input type="text" hidden id="pageSize" name="pageSize" value="${pageSize}"/>
			<div class="form-group">
				<label for="price" class="control-label">菜品:</label>
				<input type="text" class="form-control" id="name" name="name" value="${vfood.name}" disabled/>
				<label for="price" class="control-label">成本价:</label>
				<input type="text" class="form-control" id="price" name="price" value="${vfood.price}"/>
			</div>
		</form>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		<button type="button" onclick="submit();" class="btn btn-primary">保存</button>
	</div>
</body>
<script type="text/javascript">
	function submit() {
		$('#priceForm').submit();
	}
</script>
</html>