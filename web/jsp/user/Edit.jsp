<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function() {
            var text = document.getElementById('phone');
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

            $('#phone').blur(function(e) {
                if (validatePhone('phone')) {
                    $('#spnPhoneStatus').html('');
                }
                else {
                    $('#spnPhoneStatus').html('Invalid');
                    $('#spnPhoneStatus').css('color', 'red');
                }
            });

        });

        function validatePhone(txtPhone) {
            var a = document.getElementById(txtPhone).value;
            var filter =/(09|01[2|6|8|9])+([0-9]{8})\b/;
            if (filter.test(a)) {
                return true;
            }
            else {
                return false;
            }
        }
        function checkInput() {
            var flag = true;

            if (!validatePhone('phone')) {
                alert("So dien thoai khong hop le");
                flag = false;
            }

            if (flag == false) {
                return false;
            }

            else {
                return true;
            }
        }
    </script>
    <title>Thêm User</title>
</head>
<body>
<form method="post" action="/EditUserServlet" onsubmit="return checkInput()">
    <div style="float: right">
        <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a> &nbsp;
        <span style="color:blue">[ ${loginUser.getEmail()} ]</span>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-2">
                <h1>Sửa User</h1>
                <div class="form-group">
                    <label>Email:</label>

                    <c:out>
                        <input type="text" class="form-control" name="email" value="${userAccount.email} " disabled>
                    </c:out>

                </div>
                <div class="form-group">
                    <label>Name:</label>
                    <input type="text" class="form-control" name="name" maxlength="40" value="${userAccount.name}">
                </div>

                <div class="form-group">
                    <label>Phone:</label>
                    <input type="text" class="form-control" name="phone" id="phone" value="${userAccount.phone}"> <span id="spnPhoneStatus"></span>
                </div>

                <div>
                    <button type="submit" class="btn btn-default">Sửa</button>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>