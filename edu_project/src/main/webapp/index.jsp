<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>login</title>
<link href="${ctx}/assets/style/login.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
<div class="htmleaf-container">
	<div class="wrapper">
		<div class="container">
			<h1>Welcome to book search system</h1>
			
			<form class="form"  name="loginForm" action="login.htm">
				<input type="text" placeholder="Username">
				<input type="password" placeholder="Password">
				<button type="submit" id="login-button">Login</button>
			</form>
		</div>		
		
	</div>
</div>

<script src="${ctx}/assets/plugins/jquery-3.2.1.min.js"></script>
<script>
$('#login-button').click(function (event) {
	event.preventDefault();
	$('form').fadeOut(500);
	$('.wrapper').addClass('form-success');
	submitform();
	
});
function submitform()
{
    if(document.loginForm.onsubmit &&
    !document.loginForm.onsubmit()) //判断是否正在提交，如果是则直接返回
    {
        return;
    }
 document.loginForm.submit(); //调用form的submit()方法完成表单的提交
}
</script>

<!-- <div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';color:#000000">
<h1>Welcome to book search system</h1>
</div> -->
</body>
</html>