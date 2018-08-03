<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function  myfunction() {
            var text= document.getElementById("type").value;
            var data = document.getElementById("name");
            data.value = text;

        }
    </script>
</head>
<body>
<form action="/EditTypeServlet" method="post">
    <div class="container"> 
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <h1>Sửa The Loai</h1>
                <div class="form-group">
                    <label >Name:</label>
                    <input type="text" class="form-control" id="name" name="name" value="${type}" >
                </div>
            <!--    <div class="form-group">
                    <label>Thể loại :</label>
                    <select class="form-control" id="type" name="type" onclick="myfunction()">

                        <option value="-----">-----</option>
                    </select>
                </div>
                -->
                <div>
                    <button type="submit" class="btn btn-default" id="them">Sửa</button>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
