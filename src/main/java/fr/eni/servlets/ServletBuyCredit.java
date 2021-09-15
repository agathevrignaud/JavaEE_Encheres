package fr.eni.servlets;

import fr.eni.bll.CategorieManager;
import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.Categorie;
import fr.eni.bo.Utilisateur;
import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(
        urlPatterns= {
                "/creditShop"
        })
public class ServletBuyCredit extends HttpServlet {
    private static final UtilisateurManager utilisateurManager = new UtilisateurManager();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/buyCredit.jsp") ;
        requestDispatcher.forward(request, response) ;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession laSession = request.getSession();
            Utilisateur lUtilisateur = (Utilisateur) laSession.getAttribute("userInfo");
            int newBalance = utilisateurManager.updateUserCredit(Integer.parseInt(request.getParameter("creditsBought")), Integer.parseInt(request.getParameter("idUser")));
            lUtilisateur.setCredit(newBalance);
            request.setAttribute("newBalance", newBalance);
            laSession.setAttribute("userInfo", lUtilisateur);
        } catch (Exception e) {
            e.printStackTrace();
        }
        doGet(request, response);
    }

}








