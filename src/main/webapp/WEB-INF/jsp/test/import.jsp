<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/basepath.jsp"%>
<html>
<head>
    <title>导入页面</title>
    <script src="${PATH}/js/jquery-1.11.0.min.js"></script>
</head>
<body>
    <form>
        <textarea id="area" style="height: 600px;width: 800px;"></textarea>
        <button type="button" onclick="imp();">提交</button>
    </form>
    <script>
        $('#area').keydown(function(e){
            if(e.keyCode==13){
                imp();
            }
        });
        function imp() {
            var data = $("#area").val();
            if (data) {
                $.ajax({
                    url:"import",
                    method:"post",
                    data:{data:data},
                    success: function (result) {
                        if (result == "success") {
                            $("#area").val("");
                            alert("提交成功");
                        } else {
                            alert("提交失败!手机号有误");
                        }
                    }
                });
            } else {
                alert("数据不能为空");
            }
        }
    </script>
</body>
</html>
