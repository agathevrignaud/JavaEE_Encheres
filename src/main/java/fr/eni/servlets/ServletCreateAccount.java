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
        switch (request.getParameter("btnPressed")) {
            case "createAccount":
                //  TODO : compléter des fonctions de vérif avec code d'erreur personnalisé
                String pseudo = request.getParameter("username");
                String nom = request.getParameter("surname");
                String prenom = request.getParameter("firstname");
                String email = request.getParameter("email");
                String telephone = request.getParameter("phoneNumber");
                String rue = request.getParameter("streetName");
                String codePostal = request.getParameter("zipCode");
                String ville = request.getParameter("city");
                String motDePasse = request.getParameter("password");
                String motDePasseConfirmation = request.getParameter("confirmPwd");
                try {
                    Utilisateur lUtilisateur = utilisateurManager.addNewUser(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, motDePasseConfirmation);
                    request.setAttribute("info", String.format("L'inscription de l'utilisateur %s s'est bien effectuée", pseudo));
                    HttpSession laSession = request.getSession();
                    laSession.setAttribute("isUserLoggedIn", true );
                    laSession.setAttribute("userInfo", lUtilisateur);
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("error", "Une erreur s'est produite. Merci de revoir vos informations saisies");
                    doGet(request, response);
                }
                break;
            case "cancelCreateAccount":
                break;
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
        rd.forward(request, response);
    }
}
