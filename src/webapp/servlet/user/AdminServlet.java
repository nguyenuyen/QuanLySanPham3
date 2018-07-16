package webapp.servlet.user;


import dao.UserDao;
import dao.User_logDao;
import model.UserAccount;
import model.User_log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    public static final Logger logger = LogManager.getRootLogger();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lay danh sach tat ca nguoi dung va tra ve de hien thi
        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());
        Timestamp timestamp;
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
        User_log user_log = new User_log(loginUser.getEmail(), timestamp, "ShowUser");
        UserDao userDao=new UserDao();
        request.setAttribute("listUser",  userDao.findAllUser());

        User_logDao.AddUser_log(user_log);

        RequestDispatcher dispatcher =  request.getServletContext().getRequestDispatcher("/jsp/user/Home.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

    }
}
