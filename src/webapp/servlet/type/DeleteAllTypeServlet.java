package webapp.servlet.type;

import dao.TypeDao;
import model.UserAccount;
import utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteAllTypeServlet")
public class DeleteAllTypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TypeDao typeDao = new TypeDao();
        UserAccount loginUser = AppUtils.getLoginUser(request.getSession());
        String[] value =  request.getParameterValues("check");
        int s = typeDao.deleteAllType(value);
        if(s ==1 )
            response.sendRedirect(request.getContextPath() +"/TypeServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
