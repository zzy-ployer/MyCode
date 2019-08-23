<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>成功</title>
<style type="text/css">
.font-g {
	color: green;
}

.font-r {
	color: red;
}
</style>
</head>
<body>
	<span class=".font-g">登陆成功，5秒后跳转</span>
	<%
		response.setHeader("Refresh", "5;URL=../movie/selectByName.jsp");
	%>
</body>
</html>