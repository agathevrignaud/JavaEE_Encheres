package fr.eni.dal;

public abstract class DAOFactory {
    public static UtilisateurDAO getUtilisateurDAO()
        {
            return new UtilisateurDAOJdbc();
        }
    public static CategorieDAO getCategorieDAO() { return new CategorieDAOJdbc();}
    public static ArticleVenduDAO getArticleVenduDAO() { return new ArticleVenduDAOJdbc();}
    public static EnchereDAO getEnchereDAO() { return new EnchereDAOJdbc(); };
    public static RetraitDAO getRetraitDAO() { return new RetraitDAOJdbc(); };
}

