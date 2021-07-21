package demo.servlet;

import utils.JWTTokenUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.logging.Logger;

/*
@WebFilter(urlPatterns = "/*")
*/
public class DemoFilter extends HttpFilter {
    private Logger logger = Logger.getLogger(DemoFilter.class.getName());
    private static final String usernameCred="USERNAME";
    private static final String passwordCred="PASSWORD";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("initializing filter...");
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        logger.info("The request is going through filter...");

        HttpSession session = req.getSession(true);

        try {
            String username = req.getHeader("username");
            String password = req.getHeader("password");
            logger.info("parameters: " + req.getParameterMap().toString());
            logger.info("username: " + username);
            logger.info("password: " + password);
            if(username.equals(usernameCred) && passwordCred.equals(password)) {
                logger.info("creating jwt token...");
                String token = JWTTokenUtil.generateToken();
                Cookie cookie = new Cookie("token", token);
                res.addCookie(cookie);
            }

            logger.info("newly created session: " + session.getId());
            Cookie cookie = new Cookie("helloworldcookie", "helloworldvalue");
            res.addCookie(cookie);

        } catch (Exception e) {
            logger.info("exception e: " + e.getLocalizedMessage());
            e.printStackTrace();
        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        logger.info("uninitializing filter...");
    }
}
