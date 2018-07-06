<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://code.jquery.com/jquery-1.7.2.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script type="text/javascript">

       $(document).ready(function() {
           $('#email').blur(function(e) {
               if (validateMail('email')) {
                   $('#spnEmailStatus').html("");
               }
               else {
                   $('#spnEmailStatus').html('Invalid');
                   $('#spnEmailStatus').css('color', 'red');
               }
           });

           function validateMail(txtMail) {
               var a = document.getElementById(txtMail).value;

               if (isEmail(a)) {
                   return true;
               }
               else {
                   return false;
               }
           }
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

       function isEmail(emailStr) {
           var emailPat = /^(.+)@(.+)$/;
           var specialChars = "\\(\\)<>@,;:\\\\\\\"\\.\\[\\]";
           var validChars = "\[^\\s" + specialChars + "\]";
           var quotedUser = "(\"[^\"]*\")";
           var ipDomainPat = /^\[(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})\]$/;
           var atom = validChars + '+';
           var word = "(" + atom + "|" + quotedUser + ")";
           var userPat = new RegExp("^" + word + "(\\." + word + ")*$");
           var domainPat = new RegExp("^" + atom + "(\\." + atom + ")*$");
           var matchArray = emailStr.match(emailPat);
           if (matchArray == null) {
               return false;;
           }
           var user = matchArray[1];
           var domain = matchArray[2];

           // See if "user" is valid
           if (user.match(userPat) == null) {
               return false;
           }
           var IPArray = domain.match(ipDomainPat);
           if (IPArray != null) {
               // this is an IP address
               for (var i = 1; i <= 4; i++) {
                   if (IPArray[i] > 255) {
                       return false;
                   }
               }
               return true;
           }
           var domainArray = domain.match(domainPat);
           if (domainArray == null) {
               return false;
           }

           var atomPat = new RegExp(atom, "g");
           var domArr = domain.match(atomPat);
           var len = domArr.length;

           if (domArr[domArr.length - 1].length < 2 ||
               domArr[domArr.length - 1].length > 3) {
               return false;
           }

           // Make sure there's a host name preceding the domain.
           if (len < 2) {
               return false;
           }

           // If we've gotten this far, everything's valid!
           return true;
       }

        function checkInput() {

            var flag = isValid;
            var message = '';


            //Kiem tra sdt
            flag = true;
            if(!validatePhone('phone')){
                alert("So dien thoai khong hop le")
                flag=false;
            }
            if(!validateMail('email'))
            {
                alert("mail khong hop le");
                flag = false;
            }

            if(flag == false)
            {
                alert("--------------");
                return false;
            }
            else
            {
                return true;
            }
        }

    </script>


    <title>Thêm User</title>
</head>
<body>

<form method="post" action="/AddUserServlet" onsubmit="return checkInput()" >

    <div class="container">
         
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <h1>Thêm User</h1>
                <div class="form-group">
                    <label>Name:</label>
                    <input type="text" class="form-control" name="name" id="name" required="required" maxlength="40">
                </div>
                <div class="form-group">
                    <label>Email:</label>
                    <input type="text" class="form-control" name="email" id="email" required><span id="spnEmailStatus"></span>
                </div>
                <div class="form-group">
                    <label>Sđt:</label>
                    <input type="text" class="form-control" name="phone" id="phone" maxlength="11" required> <span id="spnPhoneStatus"></span>
                </div>
                <div class="form-group">
                    <label>PassWord</label>
                    <input type="text" class="form-control" name="pass" id="pass" maxlength="16" required="required">
                </div>
                <div>
                    <input type="submit" class="btn btn-default" id="" value="Thêm User">

                    <button type="reset" class="btn btn-default">Làm mới</button>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>