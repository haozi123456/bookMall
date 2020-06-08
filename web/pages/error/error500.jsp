<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>错误页面</title>
    <%--	静态包含base标签及css样式、jquery	--%>
    <%@ include file="/pages/common/head.jsp"%>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="/static/img/logo.gif" >
    <span class="wel_word">不好意思，-.-出错了</span>
    <%--	静态包含登陆成功之后的菜单 	--%>
    <%@ include file="/pages/common/login_success_menu.jsp"%>
</div>

<div id="main">

    <h1>网络暂时出错，请您重新尝试</h1>

</div>

<%--  静态包含页脚	--%>
<%@include file="/pages/common/foot.jsp"%>

</body>
</html>