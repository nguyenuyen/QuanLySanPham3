<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Quản lí user</title>
</head>
<body>
<form method="get" action="/AddUserServlet">
    <div style="background: #E0E0E0; height: 65px; padding: 5px;">
        <div style="float: left">
            <h1>Quản lí user</h1>
        </div>
    </div>
    <div class="container">
        <h2>Danh sách các user:</h2>

        <table class="table table-striped">
            <thead>

            <tr>

                <th>name</th>
                <th>sdt</th>
                <th>email</th>
                <th>sua</th>
                <th>xoa</th>
            </tr>
            </thead>
            <tbody>

            <c:if test="${isError == '1'}">
                <script>
                    alert("Ban da sua thanh cong");
                </script>

            </c:if>

            <c:if test="${isError == '0'}">
                <script>
                    alert("ban sua khong thanh cong ");
                </script>
            </c:if>

            <c:if test="${isErrorDelete == '1'}">
                <script>
                    alert("Ban da xoa thanh cong");
                </script>

            </c:if>

            <c:if test="${isErrorDelete == '0'}">
                <script>
                    alert("ban xoa khong thanh cong ");
                </script>
            </c:if>

            <c:forEach items="${listUser}" var="user">
                <tr>
                    <td>${user.name}</td>
                    <td>${user.phone}</td>
                    <td>${user.email} </td>
                    <td class="center"><i class="fa fa-trash-o  fa-fw"></i><a
                            href="${pageContext.request.contextPath}/EditUserServlet?id=${user.id}"> Sửa</a></td>
                    <td class="center"><i class="fa fa-pencil fa-fw"></i> <a
                            href="${pageContext.request.contextPath}/DeleteUserServlet?id=${user.id}">Xóa</a></td>
                </tr>
            </c:forEach>

            </tbody>

        </table>

        <input type="submit" name="them User" value="Thêm User" class="btn btn-primary"></input>

    </div>
</form>
</body>
</html>