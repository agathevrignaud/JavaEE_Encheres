package fr.eni.servlets;

import fr.eni.bll.ArticleVenduManager;
import fr.eni.bll.EnchereManager;
import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Enchere;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(value = "/home")
public class ServletHome extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
        EnchereManager enchereManager = new EnchereManager();
        ArticleVenduManager articleVenduManager = new ArticleVenduManager();

        List<Enchere> lesEncheres = enchereManager.getAllEncheres();
        List<Map<String, String>> lesEncheresAffiche = new ArrayList<>();

        for (Enchere lEnchere : lesEncheres) {
            Article article = articleVenduManager.get

            Map<String, String> infoEnchere = new HashMap<String, String>();
            infoEnchere.put("nom_article", )
        }


        request.setAttribute("lesEncheres" , lesEncheresAffiche);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
