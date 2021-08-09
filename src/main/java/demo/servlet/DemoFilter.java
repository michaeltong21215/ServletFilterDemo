package demo.servlet;

import utils.JWTTokenUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;

/*
@WebFilter(urlPatterns = "/*")
*/
public class DemoFilter extends HttpFilter {
    private Logger logger = Logger.getLogger(DemoFilter.class.getName());
    private static final String usernameCred="USERNAME";
    private static final String passwordCred="PASSWORD";
    private static String currentUserSessionId="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1ZCI6IndlYiIsImlhdCI6MTYyNzE4NTY5N30.cAU3sfIDw49YhE-tvwAtHSKHYFtnLkYt6TjHHb7gVP0-aPPNIGFiZa5cTLMYGpd2dxEfA1bvvDilQQxI4dWAjA";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("initializing filter...");
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        logger.info("The request is going through filter...");

        HttpSession session = req.getSession(true);

        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String usersessionid = req.getParameter("usersessionid");
            Cookie[] currentCookies = req.getCookies();
            Optional<Cookie> tokenCookie = Arrays.stream(currentCookies).filter(cookie -> cookie.getName().equals("token")).findFirst();
            logger.info("username: " + username);
            logger.info("password: " + password);
            logger.info("user session id: " + usersessionid);
            logger.info("is password same as passwordCred: " + password.equals(passwordCred));
            if(username.equals(usernameCred) && passwordCred.equals(password)) {
                if(!tokenCookie.get().getValue().equals(usersessionid)) {
                    logger.info("creating new jwt token...");
                    String token = JWTTokenUtil.generateToken();
                    Cookie cookie = new Cookie("token", token);
                    res.addCookie(cookie);
                } else {
                    logger.info("valid user!");
                }
            } else {
                logger.info("unauthorized user");
            }
        } catch (Exception e) {
            logger.info("exception e: " + e.getLocalizedMessage());
            e.printStackTrace();
        }

        chain.doFilter(req, res);
        logger.info("postprocessing DemoFilter...");
    }

    @Override
    public void destroy() {
        logger.info("uninitializing filter...");
    }
}
