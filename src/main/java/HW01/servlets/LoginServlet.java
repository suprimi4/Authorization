package HW01.servlets;

import HW01.model.UserProfile;
import HW01.services.AccountService;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginServlet extends HttpServlet {

    private final AccountService accountService = (AccountService) getServletContext().getAttribute("accountService");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=utf-8");

        resp.getWriter().println("<!DOCTYPE html>");
        resp.getWriter().println("<html>");
        resp.getWriter().println("<head><title>Авторизация</title></head>");
        resp.getWriter().println("<body>");
        resp.getWriter().println("<h2>Авторизация</h2>");
        resp.getWriter().println("<form action='/' method='post'>");
        resp.getWriter().println("<label>Логин:</label>");
        resp.getWriter().println("<input type='text' id='username' name='username' required><br><br>");
        resp.getWriter().println("<label>Пароль:</label>");
        resp.getWriter().println("<input type='password' id='password' name='password' required><br><br>");
        resp.getWriter().println("<button type='submit'>Зарегистрироваться</button>");
        resp.getWriter().println("</form>");
        resp.getWriter().println("</body>");
        resp.getWriter().println("</html>");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=utf-8");
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        UserProfile userProfile = accountService.getUserProfile(name);
        if (userProfile == null) {
            resp.getWriter().println("Нет зарегистрированных пользователей с данным именем   \n" +
                    "<a href = \"/\"> Назад </a>");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        if (userProfile.getPassword().equals(password)) {
            String key = req.getSession().toString();
            accountService.putUserSession(key, userProfile);
        } else {
            resp.getWriter().println("Неверный пароль, попробуйте еще раз.  \n" +
                    "<a href = \"/\"> Назад </a>");
        }


    }
}
