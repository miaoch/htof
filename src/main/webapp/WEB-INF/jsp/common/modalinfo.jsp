<%@ page contentType="text/html;charset=UTF-8" %>
<div id="detail" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content" >
        </div>
    </div>
</div>
<script type="text/javascript">
$("#detail").on("hidden.bs.modal", function() {
   	$(this).removeData("bs.modal");
});
$('#log').click(function(){
	if(id == undefined){
		swal("请选择一条记录！");
		return;
	}
	$("#detail").modal({
	    remote: "log?id=" + id
	});
})
</script>