<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <!--  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->

    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">

    <title>Quản lí user</title>
    <script>

        $(document).ready(function () {
            $('#example').DataTable();
            $("#checkedAll").on('click', function () {
                if ($(this).is(':checked', true)) {
                    $(".checkSingle").prop('checked', true);
                }
                else {
                    $(".checkSingle").prop('checked', false);
                }
                $("#select_count").html($("input.checkSingle:checked").length + " Selected");
            });
// set particular checked checkbox count
            $(".checkSingle").on('click', function (e) {
                $("#select_count").html($("input.checkSingle:checked").length + " Selected");
            });

            $(".checkSingle").click(function () {
                if ($(this).is(":checked")) {
                    var isAllChecked = 0;
                    $(".checkSingle").each(function () {
                        if (!this.checked)
                            isAllChecked = 1;
                    });
                    if (isAllChecked == 0) {
                        $("#checkedAll").prop("checked", true);
                    }
                } else {
                    $("#checkedAll").prop("checked", false);
                }
            });

            $("#deleteAll").click(function (e) {
                var product = [];
                $(".checkSingle:checked").each(function () {
                    product.push($(this).data('value'));
                });
                if (product.length <= 0) {
                    alert("Please select records.");
                } else {
                    WRN_PROFILE_DELETE = "Are you sure you want to delete " + (product.length > 1 ? "these" : "this") + " row?";
                    var checked = confirm(WRN_PROFILE_DELETE);
                    if (checked == true) {
                        var selected_values = product.join(",");
                        $.ajax({
                            type: "post",
                            url: "${pageContext.request.contextPath}/DeleteAllUserServlet",
                            cache: false,
                            data: 'user_id=' + selected_values,
                            success: function (response) {
// remove deleted employee rows
                                var product_ids = response.split(",");
                                for (var i = 0; i < product_ids.length; i++) {
                                    $("#" + product_ids[i]).remove();
                                }
                                window.location.reload(true);
                            }
                        });
                    }
                }
            });
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
</head>
<body>
<form method="get" action="/AddUserServlet">
    <div style="background: #E0E0E0; height: 65px; padding: 5px;">
        <div style="float: right">
            <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a> &nbsp;
            <span style="color:blue">[ ${loginUser.name} ]</span>
        </div>
        <div style="float: left">
            <h1>Quản lí user</h1>
        </div>
    </div>
    <div class="container">
        <h2>Danh sách các user:</h2>

        <table id="example" class="table table-striped">
            <thead>

            <tr>
                <th><label><input type="checkbox" id="checkedAll" name="checkedAll" value=""> </label></th>
                <th>name</th>
                <th>sdt</th>
                <th>email</th>
                <th>edit</th>
                <th>delete</th>
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

            <c:forEach items="${listUser}" var="user">
                <tr>
                    <td><label><input type="checkbox" id="check" name="check" class="checkSingle"
                                      data-value="${user.id}"> </label></td>
                    <td>${user.name}</td>
                    <td>${user.phone}</td>
                    <td>${user.email} </td>
                    <td class="center"><i class="fa fa-trash-o  fa-fw"></i><a
                            href="${pageContext.request.contextPath}/EditUserServlet?id=${user.id}"> Sửa</a></td>
                    <td class="center"><i class="fa fa-pencil fa-fw"></i> <a
                            href="${pageContext.request.contextPath}/DeleteUserServlet?id=${user.id}"
                            onclick="return confirmDelete()">Xóa</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <input type="button" class="btn btn-primary" value="Add User"
               onclick='window.location="${pageContext.request.contextPath}/AddUserServlet"'>
        </input> &nbsp; &nbsp;

        <!-- <input type="button" id = "deleteAll" class="btn btn-primary" value="Delete All"  onclick="return confirmDelete()" >
         </input> <br><br> -->

        <div class="row">
            <div class="col-md-2 well">
                <span class="rows_selected" id="select_count">0 Selected</span>
                <input type="button" id="deleteAll" class="btn btn-primary" value="Delete All"></input>
            </div>
        </div>

        <a href="${pageContext.request.contextPath}/HomeServlet">Quản lí sản phẩm </a> <br><br>


    </div>
</form>
</body>
</html>