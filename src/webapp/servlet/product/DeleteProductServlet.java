package webapp.servlet.product;

import dao.ProductDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int product_id = Integer.parseInt(id);
        ProductDao productDao = new ProductDao();
        int kq = productDao.deleteProduct(product_id);
        request.setAttribute("isErrorDelete", kq == 1 ? 1 : 0);
        request.setAttribute("listProduct", productDao.findAllProduct());
        RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/product/Home.jsp");
        dispatcher.forward(request,response);
    }
}
