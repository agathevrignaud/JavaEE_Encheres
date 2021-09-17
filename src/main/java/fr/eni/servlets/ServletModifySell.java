package fr.eni.servlets;

import fr.eni.bll.ArticleVenduManager;
import fr.eni.bll.CategorieManager;
import fr.eni.bll.RetraitManager;
import fr.eni.bll.UtilisateurManager;
import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import fr.eni.bo.Retrait;
import fr.eni.bo.Utilisateur;
import fr.eni.dal.CategorieDAO;
import fr.eni.dal.DAOFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "ServletModifySell", value = "/modifysell")
public class ServletModifySell extends HttpServlet {
    private final static ArticleVenduManager articleVenduManager = new ArticleVenduManager();
    private final static CategorieManager categorieManager = new CategorieManager();
    private final static RetraitManager retraitManager = new RetraitManager();
    private final static UtilisateurManager utilisateurManager = new UtilisateurManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        ArticleVenduManager articleVenduManager = new ArticleVenduManager();
        RetraitManager retraitManager = new RetraitManager();

        CategorieDAO dao = DAOFactory.getCategorieDAO();
        List<Categorie> listeCategorie = dao.selectAll();
        request.setAttribute("listeCategorie", listeCategorie);

        //TODO: récupérer l'id de l'article sur lequel l'utilisateur à cliqué avant d'arriver sur la page d'annulation
        ArticleVendu articleVendu = articleVenduManager.getArticleById(1);
        Retrait retrait = retraitManager.getRetraitById(articleVendu.getNumArticle());

        request.setAttribute("articleVenduManager",articleVenduManager);
        request.setAttribute("articleVendu", articleVendu);
        request.setAttribute("retraitManager", retraitManager);
        request.setAttribute("retrait", retrait);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modifySell.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession laSession = request.getSession();
        Utilisateur lUtilisateur = (Utilisateur) laSession.getAttribute("userInfo");

        if ("save".equals(request.getParameter("btnPressed"))) {
            try {
                articleVenduManager.updateArticle(
                        request.getParameter("nomArticle"),
                        request.getParameter("descArticle"),
                        LocalDate.parse(request.getParameter("debutEnchere")),
                        LocalDate.parse(request.getParameter("finEnchere")),
                        Integer.parseInt(request.getParameter("prixArticle")),
                        "C",
                        categorieManager.selectCategoryById(Integer.parseInt(request.getParameter("categories"))),
                        utilisateurManager.getUserById(lUtilisateur.getNumUtilisateur())
                );
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                retraitManager.updateRetrait(

                articleVenduManager.getArticleById(1),
                        request.getParameter("rue"),
                        request.getParameter("cp"),
                        request.getParameter("ville")
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
            rd.forward(request, response);
        }
    }
}
