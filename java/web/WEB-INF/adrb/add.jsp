<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>addressBook</title>
	<link rel="stylesheet" type="text/css" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/layout.css">
	<link rel="stylesheet" type="text/css" href="css/color.css">
	<link rel="stylesheet" type="text/css" href="css/animate.css">
</head>
<body>
	<div id="left">
		<p id="title">addressBook</p>
		<p id="desc">在人群中找到你的联系方式<a href="/index">（点此查看通讯录）</a></p>
	</div>
	<div id="right">
		<form action="/user/add" name="info" method="post">
		<input type="text" name="name" placeholder="姓名" class="words" >
		<input type="text" name="address" placeholder="家庭住址" class="words"  >
		<input type="text" name="tel" placeholder="电话" class="words" >
		<input type="text" name="wechatNo" placeholder="微信" class="words"  >
		<input type="text" name="email" placeholder="邮箱" class="words">
		<input type="text" name="qq" placeholder="qq" class="words"  >
		<input type="text" name="intro" placeholder="个性留言" class="words">
		<input type="submit" name="submit" value="添加" class="click"  >
		<input type="reset" name="reset" value="重置" class="click" >
		</form>
	</div>
	<script type="text/javascript" src="js/animasub.js"></script>
</body>
</html>