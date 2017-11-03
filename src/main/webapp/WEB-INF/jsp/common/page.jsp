<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 需要引入bootstrap文件 -->
<div>
    <nav class="navbar-right pager-all" style="margin-right: 10px">
        <ul class="pagination" style="margin-bottom: 0">
            ${pagination }
        </ul>
        <ul class="pagination" style="margin-bottom: 0">
            <li>
                <span style="border: 0px;font-size: 14px;">共${page.totalPage}页 ${page.totalRecord}条</span>
            </li>
        </ul>
    </nav>
</div>