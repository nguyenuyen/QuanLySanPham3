package webapp.servlet.product;

import dao.ProductDao;
import model.Product;
import model.UserAccount;
import utils.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/product/Add.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String gia = req.getParameter("price");
        String type = req.getParameter("type");
        int price = Integer.parseInt(gia);
        ProductDao productDao = new ProductDao();
        UserAccount userAccount = AppUtils.getLoginUser(req.getSession());
        int user_id = productDao.findUser_idByEmail(userAccount.getEmail());
        Product product = new Product(name, price, type, user_id);
        int kq = productDao.addProduct(product);
        if (kq == 1) {
            resp.sendRedirect("/UserServlet");
        } else {
            resp.sendRedirect("/ErrorServlet");
        }
    }
}
