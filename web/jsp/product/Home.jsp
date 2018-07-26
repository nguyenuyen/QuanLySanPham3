<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <title>Quản lí sản phẩm</title>
</head>
<body>
<form method="get" action="/AddProductServlet">
    <div style="background: #E0E0E0; height: 65px; padding: 5px;">
        <div style="float: left">
            <h1>Quản lí sản phẩm</h1>
        </div>
    </div>
    <div class="container">
        <h2>Danh sách các sản phẩm:</h2>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>name</th>
                <th>price</th>
                <th>type</th>
                <th>sửa</th>
                <th>xóa</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${isError == '1'}">
                <script>
                    alert("Ban da sua thanh cong");
                </script>
            </c:if>

            <c:if test="${isErrorDelete == '1'}">
                <script>
                    alert("Ban da xoa thanh cong");
                </script>
            </c:if>

            <c:forEach items="${listProduct}" var="product">
                <tr>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.type}</td>
                    <td class="center"><i class="fa fa-trash-o  fa-fw"></i><a
                            href="${pageContext.request.contextPath}/EditProductServlet?id=${product.id}">Sửa</a></td>
                    <td class="center"><i class="fa fa-pencil fa-fw"></i> <a
                            href="${pageContext.request.contextPath}/DeleteProductServlet?id=${product.id}">Xóa</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <input type="submit" class="btn btn-primary" value="Add Product"></input>
    </div>
</form>
</body>
</html>