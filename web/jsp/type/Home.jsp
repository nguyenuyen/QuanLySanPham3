<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function confirmDelete(){
            var doIt=confirm('Do you want to delete the record?');
            if(doIt){
                return true;
            }
            else{
                return false;
            }
        }
    </script>

    <title>Quản lí thể loại</title>
</head>
<body>
<form method="get" action="/AddProductServlet">
    <div style="background: #E0E0E0; height: 65px; padding: 5px;">
        <div style="float: right; padding: 30px; text-align: right;">
            <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a> &nbsp;
            <span style="color:blue">[ ${loginUser.getEmail()} ]</span>
        </div>
        <div style="float: left">
            <h1>Quản lí thể loại</h1>
        </div>
    </div>
    <div class="container">
        <h2>Danh sách các thể loại:</h2>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>name</th>
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
                    <td class="center"><i class="fa fa-trash-o  fa-fw"></i><a
                            href="${pageContext.request.contextPath}/EditProductServlet?id=${product.id}" >Sửa</a></td>
                    <td class="center"><i class="fa fa-pencil fa-fw"></i> <a
                            href="${pageContext.request.contextPath}/DeleteProductServlet?id=${product.id}"onclick="return confirmDelete()">Xóa</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <input type="submit" class="btn btn-primary" value="Add Type"></input> <br> <br>
        <table border="0" cellpadding="0" cellspacing="0">
            <td>
                <%--For displaying Previous link except for the 1st page --%>
                <c:if test="${currentPage != 1}">
                    <!--  <td> --><a href="/UserServlet?page=${currentPage - 1}">&nbsp;Previous</a><!-- </td> -->
                </c:if>
                <%--For displaying Page numbers.
                The when condition does not display a link for the current page--%>
                <!-- <table border="1" cellpadding="5" cellspacing="5"> -->
                <!--  <tr> -->
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <!-- <td> -->${i}&nbsp;&nbsp;<!-- </td> -->
                        </c:when>
                        <c:otherwise>
                            <!--  <td> --><a href="/UserServlet?page=${i}">${i}&nbsp;</a><!-- </td> -->
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <!--  </tr> -->

                <%--For displaying Next link --%>
                <c:if test="${currentPage lt noOfPages}">
                    <!--  <td> --><a href="/UserServlet?page=${currentPage + 1}">&nbsp;Next</a><!-- </td> -->
                </c:if>
            </td>
        </table>
    </div>
</form>
</body>
</html>