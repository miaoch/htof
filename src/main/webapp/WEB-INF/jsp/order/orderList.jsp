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
    <title>人员统计查阅</title>
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
        订单详情
        <p>
            <%--<a href="#" onclick="export_excel();">
                <i class="glyphicon glyphicon-cloud-download"></i>&nbsp;导出excel&nbsp;&nbsp;
            </a>--%>
        </p>
    </h4>
    <form id="searchform" class="form-inline" role="form" action="orderList" method="get" style="margin: 5px 0px 0px 14px;">
        <input type="text" name="shopId" value="${shopId }" placeholder="店铺ID" hidden>
        <div class="form-group">
            <input type="text" class="form-control" id="search" name="date" value="${date }" placeholder="日期">
        </div>
        <button type="submit" class="btn btn-default">搜&nbsp;索</button>
    </form>
    <div class="table">
        <ul class="tableList tableList2">
            <li class="citytitle">
                <span style="width:20%">订单号</span>
                <span style="width:8%">流水号</span>
                <span style="width:7%">毛收入</span>
                <span style="width:20%">下单时间</span>
                <span style="width:10%">顾客姓名</span>
                <span style="width:20%">顾客地址</span>
                <span style="width:15%">订单状态</span>
            </li>
            <c:forEach var="item" items="${list}">
                <li>
                    <span style="width:20%">
                        <c:out value="${item.id}"/>
                    </span>
                    <span style="width:8%">
                        <c:out value="${item.daySn}"/>
                    </span>
                    <span style="width:7%">
                        <c:out value="${item.income}"/>
                    </span>
                    <span style="width:20%">
                        <fmt:formatDate value="${item.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </span>
                    <span style="width:10%">
                        <c:out value="${item.consignee}"/>
                    </span>
                    <span title="${item.address}" style="width:20%">
                        <c:out value="${item.address}"/>
                    </span>
                    <span style="width:15%">
                        <util:parseState mapName="ORDERSTATUSMAP" state="${item.status}"/>
                    </span>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
<%@include file="../common/pagination.jsp" %>
<script src="${PATH }/easyui/jquery.easyui.min.js"></script>
<script src="${PATH }/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="${PATH}/js/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="${PATH}/js/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
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