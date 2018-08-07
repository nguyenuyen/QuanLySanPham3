package webapp.servlet.product;

import dao.ProductDao;
import model.UserAccount;
import utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/DeleteAllProductServlet")
public class DeleteAllProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProductDao productDao = new ProductDao();
        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());
        String value =  request.getParameter("product_id");
        String s = productDao.deleteAllProduct(value);
        if(s != null )
        {
            response.setContentType("data/plain");
            response.getWriter().write(s);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {



    }
}
