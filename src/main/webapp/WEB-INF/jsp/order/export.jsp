<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>导出excel页面</title>
</head>
<body>
    <form action="#" method="post">
        <c:forEach items="${shopMap }" var="map">
            <input type="checkbox" value="${map.value }" name="shopIds">${map.key }<br>
        </c:forEach>
        开始时间:<input type="text" name="beginDate"><br>
        结束时间:<input type="text" name="endDate"><br>
        <button type="submit">提交</button>
    </form>
</body>
</html>
