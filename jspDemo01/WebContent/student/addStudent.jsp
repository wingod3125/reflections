<%@page import="entity.Grade"%>
<%@page import="java.sql.*,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加学生</title>
</head>
<body>
	<form action="RegisterSer" method="post">
		<p>
			学生姓名：&nbsp;<input type="text" name="name" />
		</p>
		<p>
			性别：&nbsp;<input type="radio" name="sex" value="男" />男<input
				type="radio" name="sex" value="女" />女
		</p>
		<p>
			电话：&nbsp;<input type="text" name="phone" />
		</p>
		<p>
			生日：&nbsp;<input type="text" name="birthday" /><span>格式：1993-04-22</span>
		</p>
		<p>
			地址：&nbsp;<input type="text" name="address" />
		</p>
		<p>
			邮箱：&nbsp;<input type="text" name="email" />
		</p>
		<p>
			年级：&nbsp;<select id="gradeList" name="grade">
				<c:forEach items="${gradeList }" var="g">
					<option value="${g.gid }">${g.gname }</option>
				</c:forEach>
			</select>
		</p>
		<p>
			<input type="submit" value="提交">
		</p>
		<script type="text/javascript">
			
		</script>
	</form>
</body>
</html>