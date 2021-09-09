package fr.eni.servlets;

import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Utilisateur;
import org.apache.tomcat.util.modeler.Util;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletEditProfile", value = "/editprofile")
public class ServletEditProfile extends HttpServlet {
    private static final UtilisateurManager utilisateurManager = new UtilisateurManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idUser = 2;// TODO : Gestion dynamique à mettre en place avec la navigation
        Utilisateur lUtilisateur = utilisateurManager.getUserById(idUser);

        request.setAttribute("userInfo", lUtilisateur);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/editprofile.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }
}
