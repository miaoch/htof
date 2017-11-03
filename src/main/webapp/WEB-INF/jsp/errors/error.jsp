<%@ page contentType="text/html;charset=UTF-8"%>
<%
response.setStatus(200);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>404页面</title>
    <style>
        #position {
            position: absolute;
            top: 50%;
            left: 50%;
            width: 500px;
            height: 300px;
            margin: -150px 0 0 -250px;
            background: url('images/404.png') no-repeat;
        }
        #errorinfo {
            position: absolute;
            top: 50%;
            left: 50%;
            margin-top: 80px;
            margin-left: -250px;
            width: 500px;
            height: 200px;
            padding: 10px;
            font-family: "Microsoft YaHei", "微软雅黑", "SimSun", "宋体";
            font-style: italic;
            color: #52483B;
        }
    </style>
</head>
<body style="background-color: white">
<div id="position"></div>
<div id="errorinfo">
	${requestScope.ex }
</div>
<script>
</script>
</body>
</html>