<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="common/basepath.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<title>登录</title>
<link rel="stylesheet" type="text/css" href="${PATH}/css/main.css"/>
<style type="text/css">
.logtitle {
	text-align:center;
	margin: 0px auto;
	width: 520px;
	font-size: 40px;
	color: #fff;
	padding-top: 50px;
	font-family: "Microsoft YaHei","黑体","宋体",sans-serif;
}
#img {
	position: absolute;
  	left: 458px;
  	top: 0px;
  	z-index:9999;
	display:none;
	width:200px; 
	height:200px; 
}
</style>
</head>
<body>
	<div class="wrap">
		<div class="logtitle">涵哥小站</div>
		<div class="login">
			<h1>用户登入</h1>
			<img alt="" src="images/apk.png" id="img">
			<form name="loginform" action="${PATH}/login" method="post">
				<input type="text" placeholder="账户名" name="username"/>
				<input type="password" placeholder="密码" name="password"/>
				<a href="#" onclick="javascript:document.loginform.submit();">登录</a>
				<div style="margin-top: 5px; text-align: center; color: red;">
					<span id="span">用户名或密码不正确</span>
				</div>
			</form>
		</div>
	</div>
<script type="text/javascript" src="${PATH}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${PATH}/js/login.js"></script>
<script type="text/javascript">
    document.onkeydown = function(e) {
        var theEvent = e || window.event;
        var code = theEvent.keyCode || theEvent.which || theEvent.charCode;  
        if (code == 13) {
            document.loginform.submit();
        }
    }  
   $(document).ready(function(){
    	var errorinfor="${requestScope.error}";
    	 if(errorinfor=="2"){
    		$('span').hide();
   		}else if(errorinfor=="1"){
   			$('span').show();
    	}else if(errorinfor==""){
    		$('span').hide();
    	}
    })
</script>
</body>
</html>
