package fr.eni.servlets;

import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Utilisateur;

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
                if(unCookie.getName().equals("cookie_rememberMe")){
                    rememberMe = unCookie.getValue();
                }
            }
            request.setAttribute("cUsername", username);
            request.setAttribute("cPwd", pwd);
            request.setAttribute("cRemember", rememberMe);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd;

        if (request.getParameter("rememberMe") != null) {
            Cookie cUserName = new Cookie("cookie_user", request.getParameter("username").trim());
            Cookie cPassword = new Cookie("cookie_pwd", request.getParameter("password").trim());
            Cookie cRemember = new Cookie("cookie_rememberMe", request.getParameter("rememberMe").trim());
            cUserName.setMaxAge(60 * 60 * 24 * 15); // 15 jours
            cPassword.setMaxAge(60 * 60 * 24 * 15);
            cRemember.setMaxAge(60 * 60 * 24 * 15);
            response.addCookie(cUserName);
            response.addCookie(cPassword);
            response.addCookie(cRemember);
        }

        switch (request.getParameter("btnPressed")) {
            case "createAccount":
                rd = request.getRequestDispatcher("/WEB-INF/createAccount.jsp");
                rd.forward(request, response);
                break;
            case "login":

                Utilisateur lUtilisateur = utilisateurManager.authenticateUser(
                        request.getParameter("username"),
                        request.getParameter("password")
                );

                if (lUtilisateur != null) {
                    HttpSession laSession = request.getSession();
                    laSession.setAttribute("isUserLoggedIn", true );
                    laSession.setAttribute("userInfo", lUtilisateur);
                    rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
                    rd.forward(request, response);
                } else {
                    request.getSession().setAttribute("authenticationError", true);
                    doGet(request, response);
                }
                break;
        }
    }






}
