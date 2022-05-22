package taxi.controller;

import taxi.lib.Injector;
import taxi.model.Driver;
import taxi.service.AuthenticationService;
import java.io.IOException;
import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private static Injector injector = Injector.getInstance("taxi");
    private final AuthenticationService authenticationService = (AuthenticationService)
            injector.getInstance(AuthenticationService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String driverLogin = req.getParameter("login");
        String driversPassword = req.getParameter("password");
        try {
            Driver driver = authenticationService.login(driverLogin, driversPassword);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }
}
