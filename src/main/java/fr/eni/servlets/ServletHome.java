package fr.eni.servlets;

import fr.eni.bll.ArticleVenduManager;
import fr.eni.bll.BLLException;
import fr.eni.bll.CategorieManager;
import fr.eni.bll.EnchereManager;
import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import fr.eni.bo.Enchere;
import fr.eni.bo.Utilisateur;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/encheres")
public class ServletHome extends HttpServlet {
    public static final ArticleVenduManager articleVenduManager = new ArticleVenduManager();
    public static final CategorieManager categorieManager = new CategorieManager();
    public static EnchereManager enchereManager = new EnchereManager();
    final String NOT_STARTED = "P";
    final String IN_PROGRESS = "E";
    final String FINISHED = "F";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            for (ArticleVendu unArticle : articleVenduManager.getAllArticles()) {
                if (unArticle.getEtatVente().equals(IN_PROGRESS) || unArticle.getEtatVente().equals(NOT_STARTED)) {
                    updateAllAuctions(enchereManager.getHighestBidByIdArticle(unArticle.getNumArticle()));
                }
            }
            List<ArticleVendu> lesArticles = articleVenduManager.getAllArticles();
            request.setAttribute("lesArticles", lesArticles);
            List<Categorie> lesCategories = categorieManager.getAllCategories();
            request.setAttribute("lesCategories", lesCategories);
        } catch (BLLException e) {
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // TODO : gérer la recherche

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
        rd.forward(request, response);
    }

    private void updateAllAuctions(Enchere lEnchere) {
        if (LocalDate.now().isAfter(lEnchere.getlArticle().getDateFinEnchere())) {
            try {
                articleVenduManager.updateAuctionStatus(FINISHED, lEnchere.getlArticle().getNumArticle());
            } catch (BLLException e) {
                e.printStackTrace();
            }
        }
        if (LocalDate.now().isAfter(lEnchere.getlArticle().getDateDebutEnchere())) {
            try {
                articleVenduManager.updateAuctionStatus(IN_PROGRESS, lEnchere.getlArticle().getNumArticle());
            } catch (BLLException e) {
                e.printStackTrace();
            }
        }

    }
}
