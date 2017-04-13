<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户登录</title>
</head>

<body>
	<div>
		<h2>用户登录</h2>
		<%
			//取到所有的Cookie
			Cookie[] cookies = request.getCookies();
			String name="";
			if(request.getAttribute("name")!=null){
				name=(String)request.getAttribute("name");
			}
			String pwd = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("name")) {
					name = c.getValue();
				}
				if (c.getName().equals("pwd")) {
					pwd = c.getValue();
				}
			}
		%>
		<p>
			会话ID：<%=session.getId()%>
		</p>
		<form action="Account?tag=log" method="post">
			<p>
				用户名：<input type="text" name="userName" value="<%=name%>" <%-- value='<%=request.getAttribute("name") == null ? "" : request
					.getAttribute("name")%>' --%> />
			</p>
			<p>
				密&nbsp;码：<input type="password" name="pwd" value="<%=pwd%>" />
			</p>
			<p>
				<input type="radio" name="saveAge" value="1" />1天内自动登录
				<input type="radio" name="saveAge" value="2" />2天内自动登录
			</p>
			<p id="">
				<a href="RegisterSer">立即注册</a>&nbsp;&nbsp; <a href="#">忘记密码</a>
			</p>
			<p id="">
				<input type="submit" name="" id="" value="登录" /> &nbsp;&nbsp; <input
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
	</div>
</body>
</html>
