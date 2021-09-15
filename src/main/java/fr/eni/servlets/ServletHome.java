package fr.eni.servlets;

import fr.eni.bll.ArticleVenduManager;
import fr.eni.bll.BLLException;
import fr.eni.bll.CategorieManager;
import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/home")
public class ServletHome extends HttpServlet {
    public final ArticleVenduManager articleVenduManager = new ArticleVenduManager();
    public final CategorieManager categorieManager = new CategorieManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Categorie> lesCategories = categorieManager.getAllCategories();

            if(request.getAttribute("lesArticles") == null){
                List<ArticleVendu> lesArticles = articleVenduManager.getAllArticles();
                request.setAttribute("lesArticles", lesArticles);
            }

            request.setAttribute("lesCategories", lesCategories);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
            rd.forward(request, response);
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<ArticleVendu> lesArticles = articleVenduManager.getAllArticles();
            List<ArticleVendu> articlesTrouveParFiltre = new ArrayList<>();

            String nomArticle = request.getParameter("nomArticle");
            String laCategorie = request.getParameter("categories");

            if (!nomArticle.equals("") && laCategorie.equals("toutes")) {
                for (ArticleVendu lArticle : lesArticles) {
                    if (lArticle.getNomArticle().contains(nomArticle)) {
                        articlesTrouveParFiltre.add(lArticle);
                    }
                }
            } else if (nomArticle.equals("") && laCategorie.equals("toutes")) {
                articlesTrouveParFiltre.addAll(lesArticles);
            }

            if (!nomArticle.equals("") && !laCategorie.equals("toutes")) {
                for (ArticleVendu lArticle : lesArticles) {
                    if (lArticle.getNomArticle().contains(nomArticle) && lArticle.getLaCategorie().getLibelle().equals(laCategorie)) {
                        articlesTrouveParFiltre.add(lArticle);
                    }
                }
            } else if (nomArticle.equals("") && !laCategorie.equals("toutes")) {
                for (ArticleVendu lArticle : lesArticles) {
                    if (lArticle.getLaCategorie().getLibelle().equals(laCategorie)) {
                        articlesTrouveParFiltre.add(lArticle);
                    }
                }
            }

            request.setAttribute("lesArticles", articlesTrouveParFiltre);
            System.out.println(nomArticle + " - " + laCategorie);

        } catch (BLLException e) {
            e.printStackTrace();
        }

        doGet(request, response);
    }
}
