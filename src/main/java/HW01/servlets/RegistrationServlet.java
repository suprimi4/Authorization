package HW01.servlets;

import HW01.model.UserProfile;
import HW01.services.AccountService;
import HW01.utils.Validator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationServlet extends HttpServlet {
    private final AccountService accountService;

    public RegistrationServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=utf-8");

        resp.getWriter().println("<!DOCTYPE html>");
        resp.getWriter().println("<html>");
        resp.getWriter().println("<head><title>Регистрация</title></head>");
        resp.getWriter().println("<body>");
        resp.getWriter().println("<h2>Регистрация</h2>");
        resp.getWriter().println("<form action='/' method='post'>");
        resp.getWriter().println("<label>Логин:</label>");
        resp.getWriter().println("<input type='text' id='username' name='username' ><br><br>");
        resp.getWriter().println("<label>Email:</label>");
        resp.getWriter().println("<input type='email' id='email' name='email'><br><br>");
        resp.getWriter().println("<label>Пароль:</label>");
        resp.getWriter().println("<input type='password' id='password' name='password'><br><br>");
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
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserProfile userProfile = new UserProfile(name, email, password);

        List<String> errors = new ArrayList<>();

        if (!Validator.isValidEmail(email)) {
            errors.add("Некорректная почта пользователя!");
        }
        if (!Validator.isValidUserName(name)) {
            errors.add("Некорректное имя пользователя!");
        }

        if (!Validator.isValidPassword(password)) {
            errors.add("Пароль меньше 6 символов");
        }

        if (Validator.isUserNameExist(accountService, userProfile, name)) {
            errors.add("Пользователь с данным именем уже существует");
        }

        if (Validator.isUserMailExits(accountService, userProfile, email)) {
            errors.add("Пользователь с данной почтой уже существует");
        }

        if (errors.isEmpty()) {
            accountService.putUserProfile(userProfile);
            resp.getWriter().println("Регистрация прошла успешно");
            resp.setStatus(HttpServletResponse.SC_OK);
            req.getServletContext().setAttribute("accountService", accountService);
            resp.sendRedirect("/login");
        } else {
            for (String error : errors) {
                resp.getWriter().println(error);
            }
            resp.getWriter().println("<a href = \"/\"> Назад </a>");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            errors.clear();
        }


    }


}
