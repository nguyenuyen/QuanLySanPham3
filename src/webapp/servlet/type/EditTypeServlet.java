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

@WebServlet("/EditTypeServlet")
public class EditTypeServlet extends HttpServlet {
    public static final Logger logger = LogManager.getRootLogger();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      /*  String type = (String) request.getSession().getAttribute("type");
        String name = request.getParameter("name");
        ProductDao productDao= new ProductDao();
        int kq=productDao.editTypeProduct(name,type);
        if(kq ==1 )
        {
            request.getSession().setAttribute("isMessage",1);
            response.sendRedirect("/AddProductServlet");
        }
        else {
            throw new ServletException("HTTP GET Method Is Not Supported");
        }*/
        String type = request.getParameter("name_type");
        request.setAttribute("type",type);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao productDao= new ProductDao();
        //request.setAttribute("listType",productDao.findAllTypeProduct());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/product/EditType.jsp");
        dispatcher.forward(request, response);
    }
}
