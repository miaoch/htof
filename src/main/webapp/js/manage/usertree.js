var nodeid;
var currentid="";
$(document).ready(function(){
	$("#box").tree({
        url : 'usertreejson?id=0',
        lines : true,
        animate : true,
        formatter:function(node){
            return "[" + node.text + "]";
        },
        onSelect: function(node){
        	nodeid=node.id;
        },
        onClick: function(node){
        	//jquery iframe操作
            $("#userlist", window.parent.document).attr("src","userlist?id="+node.id);
        },
        onContextMenu: function(e, node){
        	nodeid = node.id;
            e.preventDefault();
            $('#box').tree('select', node.target);
            $('#menu').menu('show', {
                left: e.pageX,
                top: e.pageY
            });
        },
    });
    $("#userdlg").dialog({
		width : 200,
		height : 150,
		title : '添加用户信息',
		closed : true,
		modal : true,
		buttons : [ {
			text : '保存',
			plain : true,
			iconCls : 'icon-ok',
			handler : saveUserNode
		}, {
			text : '关闭',
			plain : true,
			iconCls : 'icon-no',
			handler : closeUserNodeAddDialog
		} ],
	});
});
function addnode(){
	currentid="";
	$("#userdlg").dialog("open").dialog("setTitle", "添加用户信息");
}
function updatenode(){
	currentid=nodeid;
	$("#userdlg").dialog("open").dialog("setTitle", "修改用户信息");
	var selectedRows = $("#box").tree('getSelected');
	$("#fm").form("load", {
		name: selectedRows.text,
		city: selectedRows.attributes.attrib1
	});
}
function updaterole(){
	currentid = nodeid;
	window.parent.updaterole(nodeid);
}
function removenode(){
	$.messager.confirm('确认对话框', '确认删除吗？', function(r){
		if (r){
			$("#fm").form("submit", {
				url : "removeUserNode?id="+nodeid,
				onSubmit : function() {
					return true;
				},
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.errorMsg) {
						$.messager.show({
							title:'消息',
							msg:'删除失败！',
							timeout:500,
							showType:'slide',
							style:{
								right:'',
								top:document.body.scrollTop+document.documentElement.scrollTop,
								bottom:''
							}
						});				
					} else {
						$.messager.show({
							title:'消息',
							msg:'删除成功！',
							timeout:500,
							showType:'slide',
							style:{
								right:'',
								top:document.body.scrollTop+document.documentElement.scrollTop,
								bottom:''
							}
						});
						$("#box").tree("reload");
					}
				}
			});
		}
	});
	setWidth();
}
function setWidth(){
	$(".panel.window.messager-window").width(188);
	$(".panel.window.messager-window").css("left", "35px"); 
	$(".panel-header.panel-header-noborder.window-header").width(188);
	$(".messager-body.panel-body.panel-body-noborder.window-body").width(166);
	$(".window-shadow").width(0);
}
function closeUserNodeAddDialog() {
	$("#userdlg").dialog("close");
	$("#fm").form('clear');
}
function saveUserNode() {
	$("#fm").form("submit", {
		url : "saveOrUpdateUserNode?nodeid="+nodeid+"&currentid="+currentid,
		onSubmit : function() {
			return $(this).form("validate");
		},
		success : function(result) {
			var result = eval('(' + result + ')');
			if (result.errorMsg) {
				$.messager.show({
					title:'消息',
					msg:'保存失败！',
					timeout:500,
					showType:'slide',
					style:{
						right:'',
						top:document.body.scrollTop+document.documentElement.scrollTop,
						bottom:''
					}
				});				
			} else {
				$.messager.show({
					title:'消息',
					msg:'保存成功！',
					timeout:500,
					showType:'slide',
					style:{
						right:'',
						top:document.body.scrollTop+document.documentElement.scrollTop,
						bottom:''
					}
				});
				closeUserNodeAddDialog();
				$("#box").tree("reload");
			}
		}
	});
}