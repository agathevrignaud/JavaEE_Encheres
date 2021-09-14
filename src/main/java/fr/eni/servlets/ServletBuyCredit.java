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

    /* TODO : Ajouter un doFilter pour que seul un utilisateur connecté puisse accéder à cette page !
    public  void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        vérifier si l'utilisateur qui navigue est connecté (isAuthenticated)
        &&
        vérifier si celui qui achète est le proprio du compte
        -> msg d'erreur "accès refusé : touche à ton cul"

        filterChain.doFilter(request, response) ;
    }
    */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("firstName", request.getParameter("firstName"));
        request.setAttribute("idUser", request.getParameter("idUser"));

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








