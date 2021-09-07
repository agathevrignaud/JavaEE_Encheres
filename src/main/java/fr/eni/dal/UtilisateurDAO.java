package fr.eni.dal;

import fr.eni.bo.Utilisateur;

import java.util.List;

public interface UtilisateurDAO {
    public List<Utilisateur> selectAll();
    public Utilisateur selectById(int userId);
}
