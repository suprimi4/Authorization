package HW01.servlets;

import HW01.model.UserProfile;
import HW01.services.AccountService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.stream.IntStream;


public class LoginServlet extends HttpServlet {

    private final AccountService accountService;

    public LoginServlet(AccountService accountService) {
        this.accountService = accountService;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/html;charset=utf-8");
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        UserProfile userProfile = accountService.getUserProfile(name);
        if (userProfile.isEmpty()) {
            resp.getWriter().println("Нет зарегистрированных пользователей с данным именем   \n" +
                    "<a href = \"/\"> Назад </a>");
            resp.setStatus(401);
            return;
        }

        if (userProfile.getPassword().equals(password)) {
            resp.setStatus(200);
            resp.sendRedirect("/mirror");
        } else {
            resp.setStatus(401);
            resp.getWriter().println("Неверный пароль, попробуйте еще раз.  \n" +
                    "<a href = \"/login\"> Назад </a>");

        }


    }
}
