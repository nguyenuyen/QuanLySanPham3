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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
    public static final Logger logger = LogManager.getRootLogger();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        int product_id = Integer.parseInt(id);
        request.getSession().setAttribute("product_id",product_id);
        ProductDao productDao = new ProductDao();
        int kq = 0;
        kq = productDao.deleteProduct(product_id);
        logger.error("id san pham : "+product_id);

        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());

        Timestamp timestamp;
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
        Product_log product_log = new Product_log(loginUser.getEmail(), timestamp, "DeleteProduct");

        logger.error("email:"+loginUser.getEmail()+" time : " +timestamp +" DeleteProduct");

        request.setAttribute("isErrorDelete", kq == 1 ? 1 : 0);

        logger.error("ket qua DeleteProduct: "+kq );

        request.setAttribute("listProduct", productDao.findAllProduct(loginUser.getEmail()));

        Product_logDao.AddProduct_log(product_log);

      //  RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/product/Home.jsp");
      //  dispatcher.forward(request,response);
        response.sendRedirect("/UserServlet");
    }
}
