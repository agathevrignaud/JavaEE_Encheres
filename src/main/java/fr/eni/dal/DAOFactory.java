package fr.eni.dal;

public abstract class DAOFactory {
    public static UtilisateurDAO getUtilisateurDAO()
        {
            return new UtilisateurDAOJdbc();
        }
    public static CategorieDAO getCategorieDAO() { return new CategorieDAOJdbc();}
    public static ArticleVenduDAO getArticleVenduDAO() { return new ArticleVenduDAOJdbc();}
}

