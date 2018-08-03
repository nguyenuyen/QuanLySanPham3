package webapp.servlet.type;

import dao.ProductDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeleteTypeServlet")
public class DeleteTypeServlet extends HttpServlet {
    public static final Logger logger = LogManager.getRootLogger();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        logger.error("name : " +name);
        ProductDao productDao = new ProductDao();
        int kq = productDao.deleteTypeProduct(name);
        if(kq ==1 )
        {
            request.getSession().setAttribute("isMessage",1);
            response.sendRedirect("/AddProductServlet");
        }
        else {
            throw new ServletException("HTTP GET Method Is Not Supported");
        }


    }
}
