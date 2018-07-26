<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function  myfunction() {
            var text= document.getElementById("type").value;
            var data = document.getElementById("user");
            data.value = text;
        }
    </script>
</head>
<body>
<div class="container"> 
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <h1>Thêm The Loai</h1>
            <div class="form-group">
                <label >Name:</label>
                <input type="text" class="form-control" id="user">
            </div>
            <div class="form-group">
                <label>Thể loại :</label>
                <select class="form-control" id="type" name="type" onclick="myfunction()">
                    <option value="dien tu">dien tu</option>
                    <option value="van phong pham">van phong pham</option>
                    <option value="do gia dung">do gia dung</option>
                    <option value="-----">-----</option>
                </select>
                <br>

                <!--  <input type="submit" class="btn"value="them moi"></input>-->
            </div>

            <div>
                <button type="submit" class="btn btn-default">Sua</button>
                <button type="reset" class="btn btn-default">Reset</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
