package fr.eni.servlets;

import fr.eni.bll.ArticleVenduManager;
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

@WebServlet(name = "ServletVendreObjet", value = "/VendreObjet")
public class ServletVendreObjet extends HttpServlet {
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


        String nomArticle;
        String description;
        Categorie categorie;
        int prix;
        LocalDate dateDebut;
        LocalDate dateFin;
        String etatVente;
        int idUser;
        Retrait retrait;

        nomArticle = request.getParameter("nomArticle");
        description = request.getParameter("descArticle");
        //TODO set category number
        categorie = new Categorie(1, request.getParameter("libelle"));
        prix = Integer.parseInt(request.getParameter("prixArticle"));
        dateDebut = LocalDate.parse(request.getParameter("debutEnchere"));
        dateFin = LocalDate.parse(request.getParameter("finEnchere"));
        //TODO set state
        etatVente = "C";
        idUser = 1;
        //TODO Change no_article
        retrait = new Retrait(1, request.getParameter("rue"), request.getParameter("cp"), request.getParameter("ville"));
        ArticleVenduManager articleVenduManager = new ArticleVenduManager();
        ArticleVendu articleVendu = articleVenduManager.addNewArticle(nomArticle, description, dateDebut, dateFin, prix, etatVente, idUser, categorie, retrait);
        request.setAttribute("articleVendu", articleVendu);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
        rd.forward(request, response);
    }
}
