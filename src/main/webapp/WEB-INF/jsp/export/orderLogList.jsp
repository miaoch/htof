<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/basepath.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <p>
            <a href="#" id="info">
                <i class="glyphicon glyphicon-search"></i>&nbsp;查看菜品详情&nbsp;&nbsp;
            </a>
        </p>
    </h4>
    <form id="searchform" class="form-inline" role="form" action="orderLogList" method="get" style="margin: 5px 0px 0px 14px;">
        <div class="form-group">
            <select class="form-control" id="shopId" name="shopId" >
                <option value="">所有店铺</option>
                <c:forEach var="shop" items="${shopList}">
                    <option value="${shop.id}"
                            <c:if test='${shop.id == shopId}'>selected="selected"</c:if>>${shop.name}</option>
                </c:forEach>
            </select>
            <input type="text" class="form-control" id="orderId" name="orderId" value="${orderId }" placeholder="订单号">
            <input type="text" class="form-control" id="starttime" name="starttime" value="${starttime }" placeholder="开始时间" readonly>
            <input type="text" class="form-control" id="endtime" name="endtime" value="${endtime }" placeholder="结束时间" readonly>
        </div>
        <button type="submit" class="btn btn-default">搜&nbsp;索</button>
    </form>
    <div class="table">
        <ul class="tableList tableList2">
            <li class="citytitle">
                <em style="width:15%">店铺</em>
                <span style="width:20%">订单号</span>
                <span style="width:15%">用户手机号</span>
                <span style="width:20%">用户姓名</span>
                <span style="width:15%">毛收入</span>
                <span style="width:15%">净利润</span>
            </li>
            <c:forEach var="item" items="${list}">
                <li onclick="oid = '${item.orderId}';">
                    <em style="width:15%">
                        <c:out value="${item.shopName}"/>
                    </em>
                    <span style="width:20%">
                        <c:out value="${item.orderId}"/>
                    </span>
                    <span style="width:15%">
                        <c:out value="${item.customerPhone}"/>
                    </span>
                    <span style="width:20%">
                        <c:out value="${item.customerName}"/>
                    </span>
                    <span style="width:15%">
                        <c:out value="${item.income}"/>
                    </span>
                    <span style="width:15%">
                        <c:if test="${item.cost != null}">
						    <fmt:formatNumber type="number" value="${item.income - item.cost}" pattern="0.00" maxFractionDigits="2"/>
                        </c:if>
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
    var oid;
    $("#detail").modal({
        show: false,
        remote:"../order/getOrder"
    });
    $('#info').click(function() {
        if(oid == undefined){
            swal("请选择一条记录！");
            return;
        }
        $.ajax({
            url:"../order/getItemsCharts?orderId="+oid,
            type:'get',
            success:function(data){
                refresh(JSON.parse(data));
                $("#detail").modal({
                    show: true,
                });
            }
        });
    });
    $("#starttime").datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        autoclose: true,
        startView: 2,
        minView: 2,
    });
    $("#endtime").datetimepicker({
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
        window.open("exportOrderlogExcel?orderId=${orderId}&starttime=${starttime}&endtime=${endtime}&shopId=${shopId}");
    }
</script>
</body>
</html>