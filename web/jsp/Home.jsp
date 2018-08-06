<%--
  Created by IntelliJ IDEA.
  User: SYSTEM
  Date: 03-Aug-18
  Time: 12:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <title>Trang chủ</title>
</head>
<body>
<form>
    <div style="background: #E0E0E0; height: 65px; padding: 5px;">
        <div style="float: right ; padding: 30px ">
            <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a> &nbsp;
            <span style="color:blue">[ ${loginUser.name} ]</span>
        </div>
        <div style="float: left">
            <h1>Quản lí user</h1>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4" >
                <h2 style="color: green "> Quản lí sản phẩm </h2>
                <a href="${pageContext.request.contextPath}/UserServlet">Quản lí sản phẩm </a> <br><br>
                <a href="${pageContext.request.contextPath}/TypeServlet">Quản lí thể loại </a>
            </div>
        </div>
    </div>
</form>
</body>
</html>
