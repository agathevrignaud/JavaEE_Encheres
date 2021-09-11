package fr.eni.servlets;

import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Utilisateur;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(value = "/signup")
public class ServletCreateAccount extends HttpServlet {
    private static final UtilisateurManager utilisateurManager = new UtilisateurManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/createAccount.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Integer> listeCodesErreur = new ArrayList<>();

        switch (request.getParameter("btnPressed")) {
            case "createAccount":
                //  TODO : compléter des fonctions de vérif avec code d'erreur personnalisé
                String pseudo = checkUsername(request, listeCodesErreur);
                System.out.print(listeCodesErreur.size());
                String nom = request.getParameter("surname");
                String prenom = request.getParameter("firstname");
                String email = request.getParameter("email");
                String telephone = request.getParameter("phoneNumber");
                String rue = request.getParameter("streetName");
                String codePostal = request.getParameter("zipCode");
                String ville = request.getParameter("city");
                String motDePasse = request.getParameter("password");
                String motDePasseConfirmation = request.getParameter("confirmPwd");

                if (listeCodesErreur.size()>0) {
                    Collections.sort(listeCodesErreur);
                    request.setAttribute("listeCodesErreur",listeCodesErreur);
                    doGet(request, response);
                } else {
                    try {
                        Utilisateur lUtilisateur = utilisateurManager.addNewUser(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, motDePasseConfirmation);
                        HttpSession laSession = request.getSession();
                        laSession.setAttribute("isUserLoggedIn", true );
                        laSession.setAttribute("userInfo", lUtilisateur);
                        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
                        rd.forward(request, response);
                    } catch (Exception e) {
                        e.printStackTrace();
                        doGet(request, response);
                    }
                }
                break;
            case "cancelCreateAccount":
                break;
        }


    }


    private String checkUsername(HttpServletRequest request, List<Integer> listeCodesErreur) {
        String username;
        username = request.getParameter("username");
        if(username == null || username.trim().equals("")) {
            listeCodesErreur.add(CodesResultatServlets.USERNAME_REQUIRED);
        }
        return username;
    }
}
