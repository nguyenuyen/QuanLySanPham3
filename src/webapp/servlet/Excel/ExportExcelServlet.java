package webapp.servlet.Excel;

import dao.ProductDao;
import model.Product;
import model.UserAccount;
import utils.AppUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;

import static webapp.servlet.Excel.WriteExcelProduct.writeExcel;

@WebServlet("/ExportExcelServlet")
public class ExportExcelServlet extends HttpServlet {
    public int i = 1;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());
        ProductDao productDao = new ProductDao();
        List<Product> productList = productDao.findAllProductToExport(loginUser.getEmail());
        System.out.println("124324");
        while (i > 0) {
            ++i;
            String excelFilePath = "C:\\Users\\trann\\OneDrive\\Documents\\GitHub\\QuanLySanPham3.1\\QuanLySanPham3\\excel\\a" + i + ".xlsx";
            writeExcel(productList, excelFilePath);

            OutputStream out = response.getOutputStream();
            response.setContentType("text/html");
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", "attachment; filename=excel.xlsx");
            FileInputStream in = new FileInputStream(excelFilePath);
            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.flush();
            break;
        }

        if (productList.size() > 0)
        {
            String text = "xuat file thanh cong";
            response.setContentType("text/plain");
            response.getWriter().write(text);

        } else

        {
            String text = "khong co du lieu xuat file that bai";
            response.setContentType("text/plain");
            response.getWriter().write(text);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
