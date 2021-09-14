package fr.eni.servlets;

import fr.eni.bll.BLLException;
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
        HttpSession laSession = request.getSession();
        Utilisateur lUtilisateurCo = (Utilisateur) laSession.getAttribute("userInfo");
        Utilisateur lUtilisateurVu = null;
        
        try {
            lUtilisateurVu = utilisateurManager.getUserById(Integer.parseInt(request.getParameter("idUser")));
            request.setAttribute("userInfo", lUtilisateurVu);
        } catch (BLLException e) {
            e.printStackTrace();
        }

        if (lUtilisateurCo != null) {
            if (lUtilisateurVu.getNo_utilisateur() == lUtilisateurCo.getNo_utilisateur()) {
                request.setAttribute("isItYou", true);
            }
        }

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
