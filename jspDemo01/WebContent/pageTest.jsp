<%@page import="java.util.List"%>
<%@page import="util.PageUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分页功能</title>
</head>
<body>
	<table>
		<%
		    PageUtil pu = (PageUtil) request.getAttribute("pu");
		    List list = pu.getList();
		    for (int i = 0; i < list.size(); i++) {
		        List line = (List) list.get(i);
		        out.print("<tr>");
		        for (Object s : line) {
		            out.print("<td>" + s + "</td>");
		        }
		        out.print("</tr>");
		    }
		%>
		<p>
			总记录数：<%=pu.getPageNumber()%>条，共<%=pu.getPageCount()%>页，当前第<%=pu.getPageIndex()%>页
			<a href="PageSer">首页</a>&nbsp&nbsp<a
				href="PageSer?pageIndex=<%=pu.getPageIndex() - 1%>">上一页</a>&nbsp&nbsp
			<%
		    for (int i = 1; i <= pu.getPageCount(); i++) {
		        out.print("<a href=\"PageSer?pageIndex=" + i + "\">" + i
		                + "</a>&nbsp&nbsp");
		    }
		%>
			<a
				href="PageSer?pageIndex=<%=pu.getPageIndex() < pu.getPageCount() ? pu.getPageIndex() + 1
                    : pu.getPageCount()%>">下一页</a>&nbsp&nbsp
			<a href="PageSer?pageIndex=<%=pu.getPageCount()%>">尾页</a>
		</p>

	</table>
</body>
</html>