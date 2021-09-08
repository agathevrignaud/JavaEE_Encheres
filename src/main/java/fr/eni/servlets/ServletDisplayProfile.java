package fr.eni.servlets;

import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletDisplayProfile", value = "/myProfile")
public class ServletDisplayProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //  TODO : Ajouter dynamiquement l'Id de l'utilisateur (pour l'instant sur 2 pour prouver que ça marche)
        UtilisateurManager utilisateurManager = new UtilisateurManager();
        Utilisateur lUtilisateur = utilisateurManager.getUserById(2);
        System.out.println(lUtilisateur.getPseudo());
        request.setAttribute("userInfo", lUtilisateur);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/profileInformation.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
