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
    <title>菜品管理</title>
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
        ${shopName }---菜品查看&nbsp;&nbsp;
            <c:if test="${shop.isOpen == 0}">
                <span style="color: red">未营业</span>
            </c:if>
            <c:if test="${shop.isOpen == 1}">
                <span style="color: #00ee00">营业中</span>
            </c:if>
            <c:if test="${shop.servingTime != null}">
                &nbsp;&nbsp;营业时间：${shop.servingTime[0]}
            </c:if>

        <p class="sectionP">
            <a href="#" id="info">
                <i class="glyphicon glyphicon-search"></i>&nbsp;查看店铺信息&nbsp;&nbsp;
            </a>
            <a href="#" id="setPrice">
                <i class="glyphicon glyphicon-pencil"></i>&nbsp;设置成本价&nbsp;&nbsp;
            </a>
        </p>
    </h4>
    <form id="searchform" class="form-inline" role="form" action="commentList" method="get" style="margin: 5px 0px 0px 14px;">
    </form>
    <div class="table">
        <ul class="tableList tableList2">
            <li class="citytitle">
                <span style="width:10%">菜品ID</span>
                <span style="width:20%">分类名</span>
                <span style="width:24%">菜品名</span>
                <span style="width:30%">描述</span>
                <span style="width:8%">标价</span>
                <span style="width:8%">设置成本价</span>
            </li>
            <c:forEach var="item" items="${list}">
                <li onclick="vid = '${item.id}';" ondblclick="$('#setPrice').click();">
                    <span style="width:10%">
                        <c:out value="${item.id}"/>
                    </span>
                    <span style="width:20%">
                        <c:out value="${item.categoryName}"/>
                    </span>
                    <span style="width:24%">
                        <c:out value="${item.name}"/>
                    </span>
                    <span style="width:30%" title="${item.description}">
                        <c:out value="${item.description}"/>
                    </span>
                    <span style="width:8%">
                        <c:out value="${item.costPrice}"/>
                    </span>
                    <span style="width:8%">
                        <c:out value="${item.price}"/>
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
    var vid;
    $('#info').click(function() {
        $("#detail").modal({
            remote:"getShop?shopId=${shopId}"
        });
    });
    $('#setPrice').click(function() {
        debugger;
        if(vid == undefined){
            swal("请选择一条记录！");
            return;
        }
        $("#detail").modal({
            remote:"setPrice?vfoodId="+vid+"&curPage=${page.curPage}&pageSize=${page.pageSize}&shopId=${shopId}"
        });
    });
    $("#search").datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        autoclose: true,
        startView: 2,
        minView: 2,
    });
    $(function () {
        $('.spantip').tooltip();
        $("#search").change(function () {
            $("#searchform").submit();
        });
    });
</script>
</body>
</html>