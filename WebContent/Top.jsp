<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>匿名掲示板</title>
<link rel="stylesheet" href="css/styles.css" type="text/css" />
<style>
.footer_left a {
	position: absolute;
	bottom: 40px;
	left: 20px;
	padding: 5px 30px; display : block;
	border: 1px solid #000;
	line-height: 40px;
	font-size: 30px;
	text-decoration: none;
	display: block;
}
</style>
</head>
<body>
	<!-- ヘッダー -->
	<div class="header">匿名掲示板</div>
	<!-- ヘッダー -->
	<div class="main">
		<div class="search">
			<form action="top" method="get">
				<input type="text" name="searchWord" placeholder="検索">
				<button class="button6" type="submit">検索</button>
			</form>
		</div>
		<div class="main_content">
			<c:forEach items="${messages}" var="message">
				<div class="content">
					投稿内容：
					<c:out value="${message.text}" />
					<c:choose>
						<c:when test="${not empty loginUser}">
							<form action="deleteMessage" method="get">
								<input type="hidden" name="messageId" value="${message.id}">
								<button class="button5" type="submit">削除</button>
							</form>
							<a href="./editMessage/${message.id}">編集</a>
						</c:when>
					</c:choose>
				</div>
			</c:forEach>
		</div>
	</div>
	<!-- フッター -->
	<div class="footer">
		<div class="footer_left">
			<a href="./message">投稿</a>
		</div>
		<div class="footer_right">
			<c:choose>
				<c:when test="${not empty loginUser}">
					<a href="./logout">ログアウト</a>
				</c:when>
				<c:otherwise>
					<a href="./login">ログイン</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<!-- フッター -->
</body>
</html>
