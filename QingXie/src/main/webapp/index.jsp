<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>掌上青协</title>
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	/* const serverPath = 'http://localhost:8787/';
	 const access_token = '';
	 */
	//模拟Get请求
	function ajaxRequestGet(uri) {
		/* var server = serverPath;
		var url = server + uri; */
		var url = uri;
		$.ajax({
			type : 'GET',
			contentType : 'application/json',
			dataType : 'json',
			url : url,
			success : function(response) {
				console.log(response);
			},
			error : function() {
				console.log('Ajax请求失败！');
			}
		});
	}

	//模拟Post请求
	function ajaxRequestPost(uri, data) {
	/* 	var server = serverPath;
		var url = server + uri; */
		var url = uri;
		$.ajax({
			type : 'POST',
			contentType: 'application/json;charset=UTF-8',//此句话需要配合 json序列化方法使用
			dataType : 'json',
			data : JSON.stringify(data),
			url : url,
			success : function(response) {
				console.log(response);
			},
			error : function(response) {
				console.log('Ajax error');
				console.log(response);
			}
		});
	}

	$(function() {
		$("#getAll").click(function() {
			ajaxRequestGet("user/getAll");
		});

	})
</script>
</head>
<body>
	<h4>User测试接口</h4>
	<input type="button" value="GetAll" id="getAll" />

</body>
</html>