package webapp.servlet.type;

import dao.TypeDao;
import dao.UserDao;
import model.Type;
import model.UserAccount;
import utils.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/TypeServlet")
public class TypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());
        request.setAttribute("loginUser",userDao.findUser(loginUser.getEmail()));
        TypeDao typeDao = new TypeDao();
        List<Type> list = new ArrayList<>();
        list = typeDao.findAllType();
        request.setAttribute("listType",list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/type/Home.jsp");
        dispatcher.forward(request,response);
    }
}
