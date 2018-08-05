package webapp.servlet.type;

import dao.ProductDao;
import dao.TypeDao;
import dao.UserDao;
import model.UserAccount;
import utils.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddTypeServlet")
public class AddTypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TypeDao typeDao = new TypeDao();
        String name= request.getParameter("name");
        int kq=typeDao.addType(name);
        if(kq ==1 )
        {
            request.getSession().setAttribute("isMessage",1);
            response.sendRedirect("/TypeServlet");
        }
        else {
            throw new ServletException("HTTP Post Method Is Not Supported");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TypeDao typeDao = new TypeDao();
        UserDao userDao = new UserDao();
        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());
        request.setAttribute("loginUser",userDao.findUser(loginUser.getEmail()));
        request.setAttribute("listType",typeDao.findAllType());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/type/AddType.jsp");
        dispatcher.forward(request, response);
    }
}
