<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加管理员账号</title>
<script src="http://libs.baidu.com/jquery/1.8.3/jquery.min.js"></script>
<!-- <script src="js/jquery-1.8.3.js"></script> -->
<script type="text/javascript">
	var xhr;
	//声明一个函数，创建XMLHttpRequest对象
	function getXmlHttpRequest() {
		if (window.XMLHttpRequest)
			return new XMLHttpRequest();
	}
	function userInput(txtName) {
		var name = txtName.value;
		$.ajax({
			type : "GET",
			url : "AdminSer",
			data : "name=" + name,
			dataType : "text",
			success : function(data) {
				if (data == "y") {
					$("#span").css("color", "#0f0").html("用户名可用");
				} else {
					$("#span").css("color", "#f00").html("用户名不可用");
				}
			}

		})
		/* xhr = getXmlHttpRequest();
		//设置回调函数
		xhr.onreadystatechange = getResult;
		//封装请求，请求的方式，请求的URL及数据，异步
		xhr.open("GET", "AdminSer?name=" + name, true);
		//发送请求
		xhr.send(null); */
	}
	/* //回调函数
	function getResult() {
		var s = document.getElementById("span");
		//判断服务器端是否成功返回数据
		if (xhr.readyState == 4 && xhr.status == 200) {
			//接收并处理服务器端返回的数据
			var result = xhr.responseText;
			if (result == "y") {
				s.style.color = "#0f0";
				s.innerHTML = "用户名可用";
			} else {
				s.style.color = "#f00";
				s.innerHTML = "用户名不可用";
			}
		}
	} */
</script>
</head>
<body>
	<h3>添加管理员账号</h3>
	<form action="" method="post">
		<p>
			用户名：<input type="text" name="name" onblur="userInput(this)"><span
				id="span"></span>
		</p>
		<p>
			密&nbsp;码：<input type="text" name="password"><span id="span"></span>
		</p>
	</form>
</body>
</html>