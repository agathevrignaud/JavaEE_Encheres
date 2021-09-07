package fr.eni.dal;

public abstract class DAOFactory {
     public static UtilisateurDAO getUtilisateurDAO()
        {
            return new UtilisateurDAOJdbc();
        }
}

