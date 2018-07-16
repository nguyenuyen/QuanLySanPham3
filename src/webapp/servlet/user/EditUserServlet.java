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

@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
    public static final Logger logger = LogManager.getRootLogger();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        int id = (int) request.getSession().getAttribute("userId");

        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());
        Timestamp timestamp;
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
        User_log user_log = new User_log(loginUser.getEmail(), timestamp, "EditUser");

        logger.error("email:"+loginUser.getEmail()+" time : " +timestamp +" EditUser");

        UserAccount userAccount = new UserAccount(phone, name);
        UserDao userDao = new UserDao();
        int kq = userDao.editUser(id, userAccount);

        request.setAttribute("isError",kq ==1 ? "1":"0");

        logger.error("ket qua EditUser(1 la dung): "+kq );

        request.setAttribute("listUser",  userDao.findAllUser());

        User_logDao.AddUser_log(user_log);

        RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/user/Home.jsp");
        dispatcher.forward(request,response);
        // response.sendRedirect("/AdminServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String idUser = request.getParameter("id");
        int id = Integer.parseInt(idUser);
        UserDao userDao = new UserDao();
        //Lay user qua id.
        UserAccount userAccount = userDao.getInformationUserById(id);

        //
        request.getSession().setAttribute("userId", id);

        //Tra  ve userAccount , truyen vao requet
        request.setAttribute("userAccount", userAccount);


        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/jsp/user/Edit.jsp");
        dispatcher.forward(request, response);

    }
}
