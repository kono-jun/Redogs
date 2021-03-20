<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ include file="mainHeader.jsp" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Re:dogs マイページ</title>
<link rel="icon" type="image/x-icon" href="img/favicon.JPG">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
	<h1>Re:dogs</h1>
	<h2>最近の投稿</h2>
	<div class="submission">
			<form action="Submission" method="post">
				<c:forEach var="item" items="${submissionData}" varStatus="sts">
					<div>
				    	<c:out value="${item.nickname}さん" />
				    </div>
					<div>
				        <span class="title"><c:out value="${item.title}" /></span>
				        <span class="createDate">投稿日&nbsp;<c:out value="${item.createDate}" /></span>
				    </div>
				    <div>
				    	<c:out value="${item.article}" />
				    </div>
				    <div>
				    	<img src="C:/Users/kohno/git/redogs/redogs/Redog/WebContent/img/luckychan.JPG" alt="dogImage">
				    </div>
				    <br>
				    <br>
				</c:forEach>
			</form>
	</div>
</body>
</html>