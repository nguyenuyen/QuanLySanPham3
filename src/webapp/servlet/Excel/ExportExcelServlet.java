package webapp.servlet.Excel;

import dao.ProductDao;
import model.Product;
import model.UserAccount;
import utils.AppUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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
            File file = new File(excelFilePath);

            ServletOutputStream stream = null;
            BufferedInputStream buf = null;
            try {
                stream = response.getOutputStream();
                // set response headers
                response.setContentType("application/vnd.ms-excel");
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.addHeader("Content-Disposition", "attachment; filename=excel.xlsx");
                response.setContentLength((int) file.length());
                buf = new BufferedInputStream(new FileInputStream(file));
                int readBytes = 0;
                while ((readBytes = buf.read()) != -1)
                    stream.write(readBytes);
            } finally {
                if (stream != null)
                    stream.flush();
                stream.close();
                if (buf != null)
                    buf.close();
            }
            break;
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
