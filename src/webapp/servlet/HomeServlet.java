package webapp.servlet;

import dao.UserDao;
import model.UserAccount;
import utils.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());
        request.setAttribute("loginUser",userDao.findUser(loginUser.getEmail()));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Home.jsp");
        dispatcher.forward(request,response);
    }
}
