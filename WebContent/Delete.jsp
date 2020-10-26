<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>投稿削除</title>
<link rel="stylesheet" href="css/styles.css" type="text/css" />
<style>
.text_1 a {
	margin: 0 200px 0 0;
	float: right;
	position: absolute;
	bottom: 200px;
	right: 200px;
}
</style>
</head>
<body>
	<!-- ヘッダー -->
	<div class="header">投稿削除</div>
	<!-- ヘッダー -->
	<div class="Delete_Comments">
		<h3>この投稿を削除しますか？</h3>
	</div>
	<div class="text_1">
		<form action="deleteMessage" method="post">
			<input type="hidden" name="messageId" value="${messageId}">
			<button class="button1" type="submit" style="margin-left: 680px;">削除</button>
			<p class="msr_btn15">
				<a href="./top">戻る</a>
			</p>
		</form>
	</div>
</body>
</html>