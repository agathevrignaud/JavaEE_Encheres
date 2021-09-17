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
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/home")
public class ServletHome extends HttpServlet {
    public final ArticleVenduManager articleVenduManager = new ArticleVenduManager();
    public final CategorieManager categorieManager = new CategorieManager();
    public final EnchereManager enchereManager = new EnchereManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Categorie> lesCategories = categorieManager.getAllCategories();

            if (request.getAttribute("lesArticles") == null) {
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

            String nomArticle = new String(request.getParameter("nomArticle").getBytes(), "UTF-8");
            String laCategorie = new String(request.getParameter("categories").getBytes(), "UTF-8");

            boolean isUserLoggedIn = request.getSession().getAttribute("isUserLoggedIn") != null && (boolean) request.getSession().getAttribute("isUserLoggedIn");

            if (!nomArticle.equals("") && laCategorie.equals("toutes")) {
                for (ArticleVendu lArticle : lesArticles) {
                    if (lArticle.getNomArticle().contains(nomArticle)) {
                        if (!isUserLoggedIn) {
                            articlesTrouveParFiltre.add(lArticle);
                        } else {
                            filtreSupplementaireModeConnecte(request, articlesTrouveParFiltre, lArticle);
                        }
                    }
                }
            } else if (nomArticle.equals("") && laCategorie.equals("toutes")) {
                for (ArticleVendu lArticle : lesArticles) {
                    if (!isUserLoggedIn) {
                        articlesTrouveParFiltre.add(lArticle);
                    } else {
                        filtreSupplementaireModeConnecte(request, articlesTrouveParFiltre, lArticle);
                    }
                }
            }

            if (!nomArticle.equals("") && !laCategorie.equals("toutes")) {
                for (ArticleVendu lArticle : lesArticles) {
                    if (lArticle.getNomArticle().contains(nomArticle) && lArticle.getLaCategorie().getLibelle().equals(laCategorie)) {
                        if (!isUserLoggedIn) {
                            articlesTrouveParFiltre.add(lArticle);
                        } else {
                            filtreSupplementaireModeConnecte(request, articlesTrouveParFiltre, lArticle);
                        }
                    }
                }
            } else if (nomArticle.equals("") && !laCategorie.equals("toutes")) {
                for (ArticleVendu lArticle : lesArticles) {
                    if (lArticle.getLaCategorie().getLibelle().equals(laCategorie)) {
                        if (!isUserLoggedIn) {
                            articlesTrouveParFiltre.add(lArticle);
                        } else {
                            filtreSupplementaireModeConnecte(request, articlesTrouveParFiltre, lArticle);
                        }
                    }
                }
            }


            request.setAttribute("lesArticles", articlesTrouveParFiltre);

        } catch (BLLException e) {
            e.printStackTrace();
        }

        doGet(request, response);
    }

    private void filtreSupplementaireModeConnecte(HttpServletRequest request, List<ArticleVendu> articlesTrouveParFiltre, ArticleVendu lArticle) {
        HttpSession laSession = request.getSession(false);
        Utilisateur lUtilisateur = (Utilisateur) laSession.getAttribute("userInfo");

        if (request.getParameter("choix") != null) {
            if (request.getParameter("choix").equals("achat")) {
                boolean enchereOuverte = Boolean.parseBoolean(request.getParameter("enchereOuverte"));
                boolean enchereEnCours = Boolean.parseBoolean(request.getParameter("enchereEnCours"));
                boolean enchereRemportees = Boolean.parseBoolean(request.getParameter("enchereRemportees"));

                if (enchereOuverte) {
                    if ("A".equals(lArticle.getEtatVente())) {
                        if (!articlesTrouveParFiltre.contains(lArticle)) {
                            articlesTrouveParFiltre.add(lArticle);
                        }
                    }
                }
                if (enchereEnCours) {
                    if ("E".equals(lArticle.getEtatVente())) {
                        List<Enchere> encheres = enchereManager.getAllEncheresByIdArticle(lArticle.getNo_article());
                        for (Enchere enchere : encheres) {
                            if (enchere.getNo_utilisateur() == lUtilisateur.getNo_utilisateur()) {
                                if (!articlesTrouveParFiltre.contains(lArticle)) {
                                    articlesTrouveParFiltre.add(lArticle);
                                }
                            }
                        }
                    }
                }
                if (enchereRemportees) {
                    if ("F".equals(lArticle.getEtatVente())) {
                        List<Enchere> encheres = enchereManager.getAllEncheresByIdArticle(lArticle.getNo_article());
                        for (Enchere enchere : encheres) {
                            if (enchere.getNo_utilisateur() == lUtilisateur.getNo_utilisateur() && enchere.getMontantEnchere() == lArticle.getPrixVente()) {
                                if (!articlesTrouveParFiltre.contains(lArticle)) {
                                    articlesTrouveParFiltre.add(lArticle);
                                }
                            }
                        }
                    }
                }
            } else if (request.getParameter("choix").equals("vente")) {
                boolean venteEnCours = Boolean.parseBoolean(request.getParameter("ventesEnCours"));
                boolean venteNonCommence = Boolean.parseBoolean(request.getParameter("ventesNonCommence"));
                boolean venteTermine = Boolean.parseBoolean(request.getParameter("ventesTermine"));


                if (lArticle.getNo_utilisateur() == lUtilisateur.getNo_utilisateur()) {

                    if (venteEnCours) {
                        if ("E".equals(lArticle.getEtatVente())) {
                            if (!articlesTrouveParFiltre.contains(lArticle)) {
                                articlesTrouveParFiltre.add(lArticle);
                            }
                        }
                    }
                    if (venteNonCommence) {
                        if ("A".equals(lArticle.getEtatVente())) {
                            if (!articlesTrouveParFiltre.contains(lArticle)) {
                                articlesTrouveParFiltre.add(lArticle);
                            }
                        }
                    }
                    if (venteTermine) {
                        if ("F".equals(lArticle.getEtatVente())) {
                            if (!articlesTrouveParFiltre.contains(lArticle)) {
                                articlesTrouveParFiltre.add(lArticle);
                            }
                        }
                    }
                }
            }
        } else {
            articlesTrouveParFiltre.add(lArticle);
        }


    }
}
