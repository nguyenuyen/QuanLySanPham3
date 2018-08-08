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
import java.util.List;

import static webapp.servlet.Excel.WriteExcelProduct.writeExcel;

@WebServlet("/ExportExcelServlet")
public class ExportExcelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());
        ProductDao productDao = new ProductDao();
        final List<Product> productList = productDao.findAllProductToExport(loginUser.getEmail());
        System.out.println("124324");
        final String excelFilePath = "D:/a.xlsx";
        writeExcel(productList, excelFilePath);
        if(productList != null)
        {
            System.out.println("asasssd");
            String text = "xuat file thanh cong";
            response.setContentType("text/plain");
            response.getWriter().write(text);

        }
        else
        {
            String text = "xuat file that bai";
            response.setContentType("text/plain");
            response.getWriter().write(text);
        }

    }
}