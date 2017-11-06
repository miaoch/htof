<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/basepath.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8"/>
    <title>订单统计查阅</title>
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
        订单统计查阅
        <p>
            <a href="#" onclick="export_excel();">
                <i class="glyphicon glyphicon-cloud-download"></i>&nbsp;导出excel&nbsp;&nbsp;
            </a>
        </p>
    </h4>
    <form id="searchform" class="form-inline" role="form" action="list" method="get" style="margin: 5px 0px 0px 14px;">
        <div class="form-group">
            <input type="text" class="form-control" id="search" name="date" value="${date }" placeholder="日期"
                   readonly="readonly">
        </div>
        <button type="submit" class="btn btn-default">搜&nbsp;索</button>
    </form>
    <div class="table">
        <ul class="tableList tableList2">
            <li class="citytitle">
                <em style="width:15%">店铺</em>
                <span style="width:15%">订单号</span>
                <span style="width:15%">消费金额</span>
                <span style="width:15%">用户手机号</span>
                <span style="width:20%">用户地址</span>
                <span style="width:20%">用户姓名</span>
            </li>
            <c:forEach var="item" items="${list}">
                <li>
                    <em style="width:15%">
                        <c:out value="${item.shopName}"/>
                    </em>
                    <span style="width:15%">
                        <c:out value="${item.orderId}"/>
                    </span>
                    <span style="width:15%">
                        <c:out value="${item.totalPay}"/>
                    </span>
                    <span style="width:15%">
                        <c:out value="${item.customerPhone}"/>
                    </span>
                    <span style="width:20%">
                        <c:out value="${item.customerAddress}"/>
                    </span>
                    <span style="width:20%">
                        <c:out value="${item.customerName}"/>
                    </span>
                </li>
            </c:forEach>
            <%--</c:forEach><c:forEach var="record" items="${page.recordList}">
                <li>
                    <em style="width:15%"><c:out value="${record.attendance.userName}"/></em>
                    <span style="width:15%">
                        <util:parseState mapName="TIMEMAP" state="${record.hour}"/>
                    </span>
                    <span style="width:15%">
                        <util:parseDate value="${record.attendance.signInTime}" format="HH:mm"/>
                    </span>
                    <span style="width:15%">
                        <util:parseDate value="${record.attendance.signOutTime}" format="HH:mm"/>
                    </span>
                    <span style="width:20%">
                        <util:parseState mapName="SIGNINMAP" state="${record.attendance.onLateFlag}" defaultValue="未签" redKeys=""/>
                    </span>
                    <span style="width:20%">
                        <util:parseState mapName="SIGNOUTMAP" state="${record.attendance.onEarlyFlag}" defaultValue="未签" redKeys=""/>
                    </span>
                </li>
            </c:forEach>--%>
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
    function export_excel() {
        $.ajax({
            type: "POST",
            url: "../util/exportJSKQExcel",
            async: false,
            success: function (result) {
                if (result == "error") {
                    $.messager.alert("错误", "导出失败,请联系开发人员！");
                } else {
                    var elemIF = document.createElement("iframe");
                    elemIF.src = result;
                    elemIF.style.display = "none";
                    document.body.appendChild(elemIF);
                }
            },
        });
    }
</script>
</body>
</html>