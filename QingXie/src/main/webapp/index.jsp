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
	function ajaxRequestPost(uri,method,data) {
		/* 	var server = serverPath;
			var url = server + uri; */
		var url = uri;
		$.ajax({
			type : method,
			contentType : 'application/json;charset=UTF-8',//此句话需要配合 json序列化方法使用
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
		$("#getMyVolunteerService").click(function() {
			ajaxRequestGet("activity/getMyVolunteerService?page=2&size=2");
		});

		$("#getAllActivities").click(function() {
			ajaxRequestGet("activity/getAllActivities?page=1&size=2");
		});

		$("#releaseActivity").click(function() {
			var data = {
				'name':'敬老院活动',
				'managerId':1,
				'hourPerTime':4,
				'regTime':'2018-03-03 14:00:00',
				'regEndTime':'2018-03-04 17:00:00',
				'interviewTime':'2018-03-05 10:30:00',
				'startTime':'2018-03-12 10:00:00',
				'endTime':'2018-03-12 14:00:00',
				//'createTime': new Date(),
				'general':'东院敬老院活动，打扫卫生',
				'needVolunteers':10,
				'place':'东院敬老院',
				'type':2,
				'status':1
			}
			ajaxRequestPost('activity/releaseActivity','PUT',data);
		});

	})
</script>
</head>
<body>
	<h4>我的志愿服务获取接口测试</h4>
	<input type="button" value="GetMyVolunteerService" id="getMyVolunteerService" />

	<h4>Activity获取接口测试</h4>
	<input type="button" value="GetAllActivities" id="getAllActivities" />

	<h4>Activity发布活动接口测试</h4>
	<input type="button" value="ReleaseActivity" id="releaseActivity" />
</body>
</html>