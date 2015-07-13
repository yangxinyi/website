<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
<div id="login-page">
	  	<div class="container">
	  		<p>${errorMessage }</p>
			<form class="form-login" action="/login" method="post">
			       <h2 class="form-login-heading">自测系统</h2>
			       <div class="login-wrap">
			           <input type="text" name="name" class="form-control" placeholder="用户名" autofocus>
			           <br>
			           <input type="password" name="password"  class="form-control" placeholder="密码">
			           <button class="btn btn-theme btn-block" href="index.html" type="submit"><i class="fa fa-lock"></i> 登陆</button>
			       </div>
			</form>
	</div>
</div>
</body> 
</html>