<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>addressBook</title>
    <link rel="stylesheet" type="text/css" href="css/color.css" >
    <link rel="stylesheet" type="text/css" href="css/layout.css" >
</head>
<body>
    <form action="/user/sendEmail" method="post" name="email">
        <textarea name="content"></textarea>
        <input type="submit" value="发送" name="send" class="send">
        <input type="reset" value="重置" name="reset" class="send">
    </form>
</body>
</html>