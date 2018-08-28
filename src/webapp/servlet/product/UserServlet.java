package webapp.servlet.product;

import dao.PagingDao;
import dao.ProductDao;
import dao.Product_logDao;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

    public static final Logger logger = LogManager.getRootLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDao userDao = new UserDao();
        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());
        Timestamp timestamp;
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
        Product_log product_log = new Product_log(loginUser.getEmail(), timestamp, "ShowProduct");
        request.setAttribute("loginUser", userDao.findUser(loginUser.getEmail()));

        ProductDao productDao = new ProductDao();
        List<Product> productList = new ArrayList<>();
        productList = productDao.findAllProduct(loginUser.getEmail());
        //  request.setAttribute("listProduct", productDao.findAllProduct(loginUser.getEmail()));


       /* JSONArray jsonArray = (JSONArray)JSONSerializer.toJSON(productDao.findAllProduct(loginUser.getEmail()));
        String json = "{'page':1,'total':'2','records':'1','rows':"+jsonArray+"}";
        JSONObject jsonObject = (JSONObject)JSONSerializer.toJSON(json);

        PrintWriter out = response.getWriter();
        out.print(jsonObject.toString());

*/
        Product_logDao.AddProduct_log(product_log);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/product/Home.jsp");
//        dispatcher.forward(request, response);


        String s = request.getParameter("search");
        int page = 1;
        String option = request.getParameter("option");
        int recordsPerpage = 0;
        if (option == null) {
            recordsPerpage = 5;
        }  else {
            recordsPerpage = Integer.parseInt(request.getParameter("option"));
        }


        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        request.getSession().setAttribute("search", s);
        request.getSession().setAttribute("record", recordsPerpage);

        PagingDao pagingDao = new PagingDao();
        List<Product> products = pagingDao.viewAllProduct((page - 1) * recordsPerpage, recordsPerpage, loginUser.getEmail(), s);
        int noOfRecords = pagingDao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerpage);
        request.setAttribute("listProduct", products);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/product/Home.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest requset, HttpServletResponse resp) {

    }
}

