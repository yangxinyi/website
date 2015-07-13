<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>${message}</title>
	<meta content="text/html; charset=utf-8" http-equiv="content-type"/>
	<link type="text/css"
	href="${pageContext.request.contextPath}/css/authority/authority.css"
	rel="stylesheet" />
</head>
<body>
	<div class="frame" >
		<!-- 引入top部分 -->
		<!-- 引入left菜单列表部分 -->
		<div class="frame-main">
			<div class="frame-main-inner" id="content">
			<div>
				<p class="border_style changed_suc margin10"><span class="greenhook">&nbsp;</span><b>${message}</b><br><br></p>
			</div>
 		</div>
 		</div>
 	</div>
</body>
</html>