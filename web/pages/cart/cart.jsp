<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--	静态包含base标签及css样式、jquery	--%>
	<%@ include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("#clearCart").click(function () {
				return confirm("你确认要清空购物车吗");
			})
			$("#deleteBook").click(function () {
				return confirm("你确认要删除该商品吗");
			})
			//
			$(".updateCount").change(function () {
				//获取商品名称
				var name = $(this).parent().parent().find("td:first").text();
				//获取自定义的bookId属性的值。
				var bookId= $(this).attr("bookId");
				//获取商品数量
				var count = this.value;
				if(confirm("你确定要将【" + name + "】商品修改数量为："+count+"吗？")) {
					//发起请求，给服务器保存修改
					location.href = "http://localhost:8080/book/cartServlet?action=updateCount&count="+count+"&id="+bookId;
				}else{
					//defaultValue属性是表单项dom对象的属性，它表示默认的value属性值
					this.value = this.defaultValue;
				}
			});
		})
	</script>
</head>
<body>
<%--若提交订单后库存不够，需要提示用户--%>
<%
	String bookName = (String) session.getAttribute("bookName");
	if (bookName != null && !" ".equals(bookName)) {
%>
<script type="text/javascript">
	alert("图书【${sessionScope.bookName}】仅剩${sessionScope.stock}本，请修改购买数量");
</script>
<%}
	session.removeAttribute("bookName");
	session.removeAttribute("stock");
%>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%--	静态包含登陆成功之后的菜单 	--%>
		<%@ include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>

			<%--购物车，没有东西的情况下--%>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">亲，购物车为空，去首页看看喽！</a></td>
				</tr>
			</c:if>

			<c:forEach items="${sessionScope.cart.items}" var="entry">
				<tr>
					<td>${entry.value.name}</td>
					<td>
						<input class="updateCount"
							   bookId = "${entry.value.id}"
							   type="text" style="width: 80px;text-align:center" value="${entry.value.count}">
					</td>
					<td>${entry.value.price}</td>
					<td>${entry.value.totalPrice}</td>
					<td><a id="deleteBook" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
				</tr>
			</c:forEach>

		</table>

		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span></span>
				<span class="cart_span"><a id="clearCart" href="cartServlet?action=clearCart">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=creatOrder">去结账</a></span>
			</div>
		</c:if>

	</div>

	<%--  静态包含页脚	--%>
	<%@include file="/pages/common/foot.jsp"%>

</body>
</html>