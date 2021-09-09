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
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/createaccount.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pseudo = request.getParameter("pseudo");
        String nom = request.getParameter("name");
        String prenom = request.getParameter("surname");
        String email = request.getParameter("mail");
        String telephone = request.getParameter("phone");
        String rue = request.getParameter("street");
        String codePostal = request.getParameter("zip");
        String ville = request.getParameter("city");
        String motDePasse = request.getParameter("password");
        String motDePasseConfirmation = request.getParameter("confirmPwd");

        UtilisateurManager utilisateurManager = new UtilisateurManager();
        try {
            utilisateurManager.addNewUser(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, motDePasseConfirmation);
        } catch (Exception e) {
            e.printStackTrace();
        }


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
        rd.forward(request, response);
    }
}
