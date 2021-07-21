package demo.servlet;

import utils.JWTTokenUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebServlet("/httpservlet")
public class HttpServletDemo extends HttpServlet {
    private Logger logger = Logger.getLogger(HttpServletDemo.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("text/html");
            PrintWriter writer = resp.getWriter();
            writer.print("<HTML><body>");
            writer.print("<div>Http servlet demo printing...</div>");
            writer.print("</body></HTML>");
            logger.info("received the request.");
            String token = JWTTokenUtil.generateToken();
            Cookie cookie = new Cookie("token", token);
            resp.addCookie(cookie);
            resp.sendRedirect("success.jsp");
        } catch (Exception e) {
            logger.info("error: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession(true);
        session.setMaxInactiveInterval(1);
        Cookie cookie = new Cookie("current cookie", "current value");
        resp.addCookie(cookie);
        resp.setContentType("text/html");
        resp.sendRedirect("success.jsp");
    }

}
