package webapp.servlet.user;


import dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lay danh sach tat ca nguoi dung va tra ve de hien thi
        UserDao userDao=new UserDao();
        request.setAttribute("listUser",  userDao.findAllUser());

        RequestDispatcher dispatcher =  request.getServletContext().getRequestDispatcher("/jsp/user/Home.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

    }
}
