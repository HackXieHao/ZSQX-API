<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>掌上青协</title>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript">
        /* const serverPath = 'http://localhost:8787/';
         const access_token = '';
         */
        var user={
            userId:null,
            studentId:null,
            name:null
        }
        //模拟Get请求
        function ajaxRequestGet(uri) {
            /* var server = serverPath;
            var url = server + uri; */
            var url = uri;
            $.ajax({
                type: 'GET',
                contentType: 'application/json',
                dataType: 'json',
                url: url,
                success: function (response) {
                    console.log(response);
                },
                error: function () {
                    console.log('Ajax请求失败！');
                }
            });
        }

        //模拟Post请求
        function ajaxRequestPost(uri, method, data) {
            /* 	var server = serverPath;
                var url = server + uri; */
            var url = uri;
            $.ajax({
                type: method,
                contentType: 'application/json;charset=UTF-8',//此句话需要配合 json序列化方法使用
                dataType: 'json',
                data: JSON.stringify(data),
                url: url,
                success: function (response) {
                    console.log(response);
                },
                error: function (response) {
                    console.log('Ajax error');
                    console.log(response);
                }
            });
        }
        function ajaxWithoutJson(uri,method,header) {
            $.ajax({
                type: method,
                contentType: 'application/json;charset=UTF-8',//此句话需要配合 json序列化方法使用
                dataType: 'json',
                data: JSON.stringify(header),
                url: uri,
                beforeSend: function (request) {
                    request.setRequestHeader("studentId",header.studentId);
                    request.setRequestHeader("password", header.password)
                },
                success: function (response) {
                    var result=response['data'];
                    if(result&&result['user']){
                        var obj=result['user'];
                        user.name=obj['name'];
                        user.userId=obj['id'];
                        user.studentId=obj['studentId'];
                        console.log(user);
                    }
                    console.log(response);
                },
                error: function (response) {
                    console.log('Ajax error');
                    console.log(response);
                }
            });
        }
        $(function () {
            $("#getAll").click(function () {
                ajaxRequestGet("user/getAll");
            });

            $("#getAllActivities").click(function () {
                ajaxRequestGet("activity/getAllActivities");
            });

            $("#releaseActivity").click(function () {
                var data = {
                    'name': '敬老院活动',
                    'managerId': 1,
                    'hourPerTime': 4,
                    'regTime': '2018-03-03 14:00:00',
                    'regEndTime': '2018-03-04 17:00:00',
                    'interviewTime': '2018-03-05 10:30:00',
                    'startTime': '2018-03-12 10:00:00',
                    'endTime': '2018-03-12 14:00:00',
                    //'createTime': new Date(),
                    'general': '东院敬老院活动，打扫卫生',
                    'needVolunteers': 10,
                    'place': '东院敬老院',
                    'type': 2
                }
                ajaxRequestPost('activity/releaseActivity', 'PUT', data);
            });
            $("#login").click(function () {
                var data={
                    'studentId':$("input[name='identification']").val(),
                    'password':md5($("input[name='password']").val())
                }
                console.log(data.studentId);
                console.log(data.password);
                ajaxWithoutJson("/user/login",'POST',data);
            });
            $("#getResume").click(function () {
                ajaxRequestGet("user/"+$("input[name='identification']").val()+"/resume");
            });
        })
    </script>
</head>
<body>
<h4>User获取接口测试</h4>
<input type="button" value="GetAll" id="getAll"/>

<h4>Activity获取接口测试</h4>
<input type="button" value="GetAllActivities" id="getAllActivities"/>

<h4>Activity发布活动接口测试</h4>
<input type="button" value="ReleaseActivity" id="releaseActivity"/>
<h4>登陆接口测试</h4>
<form action="/user/login" method="post">
    <p>identification: <input type="text" name="identification" class="text-input"/></p>
    <p>password: <input type="text" name="password" class="text-input"/></p>
    <%--<input type="submit" value="submit">--%>
    <input type="button" value="login" id="login"> <input type="button" value="getResume" id="getResume">
</form>
<script src="http://cdn.bootcss.com/blueimp-md5/1.1.0/js/md5.js"></script>
</body>
</html>