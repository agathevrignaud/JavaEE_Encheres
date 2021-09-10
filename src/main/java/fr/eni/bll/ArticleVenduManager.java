package fr.eni.bll;

import fr.eni.bo.ArticleVendu;
import fr.eni.dal.ArticleVenduDAO;
import fr.eni.dal.DAOFactory;

import java.util.List;

public class ArticleVenduManager {
    private ArticleVenduDAO articleVenduDAO;

    public ArticleVenduManager() { articleVenduDAO = DAOFactory.getArticleVenduDAO();}

    public List<ArticleVendu> getAllArticles() {
        return articleVenduDAO.selectAll();
    }

    public void deleteArticle(int id) {}

    public List<ArticleVendu> selectById(int id){

        return null;
    }

    public void addNewArticle() {

    }
}
