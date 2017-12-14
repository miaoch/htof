<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/basepath.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>菜品图表分析</title>
<link rel="stylesheet" type="text/css" href="${PATH}/css/main.css"/>
<link rel="stylesheet" type="text/css" href="${PATH}/css/bootstrap-theme.min.css"/>
<link rel="stylesheet" type="text/css" href="${PATH}/css/bootstrap.min.css"/>
</head>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title" id="exampleModalLabel">菜品图表分析</h4>
</div>
<div class="modal-body">
    <div id="container" style="min-width: 550px; height: 400px; margin: 0 auto"></div>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
</div>

<script type="text/javascript" src="${PATH}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${PATH}/js/bootstrap/bootstrap.min.js"></script>
<script src="${PATH}/js/highcharts/highcharts.js"></script>
<script src="${PATH}/js/highcharts/themes/grid-light.js"></script>
<script type="text/javascript">
function refresh(data) {
    console.log(data);
    Highcharts.chart('container', {
        chart: {
            type: 'column'
        },
        title: {
            text: '菜品分析'
        },
        xAxis: {
            type: 'category'
        },
        yAxis: {
            title: {
                text: '金额'
            }
        },
        plotOptions: {
            series: {
                borderWidth: 0,
                dataLabels: {
                    enabled: true,
                    format: '{point.y}'
                }
            }
        },
        tooltip: {
            headerFormat:''
        },
        series: data
    });
}
</script>
</body>
</html>
