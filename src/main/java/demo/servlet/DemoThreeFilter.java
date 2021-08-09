package demo.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.logging.Logger;

/*
@WebFilter(urlPatterns = "/*")
*/
public class DemoThreeFilter implements Filter {
    private Logger logger = Logger.getLogger(DemoFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("initializing filter from demo three...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("The request is going through demo three filter...");
        filterChain.doFilter(servletRequest, servletResponse);
        logger.info("postprocessing demo three...");
    }

    @Override
    public void destroy() {
        logger.info("uninitializing demo three filter...");
    }
}
