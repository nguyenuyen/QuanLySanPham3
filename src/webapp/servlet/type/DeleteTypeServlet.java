package webapp.servlet.type;

import dao.ProductDao;
import dao.TypeDao;
import dao.UserDao;
import model.Product_log;
import model.UserAccount;
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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

@WebServlet("/DeleteTypeServlet")
public class DeleteTypeServlet extends HttpServlet {
    public static final Logger logger = LogManager.getRootLogger();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        logger.error("id : " +id);
        TypeDao typeDao = new TypeDao();
        int kq = typeDao.deleteType(id);
        if(kq ==1 )
        {
            request.getSession().setAttribute("isMessage",1);
            UserAccount loginUser = AppUtils.getLoginUser(request.getSession());

            Timestamp timestamp;
            Date date = new Date();
            timestamp = new Timestamp(date.getTime());
            Product_log product_log = new Product_log(loginUser.getEmail(), timestamp, "DeleteType");
            response.sendRedirect("/TypeServlet");
        }
        else {
            throw new ServletException("HTTP GET Method Is Not Supported");
        }


    }
}
