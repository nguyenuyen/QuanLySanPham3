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

    <title>Quản lý user</title>
    <script>
        $(document).ready(function() {
            $('#example').DataTable({
                "scrollY": "50vh",
                "scrollCollapse": true,
            });


            $("#checkedAll").on('click',function(){
                if($(this).is(':checked',true)) {
                    $(".checkSingle").prop('checked', true);
                }
                else {
                    $(".checkSingle").prop('checked', false);
                }

            });

            $(".checkSingle").click(function () {
                if ($(this).is(":checked")){
                    var isAllChecked = 0;
                    $(".checkSingle").each(function(){
                        if(!this.checked)
                            isAllChecked = 1;
                    });
                    if(isAllChecked == 0){ $("#checkedAll").prop("checked", true); }
                }else {
                    $("#checkedAll").prop("checked", false);
                }
            });
        } );
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
</head>
<body>
<form method="post" action="/DeleteAllUserServlet">
    <div style="background: #E0E0E0; height: 65px; padding: 5px;">
        <div style="float: right ;padding: 30px; text-align: right;">
            <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a> &nbsp;
            <span style="color:blue">[ ${loginUser.name} ]</span>
        </div>
        <div style="float: left">
            <h1>Quản lý user</h1>
        </div>
    </div>
    <div class="container">
        <h2>Danh sách các user:</h2>

        <table id ="example" class="table table-striped table-bordered">
            <thead>
            <tr>
                <th> <label><input type="checkbox" id = "checkedAll" name = "checkedAll"  value="" > </label></th>
                <th>no</th>
                <th>name</th>
                <th>email</th>
                <th>phone</th>
                <th>action</th>
            </tr>
            </thead>
            <tbody>

            <% int i = 1; %>
            <c:forEach items="${listUser}" var="user">
                <tr id="${user.id}">
                    <td><label><input type="checkbox" id="check" name ="check"class="checkSingle" value ="${user.id}" > </label></td>
                    <td><%= i %> <% i++; %></td>
                    <td>${user.name}</td>
                    <td>${user.email} </td>
                    <td>${user.phone}</td>
                    <td class="center"><i class="fa fa-trash-o  fa-fw"></i><a
                            href="${pageContext.request.contextPath}/EditUserServlet?id=${user.id}"> Edit</a> &emsp;  &emsp;  &emsp;  &emsp;
                     <a href="${pageContext.request.contextPath}/DeleteUserServlet?id=${user.id}" onclick="return confirmDelete()">Delete</a></td>
                </tr>
            </c:forEach>

            </tbody>

        </table>

        <input type="button" value="Add_User" class="btn btn-primary"  onclick='window.location="${pageContext.request.contextPath}/AddUserServlet"'> </input>  &nbsp;  &nbsp;

        <input type="submit" id = "deleteAll" class="btn btn-primary" value="Delete All"  onclick="return confirmDelete()" >
        </input> <br><br>



    </div>
</form>
</body>
</html>