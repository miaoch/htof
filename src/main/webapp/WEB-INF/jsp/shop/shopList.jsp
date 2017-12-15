<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/basepath.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="util" uri="/WEB-INF/tlds/utiltag.tld" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8"/>
    <title>所有店铺</title>
    <link href="${PATH}/js/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="${PATH}/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="${PATH}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${PATH }/easyui/themes/default/easyui.css" type="text/css" rel="stylesheet">
    <link href="${PATH }/easyui/themes/icon.css" type="text/css" rel="stylesheet">
    <link href="${PATH}/css/main.css" rel="stylesheet"/>
    <link href="${PATH}/js/sweetalert/css/my-sweet-alert.css" rel="stylesheet">
    <script src="${PATH}/js/jquery-1.11.0.min.js"></script>
    <script src="${PATH}/js/bootstrap/bootstrap.min.js"></script>
    <script src="${PATH}/js/pubinfo.js"></script>
    <script src="${PATH}/js/sweetalert/js/sweet-alert.js"></script>
    <style type="text/css">
        .section a {
            color: #3AC5E2;
        }
    </style>
</head>
<body>
<div class="section">
    <h4>
        店铺列表&nbsp;&nbsp;
        <p class="sectionP">
            <a href="#" id="init">
                <i class="glyphicon glyphicon-search"></i>&nbsp;同步菜品信息&nbsp;&nbsp;
            </a>
        </p>
    </h4>
    <form id="searchform" class="form-inline" role="form" action="commentList" method="get" style="margin: 5px 0px 0px 14px;">
    </form>
    <div class="table">
        <ul class="tableList tableList2">
            <li class="citytitle">
                <span style="width:20%">店铺名</span>
                <span style="width:50%">店铺描述</span>
                <span style="width:10%">营业状态</span>
                <span style="width:20%">营业时间</span>
            </li>
            <c:forEach var="item" items="${list}">
                <li>
                    <span style="width:20%">
                        <c:out value="${item.name}"/>
                    </span>
                    <span title="${item.description}" style="width:50%">
                        <c:out value="${item.description}"/>
                    </span>
                    <span style="width:10%">
                        <c:if test="${item.isOpen == 0}">
                            <div style="color: red">未营业</div>
                        </c:if>
                        <c:if test="${item.isOpen == 1}">
                            <div style="color: #00ee00">营业中</div>
                        </c:if>
                    </span>
                    <span style="width:20%">
                        <c:out value="${item.servingTime[0]}"/>
                    </span>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<%@include file="../common/pagination.jsp" %>
<%@include file="../common/modalinfo.jsp" %>
<script src="${PATH }/easyui/jquery.easyui.min.js"></script>
<script src="${PATH }/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="${PATH}/js/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${PATH}/js/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
    $(function () {
        $('.spantip').tooltip();
        $("#search").change(function () {
            $("#searchform").submit();
        });
    });
    $('#init').click(function() {
        swal({
            title: "确定要同步菜品吗？需要等待一段时间",
            text: "",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确认！",
            cancelButtonText: "取消！",
            closeOnConfirm: false,
            closeOnCancel: true,
            imageUrl:"",
        },function(isConfirm){
            if(isConfirm){
                $.ajax({
                    url:'../test/initFood',
                    type:'get',
                    //dataType:'text',
                    //data:"",
                    success:function(data){
                        if (data == "true") {
                            location.reload(true);
                        } else {
                            swal("菜品同步失败！");
                        }
                    }
                });
            }
        });
    });
</script>
</body>
</html>