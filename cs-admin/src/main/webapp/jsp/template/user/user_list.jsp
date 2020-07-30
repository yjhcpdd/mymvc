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
</head>
<body>
    <img src="img/template/dog.png">
    <hr>
    <c:if test="${not empty msg}">
        ${msg}
    </c:if>
    <hr>
    <div>
        上传文件：
        <form action="<%=request.getContextPath()%>/tempUser/doFileUpload" enctype="multipart/form-data" method="post">
            <input type="file" name="file"><br><br>
            <input type="submit" value="上传">
        </form>
    </div>
    <script>
        
    </script>

</body>
</html>
