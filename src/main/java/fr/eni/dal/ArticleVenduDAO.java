package fr.eni.dal;

import fr.eni.bo.ArticleVendu;

import java.util.List;

public interface ArticleVenduDAO {
    public List<ArticleVendu> selectAll();

    public ArticleVendu createArticle(ArticleVendu lArticle);
}
