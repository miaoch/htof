<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../../common/basepath.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>图表分析</title>
<link rel="stylesheet" type="text/css" href="${PATH}/css/main.css"/>
<link rel="stylesheet" type="text/css" href="${PATH}/css/bootstrap-theme.min.css"/>
<link rel="stylesheet" type="text/css" href="${PATH}/css/bootstrap.min.css"/>
</head>
<body style="background-color: white;">
<div class="homepage_title" style="margin:5px auto;">
	考勤情况比例
</div>
<!-- <ul class="nav nav-tabs" role="tablist">
	<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">事件发生量</a></li>
    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">部件发生量</a></li>
</ul> -->
<!-- Tab panes -->
<div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="home">
		<div id="middle_1" style="width:490px;height:470px;"></div>
    </div>
    <div role="tabpanel" class="tab-pane" id="profile">
    	<div id="middle_2" style="width:490px;height:470px;"></div>
    </div>
</div>
<script type="text/javascript" src="${PATH}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${PATH}/js/bootstrap/bootstrap.min.js"></script>
<script src="${PATH}/js/highcharts/highcharts.js"></script>
<script src="${PATH}/js/highcharts/themes/grid-light.js"></script>
<script type="text/javascript">
$(function () {
	Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function(color) {
	    return {
	        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
	        stops: [
	            [0, color],
	            [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
	        ]
	    };
	});
});	
function refresh(data) {
	$('#middle_1').highcharts({
    	credits: {
            enabled: false
        },
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: ''
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    formatter: function() {
                        return '<b>'+ this.point.name +'</b>: '+ this.percentage.toFixed(0) +' %';
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: '占',
            data: data.kqbl
        }]
    });
    
    /* $('#middle_2').highcharts({
    	credits: {
            enabled: false
        },
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: ''
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    formatter: function() {
                        return '<b>'+ this.point.name +'</b>: '+ this.percentage.toFixed(0) +' %';
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: '占',
            data: data.kqbl
        }]
    }); */
}
</script>
</body>
</html>
