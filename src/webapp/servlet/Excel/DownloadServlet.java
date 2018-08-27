package webapp.servlet.Excel;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file = new File("C:\\Users\\trann\\OneDrive\\Documents\\GitHub\\QuanLySanPham3.1\\QuanLySanPham3\\excel\\a3.xlsx");
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
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
