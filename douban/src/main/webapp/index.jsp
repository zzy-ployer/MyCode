<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查询</title>
</head>
<body>
	<form
		action="${pageContext.servletContext.contextPath }/user/findOneUser.action"
		method="post">
		输入id:<input type="text" name="id"> <input type="submit"
			value="提交" /> <input type="reset" value="重置" />
	</form>
</body>
</html>