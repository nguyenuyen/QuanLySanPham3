<%@ page import="java.text.DecimalFormat" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">


    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
   <!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>-->

    <script>

        $(document).ready(function () {
            $('#example').DataTable();

          /*  $("#checkAll").click(function () {
                $('input:checkbox').not(this).prop('checked', this.checked);
            });
*/
            $("#checkedAll").change(function(){
                if(this.checked){
                    $(".checkSingle").each(function(){
                        this.checked=true;
                    })
                }else{
                    $(".checkSingle").each(function(){
                        this.checked=false;
                    })
                }
            });

            $(".checkSingle").click(function () {
                if ($(this).is(":checked")){
                    var isAllChecked = 0;
                    $(".checkSingle").each(function(){
                        if(!this.checked)
                            isAllChecked = 1;
                    })
                    if(isAllChecked == 0){ $("#checkedAll").prop("checked", true); }
                }else {
                    $("#checkedAll").prop("checked", false);
                }
            });

        });


        function checkAllProduct() {
          /*  $("#checkAll").change(function(){

                if (! $('input:checkbox').is('checked')) {
                    $('input:checkbox').prop('checked',true);
                } else {
                    $('input:checkbox').prop('checked', false);
                }


            });*/

            $("#checkedAll").change(function(){
                if(this.checked){
                    $(".checkSingle").each(function(){
                        this.checked=true;
                    })
                }else{
                    $(".checkSingle").each(function(){
                        this.checked=false;
                    })
                }
            });

            $(".checkSingle").click(function () {
                if ($(this).is(":checked")){
                    var isAllChecked = 0;
                    $(".checkSingle").each(function(){
                        if(!this.checked)
                            isAllChecked = 1;
                    })
                    if(isAllChecked == 0){ $("#checkedAll").prop("checked", true); }
                }else {
                    $("#checkedAll").prop("checked", false);
                }
            });

        }

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
        <div class="row" style="float: left">

        </div>

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
                        <th> <label><input type="checkbox" id = "checkedAll" name = "checkedAll"  value="" > </label></th>
                        <th>id</th>
                        <th>name</th>
                        <th>price</th>
                        <th>type</th>
                        <th>create_at</th>
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
                    <c:forEach items="${listProduct}" var="product">
                        <tr>
                            <td><label><input type="checkbox" id="check" name ="check"class="checkSingle" value="${product.id}"> </label></td>
                            <td>${product.id}</td>
                            <td>${product.name}</td>
                            <td> <fmt:formatNumber type="number"  pattern="###,###" value="${product.price}"/> VNĐ</td>
                            <td>${product.type}</td>
                            <td> ${product.create_at}</td>
                            <td class="center"><i class="fa fa-trash-o  fa-fw"></i><a
                                    href="${pageContext.request.contextPath}/EditProductServlet?id=${product.id}">Sửa</a>
                            </td>
                            <td class="center"><i class="fa fa-pencil fa-fw"></i> <a
                                    href="${pageContext.request.contextPath}/DeleteProductServlet?id=${product.id}"
                                    onclick="return confirmDelete()">Xóa</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table> <br>
                <input type="button" class="btn btn-primary" value="Add Product"
                       onclick='window.location="${pageContext.request.contextPath}/AddProductServlet"'>
                </input>  &nbsp;  &nbsp;
                <input type="button" class="btn btn-primary" value="Delete All"
                       onclick='window.location="${pageContext.request.contextPath}/Servlet"'>
                </input> <br><br>

                <a href="${pageContext.request.contextPath}/HomeServlet">Quản lí sản phẩm </a> <br><br>
            </div>
        </div>
    </div>
</form>
</body>
</html>