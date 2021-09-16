package fr.eni.dal;

import fr.eni.bo.ArticleVendu;

import java.util.List;

public interface ArticleVenduDAO {
    List<ArticleVendu> selectAll();
    ArticleVendu selectArticleById(int idArticle);
    ArticleVendu createArticle(ArticleVendu lArticle);
}
