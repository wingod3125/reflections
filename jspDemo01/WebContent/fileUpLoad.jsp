<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>文件上传</title>
</head>
<body>
	<h3>文件上传</h3>
	<form action="FileUpLoadSer" method="Post"
		enctype="multipart/form-data">
		<p>
			用户名：<input type="text" name="userName"><br> 
			文件名1：<input type="file" name="file1"><br> 
			文件名2：<input type="file" name="file2"><br> 
			<input type="submit" value="上传">
		</p>
	</form>
</body>
</html>