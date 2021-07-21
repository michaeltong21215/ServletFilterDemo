package demo.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/*
@WebServlet("/v1/helloworldservlet")
*/
public class HelloWorldServlet implements Servlet {
    private Logger logger = Logger.getLogger(HelloWorldServlet.class.getName());
    private ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
        logger.info("initializing Hello World servlet application...");
    }

    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html");
        PrintWriter writer = servletResponse.getWriter();
        writer.print("<HTML><body>");
        writer.print("<div><div>service giving you hi world!</div><div>Don't forget to write an article!</div></div>");
        writer.print("</body></HTML>");
    }

    @Override
    public String getServletInfo() {
        return "This is Hello World Servlet!";
    }

    @Override
    public void destroy() {
        logger.info("destroying hello world servlet right now...");
    }
}
