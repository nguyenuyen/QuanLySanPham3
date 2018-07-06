package webapp.servlet.user;

import dao.UserDao;
import model.UserAccount;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

       // response.sendRedirect("/jsp/user/Add.jsp");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String sdt = request.getParameter("phone");
        String pass = request.getParameter("pass");
        UserAccount userAccount = new UserAccount(email, pass, sdt, name);
        UserDao userDao = new UserDao();

        int kq = userDao.addUser(userAccount);
        if (  kq == 1)
        {
            response.sendRedirect("/AdminServlet");
        }
        else {
            // Chuyen sang trang loi
          response.sendRedirect("/ErrorServlet");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/user/Add.jsp");
        dispatcher.forward(request, response);
    }
}
