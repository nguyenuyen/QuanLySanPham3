package webapp.servlet.user;

import dao.UserDao;
import dao.User_logDao;
import model.UserAccount;
import model.User_log;
import org.apache.logging.log4j.LogManager;
import utils.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    public static final Logger logger = LogManager.getRootLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // response.sendRedirect("/jsp/user/Add.jsp");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String sdt = request.getParameter("phone");
        String pass = request.getParameter("pass");
        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());
        Timestamp timestamp;
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
        UserDao userDao = new UserDao();
        User_log user_log = new User_log(loginUser.getEmail(), timestamp, "AddUser");
        logger.error("email:" + loginUser.getEmail() + " time : " + timestamp + " AddUser");
        UserAccount userAccount = new UserAccount(email, pass, sdt, name);
        {
            int kq = userDao.addUser(userAccount);
            if (kq != 1) {
                throw new ServletException("HTTP GET Method Is Not Supported");
            }
            User_logDao.AddUser_log(user_log);
            logger.error("email:" + loginUser.getEmail() + " time : " + timestamp + " AddUser");
            response.sendRedirect("/AdminServlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/user/Add.jsp");
        dispatcher.forward(request, response);
    }
}
