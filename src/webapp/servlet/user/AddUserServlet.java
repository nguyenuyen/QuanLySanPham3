package webapp.servlet.user;

import dao.UserDao;
import dao.User_logDao;
import model.UserAccount;
import model.User_log;
import utils.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

       // response.sendRedirect("/jsp/user/Add.jsp");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String sdt = request.getParameter("phone");
        String pass = request.getParameter("pass");
        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());
        Timestamp timestamp;
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
        User_log user_log = new User_log(loginUser.getEmail(), timestamp, "AddUser");
        UserAccount userAccount = new UserAccount(email, pass, sdt, name);
        UserDao userDao = new UserDao();

        int kq = userDao.addUser(userAccount);
        if (  kq == 1)
        {
            User_logDao.AddUser_log(user_log);
            response.sendRedirect("/AdminServlet");
        }
        else {
            // Chuyen sang trang loi
          response.sendRedirect("/ErrorServlet");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/user/Add.jsp");
        dispatcher.forward(request, response);
    }
}
