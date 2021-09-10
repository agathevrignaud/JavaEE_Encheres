package fr.eni.servlets;

import fr.eni.bll.UtilisateurManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/signup")
public class ServletCreateAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/createAccount.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtilisateurManager utilisateurManager = new UtilisateurManager();

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
            utilisateurManager.addNewUser(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, motDePasseConfirmation);
            request.setAttribute("info", String.format("L'inscription de l'utilisateur %s s'est bien effectué", pseudo));
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Une erreur s'est produite. Merci de revoir vos informations saisies");
            doGet(request, response);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
        rd.forward(request, response);
    }
}
