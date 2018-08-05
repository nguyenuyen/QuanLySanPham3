package webapp.servlet;

import connection.ConnectDatabase;
import dao.Product_logDao;
import dao.UserDao;
import dao.User_logDao;
import model.Product_log;
import model.UserAccount;
import model.User_log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.Date;

@WebServlet("/login")
public class Login extends HttpServlet {

    Logger logger = LogManager.getRootLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = request.getParameter("email");
        String pass = request.getParameter("pwd");
        request.setAttribute("email", user);
        Timestamp currentTime;

        UserDao userDao = new UserDao();

        UserAccount userAccount = userDao.findUserByEmailandPass(user, pass);

        if (userAccount == null) {
            logger.info("user hoac password khong dung");
        }
        if (request.getParameter("ghinho") != null) {
            Cookie c = new Cookie("cookieName", user);
            Cookie cookie = new Cookie("cookiePass", pass);
            cookie.setMaxAge(1000);
            c.setMaxAge(1000);
            response.addCookie(c);
            response.addCookie(cookie);
        }

        Date date = new Date();
        currentTime = new Timestamp(date.getTime());
        User_log user_log = new User_log(user, currentTime, "login");
        Product_log product_log = new Product_log(user, currentTime, "login");

        try {
            if (userAccount != null) {
                AppUtils.storeLoginedUser(request.getSession(), userAccount);
                if ("Admin".equals(userAccount.getRole())) {
                    User_logDao.AddUser_log(user_log);
                    response.sendRedirect(request.getContextPath() + "/AdminServlet");
                } else {
                   // response.sendRedirect(request.getContextPath() + "/UserServlet");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Home.jsp");
                    dispatcher.forward(request,response);
                    Product_logDao.AddProduct_log(product_log);
                }

            } else {
                response.sendRedirect("/jsp/login.jsp");
            }

        } catch (IOException e) {
            logger.debug(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/login.jsp");
        dispatcher.forward(req, resp);

    }
}
