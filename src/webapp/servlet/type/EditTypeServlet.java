package webapp.servlet.type;

import dao.ProductDao;
import dao.TypeDao;
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
       String name = request.getParameter("name");
        int id = (int) request.getSession().getAttribute("id");
        TypeDao typeDao = new TypeDao();
        int kq = typeDao.editType(name,id);
        if(kq == 1)
        {
            response.sendRedirect("/TypeServlet");
        }
        else
        {
            throw new ServletException("HTTP Post Method Is Not Supported");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TypeDao typeDao = new TypeDao();
        //request.setAttribute("listType",productDao.findAllTypeProduct());
        int id= Integer.parseInt(request.getParameter("id"));
        request.getSession().setAttribute("id",id);
        request.setAttribute("type",typeDao.findTypeById(id));
        request.setAttribute("listType",typeDao.findAllType());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/type/EditType.jsp");
        dispatcher.forward(request, response);
    }
}
