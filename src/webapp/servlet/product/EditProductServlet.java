package webapp.servlet.product;

import dao.ProductDao;
import dao.UserDao;
import model.Product;
import model.UserAccount;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EditProductServlet")
public class EditProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String gia = request.getParameter("price");
        int price = Integer.parseInt(gia);
        String type = request.getParameter("type");
        int id = (int) request.getSession().getAttribute("product_id");
        ProductDao productDao = new ProductDao();
        Product product = new Product(id, name, price, type);
        int kq = productDao.editProduct(product);

        request.setAttribute("isError", kq == 1 ? 1 : 0);
        request.setAttribute("listProduct", productDao.findAllProduct());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/product/Home.jsp");
        dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        int product_id = Integer.parseInt(id);
        request.getSession().setAttribute("product_id", product_id);
        ProductDao productDao = new ProductDao();
        Product product = productDao.findProductById(product_id);
        request.setAttribute("product",product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/product/Edit.jsp");
        dispatcher.forward(request, response);
    }
}
