<%@ page contentType="text/html;charset=UTF-8" %>
<div id="messageinfo" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="messageinfoLabel">新任务消息提醒</h4>
            </div>
            <div class="modal-body">
            	您有新的报表需要上报
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script type="text/javascript">
	$("#messageinfo").on("hidden.bs.modal", function() {
    	$(this).removeData("bs.modal");
	});
</script>