package fr.eni.servlets;

import fr.eni.bll.*;
import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import fr.eni.bo.Retrait;
import fr.eni.bo.Utilisateur;
import fr.eni.dal.ArticleVenduDAO;
import fr.eni.dal.CategorieDAO;
import fr.eni.dal.DAOFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import java.sql.SQLException;
import java.time.LocalDate;

import java.util.List;

@WebServlet(name = "ServletVendreObjet", value = "/VendreObjet")
public class ServletVendreObjet extends HttpServlet {
    private final static ArticleVenduManager articleVenduManager = new ArticleVenduManager();
    private final static RetraitManager retraitManager = new RetraitManager();
    private final static CategorieManager categorieManager = new CategorieManager();
    private final static UtilisateurManager utilisateurManager = new UtilisateurManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vendreObjet.jsp");

        CategorieDAO dao = DAOFactory.getCategorieDAO();
        List<Categorie> listeCategorie = dao.selectAll();
        request.setAttribute("listeCategorie", listeCategorie);


        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleVendu articleVendu = null ;
        HttpSession laSession = request.getSession();
        Utilisateur lUtilisateur = (Utilisateur) laSession.getAttribute("userInfo");


        if ("save".equals(request.getParameter("btnPressed"))) {

            try {
                articleVendu = articleVenduManager.addNewArticle(
                        request.getParameter("nomArticle"),
                        request.getParameter("descArticle"),
                        LocalDate.parse(request.getParameter("debutEnchere")),
                        LocalDate.parse(request.getParameter("finEnchere")),
                        Integer.parseInt(request.getParameter("prixArticle")),
                        "C",
                        lUtilisateur.getNumUtilisateur(),
                        categorieManager.selectCategoryById(Integer.parseInt(request.getParameter("categories")))
                );
            } catch (Exception e) {
                e.printStackTrace();
            }




            try {
                retraitManager.addNewRetrait(
                        articleVendu.getNumArticle(),
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
