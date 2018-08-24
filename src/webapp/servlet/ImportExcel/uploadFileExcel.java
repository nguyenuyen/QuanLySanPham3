package webapp.servlet.ImportExcel;

import dao.UserDao;
import model.UserAccount;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.AppUtils;
import webapp.servlet.product.UserServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.*;
import java.util.List;

@WebServlet("/uploadFileExcel")

public class uploadFileExcel extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ServletFileUpload.isMultipartContent(request)) {
            request.setAttribute("message", "Error: Form tag must has 'enctype=multipart/form-data' attribute");
        }else{
            try {
                List<FileItem> fileItem =  new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for(FileItem item : fileItem){
                    if(!item.isFormField()) {
                        String name = item.getName();
                        InputStream inputStream = new ByteArrayInputStream(item.get());
                        ExcelToDatabase excelToDatabase = new ExcelToDatabase();
                        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());
                        excelToDatabase.ReadExcelInsertDatabase(loginUser.getEmail(), inputStream);
                        response.sendRedirect("/UserServlet");
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
