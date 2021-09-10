package fr.eni.dal;

import fr.eni.bo.Utilisateur;

import java.util.List;

public interface UtilisateurDAO {
    public List<Utilisateur> selectAll();
    public Utilisateur selectById(int idUser) ;
    public void createUser(Utilisateur lUtilisateur) throws DALException;
    public void updateUserData(Utilisateur lUtilisateur);
    public void updateUserAccountStatus(int idUser);
    public void updateUserCredit(int newCredit, int idUser);
    public void deleteUser(int idUser);
    public Utilisateur checkIfUserExists(String username, String email);
    public void resetPwd(int idUser, String newPwd);
}
