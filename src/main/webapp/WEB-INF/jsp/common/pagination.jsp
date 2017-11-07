<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 需要引入bootstrap文件 -->
<div>
    <nav class="navbar-right pager-all" style="margin-right: 10px">
        <ul class="pagination" style="margin-bottom: 0">
            ${page.pageString}
        </ul>
        <ul class="pagination" style="margin-bottom: 0">
            <li>
                <span style="border: 0px;font-size: 14px;">共&nbsp;${page.totalPage}&nbsp;页&nbsp;${page.totalRecord}&nbsp;条</span>
            </li>
        </ul>
    </nav>
</div>