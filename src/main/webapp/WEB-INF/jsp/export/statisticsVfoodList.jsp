<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/basepath.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="util" uri="/WEB-INF/tlds/utiltag.tld" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8"/>
    <title>成本核算</title>
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
        成本核算
        <p>
            <a href="#" onclick="export_excel()">
                <i class="glyphicon glyphicon-cloud-download"></i>&nbsp;导出excel&nbsp;&nbsp;
            </a>
        </p>
    </h4>
    <form id="searchform" class="form-inline" role="form" action="statisticsVfoodList" method="get" style="margin: 5px 0px 0px 14px;">
        <div class="form-group">
            <select class="form-control" id="shopId" name="shopId" >
                <option value="">所有</option>
                <c:forEach var="shop" items="${shopList}">
                    <option value="${shop.id}"
                            <c:if test='${shop.id == shopId}'>selected="selected"</c:if>>${shop.name}</option>
                </c:forEach>
            </select>
            <input type="text" class="form-control" id="beginDate" name="beginDate" value="${beginDate }" placeholder="开始时间" readonly>
            <input type="text" class="form-control" id="endDate" name="endDate" value="${endDate }" placeholder="结束时间" readonly>
        </div>
        <button type="submit" class="btn btn-default">搜&nbsp;索</button>
    </form>
    <div class="table">
        <ul class="tableList tableList2">
            <li class="citytitle">
                <em style="width:25%">店铺名</em>
                <span style="width:25%">菜品名</span>
                <span style="width:25%">数量</span>
                <span style="width:25%">价值</span>
            </li>
            <c:forEach var="item" items="${list}">
                <li>
                    <em style="width:25%">
                        <c:out value="${item.shopName}"/>
                    </em>
                    <span style="width:25%">
                        <c:out value="${item.vfoodName}"/>
                    </span>
                    <span style="width:25%">
                        <c:out value="${item.count}"/>
                    </span>
                    <span style="width:25%">
                        <c:out value="${item.price}"/>
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
    $("#beginDate").datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        autoclose: true,
        startView: 2,
        minView: 2,
    });
    $("#endDate").datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        autoclose: true,
        startView: 2,
        minView: 2,
    });
    $(function () {
        $('.spantip').tooltip();
    });
    function export_excel() {
        window.open("exportStatisticsVfoodExcel?beginDate=${beginDate}&endDate=${endDate}&shopId=${shopId}");
    }
</script>
</body>
</html>