<%@page import="listener.CountListener"%>
<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>首页</title>
</head>

<body>
	<p>
		<%
		    out.print("<br>在线人数:" + CountListener.getCount());
		%>
	</p>
	<p>
		会话ID：<%=session.getId()%>
	</p>
	<p>
		<%
		    //String name = (String) request.getAttribute("name");
		    String name = (String) session.getAttribute("name");
		    if (name != null)
		        out.print("欢迎你" + name
		                + "&nbsp;<a href=\"Account?tag=exit\">【退出】</a>");
		%>
	</p>
	<p>
		<a href="login.jsp">登录</a> <a href="RegisterSer">注册</a>
	</p>
	<p>
		<a href="StudentSer">后台学员列表</a>
	</p>
	<p>
		<a href="PageSer">分页查询</a>
	</p>
	<p>
		<a href="history.jsp">历史上的今天</a>
	</p>
	This is my JSP page.
	<br> 实现从 2000年到今年一个有多少个闰年
	<br> 系统时间：
	<%
	    SimpleDateFormat df = new SimpleDateFormat("yyyy" + "年" + "MM"
	            + "月" + "dd" + "日");// 设置日期格式
	    //向客户端输出一些信息。在Java控制台项目中输出：system.out.print()
	    out.println(df.format(new Date()));// new Date()为获取当前系统时间
	    int nowYear = Calendar.getInstance().get(Calendar.YEAR);
	    int count = 0;
	    int year = 2000;
	%>
	<br />
	<%
	    for (int i = year; i <= nowYear; i++) {
	        if (i % 400 == 0 || (i % 4 == 0 && i % 100 != 0)) {
	            out.println(i + "<br>");
	            count++;
	        }
	    }
	%>
	2000年到<%=nowYear%>年一共有<%=count%>个闰年
	<br> Hello World!
	<br />
	<%
	    out.println("Your IP address is " + request.getRemoteAddr());
	%>
</body>
</html>
