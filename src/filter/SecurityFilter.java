package filter;

import model.UserAccount;
import utils.AppUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class SecurityFilter implements Filter {

    public SecurityFilter() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();

        if (servletPath.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }

        //Kiem tra xem user da dang nhap hay chua
        UserAccount loginedUser = AppUtils.getLoginUser(request.getSession());
        if (loginedUser == null) {
            //Chuyen sang trang Login
            String requestUri = request.getRequestURI();
           // int redirectId = AppUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);
            response.sendRedirect(request.getContextPath() + "/login" );
            return;
        }

        chain.doFilter(request, response);
    }


    public void init(FilterConfig config) {

    }

    public void destroy() {
    }

}
