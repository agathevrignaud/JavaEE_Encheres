package fr.eni.bll;

import fr.eni.bo.Utilisateur;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.UtilisateurDAO;

import java.util.List;

public class UtilisateurManager {
    private UtilisateurDAO utilisateurDAO;

    public UtilisateurManager() {
        utilisateurDAO = DAOFactory.getUtilisateurDAO();
    }

    public List<Utilisateur> getAllUsers() {
        return utilisateurDAO.selectAll();
    }

    public Utilisateur getUserById(int userId) {
        return utilisateurDAO.selectById(userId);
    }
}
