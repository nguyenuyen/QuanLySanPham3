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

@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");

        int id = (int) request.getSession().getAttribute("userId");
        UserAccount userAccount = new UserAccount(phone, name);
        UserDao userDao = new UserDao();
        int kq = userDao.editUser(id, userAccount);

        request.setAttribute("isError",kq ==1 ? "1":"0");
        request.setAttribute("listUser",  userDao.findAllUser());

        RequestDispatcher dispatcher=request.getRequestDispatcher("/jsp/user/Home.jsp");
        dispatcher.forward(request,response);
        // response.sendRedirect("/AdminServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String idUser = request.getParameter("id");
        int id = Integer.parseInt(idUser);
        UserDao userDao = new UserDao();
        //Lay user qua id.
        UserAccount userAccount = userDao.getInformationUserById(id);

        //
        request.getSession().setAttribute("userId", id);

        //Tra  ve userAccount , truyen vao requet
        request.setAttribute("userAccount", userAccount);


        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/jsp/user/Edit.jsp");
        dispatcher.forward(request, response);

    }
}
