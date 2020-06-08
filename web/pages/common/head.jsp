<%--
  Created by IntelliJ IDEA.
  User: 10764
  Date: 2020/6/1
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--固定在工程路径	-->
<%
    String basePath = request.getScheme()+"://"+request.getServerName()
            +":"
            +request.getServerPort()
            +request.getContextPath()
            +"/";
    pageContext.setAttribute("basePath", basePath);
%>

<base href="<%=basePath%>>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
