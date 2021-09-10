package fr.eni.servlets;

import fr.eni.bll.UtilisateurManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

            HttpSession session = request.getSession(true);

            session.setAttribute("userIsAuthenticated", true);
            session.setAttribute("authenticationError", false);
            response.sendRedirect( request.getContextPath());

        } else {
            request.getSession().setAttribute("authenticationError", true);
            doGet(request, response);
        }
    }
}
