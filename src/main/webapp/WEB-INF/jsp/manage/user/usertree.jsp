<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../../common/basepath.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>用户树</title>
<link href="${PATH}/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="${PATH}/css/bootstrap.min.css" rel="stylesheet">
<link href="${PATH }/easyui/themes/default/easyui.css" type="text/css" rel="stylesheet">
<link href="${PATH }/easyui/themes/icon.css" type="text/css" rel="stylesheet">
<style type="text/css">
.col-md-9{
	padding-left: 0px;
}
.panel.window.messager-window{
	width: 188px;
}
.messager-window{
	width: 188px;
}
.panel-header.panel-header-noborder.window-header{
	width: 188px;
}
.window-header{
	width: 188px;
}
.messager-body.panel-body.panel-body-noborder.window-body{
	width: 166px;
}
.window-body{
	width: 166px;
}
.window-shadow{
	width: 200px;
}
</style>
</head>
<body>
<ul id="box"></ul>
<div id="menu" class="easyui-menu" style="width:100px;display: none;">
	<div onclick="addnode();" data-options="iconCls:'icon-add'">增加</div>
    <div onclick="removenode();" data-options="iconCls:'icon-remove'">删除</div>
    <div onclick="updatenode();" data-options="iconCls:'icon-edit'">修改组别</div>
    <div onclick="updaterole();" data-options="iconCls:'icon-edit'">设置权限</div>
</div>
<div id="userdlg">
	<form id="fm" method="post">
		<table>
			<tr>
				<td>&nbsp;组别名称：</td>
			</tr>
			<tr>
				<td>
					&nbsp;<input id="name" name="name" class="easyui-validatebox" required="true" />
				</td>
			</tr>
			<!-- <tr>
				<td>&nbsp;组别权限标识：</td>
			</tr>
			<tr>
				<td>
					&nbsp;<input id="flag" name="flag" class="easyui-validatebox" required="true" />
				</td>
			</tr> -->
		</table>
	</form>
</div>
<script src="${PATH }/easyui/jquery.min.js"></script>
<script src="${PATH }/easyui/jquery.easyui.min.js"></script>
<script src="${PATH }/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="${PATH }/js/manage/usertree.js"></script>
<script>
	
</script>
</body>
</html> 
