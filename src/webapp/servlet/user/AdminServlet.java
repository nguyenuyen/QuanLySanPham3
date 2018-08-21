package webapp.servlet.user;


import dao.PagingDao;
import dao.UserDao;
import dao.User_logDao;
import model.Product;
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
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    public static final Logger logger = LogManager.getRootLogger();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lay danh sach tat ca nguoi dung va tra ve de hien thi
        UserDao userDao=new UserDao();
        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());
        request.setAttribute("loginUser",userDao.findUser(loginUser.getEmail()));
        Timestamp timestamp;
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
        User_log user_log = new User_log(loginUser.getEmail(), timestamp, "ShowUser");

        request.setAttribute("listUser",  userDao.findAllUser());

        User_logDao.AddUser_log(user_log);

        RequestDispatcher dispatcher =  request.getServletContext().getRequestDispatcher("/jsp/user/Home.jsp");
        dispatcher.forward(request, response);

     /*   int page =1;
        int recordsPerpage =5;
        if(request.getParameter("page") != null)
        {
            page = Integer.parseInt(request.getParameter("page"));
        }
     //   PagingDao pagingDao = new PagingDao();
     //   List<UserAccount> user = pagingDao.viewAllUser((page - 1)* recordsPerpage , recordsPerpage );
      ///  int noOfRecords = pagingDao.getNoOfRecords();
//        request.setAttribute("noOfPages", noOfPages);
        //request.setAttribute("currentPage", page);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/user/Home.jsp");
        dispatcher.forward(request,response);*/
    }




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

    }
}
