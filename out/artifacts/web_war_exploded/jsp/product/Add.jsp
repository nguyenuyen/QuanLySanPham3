<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>

        $(document).ready(function () {

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

        function checkType() {
            var text = document.getElementById("type").value;
            if (text == "-----") {
                alert("chua co the loai");
                return false;
            }
        }
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
<form method="post" action="/AddProductServlet" onsubmit="return checkType()">
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
                <h1>Thêm Sản Phẩm</h1>
                <div class="form-group">
                    <label>Tên sản phẩm :</label>
                    <input type="text" class="form-control" name="name" required maxlength="40">
                </div>
                <div class="form-group">
                    <label>Thể loại :</label>
                    <c:if test="${isError == '1'}">
                        <script>
                            alert("Ban da sua thanh cong");
                        </script>
                    </c:if>
                    <select class="form-control" id="type" name="name_type">
                        <option value="-----">-----</option>
                        <c:forEach items="${listType}" var="list">
                            <option value="${list.name}">${list.name}</option>
                        </c:forEach>
                    </select>
                    <br>

                    <!-- <input type="" value="them moi" class="btn"></input>-->
                </div>
                <div class="form-group">
                    <label>Giá</label>
                    <input type="text" class="form-control" name="price" required id="price">
                </div>
                <div>
                    <button type="submit" class="btn btn-default">Add</button>
                    <button type="reset" class="btn btn-default">Reset</button>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>