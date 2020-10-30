<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>投稿</title>
<link rel="stylesheet" href="css/styles.css" type="text/css" />
</head>
<body>
	<!-- ヘッダー -->
	<div class="header">投稿</div>
	<!-- ヘッダー -->
	<div class="text_2">
		<form action="message" method="post">
			<c:if test="${ not empty errorMessages }">
				<div class="errorMessages" style="color: red;">
					<ul class="error-list-wrap">
						<c:forEach items="${errorMessages}" var="errorMessage">
							<li class="error-list"><c:out value="${errorMessage}"/>
						</c:forEach>
					</ul>
				</div>
			</c:if>
			<textarea name="message"><c:out value="${message.text}" /></textarea>
			<button class="button2" type="submit">投稿</button>
			<p class="msr_btn15">
				<a href="./top">戻る</a>
			</p>
		</form>
	</div>
</body>
</html>