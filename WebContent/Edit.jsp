<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>投稿編集</title>
<link rel="stylesheet" href="css/styles.css" type="text/css" />
<style>
.body {
	margin: 0 auto 0 auto;
}

.header {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100px;
	padding: 10px;
	background-color: #D9E5FF;
	color: #000000;
	display: flex;
	align-items: center;
	justify-content: center;
	font-family: "ヒラギノ丸ゴ Pro W4", "ヒラギノ丸ゴ Pro", "Hiragino Maru Gothic Pro",
		"ヒラギノ角ゴ Pro W3", "Hiragino Kaku Gothic Pro", "HG丸ｺﾞｼｯｸM-PRO",
		"HGMaruGothicMPRO";
	font-size: 40px;
}

.footer {
	position: fixed;
	bottom: 0;
	left: 0;
	width: 100%;
	height: 100px;
	padding: 10px;
	background-color: #D9E5FF;
	color: #000000;
	display: flex;
	font-size: 20px;
}

button.button2 {
	font-size: 1.4em;
	font-weight: bold;
	padding: 10px 50px;
	background-color: #006400;
}

.msr_btn15 a {
	width: 230px;
	padding: 15px;
	margin: 150px 500px;
	color: #ffffff;
	font-size: 15px;
	line-height: 120%;
	text-align: center;
	text-decoration: none;
	border-radius: 8px;
	background: #FF9C15;
	display: block;
	position: relative;
	transition: 0.3s ease-out;
}

.msr_btn15 a::before {
	content: '';
	width: 16px;
	height: 16px;
	margin: -8px 0 0 0;
	border-radius: 50%;
	background: #fff;
	position: relative;
	position: absolute;
	top: 50%;
	left: 20px;
	display: block;
}

.msr_btn15 a::after {
	content: '';
	width: 4px;
	height: 4px;
	border: 0px;
	margin: -3px 0 0 0;
	border-top: solid 2px #FF9C15;
	border-right: solid 2px #FF9C15;
	-ms-transform: rotate(45deg);
	-webkit-transform: rotate(45deg);
	transform: rotate(45deg);
	position: absolute;
	top: 50%;
	left: 24px;
	display: block;
	transition: 0.3s ease-out;
}

.msr_btn15 a:hover {
	background: #FFAF42;
}

.msr_btn15 a:hover::after {
	border-top: solid 2px #FFAF42;
	border-right: solid 2px #FFAF42;
}

.text_2 a {
	margin: 0 200px 0 0;
	float: right;
	position: absolute;
	bottom: 200px;
	right: 200px;
}

.text_2 {
	margin: 0 auto;
	margin: 150px 300px
}

textarea {
	border: 1px solid #000;
	color: #333;
	/* text-align: center; */
	font-size: 20px;
	padding: 10px;
	height: 300px;
	width: 800px;
	margin-top: 50px;
}
</style>
</head>
<body>
	<!-- ヘッダー -->
	<div class="header">投稿編集</div>
	<!-- ヘッダー -->
	<div class="text_2">
		<form action="./${message.id}" method="post">
			<c:if test="${ not empty errorMessages }">
				<div class="errorMessages">
					<ul class="error-list-wrap">
						<c:forEach items="${errorMessages}" var="errorMessage">
							<li class="error-list"><c:out value="${errorMessage}" />
						</c:forEach>
					</ul>
				</div>
			</c:if>
			<input type="hidden" name="messageId" value="${message.id }">
			<textarea name="text"><c:out value="${message.text}" /></textarea>
			<button class="button2" type="submit"
				style="font-size: 1.4em; font-weight: bold; padding: 10px 50px; background-color: #006400;">更新</button>
			<p class="msr_btn15">
				<a href="/bbs/top"
					style="position: absolute; bottom: 0px; right: 160px;">戻る</a>
			</p>
		</form>
	</div>
</body>
</html>
