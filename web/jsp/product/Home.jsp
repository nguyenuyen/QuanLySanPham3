<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">


    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">


    <script>

        $(document).ready(function () {
            $('#example').DataTable();
        });

        function confirmDelete() {
            var doIt = confirm('Do you want to delete the record?');
            if (doIt) {
                return true;
            }
            else {
                return false;
            }
        }
    </script>

    <title>Quản lí sản phẩm</title>
</head>
<body>
<form method="post" action="/SearchServlet">
    <div style="background: #E0E0E0; height: 65px; padding: 5px;">
        <div style="float: right;padding: 30px;">
            <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a> &nbsp;
            <span style="color:blue">[ ${loginUser.name} ]</span>
        </div>
        <div style="float: left">
            <h1>Quản lí sản phẩm</h1>
        </div>
    </div>

    <div class="container">

        <div class="row">
            <div class="col-lg-12">
                <h2>Danh sách các sản phẩm:</h2> <br>
                <!-- <div style="float: right">
                     <input name="search">
                     <input type="submit" class="btn btn-primary" value="Search" ></input> <br>
                 </div>
     -->
                <table id="example" class="table table-striped table-bordered" style="width:100%" cellpadding="0"
                       cellspacing="0" border="0">
                    <thead>
                    <tr>
                        <th>id</th>
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
                            <td>${product.id}</td>
                            <td>${product.name}</td>
                            <td>${product.price}</td>
                            <td>${product.type}</td>
                            <td class="center"><i class="fa fa-trash-o  fa-fw"></i><a
                                    href="${pageContext.request.contextPath}/EditProductServlet?id=${product.id}">Sửa</a>
                            </td>
                            <td class="center"><i class="fa fa-pencil fa-fw"></i> <a
                                    href="${pageContext.request.contextPath}/DeleteProductServlet?id=${product.id}"
                                    onclick="return confirmDelete()">Xóa</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <input type="button" class="btn btn-primary" value="Add Product"
                       onclick='window.location="${pageContext.request.contextPath}/AddProductServlet"'>
                </input> <br> <br>
            </div>
        </div>
    </div>
</form>
</body>
</html>