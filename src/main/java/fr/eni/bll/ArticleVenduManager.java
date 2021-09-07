package fr.eni.bll;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import fr.eni.dal.ArticleVenduDAO;
import fr.eni.dal.CategorieDAO;
import fr.eni.dal.DAOFactory;

import java.util.List;

public class ArticleVenduManager {
    private ArticleVenduDAO articleVenduDAO;

    public ArticleVenduManager() { articleVenduDAO = DAOFactory.getArticleVenduDAO();}

    public List<ArticleVendu> getAllArticles() {
        return articleVenduDAO.selectAll();
    }
}
