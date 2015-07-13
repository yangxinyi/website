<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
<div id="login-page">
	  	<div class="container">
	  		<p>webcome back!${requestScope["com.map.model.user.User"].name}</p>
	  		<p><a href="/logout">退出</a></p>
	</div>
</div>
</body> 
</html>