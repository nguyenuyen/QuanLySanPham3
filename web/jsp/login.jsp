<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    </script>

    <title>Đăng nhập</title>
</head>
<body>
<form method="post" action="/login">
    <input type="hidden" name="redirectId" value=""/>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4" style="background-color:lightcyan;">
                <h1>Đăng nhập</h1>
                <div class="form-group">
                    <label>Email:</label>
                    <input type="text" class="form-control" name="email"  requiredid="email" value="${cookie.cookieName.value}">
                </div>
                <div class="form-group">
                    <label>Password:</label>
                    <input type="password" class="form-control" name="pwd" id="pwd"  maxlength="16" value="${cookie.cookiePass.value}">
                </div>
                <div class="checkbox">
                    <label><input type="checkbox" name="ghinho"> Ghi Nhớ</label>
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </div>
        </div>
    </div>
</form>
</body>
</html>