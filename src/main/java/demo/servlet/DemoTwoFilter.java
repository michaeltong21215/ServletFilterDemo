package demo.servlet;

import javax.servlet.*;
import java.io.IOException;
import javax.servlet.annotation.WebFilter;
import java.util.logging.Logger;

@WebFilter(urlPatterns="/*")
public class DemoTwoFilter implements Filter{
    private Logger logger = Logger.getLogger(DemoFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("initializing filter from demo two...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("The request is going through demo two filter...");
       filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("uninitializing demo two filter...");
    }
}
