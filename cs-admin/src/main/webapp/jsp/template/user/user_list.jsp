<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户列表</title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>">
    <script src="<%=basePath%>/js/jquery-3.5.1.js"></script>
</head>
<body>
    <div>
        <h3>页面回显参数</h3>
        userName：${model.userName}<br>
        age：${model.age}
    </div>
    <hr>
    <div>
        <h3>执行业务操作</h3>
        <form action="<%=basePath%>/tempUser/doBusniess" method="post">
            userName：<input type="text" name="userName" value="${model.userName}"><br>
            age：<input type="text" name="age" value="${model.age}"><br>
            <input type="submit" value="保存">
        </form>
    </div>
    <hr>
    <div>
        <h3>上传文件</h3>
        <form action="<%=basePath%>/tempUser/doUploadFile" enctype="multipart/form-data" method="post">
            <input type="file" name="file"><br>
            userName：<input type="text" name="userName" value="${model.userName}"><br>
            age：<input type="text" name="age" value="${model.age}"><br>
            <input type="submit" value="上传">
        </form>
    </div>
    <hr>
    <div>
        <h3>下载文件</h3>
        <a href="<%=basePath%>/tempUser/doDownloadFile">下载</a>
    </div>
    <hr>
    <div>
        <h3>json请求</h3>
        <input type="button" value="发起请求" onclick="doJsonBusinessAction()">
    </div>
    <div>
        <h3>日志打印测试</h3>
        <a href="<%=basePath%>/tempUser/doLogPrint">日志打印测试</a>
    </div>
    <script>
        //发起json业务请求
        var doJsonBusinessAction=function () {
            $.ajax({
                type: "POST",
                url:"<%=basePath%>/tempUser/doJsonBusiness",
                //将JavaScript值转换为JSON字符串，使请求以json的形式传输到后台
                data: JSON.stringify({"userName":"张三","age":18}),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (res) {
                    alert(JSON.stringify(res));
                },
                error: function () {
                    alert("通信异常，访问失败！");
                }
            });
        }
    </script>

</body>
</html>
