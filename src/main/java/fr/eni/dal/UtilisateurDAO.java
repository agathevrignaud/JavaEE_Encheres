package fr.eni.dal;

import fr.eni.bo.Utilisateur;

import java.util.List;

public interface UtilisateurDAO {
    public List<Utilisateur> selectAll();
    public Utilisateur selectById(int idUser);
    public void createUser(Utilisateur lUtilisateur);
    public void updateUserData(Utilisateur lUtilisateur);
    public void updateUserAccountStatus(int idUser);
    public void deleteUser(int idUser);
}
