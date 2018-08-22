package webapp.servlet.Excel;

import dao.ProductDao;
import model.Product;
import model.UserAccount;
import utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import static webapp.servlet.Excel.WriteExcelProduct.writeExcel;

@WebServlet("/ExportExcelServlet")
public class ExportExcelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());
        ProductDao productDao = new ProductDao();
         List<Product> productList = productDao.findAllProductToExport(loginUser.getEmail());
        System.out.println("124324");
        String excelFilePath = "D:/a"+1+".xlsx";
        writeExcel(productList, excelFilePath);
        if(productList.size() != 0)
        {
            System.out.println("asasssd");
            String text = "xuat file thanh cong";
            response.setContentType("text/plain");
            response.getWriter().write(text);

        }
        else
        {
            String text = "khong co du lieu xuat file that bai";
            response.setContentType("text/plain");
            response.getWriter().write(text);
        }

    }
}
