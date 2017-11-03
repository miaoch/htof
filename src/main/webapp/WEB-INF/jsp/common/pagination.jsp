<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 需要引入bootstrap文件 -->
<div>
    <nav class="navbar-right pager-all" style="margin-right: 10px">
        <ul class="pagination" style="margin-bottom: 0">
        	<c:choose>
   			<c:when test="${fn:contains(pagination, '&')}">  
   			${pagination }
   			</c:when>
   			<c:otherwise>
   			<%-- <li><a href="${page.requestURI} ">首页</a></li>
            <li>
                <a href="${page.requestURI}?pageNo=${page.prePage}" aria-label="Previous">
                    <span aria-hidden="true">上一页</span>
                </a>
            </li> --%>
            ${pagination }
            <%-- <li>
                <a href="${page.requestURI}?pageNo=${page.nextPage}" aria-label="Next">
                    <span aria-hidden="true">下一页</span>
                </a>
            </li>
            <li><a href="${page.requestURI}?pageNo=${page.totalPage}">末页</a></li> --%>
   			</c:otherwise>
   			</c:choose> 
        </ul>
        <ul class="pagination" style="margin-bottom: 0">
            <li>
                <span style="border: 0px;font-size: 14px;">共${page.totalPage}页 ${page.totalRecord}条</span>
            </li>
        </ul>
    </nav>
</div>