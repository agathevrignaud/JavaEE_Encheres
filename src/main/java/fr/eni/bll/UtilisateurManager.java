package fr.eni.bll;

import fr.eni.bo.Utilisateur;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.UtilisateurDAO;

public class UtilisateurManager {
    private UtilisateurDAO utilisateurDAO;

    public UtilisateurManager() {
        utilisateurDAO = DAOFactory.getUtilisateurDAO();
    }

    public Utilisateur lecture()
    {
        return utilisateurDAO.read();
    }
}
