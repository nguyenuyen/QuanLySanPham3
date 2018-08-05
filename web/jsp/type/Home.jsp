<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <%--
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    --%>
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

    <title>Quản lí thể loại</title>
</head>
<body>
<form method="get" action="/AddProductServlet">
    <div style="background: #E0E0E0; height: 65px; padding: 5px;">
        <div style="float: right; padding: 30px; text-align: right;">
            <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a> &nbsp;
            <span style="color:blue">[ ${loginUser.name} ]</span>
        </div>
        <div style="float: left">
            <h1>Quản lí thể loại</h1>
        </div>
    </div>
    <div class="container">
        <h2>Danh sách các thể loại:</h2>
        <table   id="example" class="table table-striped table-bordered" style="width:100%" cellpadding="0" cellspacing="0" border="0">
            <thead>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>Edit</th>
                <th>Delete</th>
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

            <c:forEach items="${listType}" var="list">
                <tr>
                    <td>${list.id}</td>
                    <td>${list.name}</td>
                    <td class="center"><i class="fa fa-trash-o  fa-fw"></i><a
                            href="${pageContext.request.contextPath}/EditTypeServlet?id=${list.id}">Edit</a></td>
                    <td class="center"><i class="fa fa-pencil fa-fw"></i> <a
                            href="${pageContext.request.contextPath}/DeleteTypeServlet?id=${list.id} "
                            onclick="return confirmDelete()">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <input type="button" class="btn btn-primary" value="Add Type"
               onclick='window.location="${pageContext.request.contextPath}/AddTypeServlet"'> </input> <br> <br>
    </div>
</form>
</body>
</html>