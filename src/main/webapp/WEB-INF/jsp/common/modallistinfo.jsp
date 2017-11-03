<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="detail" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg">
    <form:form id="Log" name="Log" action="${pojo}denyreason" method="post" modelAttribute="Log">
        <div class="modal-content" style="height:300px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myLargeModalLabel">回退意见</h4>
            </div>
	        <input id="eventid" name="eventid" value="${pojoFactory.id}" type="hidden"/>
            <div class="modal-body" style="width:100%;height:80%;">
             	<form:textarea id="denyreason" path="denyreason" style="width:100%;height:75%;border:1px solid #ddd" class="form-control"></form:textarea>
	            <div style="margin-top:20px">
	            <a href="javascript:deny()" style="width:80px;height:27px;display:block;border-radius:2px;margin-left:394px;background:#ed724c;text-align:center;line-height:2;color:#fff">确认回退</a>
	            </div>
            </div>
        </div><!-- /.modal-content -->
    </form:form>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
