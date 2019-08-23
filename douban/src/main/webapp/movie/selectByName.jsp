<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>搜索</title>
<script
	src="${pageContext.servletContext.contextPath }/js/jquery-3.4.1.min.js"></script>
<style type="text/css">
.movie_li {
	float: left;
	list-style: none;
	width: 210px;
	height: 356px;
	margin-left: 10px;
	margin-top: 10px;
	border: 1px solid gray;
	font-size: 13px;
	text-align: center;
}


</style>
</head>
<body>

	<form
		action="${pageContext.servletContext.contextPath }/movie/findMovieByName.action"
		method="post">
		请输入电影名：<input type="text" name="movieName"> <input
			type="submit" value="搜索">
	</form>

	<ul>
		<c:forEach items="${movies }" var="movie">
			<a
				href="${pageContext.servletContext.contextPath }/movie/movieDetail.action?id=${movie.id }">
				<li class="movie_li"><img
					style="width: 200px; height: 296px; margin: 5px;"
					onerror="javascript:this.src='${pageContext.servletContext.contextPath }/img/01.jpg'"
					src="${movie.imgurl}"> <span style="color: blue;"
					class="span">${movie.name }</span><br> <span
					style="color: black">评分：${movie.rate }</span></li>
			</a>
		</c:forEach>
	</ul>
</body>
</html>