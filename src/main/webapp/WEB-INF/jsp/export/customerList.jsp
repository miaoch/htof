<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/basepath.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        人员统计查阅
        <p>
            <a href="#" onclick="export_excel()">
                <i class="glyphicon glyphicon-cloud-download"></i>&nbsp;导出excel&nbsp;&nbsp;
            </a>
        </p>
    </h4>
    <form id="searchform" class="form-inline" role="form" action="customerList" method="get" style="margin: 5px 0px 0px 14px;">
        <div class="form-group">
            <input type="text" class="form-control" id="phone" name="phone" value="${phone }" placeholder="手机号">
            <input type="text" class="form-control" id="name" name="name" value="${name }" placeholder="姓名">
            <input type="text" class="form-control" id="vfoodId" name="vfoodId" value="${vfoodId }" placeholder="菜品ID">
            <input type="text" class="form-control" id="count" name="count" value="${count }" placeholder="数量">
            <input type="text" class="form-control" id="date" name="date" value="${date }" placeholder="最后一次购买时间" readonly>
        </div>
        <button type="submit" class="btn btn-default">搜&nbsp;索</button>
    </form>
    <div class="table">
        <ul class="tableList tableList2">
            <li class="citytitle">
                <em style="width:15%">手机号</em>
                <span style="width:15%">姓名</span>
                <span style="width:15%">地址</span>
                <span style="width:15%">用户ID</span>
                <span style="width:20%">最后一次购买时间</span>
                <span style="width:20%">累积购买次数</span>
            </li>
            <c:forEach var="item" items="${list}">
                <li>
                    <em style="width:15%">
                        <c:out value="${item.phone}"/>
                    </em>
                    <span style="width:15%">
                        <c:out value="${item.name}"/>
                    </span>
                    <span title="${item.address}" style="width:15%">
                        <c:out value="${item.address}"/>
                    </span>
                    <span style="width:15%">
                        <c:out value="${item.userId}"/>
                    </span>
                    <span style="width:20%">
                        <util:parseDate value="${item.lasttime}"/>
                    </span>
                    <span style="width:20%">
                        <c:out value="${item.count}"/>
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
    $("#date").datetimepicker({
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
        window.open("exportCustomerExcel?phone=${phone}&date=${date}&name=${name}&count=${count}&vfoodId=${vfoodId}");
    }
</script>
</body>
</html>