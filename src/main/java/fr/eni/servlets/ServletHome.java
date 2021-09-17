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
import java.util.Arrays;
import java.util.List;

@WebServlet(urlPatterns = {
        "/encheres",
        "/filterAuctions"
})
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
                    if (enchereManager.getHighestBidByIdArticle(unArticle.getNumArticle()) != null) {
                        updateAllAuctions(enchereManager.getHighestBidByIdArticle(unArticle.getNumArticle()));
                    }
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
        List<ArticleVendu> lesArticles = getSelectedOptions(request);
        request.setAttribute("lesArticles", lesArticles);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
        rd.forward(request, response);
    }

    private List<ArticleVendu> getSelectedOptions(HttpServletRequest request) {
        List<ArticleVendu> lesArticles = new ArrayList<>();
        switch (request.getParameter("optionRdButton")) {
            case "buy":
                String[] optionsBuy = request.getParameterValues("groupBuy");
                List optionsBuyList = Arrays.asList(optionsBuy);
                if (optionsBuyList.size() > 0) {
                    if (optionsBuyList.contains("openAuctions")) {
                        //ceux sur lequel y a pas de participation
                    }
                    if (optionsBuyList.contains("participated")) {
                        //ceux sur lequel y a une participation
                    }
                    if (optionsBuyList.contains("won")) {
                        //ceux sur lequel c gagné
                    }
                } else {
                    //on affiche tout balec
                }
                break;
            case "sell":
                String[] optionsSell = request.getParameterValues("groupBuy");
                List optionsSellList = Arrays.asList(optionsSell);
                if (optionsSellList.size() > 0) {
                    if (optionsSellList.contains("myAuctionsInProgress")) {
                        //mes ventes non commencées
                    }
                    if (optionsSellList.contains("myAuctionsNotStarted")) {
                        //mes ventes en cours
                    }
                    if (optionsSellList.contains("myAuctionsFinished")) {
                        //mes ventes non terminées
                    }
                } else {
                    //On montre tout
                }
                break;

        }
        return lesArticles;
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
