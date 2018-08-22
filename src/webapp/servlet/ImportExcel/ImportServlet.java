package webapp.servlet.ImportExcel;

import dao.UserDao;
import model.UserAccount;
import utils.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.IOException;

@WebServlet("/ImportServlet")
public class ImportServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String s= request.getParameter("fileName");
//        UserDao userDao = new UserDao();
//        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());
//        request.setAttribute("loginUser", userDao.findUser(loginUser.getEmail()));
//        ExcelToDatabase excelToDatabase = new ExcelToDatabase();
//        int kq = excelToDatabase.ReadExcelInsertDatabase(loginUser.getEmail());
//        response.sendRedirect("/UserServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s= request.getParameter("fileName");
        UserDao userDao = new UserDao();
        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());
        request.setAttribute("loginUser", userDao.findUser(loginUser.getEmail()));
        ExcelToDatabase excelToDatabase = new ExcelToDatabase();
        int kq = excelToDatabase.ReadExcelInsertDatabase(loginUser.getEmail());
        response.sendRedirect("/UserServlet");

    }
}
