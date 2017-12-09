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
        ${shopName }---菜品查看
        <p class="sectionP">
            <a href="#" id="info">
                <i class="glyphicon glyphicon-bell"></i>&nbsp;查看店铺信息&nbsp;&nbsp;
            </a>
            <%--<a href="#" id="add">
                <i class="glyphicon glyphicon-bell"></i>&nbsp;增加&nbsp;&nbsp;
            </a>
            <a href="#" id="edit">
                <i class="glyphicon glyphicon-edit"></i>&nbsp;编辑&nbsp;&nbsp;
            </a>
            <a href="#" id="remove">
                <i class="glyphicon glyphicon-remove"></i>&nbsp;删除&nbsp;&nbsp;
            </a>--%>
        </p>
    </h4>
    <form id="searchform" class="form-inline" role="form" action="commentList" method="get" style="margin: 5px 0px 0px 14px;">
        <%--<input type="text" name="shopId" value="${shopId }" placeholder="店铺ID" hidden>
        <div class="form-group">
            <input type="text" class="form-control" id="search" name="date" value="${date }" placeholder="日期">
        </div>
        <button type="submit" class="btn btn-default">搜&nbsp;索</button>--%>
    </form>
    <div class="table">
        <ul class="tableList tableList2">
            <li class="citytitle">
                <span style="width:20%">分类名</span>
                <span style="width:27%">菜品名</span>
                <span style="width:37%">描述</span>
                <span style="width:8%">标价</span>
                <span style="width:8%">设置成本价</span>
            </li>
            <c:forEach var="item" items="${list}">
                <li>
                    <span style="width:20%">
                        <c:out value="${item.categoryName}"/>
                    </span>
                    <span style="width:27%">
                        <c:out value="${item.name}"/>
                    </span>
                    <span style="width:37%" title="${item.description}">
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
    $('#info').click(function() {
        $("#detail").modal({
            remote:"getShop?shopId=${shopId}"
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