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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
