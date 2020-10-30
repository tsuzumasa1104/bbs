<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="css/styles.css" type="text/css" />
</head>
<body>
	<div class="login">
		<form action="login" method="post">
			<div class="msr_text_02">
				<input type="text" name="name" placeholder="ユーザー名"><br>
			</div>
			<div class="msr_text_02">
				<input type="password" name="password" placeholder="パスワード"><br>
			</div>
			<button class="button4" type="submit" value="ログイン">ログイン</button>
		</form>
	</div>
</body>
</html>