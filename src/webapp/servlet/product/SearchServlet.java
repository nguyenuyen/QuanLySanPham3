package webapp.servlet.product;

import dao.PagingDao;
import dao.UserDao;
import model.Product;
import model.Product_log;
import model.UserAccount;
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
import java.util.List;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());
        Timestamp timestamp;
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
        Product_log product_log = new Product_log(loginUser.getEmail(), timestamp, "ShowProduct");
        request.setAttribute("loginUser",userDao.findUser(loginUser.getEmail()));
        String s= request.getParameter("search");

      /* ProductDao productDao = new ProductDao();
        request.setAttribute("listProduct", productDao.findAllProduct());
        Product_logDao.AddProduct_log(product_log);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/product/Home.jsp");
        dispatcher.forward(request, response);*/
        int page =1;
        int recordsPerpage =5;
        if(request.getParameter("page") != null)
        {
            page = Integer.parseInt(request.getParameter("page"));
        }
        PagingDao pagingDao = new PagingDao();
        List<Product> products = pagingDao.viewAllProduct((page - 1)* recordsPerpage , recordsPerpage ,loginUser.getEmail(),s);
        int noOfRecords = pagingDao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 /recordsPerpage);
        request.setAttribute("listProduct", products);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/product/Home.jsp");
        dispatcher.forward(request,response);
        if(noOfPages != 0){
            response.setContentType("text/plain");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
