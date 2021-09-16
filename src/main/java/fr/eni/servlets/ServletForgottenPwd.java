package fr.eni.servlets;

import fr.eni.bll.BLLException;
import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/forgottenPwd")
public class ServletForgottenPwd extends HttpServlet {
    private static final UtilisateurManager utilisateurManager = new UtilisateurManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/forgottenPwd.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Integer> listeCodesErreur = new ArrayList<>();
        Utilisateur lUtilisateur = checkUser(request, listeCodesErreur);

        if (listeCodesErreur.size() > 0) {
            request.setAttribute("listeCodesErreur", listeCodesErreur);
            request.setAttribute("isUserInDb", false);
            doGet(request, response);
        } else {
            try {
                utilisateurManager.resetPassword(lUtilisateur.getNumUtilisateur());
                request.setAttribute("isUserInDb", true);
            } catch (BLLException e) {
                e.printStackTrace();
                request.setAttribute("isUserInDb", false);
                request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
            }
        }
        doGet(request, response);
    }

    public Utilisateur checkUser(HttpServletRequest request, List<Integer> listeCodesErreur) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        Utilisateur lUtilisateur = new Utilisateur() ;
        lUtilisateur.setPseudo(username);
        lUtilisateur.setEmail(email);
        if (username == null || username.trim().equals("") ||email == null || email.trim().equals("")) {
            if(username == null || username.trim().equals("")) {
                listeCodesErreur.add(CodesResultatServlets.USERNAME_REQUIRED);
            }
            if(email == null || email.trim().equals("")) {
                listeCodesErreur.add(CodesResultatServlets.EMAIL_REQUIRED);
            }
        } else {
            try {
                utilisateurManager.checkIfUserExists(username, email);
            } catch (BLLException e) {
                listeCodesErreur.add(CodesResultatServlets.USER_NOT_FOUND);
            }
        }
        return lUtilisateur;
    }
}
