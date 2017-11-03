<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
	<style type="text/css">
	</style>
</head>
<body>
	<div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	    <h4 class="modal-title" id="exampleModalLabel">用户信息</h4>
	</div>
	<div class="modal-body">
	    <form:form id="unitinfo" name="unitinfo" action="unitsave" method="post" modelAttribute="unit">
	    	<c:if test="${unit.id != null }">
				<input type="hidden" id="currentid" name="currentid" value="${unit.id }"/>
			</c:if>
	    	<c:if test="${unit.parentid != null }">
				<form:hidden path="parentid"/>
			</c:if>
			<c:if test="${unit.parentid == null }">
				<form:hidden path="parentid" value="${parentid }"/>
			</c:if>
	        <div class="form-group">
	            <label for="name" class="control-label">用户名:</label>
	            <form:input type="text" class="form-control" id="name" path="name" readonly="true"/>
	        </div>
	        <div class="form-group">
	            <label for="password" class="control-label">密码:</label>
	            <form:input type="password" class="form-control" id="password" path="password"/>
	        </div>
	        <div class="form-group">
	            <label for="realname" class="control-label">真实姓名:</label>
	            <form:input type="text" class="form-control" id="realname" path="realname"/>
	        </div>
	        <div class="form-group">
	            <label for="telephone" class="control-label">联系方式:</label>
	            <form:input type="text" class="form-control" id="telephone" path="telephone"/>
	        </div>
	    </form:form>
	</div>
	<div class="modal-footer">
	    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	    <button type="button" onclick="submit();" class="btn btn-primary">保存</button>
	</div>
</body>
<script type="text/javascript">
function submit(){
	$('#unitinfo')[0].submit();
}
</script>
</html>