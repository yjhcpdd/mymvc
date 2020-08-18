<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>">
</head>
<body>
    登录后首页
    <br>
    <a href="<%=basePath%>tmpLogin/logout">自定义退出</a>
    <a href="<%=basePath%>logout">拦截器退出</a>
</body>
</html>
