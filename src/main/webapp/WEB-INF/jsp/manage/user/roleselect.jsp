<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../common/basepath.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<style type="text/css">
.panel {
	width: 500px;
}
.panel-body{
	width: 500px;
}
</style>
</head>
<body>
<div class="modal-body">
  <div class="row">
		<div class="panel panel-success">
	  		<div class="panel-heading">权限目录</div>
	  		<div class="panel-body">
	    		<ul id="tree" style="position:absolute; width:99.5%;height:360px; overflow:auto"></ul>
	  		</div>
		</div>
  </div>
</div>
<div class="modal-footer">
    <button type="button" onclick="submit();" class="btn btn-primary" style="position:absolute; bottom: 20px;left: 80%;">保存</button>
 </div>
<script type="text/javascript">
	var refreshid = "${refreshid}";
	var id = "${requestScope.id}";
	$('#tree').tree({    
		url : "../manage/selectroletreejson?id=" + id,
        lines : true,
        animate : true,
        checkbox : true,
        formatter:function(node){
            return  node.text;
        },
	});
	function submit() {
		var rolelist = new Array();
		var nodes = $('#tree').tree('getChecked');
		for (var i=0;i<nodes.length;i++) {
			if (!nodes[i].children) {
				rolelist.push(nodes[i].id);
			}
		}
		$.ajax({
            url:"saverole",
            method:"post",
            data:{id:id, "rolelist":rolelist.toString()},
            success: function (result) {
        		if (result == "success") {
        			$('#win').window('close');
        			$("#userlist", window.document).attr("src","userlist?id=" + refreshid);
        			$.messager.alert("反馈信息", "修改成功！");
        		} else {
        			$.messager.alert("反馈信息", "操作出错，请联系开发人员！");
        		}
            }
        });
	}
</script>
</body>
</html> 
