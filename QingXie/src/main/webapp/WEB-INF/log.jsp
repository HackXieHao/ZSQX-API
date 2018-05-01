<%--
  Created by IntelliJ IDEA.
  User: evans
  Date: 2018/3/27
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>tail log</title>
    <script src="https://cdn.bootcss.com/jquery/2.1.4/jquery.js"></script>
    <style>
        html,body
        {
            height:100%;
            width:100%;
        }
    </style>
</head>
<body>
<div id="log-container" style="height: 450px; overflow-y: scroll; background: #333; color: #aaa; padding: 10px;">
    <div>
    </div>
</div>
</body>
<script>
    $(document).ready(function() {
        // 指定websocket路径
        var websocket = new WebSocket('ws://123.207.87.34:8080/log');
        websocket.onmessage = function(event) {
            // 接收服务端的实时日志并添加到HTML页面中
            $("#log-container div").append(event.data);
            // 滚动条滚动到最低部
            $("#log-container").scrollTop($("#log-container div").height() - $("#log-container").height());
        };
    });
</script>
</body>
</html>
