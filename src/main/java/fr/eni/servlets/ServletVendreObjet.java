package fr.eni.servlets;

import fr.eni.bll.ArticleVenduManager;
import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import fr.eni.bo.Retrait;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@WebServlet(name = "ServletVendreObjet", value = "/VendreObjet")
public class ServletVendreObjet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vendreObjet.jsp");
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
        //TODO
        categorie = new Categorie(1, request.getParameter("catArticle"));
        prix = Integer.parseInt(request.getParameter("prixArticle"));
        //TODO get parameter local date
        dateDebut = new SimpleDateFormat("dd-MMM-yyyy HH:mm.").parse("debutEnchere");
        //TODO get parameter local date
        dateFin = new SimpleDateFormat("dd-MMM-yyyy HH:mm.").parse("finEnchere");
        //TODO set state
        etatVente = "en cours";
        //TODO set idUser
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
