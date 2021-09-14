package fr.eni.servlets;

import fr.eni.bll.BLLException;
import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Utilisateur;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
    urlPatterns= {
        "/login"
    })
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
        Utilisateur lUtilisateur;
        List<Integer> listeCodesErreur = new ArrayList<>();

        if (request.getParameter("rememberMe") != null) {
            Cookie cUserName = new Cookie("cookie_user", request.getParameter("username").trim());
            Cookie cPassword = new Cookie("cookie_pwd", request.getParameter("pwd").trim());
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
                lUtilisateur = checkUser(request, listeCodesErreur);
                if (listeCodesErreur.size() > 0) {
                    request.setAttribute("listeCodesErreur",listeCodesErreur);
                    doGet(request, response);
                } else {
                    HttpSession laSession = request.getSession();
                    laSession.setAttribute("isUserLoggedIn", true );
                    laSession.setAttribute("userInfo", lUtilisateur);
                    rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
                    rd.forward(request, response);
                }
                break;
        }
    }

    public Utilisateur checkUser(HttpServletRequest request, List<Integer> listeCodesErreur) {
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        Utilisateur lUtilisateur = new Utilisateur() ;
        lUtilisateur.setPseudo(username);
        lUtilisateur.setEmail(pwd);

        if (username == null || username.trim().equals("") || pwd == null || pwd.trim().equals("")) {
            if(username == null || username.trim().equals("")) {
                listeCodesErreur.add(CodesResultatServlets.USERNAME_REQUIRED);
            }
            if(pwd == null || pwd.trim().equals("")) {
                listeCodesErreur.add(CodesResultatServlets.PWD_REQUIRED);
            }
        } else {
            try {
                lUtilisateur = utilisateurManager.authenticateUser(username, pwd);
                if (lUtilisateur == null) {
                    listeCodesErreur.add(CodesResultatServlets.USER_NOT_AUTHENTICATED);
                }
            } catch (BLLException e) {
                listeCodesErreur.add(CodesResultatServlets.USER_NOT_AUTHENTICATED);
            }
        }
        return lUtilisateur;
    }
}
