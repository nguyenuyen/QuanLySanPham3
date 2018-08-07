package webapp.servlet.user;

import dao.ProductDao;
import dao.UserDao;
import model.UserAccount;
import utils.AppUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteAllUserServlet")
public class DeleteAllUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDao userDao= new UserDao();
        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());
        String value =  request.getParameter("user_id");
        String s = userDao.deleteAllUser(value);
        if(s != null )
        {
            response.setContentType("data/plain");
            response.getWriter().write(s);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {



    }
}
