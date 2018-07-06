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
            var filter =/^[0-9-+]+$/;
            if (filter.test(a)) {
                return true;
            }
            else {
                return false;
            }
        }
    </script>
    <title>Thêm User</title>
</head>
<body>
<form method="post" action="/EditUserServlet">
    <div class="container">
         
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
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
                    <input type="text" class="form-control" name="phone" id="phone" value="${userAccount.phone}"> <span id="spnPhoneStatus"></span> //Uyen vang
                </div>

                <div>
                    <button type="submit" class="btn btn-default">Sửa</button>
                    <button type="reset" class="btn btn-default">Làm mới</button>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>