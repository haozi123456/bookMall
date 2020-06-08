<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<%--	静态包含base标签及css样式、jquery	--%>
	<%@ include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		//页面加载完成之后
		$(function () {
			//点击验证图片绑定单击事件
			$("#code_img").click(function () {
				//this表示当前正在响应的dom对象，即验证码图片响应事件
				//src属性表示验证码图片路径，它可读可写
				this.src = "${basePath}kaptcha.jpg?d="+new Date();
			})

			//注册绑定单击事件
			$("#sub_btn").click(function () {
				//验证用户名
				//1.获取用户名输入框的姓名
				//2.创建正则项表达式
				//3.使用test方法验证
				//4.提示结果
				var usernameText = $("#username").val();
				var usernamePattern = /^\w{5,12}$/;
				if(!usernamePattern.test(usernameText)){
					$("span.errorMsg").text("用户名不合法！");
					return false;
				}


				//验证密码
				var passwordText = $("#password").val();
				var passwordPattern = /^\w{5,12}$/;
				if(!passwordPattern.test(passwordText)){
					$("span.errorMsg").text("密码不合法！");
					return false;
				}

				//确认密码
				var repwd = $("#repwd").val();
				if(repwd!=passwordText){
					$("span.errorMsg").text("确认密码不一致！");
					return false;
				}

				//邮箱验证
				var emailText = $("#email").val();
				var emailPattern = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;

				if(!emailPattern.test(emailText)){
					$("span.errorMsg").text("邮箱名不合法！");
					return false;
				}

				var codeText = $("#code").val();
				codeText = $.trim(codeText);
				if(codeText==null || codeText==""){
					$("span.errorMsg").text("验证码为空！");
					return false;
				}

				$("span.errorMsg").text("");
			})
		})
	</script>


<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
									<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username" id="username"
											value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   autocomplete="off" tabindex="1" name="email" id="email"
										   value="<%=request.getAttribute("email")==null?"":request.getAttribute("email")%>"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" name="code" style="width: 100px;" id="code"/>
									<img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px;width: 120px;height: 40px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%--  静态包含页脚	--%>
		<%@include file="/pages/common/foot.jsp"%>
</body>
</html>