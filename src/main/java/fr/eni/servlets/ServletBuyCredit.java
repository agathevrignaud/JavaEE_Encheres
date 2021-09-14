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
            utilisateurManager.updateUserCredit(Integer.parseInt(request.getParameter("creditsBought")), Integer.parseInt(request.getParameter("idUser")));
            request.setAttribute("newBalance", utilisateurManager.getUserById(Integer.parseInt(request.getParameter("idUser"))).getCredit());
        } catch (Exception e) {
            e.printStackTrace();
        }
        doGet(request, response);
    }

}








