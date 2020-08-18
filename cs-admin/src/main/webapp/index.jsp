<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<shiro:guest>
    <!-- 无登录信息，跳转到登录页面 -->
    <jsp:forward page="/tmpLogin/initLogin" />
</shiro:guest>
<shiro:authenticated>
    <jsp:forward page="/tmpLogin/init" />
</shiro:authenticated>
