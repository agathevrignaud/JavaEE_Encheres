package fr.eni.servlets;

import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//  TODO : Ajouter dynamiquement l'Id de l'utilisateur (pour l'instant sur 2 pour prouver que ça marche)

@WebServlet(name = "ServletDisplayProfile", value = "/myProfile")
public class ServletDisplayProfile extends HttpServlet {
    private static final UtilisateurManager utilisateurManager = new UtilisateurManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utilisateur lUtilisateur = utilisateurManager.getUserById(2);
        request.setAttribute("userInfo", lUtilisateur);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/profileInformation.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO : Rediriger vers la page de modification du profil
        doGet(request, response);
    }
}
