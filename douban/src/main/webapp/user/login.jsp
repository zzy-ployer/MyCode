<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登陆</title>
<style type="text/css">
.own {
	position: absolute;
	top: 20px;
	right: 500px;
	width:300px;
	border: 1px solid black;
	background: url("${pageContext.servletContext.contextPath }/img/11.jpg") no-repeat ;
	background-size:contain;
}

input {
	width: 120px;
	height: 53px;
}

input[type="submit"] {
	/* width: 100px; */
	margin: 20px 100px 0px;
}

input[type="reset"] {
	/* width: 100px; */
	margin: 20px 100px 0px;
}
.fff{
	margin: 20px 100px 0px;
}
span{
color:green;
font-size: 30px;
}
</style>
<link
	href="${pageContext.servletContext.contextPath }/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="own">
		<form
			action="${pageContext.servletContext.contextPath }/user/findByidAndPasswd.action"
			method="post" class="form-group">
			<div class="fff">
				<span>用户名:</span><input type="text" name="id" placeholder="请输入用户名"><br /><span>密&nbsp;&nbsp;&nbsp;&nbsp;码:</span><input
					type="password" name="password" placeholder="请输入用户密码">
			</div>
			<input type="submit" value="提交" class="btn btn-success" /> <input
				type="reset" value="重置" class="btn btn-warning" />
		</form>
	</div>
</body>
</html>