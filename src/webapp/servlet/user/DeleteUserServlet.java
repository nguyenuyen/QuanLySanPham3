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

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());
        Timestamp timestamp;
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
        User_log user_log = new User_log(loginUser.getEmail(), timestamp, "DeleteUser");
        String idUser = request.getParameter("id");
        int id = Integer.parseInt(idUser);
        UserDao userDao = new UserDao();
        int kq = userDao.deleteUser(id);
        request.setAttribute("isErrorDelete", kq == 1 ? "1" : "0");
        request.setAttribute("listUser",  userDao.findAllUser());
        User_logDao.AddUser_log(user_log);
        RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/user/Home.jsp");
        dispatcher.forward(request,response);

    }
}
