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
           // $('#example').DataTable();

          /*  $("#checkAll").click(function () {
                $('input:checkbox').not(this).prop('checked', this.checked);
            });

*/

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

          /*  $("#").click( function(e) {
                var product = [];
                $(".checkSingle:checked").each(function() {
                    product.push($(this).data('value'));
                });
                if(product.length <=0) { alert("Please select records."); } else { WRN_PROFILE_DELETE = "Are you sure you want to delete "+(product.length>1?"these":"this")+" row?";
                    var checked = confirm(WRN_PROFILE_DELETE);
                    if(checked == true) {
                        var selected_values = product.join(",");
                        $.ajax({
                            type: "post",
                            url: "/DeleteAllProductServlet",
                            cache:false,
                            data: 'product_id='+selected_values,
                            success: function(response) {
// remove deleted employee rows
                                var product_ids = response.split(",");
                                for (var i=0; i < product_ids.length; i++ ) {
                                    $("#"+product_ids[i]).remove();
                                }
                            }
                        });
                    }
                }
            });*/

            $("#exportExcel").click( function(e) {
                        $.ajax({
                            type: "get",
                            url: "${pageContext.request.contextPath}/ExportExcelServlet",
                            cache:false,
                            success: function(response) {
                                alert(response);
                            }
                        });
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
    <style>



        .zoom:hover {
            -ms-transform: scale(2); /* IE 9 */
            -webkit-transform: scale(2); /* Safari 3-8 */
            transform: scale(2);
            width: 100px;
            height: 100px;
        }
    </style>

    <title>Quản lý sản phẩm</title>
</head>
<body>
<form method="post" action="/DeleteAllProductServlet">
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
                <% int i = 1; %>
                <table id="example" class="table table-striped table-bordered"  cellpadding="0"
                       cellspacing="0" border="0" >
                    <thead>
                    <tr>
                        <th> <label><input type="checkbox" id = "checkedAll" name = "checkedAll"  value="" > </label></th>
                        <th>no</th>
                        <th>name</th>
                        <th>picture</th>
                        <th>type</th>
                        <th>price</th>
                        <th>create_at</th>
                        <th>action</th>

                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${listProduct}" var="product">
                        <tr id="${product.id}">
                            <td><label><input type="checkbox" id="check" name ="check"class="checkSingle" value ="${product.id}" > </label></td>
                            <td><%= i %> <% i++; %></td>
                            <td>${product.name}</td>
                            <td  class="zoom" >  <img src="${product.url}" alt="picture" width="80px" height="80px"></td>
                            <td>${product.type}</td>
                            <td> <fmt:formatNumber type="number"  pattern="###,###" value="${product.price}"/> VNĐ</td>
                            <td> ${product.create_at}</td>
                            <td class="center"><i class="fa fa-trash-o  fa-fw"></i><a
                                    href="${pageContext.request.contextPath}/EditProductServlet?id=${product.id}"> edit</a>&nbsp;  &nbsp;
                           <a href="${pageContext.request.contextPath}/DeleteProductServlet?id=${product.id}" onclick="return confirmDelete()">delete</a>  </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table> <br>
                <input type="button" class="btn btn-primary" value="Add Product"
                       onclick='window.location="${pageContext.request.contextPath}/AddProductServlet"'>
                </input>  &nbsp;  &nbsp;

                <input type="submit" id = "deleteAll" class="btn btn-primary" value="Delete All"  onclick="return confirmDelete()" >
                </input> &nbsp;  &nbsp;

                <input type="button" id="exportExcel" class="btn btn-primary" value="Export Excel">
                </input> <br><br>

                Select a file: <input type="file" name="myFile"> <br><br>
                <input type="button" onclick="" class="btn btn-primary" value="ImpotFile"> <br><br>


                <a href="${pageContext.request.contextPath}/TypeServlet">Quản lý thể loại </a> <br><br>
            </div>
        </div>
    </div>
</form>
</body>
</html>