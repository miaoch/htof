<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../common/basepath.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>图表分析</title>
<link rel="stylesheet" type="text/css" href="${PATH}/css/main.css"/>
</head>
<body style="background-color: white;">
<div id="leftchart" style="width:490px;height:540px;"></div>
<script type="text/javascript" src="${PATH}/js/jquery-1.11.0.min.js"></script>
<script src="${PATH}/js/highcharts/highcharts.js"></script>
<script src="${PATH}/js/highcharts/themes/grid-light.js"></script>
<script type="text/javascript">
function refresh(data) {
	$('#leftchart').highcharts({
    	credits: {
            enabled: false
        },
        title: {
            text: '考勤人次折线图',
            x: 0 //center
        },
        subtitle: {
            text: '',
            x: -20
        },
        xAxis: {
            categories: data.x
        },
        yAxis: {
            title: {
                text: '人次'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '（人）'
        },
        series: [{
            name: '总考勤人次',
            data: data.kqts
        }, {
            name: '正常人次',
            data: data.zcts
        }, {
            name: '迟到人次',
            data: data.cdts
        }, {
            name: '早退人次',
            data: data.ztts
        }, {
            name: '迟到早退人次',
            data: data.cdztts
        }, {
            name: '旷工人次',
            data: data.kgts
        }, {
            name: '请假人次',
            data: data.qjts
        }]
    });
}
</script>
</body>
</html>
