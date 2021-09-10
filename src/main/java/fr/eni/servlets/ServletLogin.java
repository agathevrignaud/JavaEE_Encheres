package fr.eni.servlets;

import fr.eni.bll.UtilisateurManager;

import javax.security.sasl.AuthenticationException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/login")
public class ServletLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pseudo = request.getParameter("pseudo");
        String password = request.getParameter("password");

        UtilisateurManager utilisateurManager = new UtilisateurManager();
        RequestDispatcher rd;
        boolean isAuthenticated = utilisateurManager.authenticateUser(pseudo, password);
        if (isAuthenticated) {
            rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
            request.getSession().setAttribute("isAuthenticated", true);

            rd.forward(request, response);
        } else {
            request.getSession().setAttribute("authenticationError", true);
            doGet(request, response);
        }
    }
}
