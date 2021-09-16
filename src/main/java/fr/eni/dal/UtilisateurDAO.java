package fr.eni.dal;

import fr.eni.bll.BLLException;
import fr.eni.bo.Utilisateur;

import java.util.List;

public interface UtilisateurDAO {
    public List<Utilisateur> selectAll() throws BLLException;
    public Utilisateur selectById(int idUser) throws BLLException;
    public Utilisateur createUser(Utilisateur lUtilisateur) throws BLLException;
    public void updateUserData(Utilisateur lUtilisateur) throws BLLException;
    public void updateUserAccountStatus(int idUser) throws BLLException;
    public int updateUserCredit(int newCredit, int idUser) throws BLLException;
    public void deleteUser(int idUser) throws BLLException;
    public Utilisateur checkIfUserExists(String username, String email) throws BLLException;
    public void resetPwd(int idUser, String newPwd) throws BLLException;
}
