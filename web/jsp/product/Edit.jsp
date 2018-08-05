<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>

        $(document).ready(function() {
            var text = document.getElementById('price');
            text.onkeypress = text.onpaste = checkPhone;
            function checkPhone(e) {
                var e = e || event;
                var char = e.type == 'keypress'
                    ? String.fromCharCode(e.keyCode || e.which)
                    : (e.clipboardData || window.clipboardData).getData('Text');
                if (/[^\d]/gi.test(char)) {
                    return false;
                }
            }
        });

    </script>
    <title>Sửa Sản Phẩm</title>
</head>
<body>
<form method="post" action="/EditProductServlet" >
    <div style="background: #E0E0E0; height: 65px; padding: 5px;">
        <div style="float: right;padding: 30px;">
            <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a> &nbsp;
            <span style="color:blue">[ ${loginUser.name} ]</span>
        </div>
        <div style="float: left">
            <h1>Quản lí sản phẩm </h1>
        </div>
    </div>
    <div class="container"> 
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <h1>Sửa Sản Phẩm</h1>
                <div class="form-group">
                    <label>Tên sản phẩm  :</label>
                    <input type="text" class="form-control" name="name" required value="${product.name}">
                </div>
                <div class="form-group">
                    <label>Thể loại :</label>
                    <select class="form-control" id="sel1" name="type">
                        <c:forEach items="${listType}" var="list">
                            <option value="${list.name}">${list.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label >Giá</label>
                    <input type="text" class="form-control" name="price" value="${product.price}" id="price" required>
                </div>
                <div>
                    <button type="submit" class="btn btn-default" >Sửa</button>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>