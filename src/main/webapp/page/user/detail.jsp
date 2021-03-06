<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>用户资料</title>
	<meta content="text/html; charset=utf-8" http-equiv="content-type"/>
	<link type="text/css"
	href="${pageContext.request.contextPath}/css/authority/authority.css"
	rel="stylesheet" />
</head>
<body>
	<div class="frame" >
		<div class="frame-main">
			<div class="frame-main-inner" id="content">
			<div>
		 		 <form:form action="/user/detail" method="post" commandName="userDetailForm">
			        <input name="_method" type="hidden" value="post"/>
				    <table class="view" width="100%" border="0" cellspacing="0">
				      <tr>
				        <td class="txt_right" style="width: 255px;">真实姓名：</td>
				        <td>
				            <form:input path="realName" class="input_txt" value="${userDetailForm.realName}"/>
				            <form:errors path="realName" cssStyle="color: red;" />
				        </td>
				      </tr>
				      <tr>
				        <td class="txt_right" style="width: 255px;">电子邮箱：</td>
				        <td>
				            <form:input path="email" class="input_txt" value="${userDetailForm.email}"/>
				            <form:errors path="email" cssStyle="color: red;" />
						</td>
				      </tr>
				      <tr>
				        <td class="txt_right" style="width: 255px;">座机电话：</td>
				        <td >
				            <form:input path="phone" class="input_txt" value="${userDetailForm.phone}"/>
				            <form:errors path="phone" cssStyle="color: red;" />
						</td>
				      </tr>
				      <tr>
				        <td class="txt_right" style="width: 255px;">手机：</td>
				        <td >
				            <form:input path="mobile" class="input_txt" value="${userDetailForm.mobile}"/>
				            <form:errors path="mobile" cssStyle="color: red;" />
						</td>
				      </tr>				      
				      <tr>
				        <td>&nbsp;</td>
				        <td>
				        <button class="save" name="save_but" id="save_but" type="submit">保		存</button>
				        <button class="cancel" name="cancel" type="reset">重		置</button>
				        </td>
				      </tr>
				    </table>
			    </form:form>
			</div>
 		</div>
 		</div>
 	</div>
</body>
</html>