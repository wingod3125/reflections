<%@page import="util.PageUtil"%>
<%@page import="entity.Student"%>
<%@page import="java.sql.*,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学员列表</title>
<link rel="stylesheet" type="text/css" href="css/NewFile.css">
</head>
<body>
	<h2>系统后台页面</h2>
	<p>
		学生列表&nbsp&nbsp&nbsp<a href="RegisterSer">添加学生</a>
	</p>
	<form action="StudentSer" method="post">
		<p>
			选择性别：<input type="radio" name="sex" value="男" />男 <input
				type="radio" name="sex" value="女" />女 <input type="submit"
				value="查询">
		</p>
	</form>

	<table>
		<tr>
			<th>学生编号</th>
			<th>姓名</th>
			<th>性别</th>
			<th>电话</th>
			<th>生日</th>
			<th>地址</th>
			<th>邮箱</th>
			<th>年级</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${pu.list }" var="stu">
			<tr>
				<td>${stu.studentNo }</td>
				<td>${stu.studentName }</td>
				<td>${stu.sex }</td>
				<td>${stu.telephone }</td>
				<td>${stu.birthday }</td>
				<td>${stu.address }</td>
				<td>${stu.email }</td>
				<td>${stu.grade.gname }</td>
				<td><a href='StudentSer?tag=edit&id=${stu.studentNo }'>编辑</a> <a
					href='StudentSer?tag=del&id=${stu.studentNo }'
					onclick='return confirm(\"你确定要删除${stu.studentName}吗？\")'>删除</a></td>
			</tr>
		</c:forEach>
	</table>
	<p>
		总记录数：${pu.pageNumber }条，共${pu.pageCount }页，当前第${pu.pageIndex }页 <a
			href="StudentSer">首页</a>&nbsp&nbsp
		<c:choose>
			<c:when test="${pu.pageIndex > 1 }">
				<a href="StudentSer?pageIndex=${pu.pageIndex-1 }">上一页</a>&nbsp&nbsp
		  </c:when>
			<c:otherwise>
				<a href="StudentSer?pageIndex=1">上一页</a>&nbsp&nbsp
		  </c:otherwise>
		</c:choose>
		<c:forEach begin="1" end="${pu.pageCount }" var="i">
			<a href="StudentSer?pageIndex=${i }">${i }</a>&nbsp&nbsp
		</c:forEach>
		<c:choose>
			<c:when test="${pu.pageIndex < pu.pageCount }">
				<a href="StudentSer?pageIndex=${pu.pageIndex+1 }">下一页</a>&nbsp&nbsp
          </c:when>
			<c:otherwise>
				<a href="StudentSer?pageIndex=${pu.pageCount }">下一页</a>&nbsp&nbsp
          </c:otherwise>
		</c:choose>
		<a href="StudentSer?pageIndex=${pu.pageCount }">尾页</a>
	</p>
</body>
</html>