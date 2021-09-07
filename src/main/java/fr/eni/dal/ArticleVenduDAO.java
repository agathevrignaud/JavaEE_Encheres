package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import java.util.List;

public interface ArticleVenduDAO {
    public List<ArticleVendu> selectAll();
    public List<ArticleVendu> selectByNameSearch(String nomArticle);
    public List<ArticleVendu> selectByCategory(int idCategory);
}
