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
            //
            // $('#example').DataTable({
            //     "scrollY": "50vh",
            //     "scrollCollapse": true,
            //
            //
            // });

            $("#searchData").click(function () {
                var page = 1;
                var searchItem = $("#search").val();
                var option = $("#chose").val();
                loadPage(searchItem, page, option);
            })
            $("#chose").change(function () {
                var page = 1;
                var searchItem = $("#search").val();
                var option = $("#chose").val();
                loadPage(searchItem, page, option);

            })

            $(".item").on('click', function () {
                // alert();
                var page = $(this).val();
                var searchItem = $("#search").val();
                var option = $("#chose").val();
                loadPage(searchItem, page, option);
            })



            function loadPage(search, page, option) {
                location.href = "/UserServlet?page=" + page + "&search=" + search + "&option=" + option;

            }

            $("#checkedAll").on('click', function () {
                if ($(this).is(':checked', true)) {
                    $(".checkSingle").prop('checked', true);
                }
                else {
                    $(".checkSingle").prop('checked', false);
                }

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

            $("#exportExcel").click(function (e) {

                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/ExportExcelServlet",
                    cache: false,
                    success: function (response) {
                        alert(response);
                    }
                });
            });

        });


        function importExcel() {
            var s = document.getElementById("myFile");
            if (s == null) {
                alert("ban chua chon file");
                return false;
            }
            return true;
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

        function submitx(v) {


            var frm = document.form1;
            var page = document.getElementsByClassName("active")[0].value;
            var option = document.getElementById("chose").value;
            var search = document.getElementById("search").value;
            alert("page="+page+"&option="+option+"&search="+search);
            if (v == "1") {
                frm.action = "/DeleteAllProductServlet?page="+page+"&option="+option+"&search="+search;
            } else if (v == "2") {
                frm.action = "/ExportExcelServlet";
            }

            frm.submit();
        }


    </script>
    <style>

        .scroll {
            display: block;
            height: 500px;
            overflow-y: scroll;
        }
        .zoom:hover {
            -ms-transform: scale(2); /* IE 9 */
            -webkit-transform: scale(2); /* Safari 3-8 */
            transform: scale(2);
            width: 100px;
            height: 100px;
        }

        .pagination {
            display: inline-block;
        }

        .pagination li {
            color: black;
            float: left;
            padding: 8px 16px;
            text-decoration: none;
            transition: background-color .3s;
            border: 1px solid #ddd;
            font-size: 22px;
        }

        .pagination li.active {
            background-color: #4CAF50;
            color: white;
            border: 1px solid #4CAF50;
        }

        .pagination li:hover:not(.active) {
            background-color: #ddd;
        }
    </style>

    <title>Quản lý sản phẩm</title>
</head>
<body>
<form method="post" action="" name="form1">
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
                <div style="float: right">
                    <input id="search" name="search" value="${search}">
                    <input type="button" id="searchData" name="searchData" class="btn btn-primary" value="Search"/>  <br>
                </div>
                <div style="float: left">
                    <label>show item : </label>
                    <select id="chose" name="option">
                        <c:choose>
                            <c:when test="${record == 5}">
                                <option value="5" selected>5</option>
                            </c:when>
                            <c:otherwise>
                                <option value="5">5</option>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${record == 10}">
                                <option value="10" selected>10</option>
                            </c:when>
                            <c:otherwise>
                                <option value="10">10</option>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${record == 20}">
                                <option value="20" selected>20</option>
                            </c:when>
                            <c:otherwise>
                                <option value="20">20</option>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${record == 50}">
                                <option value="50" selected>50</option>
                            </c:when>
                            <c:otherwise>
                                <option value="50">50</option>
                            </c:otherwise>
                        </c:choose>

                    </select>

                </div>

                <% int i = 1; %>
                <table id="example" class="table table-striped table-bordered scroll " cellpadding="0" cellspacing="0" border="0" >
                    <thead>
                    <tr>
                        <th><label><input type="checkbox" id="checkedAll" name="checkedAll" value=""> </label></th>
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
                            <td><label><input type="checkbox" id="check" name="check" class="checkSingle"
                                              value="${product.id}"> </label></td>
                            <td><%= i %> <% i++; %></td>
                            <td>${product.name}</td>
                            <td class="zoom"><img src="${product.url}" alt="picture" width="80px" height="80px"></td>
                            <td>${product.type}</td>
                            <td><fmt:formatNumber type="number" pattern="###,###" value="${product.price}"/> VNĐ</td>
                            <td> ${product.create_at}</td>
                            <td class="center"><i class="fa fa-trash-o  fa-fw"></i><a
                                    href="${pageContext.request.contextPath}/EditProductServlet?page=${page}&id=${product.id}&search=${search}&option=${record}">
                                edit</a>&nbsp; &nbsp;
                                <a href="${pageContext.request.contextPath}/DeleteProductServlet?page=${page}&id=${product.id}&search=${search}&option=${record}"
                                   onclick="return confirmDelete()">delete</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </table>
                <table border="0" cellpadding="0" cellspacing="0">
                    <td class="pagination">
                        <c:if test="${currentPage != 1}">
                            <li class="item" value="${currentPage - 1}">&nbsp;Previous</li>
                        </c:if>

                        <c:forEach begin="1" end="${noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <li class="active" value="${i}">${i}&nbsp;</li>
                                </c:when>
                                <c:otherwise>
                                    <li class="item" value="${i}">${i}&nbsp;</li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:if test="${currentPage lt noOfPages}">
                            <li class="item" value="${currentPage + 1}">&nbsp;Next</li>
                        </c:if>
                    </td>
                </table>
                <br>
                <input type="button" class="btn btn-primary" value="Add Product"
                       onclick='window.location="${pageContext.request.contextPath}/AddProductServlet"'>
                </input> &nbsp; &nbsp;

                <input type="button" id="deleteAll" class="btn btn-primary" value="Delete All"
                       onclick="return confirmDelete(), submitx('1')">
                </input> &nbsp; &nbsp;
                <%--<a href="${pageContext.request.contextPath}/ExportExcelServlet"  id="exportExcel">--%>
                <%--Download File Excel </a><br><br>--%>
                <input type="button" name="exportExcel" class="btn btn-primary" value="Download File Excel"
                       onclick="submitx('2')">
                </input> <br><br>

                <%--&lt;%&ndash;Select a file excel: <input type="file" id="myFile"> <br><br>&ndash;%&gt;--%>
                <%--<input type="button" id="importExcel" class="btn btn-primary" value="ImpotFile"--%>
                <%--onclick='window.location="${pageContext.request.contextPath}/ImportServlet"'> <br><br>--%>


                <%--<a href="${pageContext.request.contextPath}/TypeServlet">Quản lý thể loại </a> <br><br>--%>
            </div>
        </div>
    </div>

</form>


<form action="/uploadFileExcel" method="post" enctype="multipart/form-data" onsubmit="return importExcel() ">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                Select a file excel:
                <input type="file" id="myFile" name="myFile"
                       accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"> <br><br>
                <input type="submit" id="importExcel" class="btn btn-primary" value="Import Excel File"> <br><br>
                <a href="${pageContext.request.contextPath}/TypeServlet">Quản lý thể loại </a> <br><br>
            </div>
        </div>
    </div>
</form>


</body>
</html>