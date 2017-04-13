<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑学员信息</title>
</head>
<body>
	<h3>编辑学员信息</h3>
	<form action="StudentSer?tag=edit" method="post">
		<fieldset>
			<legend>学员基本资料</legend>
			<input type="hidden" value="${student.studentNo}" name="studentNo">
			<p>学生姓名：&nbsp;<input type="text" name="name" value="${student.studentName}" /></p>
			<p>电话：&nbsp;<input type="text" name="phone" value="${student.telephone}" /></p>
			<p>性别：&nbsp;
				<c:choose>
					<c:when test="${student.sex.equals(\"男\") }">
						<input type="radio" name="sex" value="男" checked="checked" />男
                        <input type="radio" name="sex" value="女" />女
				    </c:when>
					<c:otherwise>
						<input type="radio" name="sex" value="男" />男
                        <input type="radio" name="sex" value="女" checked="checked" />女
				    </c:otherwise>
				</c:choose>
			</p>
			<p>生日：&nbsp;<input type="text" name="birthday" value="${student.birthday}" /></p>
			<p>地址：&nbsp;<input type="text" name="address" value="${student.address}" /></p>
			<p>邮箱：&nbsp;<input type="text" name="email" value="${student.email}" /></p>
			<p>年级：
			    <select name="grade">
					<c:forEach items="${gradeList }" var="g">
						<c:choose>
							<c:when test="${g.gid == student.grade.gid }">
								<option value="${g.gid }" selected="selected">${g.gname}</option>
							</c:when>
							<c:otherwise>
								<option value="${g.gid }">${g.gname }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</p>
			<p><input type="submit" value="保存"></p>
		</fieldset>
	</form>
</body>
</html>