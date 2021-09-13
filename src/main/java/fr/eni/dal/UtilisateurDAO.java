package fr.eni.dal;

import fr.eni.bo.Utilisateur;

import java.util.List;

public interface UtilisateurDAO {
    public List<Utilisateur> selectAll() throws DALException;
    public Utilisateur selectById(int idUser) throws DALException;
    public Utilisateur createUser(Utilisateur lUtilisateur) throws DALException;
    public void updateUserData(Utilisateur lUtilisateur) throws DALException;
    public void updateUserAccountStatus(int idUser) throws DALException;
    public void updateUserCredit(int newCredit, int idUser) throws DALException;
    public void deleteUser(int idUser) throws DALException;
    public Utilisateur checkIfUserExists(String username, String email) throws DALException;
    public void resetPwd(int idUser, String newPwd) throws DALException;
}
