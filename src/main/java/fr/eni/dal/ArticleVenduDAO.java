package fr.eni.dal;

import fr.eni.bo.ArticleVendu;

import java.util.List;

public interface ArticleVenduDAO {
    List<ArticleVendu> selectAll();
    ArticleVendu selectArticleById(int idArticle);
    ArticleVendu createArticle(ArticleVendu lArticle);
    void updateBidOnArticle(int bid, int idArticle);

    void updateArticle(ArticleVendu articleVendu);

    void deleteAllArticlesByUserId(int idUser);

}
