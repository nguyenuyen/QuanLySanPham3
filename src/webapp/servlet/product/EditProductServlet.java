package webapp.servlet.product;

import dao.ProductDao;
import dao.Product_logDao;
import dao.TypeDao;
import dao.UserDao;
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

@WebServlet("/EditProductServlet")
public class EditProductServlet extends HttpServlet {
    public static final Logger logger = LogManager.getRootLogger();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String type = request.getParameter("type");

        logger.error("name:"+name+" gia : " +price +"type : "+ type +"gia : "+price);

        int id = (int) request.getSession().getAttribute("product_id");

        logger.error("id san pham : "+id);

        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());
        Timestamp timestamp;
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
        Product_log product_log = new Product_log(loginUser.getEmail(), timestamp, "EditProduct");

        logger.error("email:"+loginUser.getEmail()+" time : " +timestamp +" EditProduct");

        ProductDao productDao = new ProductDao();
        Product product = new Product(id, name, price, type);
        int kq = productDao.editProduct(product);

        request.setAttribute("isError", kq == 1 ? 1 : 0);

        logger.error("ket qua EditProduct: "+kq );

        request.setAttribute("listProduct", productDao.findAllProduct(loginUser.getEmail()));

        Product_logDao.AddProduct_log(product_log);

        //RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/product/Home.jsp");
        //dispatcher.forward(request, response);
        response.sendRedirect("/UserServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());
        request.setAttribute("loginUser",userDao.findUser(loginUser.getEmail()));
        ProductDao productDao= new ProductDao();
        TypeDao typeDao = new TypeDao();
        request.setAttribute("listType",typeDao.findAllType());
        String id = request.getParameter("id");
        int product_id = Integer.parseInt(id);
        request.getSession().setAttribute("product_id", product_id);
        logger.error("id : " + product_id);
        Product product = productDao.findProductById(product_id);
        request.setAttribute("product", product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/product/Edit.jsp");
        dispatcher.forward(request, response);
    }
}
