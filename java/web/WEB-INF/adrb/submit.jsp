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
		<p id="title">Classmate_Book</p>
		<p id="desc">在人群中找到你的联系方式<a href="/index">（点此查看通讯录）</a></p>
	</div>
	<div id="right">
        <% User user= (User) request.getAttribute("user");
            if (user!=null&&user.getUrl() == null) {
        %>
        <form action="/user/avator" enctype="multipart/form-data" method="post">
              <span style="display: block;margin-left: auto;margin-right: auto">
                <span>上传头像</span>
              <input type="file" name="files" />
                </span>
            <input type="text" name="userId" value="${user.userId}" hidden/>
            <input type="submit" value="添加头像" class="click"  >
        </form>
        <%
            }else {
        %>
            <img src="${user.url}" width="100px" height=100px" style="margin-left: auto;margin-right: auto;display: block" class="animated fadeInRight">
        <%
            }
        %>
      
		<form action="/user/update" name="info">
            <input type="text" name="userId" value="${user.userId}" hidden>
            <input type="text" name="name" placeholder="姓名" class="words" value="${user.userName}">
            <input type="text" name="address" placeholder="家庭住址" class="words"  value="${user.address}">
            <input type="text" name="tel" placeholder="电话" class="words"  value="${user.phone}">
            <input type="text" name="wechatNo" placeholder="微信" class="words"  value="${user.weixin}">
            <input type="text" name="email" placeholder="邮箱" class="words"  value="${user.email}">
            <input type="text" name="qq" placeholder="qq" class="words"  value="${user.qq}">
            <input type="text" name="intro" placeholder="个性留言" class="words"  value="${user.note}">
            <input type="submit" name="change" value="修改" class="click"  >
		</form>
        <form action="/user/delete" name="info1" method="post">
            <input type="text" name="userId"  value="${user.userId}" hidden>
            <input type="submit" class="click" value="删除">
        </form>
	</div>
	<script type="text/javascript" src="js/animasub.js"></script>
</body>
</html>