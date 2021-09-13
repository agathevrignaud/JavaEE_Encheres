package fr.eni.bll;

import fr.eni.bo.ArticleVendu;
import fr.eni.dal.ArticleVenduDAO;
import fr.eni.dal.DAOFactory;

import java.util.List;

public class ArticleVenduManager {

    private ArticleVenduDAO articleVenduDAO;


    public ArticleVenduManager() { articleVenduDAO = DAOFactory.getArticleVenduDAO();}

    /**
     * Retourne une liste de tous les articles
     */
    public List<ArticleVendu> getAllArticles() {
        return articleVenduDAO.selectAll();
    }


    /**
     * Supprimer un article (id)
     */
    public void deleteArticle(int id) {}

    /**
     * Séléctionner un article vendu
     */
    public ArticleVendu selectArticleVendu(int idArticleVendu){

        return articleVenduDAO.selectById(idArticleVendu);
    }

    /**
     * Ajouter un nouvel article
     */
    public void addNewArticle() {

    }
}
