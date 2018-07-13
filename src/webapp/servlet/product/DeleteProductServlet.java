package webapp.servlet.product;

import dao.ProductDao;
import dao.Product_logDao;
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
import java.sql.Timestamp;
import java.util.Date;

@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
    public static final Logger logger = LogManager.getRootLogger();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        int product_id = Integer.parseInt(id);
        ProductDao productDao = new ProductDao();
        int kq = productDao.deleteProduct(product_id);

        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());

        Timestamp timestamp;
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
        Product_log product_log = new Product_log(loginUser.getEmail(), timestamp, "DeleteProduct");
        logger.info(product_log);

        request.setAttribute("isErrorDelete", kq == 1 ? 1 : 0);
        request.setAttribute("listProduct", productDao.findAllProduct());

        Product_logDao.AddProduct_log(product_log);

        RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/product/Home.jsp");
        dispatcher.forward(request,response);
    }
}
