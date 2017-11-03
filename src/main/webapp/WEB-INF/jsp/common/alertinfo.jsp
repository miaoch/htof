<%@ page contentType="text/html;charset=UTF-8" %>
<c:set var="UNIT_ROOT" value="<%=cn.com.hesc.zhcs.Constants.UNIT_ROOT%>"/>
<!-- 需要引入bootstrap文件 -->
<div id="alertinfofull" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="mySmallModalLabel">Small modal</h4>
            </div>
            <div class="modal-body">
                ${alertinfo }
            </div>
        </div><!-- /.modal-content -->
    </div>
</div>
<div id="alertinfo" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body">
                ${alertinfo }
            </div>
        </div><!-- /.modal-content -->
    </div>
</div>
<script type="text/javascript">
$(function(){ 
	if("${alertinfo}" != ""){
		$('#alertinfo').modal('toggle');
		setTimeout("$('#alertinfo').modal('toggle')", 1000);
	}
	
	if ("${alertinfo}" != "") {
		opener.location.reload();
	}
	if("${close}" != ""){
		opener.location.reload();
		window.close();
	}
	if('${parentName}'=="${UNIT_ROOT}"){
		$(".aside2Div5 a").addClass("two");
	}else{
		if("${closeOnly}" == ""){
			$(".aside2Div5 a").addClass("three");
		}else if("${closeOnly}" != ""){
			$(".aside2Div5 a").addClass("one");
		}
	}
	if("${name}" != null && "${name}" != ""){
		//$("#name").attr("readOnly","readOnly");
	}

});
//表单验证
function check(e) {
	//var r = /^\d+(\.\d+)?$/;
	var r = /^(\-|\+)?\d+(\.\d+)?$/;
	var s = $(e).val().trim();
	if (!!window.ActiveXObject || "ActiveXObject" in window) {
		//如果是IE浏览器
		if (!r.test(s) && s.length != 0) {
			//$(e).focus();
			 setTimeout(function() {
				$(e).focus();
			}, 10); 
			swal("请输入正确的数字！");
			return;
		} else if (s.length > 25) {
			setTimeout(function() {
				$(e).focus();
			}, 10);
			swal("输入字符超过最大限度！");
		}
	} else {
		//不是IE浏览器
		if (!r.test(s) && s.length != 0) {
			$(e).focus();
			//setTimeout(function () { $(e).focus(); }, 10);
			swal("请输入正确的数字！");
			return;
		} else if (s.length > 25) {
			$(e).focus();
			swal("输入字符超过最大限度！");
		}
	}

}

function checkother(e) {
	var s = $(e).val().trim();
	if (!!window.ActiveXObject || "ActiveXObject" in window) {
		//IE浏览器
		if (s.length > 25) {
			setTimeout(function() {
				$(e).focus();
			}, 10);
			swal("最多输入25个字");
			return;
		}
	}else{
		//非IE浏览器
		if (s.length > 25) {
			$(e).focus();
			swal("最多输入25个字");
			return;
		}
	}
	
}
</script>	