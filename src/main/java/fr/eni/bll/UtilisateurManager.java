package fr.eni.bll;

import fr.eni.bo.Utilisateur;
import fr.eni.dal.DALException;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.UtilisateurDAO;

import java.util.List;

public class UtilisateurManager {
    private final UtilisateurDAO utilisateurDAO;

    public UtilisateurManager() {
        utilisateurDAO = DAOFactory.getUtilisateurDAO();
    }

    public List<Utilisateur> getAllUsers() {
        return utilisateurDAO.selectAll();
    }

    public Utilisateur getUserById(int userId) {
        return utilisateurDAO.selectById(userId);
    }

    //TODO : supprimer la DALException (qui sort de nulle part)
    public Utilisateur addNewUser(String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse, String motDePasseConfirmation) throws BLLException, DALException {
        BLLException bllException = new BLLException();
        Utilisateur lUtilisateur = null;
        isUserInfoValid(pseudo, email, motDePasse, motDePasseConfirmation, bllException);
        if (!bllException.hasErreurs()) {
            lUtilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
            lUtilisateur = utilisateurDAO.createUser(lUtilisateur);
        } else {
            throw bllException;
        }
        return lUtilisateur;
    }

    public boolean isUserInfoValid(String pseudo, String email, String mdp, String mdpConf, BLLException bllException) throws BLLException {
        return isUsernameValid(pseudo, bllException) && isEmailValid(email, bllException) && isPasswordValid(mdp, mdpConf, bllException);
    }

    public boolean isUsernameValid(String pseudo, BLLException bllException) throws BLLException {
        List<Utilisateur> lesUtilisateurs = utilisateurDAO.selectAll();
        String regexPatternPseudo = "^[a-zA-Z0-9]*$";
        boolean isValid = true;
        if (pseudo.length() > 0 || pseudo.trim().equals("")) {
            if (!pseudo.matches(regexPatternPseudo)) {
                isValid = false;
                bllException.ajouterErreur(CodesResultatBLL.USERNAME_INVALID);
            }
            for (Utilisateur unUtilisateur : lesUtilisateurs) {
                if (unUtilisateur.getPseudo().equals(pseudo)) {
                    isValid = false;
                    bllException.ajouterErreur(CodesResultatBLL.USERNAME_ALREADY_USED);
                }
            }
        } else {
            isValid = false;
            bllException.ajouterErreur(CodesResultatBLL.USERNAME_REQUIRED);
        }
        return isValid;
    }

    public boolean isEmailValid(String email, BLLException bllException) throws BLLException {
        List<Utilisateur> lesUtilisateurs = utilisateurDAO.selectAll();
        boolean isValid = true;
        if (email.length() > 0 || email.trim().equals("")) {
            for (Utilisateur unUtilisateur : lesUtilisateurs) {
                if (unUtilisateur.getEmail().equals(email)) {
                    isValid = false;
                    bllException.ajouterErreur(CodesResultatBLL.EMAIL_ALREADY_USED);
                }
            }
        } else {
            isValid = false;
            bllException.ajouterErreur(CodesResultatBLL.EMAIL_REQUIRED);
        }
        return isValid;
    }

    public boolean isPasswordValid(String mdp, String mdpConf, BLLException bllException) throws BLLException {
        boolean isValid = true;
        String regexPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,12}$";
        if (mdp.length() > 0 || mdp.trim().equals("") || mdpConf.length() > 0 || mdpConf.trim().equals("")) {
            if (mdp.length() > 0 && mdpConf.length() > 0) {
                if (!mdp.matches(regexPattern)) {
                    isValid = false;
                    bllException.ajouterErreur(CodesResultatBLL.PWD_NOT_VALID);
                }
                if (!mdp.equals(mdpConf)) {
                    bllException.ajouterErreur(CodesResultatBLL.PWD_PWD_CONFIRMED_NOT_IDENTICAL);
                    isValid = false;
                }
            }
        } else {
            if (mdp.length() > 0 || mdp.trim().equals("")) {
                isValid = false;
                bllException.ajouterErreur(CodesResultatBLL.PWD_REQUIRED);
            }
            if (mdpConf.length() > 0 || mdpConf.trim().equals("")) {
                isValid = false;
                bllException.ajouterErreur(CodesResultatBLL.PWD_CONFIRMED_REQUIRED);
            }
        }

        return isValid ;
    }

    public void updateUserData(int userId, String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse, String motDePasseConfirmation) throws BLLException {
        BLLException bllException = new BLLException();
        try {
            isUserInfoValid(pseudo, email, motDePasse, motDePasseConfirmation, bllException);
            Utilisateur lUtilisateur = new Utilisateur(userId, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
            utilisateurDAO.updateUserData(lUtilisateur);
        } catch (BLLException bllEx) {

        }
    }

    public void updateUserAccountStatus(int idUser) {
        utilisateurDAO.updateUserAccountStatus(idUser);
    }

    public void updateUserCredit(int creditSpent, int idUser) throws Exception {
        if ((utilisateurDAO.selectById(idUser).getCredit() - creditSpent) < 0) {
            throw new Exception();
        } else {
            utilisateurDAO.updateUserCredit(utilisateurDAO.selectById(idUser).getCredit() - creditSpent, idUser);
        }
    }

    public void deleteUser(int idUser) {
        utilisateurDAO.deleteUser(idUser);
    }

    public Utilisateur authenticateUser(String login, String mdp) {
        Utilisateur lUtilisateur = null ;
        List<Utilisateur> lesUtilisateurs = utilisateurDAO.selectAll();
        for (Utilisateur unUtilisateur : lesUtilisateurs) {
            if (unUtilisateur.getPseudo().equals(login) || unUtilisateur.getEmail().equals(login)) {
                if (unUtilisateur.getMotDePasse().equals(mdp)) {
                    lUtilisateur = unUtilisateur;
                };
            }
        }
        return lUtilisateur;
    }

    public Utilisateur checkIfUserExists(String username, String email) {
        return utilisateurDAO.checkIfUserExists(username, email);
    }

    public void resetPassword(int userId) {
        String regexPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,12}$";
        //   TODO : trouver un moyen de générer un nouveau mdp selon le pattern regex
        utilisateurDAO.resetPwd(userId, "Testing456!");
    }

}
