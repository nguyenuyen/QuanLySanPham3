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

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idUser = request.getParameter("id");
        int id = Integer.parseInt(idUser);
        UserDao userDao = new UserDao();
        int kq = userDao.deleteUser(id);
        request.setAttribute("isErrorDelete", kq == 1 ? "1" : "0");
        request.setAttribute("listUser",  userDao.findAllUser());
        RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/user/Home.jsp");
        dispatcher.forward(request,response);

    }
}
