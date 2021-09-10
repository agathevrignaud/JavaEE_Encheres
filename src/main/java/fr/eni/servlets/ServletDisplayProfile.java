package fr.eni.servlets;

import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Utilisateur;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletDisplayProfile", value = "/myProfile")
public class ServletDisplayProfile extends HttpServlet {
    private static final UtilisateurManager utilisateurManager = new UtilisateurManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utilisateur lUtilisateur = utilisateurManager.getUserById(Integer.parseInt(request.getParameter("idUser")));
        request.setAttribute("userInfo", lUtilisateur);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/displayProfile.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idUser", request.getParameter("idUser"));
        System.out.println("displayProfile : " + request.getParameter("idUser"));
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/editProfile.jsp");
        rd.forward(request, response);
    }
}
