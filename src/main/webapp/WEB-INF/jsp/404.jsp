<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: Air15
  Date: 2021/4/26
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>

<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404</title>
</head>
<body>
页面找不到了
<input type="button" value="返回" onclick="next()">
</body>
<script type="text/javascript">
    <%--jsp实现页面跳转--%>
    function next(){
        //从http://localhost:8080/article/-1  到 http://localhost:8080
        window.location = "../../";

    }
</script>
</html>
