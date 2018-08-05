<%--
  Created by IntelliJ IDEA.
  User: nguyen_uyen
  Date: 8/5/2018
  Time: 8:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<div style="float: left">
<div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse">
        <ul class="nav" id="side-menu">
            <li>
                <a href="admin/theloai/danhsach"><i class="fa fa-bar-chart-o fa-fw"></i>Thể loại <span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="admin/theloai/danhsach">Danh sách</a>
                    </li>
                    <li>
                        <a href="admin/theloai/them">Thêm</a>
                    </li>
                </ul>
                <!-- /.nav-second-level -->
            </li>

            <li>
                <a href="#"><i class="fa fa-users fa-fw"></i> User<span class="fa arrow"></span></a>
                <ul class="nav nav-second-level">
                    <li>
                        <a href="admin/user/danhsach">Danh sách</a>
                    </li>
                    <li>
                        <a href="admin/user/them">Thêm</a>
                    </li>
                    <li>
                        <a href="admin/user/thongbaochatvoiAdmin">Thongbao</a>
                    </li>
                </ul>
                <!-- /.nav-second-level -->
            </li>
        </ul>
    </div>
    <!-- /.sidebar-collapse -->
</div>
</div>
</body>
</html>
