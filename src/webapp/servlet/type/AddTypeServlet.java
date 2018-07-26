package webapp.servlet.type;

import dao.ProductDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddTypeServlet")
public class AddTypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao productDao= new ProductDao();
        String name= request.getParameter("name");
        int kq=productDao.addTypeProduct(name);
        if(kq ==1 )
        {
            request.getSession().setAttribute("isMessage",1);
            response.sendRedirect("/AddProductServlet");
        }
        else {
            throw new ServletException("HTTP GET Method Is Not Supported");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao productDao= new ProductDao();
        request.setAttribute("listType",productDao.findAllTypeProduct());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/product/AddType.jsp");
        dispatcher.forward(request, response);
    }
}
