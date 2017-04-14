<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>省级级联</title>
<script src="http://libs.baidu.com/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript">
	//当省份选择发生变化时触发函数
	function selectChange() {
		//从服务端返回来的数据是JSON类型
		$.getJSON("ProvinceSer", {
			gid : $("#sheng").val()
		}, function(data) {
			//清除市的下拉框
			document.getElementById("city").options.length = 0;
			for ( var i = 0; i < data.city.length; i++) {

				$("#city").append(
						"<option value=\""+data.city[i].id+"\">"
								+ data.city[i].name + "</option>");
			}
		});
	}
</script>
</head>
<body>
	<h3>级联操作</h3>
	省：
	<select name="sheng" onchange="selectChange()" id="sheng">
		<option value="1">广东</option>
		<option value="2">广西</option>
		<option value="3">湖南</option>
		<option value="4">湖北</option>
		<option value="5">北京</option>
	</select> 市:
	<select name="city" id="city">
	</select>
</body>
</html>