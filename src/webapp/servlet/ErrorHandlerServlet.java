package webapp.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/error")
public class ErrorHandlerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");


        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

        if (requestUri == null) {
            requestUri = "Unknown";
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String title = "Error/Exception Information";
        String docType = "<!DOCTYPE html>\n";
        out.println(docType
                + "<html>\n" + "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>" + title + "</title></head>\n" + "<body>");

        if (throwable == null && statusCode == null) {
            out.println("<h3>Error Information Is Missing</h3>");

        } else if (statusCode != 500) {
            out.write("<h3>Error Details</h3>");
            out.write("<ul><li><strong>Status Code</strong>?= " + statusCode + "</li>");
            out.write("<li><strong>Requested URI</strong>?= " + requestUri + "</li></ul>");
        } else {
            out.println("<h3>Exception Details</h3>");
            out.println("<ul><li><strong>Servlet Name</strong>?= " + servletName + "</li>");
            out.println("<li><strong>Exception Name</strong>?= " + throwable.getClass().getName() + "</li>");
            out.println("<li><strong>Requested URI</strong>?= " + requestUri + "</li>");
            out.println("<li><strong>Exception Message</strong>?= " + throwable.getMessage() + "</li></ul>");
        }
        out.println("<div> </div>Click <a id=\"homeUrl\" href=\"/jsp/login.jsp\">login</a>");
        out.println("</body>\n</html>");
        out.close();
    }

}


