package fr.eni.servlets;

import fr.eni.bll.UtilisateurManager;

import javax.security.sasl.AuthenticationException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/login")
public class ServletLogin extends HttpServlet {
    private static final UtilisateurManager utilisateurManager = new UtilisateurManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = "", pwd = "", rememberMe="";
        if (request.getCookies() != null) {
            for (Cookie unCookie : request.getCookies()) {
                if(unCookie.getName().equals("cookie_user")) {
                    username = unCookie.getValue();
                }
                if(unCookie.getName().equals("cookie_pwd")){
                    pwd = unCookie.getValue();
                }
            }
            request.setAttribute("cUsername", username);
            request.setAttribute("cPwd", pwd);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isAuthenticated = utilisateurManager.authenticateUser(
                request.getParameter("username"),
                request.getParameter("password")
        );

        if (request.getParameter("rememberMe") != null) {
            String rememberMe = request.getParameter("remember");

            System.out.println("remember : " + rememberMe);

            Cookie cUserName = new Cookie("cookie_user", request.getParameter("username").trim());
            Cookie cPassword = new Cookie("cookie_pwd", request.getParameter("password").trim());
            cUserName.setMaxAge(60 * 60 * 24 * 15); // 15 days
            cPassword.setMaxAge(60 * 60 * 24 * 15);
            response.addCookie(cUserName);
            response.addCookie(cPassword);
        }
        HttpSession laSession = request.getSession();
        laSession.setAttribute("session_user", request.getParameter("pseudo").trim());

        if (isAuthenticated) {
            request.getSession().setAttribute("isAuthenticated", true);
            RequestDispatcher rd;
            rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
            rd.forward(request, response);
        } else {
            request.getSession().setAttribute("authenticationError", true);
            doGet(request, response);
        }

    }
}
