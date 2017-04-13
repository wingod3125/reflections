<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户注册</title>
</head>

<body>
	<h2>用户注册</h2>
	<form action="Account?tag=register" method="post">
		<p>
			用户名：&nbsp;<input type="text" name="userName" />
		</p>
		<p>
			密码：&nbsp;<input type="password" name="password" />
		</p>
		<p>
			请再次输入密码：&nbsp;<input type="password" />
		</p>
		<p>
			<input type="checkbox" />我接受&laquo;用户注册协议&raquo;
		</p>
		<p id="">
			<input type="submit" name="" id="" value="注册" /> &nbsp;&nbsp; <input
				type="button" id="" value="重置" />
		</p>
		<p style="color: red">
			<%
				if (request.getAttribute("message") != null) {
					out.print(request.getAttribute("message"));
				}
			%>
		</p>
	</form>
</body>
</html>
