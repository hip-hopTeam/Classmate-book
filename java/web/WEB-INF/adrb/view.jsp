<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Classmate_Book</title>
    <link rel="stylesheet" type="text/css" href="/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/css/layout.css">
    <link rel="stylesheet" type="text/css" href="/css/color.css">
    <link rel="stylesheet" type="text/css" href="/css/animate.css">
</head>
<body>
    <p id="add"><a href="/add">点此添加联系方式</a></p>
    <p id="sendEmail"><a id="send" href="/toemail">点此此处发送邮件</a></p>
    <div id="list">
        <p id="title">通讯录名单<a id="download" href="/user/excel">(点此下载通讯录)</a> </p>
        <% if (request.getAttribute("error") != null) {
            System.out.println("error");
        %>
        <script type="text/javascript">
            alert("${error}");
        </script>
        <%
        }
        %>
        <div>
            <ul id="list-name">
                <%
                    List<User> users= (List<User>) request.getAttribute("users");
                    if (users==null) System.out.println("null");
                if (users!=null)
                    for (int i=0;i<users.size();i++) {
                %>
                    <li ><a href="/submit?userId=<%=users.get(i).getUserId()%>"><%=users.get(i).getUserName()%></a></li>
                <%
                    }
                %>
            </ul>
        </div>
    </div>
    <script type="text/javascript" src="js/animaview.js"></script>
</body>
</html>