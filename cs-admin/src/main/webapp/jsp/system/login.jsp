<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>登录页</title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>">
</head>
<body>

    <form method="post" action="<%=basePath%>tmpLogin/login">
        用户名：<input type="text" name="userName" />
        <br>
        密码：<input type="text" name="password" />
        <input type="submit" value="登录">
    </form>
    <div style="color: red;">
        ${message}
    </div>
</body>
</html>
