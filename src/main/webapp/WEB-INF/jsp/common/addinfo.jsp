<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="adddetail" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg">
  	<form action="bicycleaddinfo" id="Log">
  		 <div class="modal-content" style="height:300px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myLargeModalLabel">添加意见</h4>
            </div>
	       <%--  <input id="eventid" name="eventid" value="${addid}" type="hidden"/> --%>
            <div class="modal-body" style="width:100%;height:80%;">
             	 <%--  <form:textarea id="addinfo" path="addinfo" style="width:100%;height:75%;border:1px solid #ddd" class="form-control"></form:textarea>  --%>
             	<!--  <input type="textarea" id="denyreason" style="width:90%;height:80%; border:solid red 1px;"/> -->
             	<textarea rows="8" cols="120" id="denyreason" style="border:1px solid #ddd" class="form-control">
             	</textarea>
	            <div style="margin-top:20px">
	            <a id="d1"  style="width:80px;height:27px;display:block;border-radius:2px;margin-left:394px;background:#ed724c;text-align:center;line-height:2;color:#fff">确认</a>
	            </div>
            </div>
        </div><!-- /.modal-content -->
  	
  	
  	
  	</form>
       
	
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
