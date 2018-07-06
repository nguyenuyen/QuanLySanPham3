package webapp.servlet;

import connection.ConnectDatabase;
import dao.UserDao;
import model.UserAccount;
import utils.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        String user = request.getParameter("email");
        String pass = request.getParameter("pwd");
        request.setAttribute("email", user);

        UserDao userDao = new UserDao();

        UserAccount userAccount = userDao.findUserByEmailandPass(user, pass);

        if (request.getParameter("ghinho") != null) {
            Cookie c = new Cookie("cookieName", user);
            Cookie cookie = new Cookie("cookiePass", pass);
            cookie.setMaxAge(500);
            c.setMaxAge(500);
            response.addCookie(c);
            response.addCookie(cookie);

        }
        try {
            if (userAccount != null) {
                AppUtils.storeLoginedUser(request.getSession(), userAccount);
                if ("Admin".equals(userAccount.getRole())) {
                    response.sendRedirect(request.getContextPath() + "/AdminServlet");
                } else {
                    response.sendRedirect(request.getContextPath() + "/UserServlet");

                }
            } else {

                response.sendRedirect("/jsp/login.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/login.jsp");
        dispatcher.forward(req, resp);
    }
}
