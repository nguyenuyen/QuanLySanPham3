package webapp.servlet.product;

import dao.ProductDao;
import dao.Product_logDao;
import model.Product;
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

@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
    public static final Logger logger = LogManager.getRootLogger();
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
        logger.error("name:"+name+" gia : " +gia +"type : "+ type +"gia : "+gia);

        UserAccount loginUser = AppUtils.getLoginUser(req.getSession());
        Timestamp timestamp;
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
        Product_log product_log = new Product_log(loginUser.getEmail(), timestamp, "AddProduct");
        logger.error("email:"+loginUser.getEmail()+" time : " +timestamp +" AddProduct");
        ProductDao productDao = new ProductDao();
        UserAccount userAccount = AppUtils.getLoginUser(req.getSession());
        int user_id = productDao.findUser_idByEmail(userAccount.getEmail());
        Product product = new Product(name, price, type, user_id);
        int kq = productDao.addProduct(product);
        logger.error("ket qua AddProduct: "+kq);
        if (kq == 1) {
            Product_logDao.AddProduct_log(product_log);
            resp.sendRedirect("/UserServlet");
        } else {
            resp.sendRedirect("/ErrorServlet");
        }
    }
}
