package fr.eni.bll;

import fr.eni.bo.Utilisateur;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.UtilisateurDAO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilisateurManager {
    private final UtilisateurDAO utilisateurDAO;
    private static final Logger myLogger = Logger.getLogger("LogsBLL");

    public UtilisateurManager() {
        utilisateurDAO = DAOFactory.getUtilisateurDAO();
    }

    public List<Utilisateur> getAllUsers() throws BLLException {
        return utilisateurDAO.selectAll();
    }

    public Utilisateur getUserById(int userId) throws BLLException {
        return utilisateurDAO.selectById(userId);
    }

    public Utilisateur addNewUser(String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse, String motDePasseConfirmation) throws BLLException {
        BLLException bllException = new BLLException();
        Utilisateur lUtilisateur;
        isUserInfoValid(pseudo, email, motDePasse, motDePasseConfirmation, bllException);
        if (!bllException.hasErreurs()) {
            lUtilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
            lUtilisateur = utilisateurDAO.createUser(lUtilisateur);
        } else {
            myLogger.log(Level.WARNING,"Erreur lors de la création de l'utilisateur " + pseudo, bllException);
            throw bllException;
        }
        return lUtilisateur;
    }

    public void isUserInfoValid(String pseudo, String email, String mdp, String mdpConf, BLLException bllException) throws BLLException {
        isUsernameValid(pseudo, bllException);
        isEmailValid(email, bllException);
        isPasswordValid(mdp, mdpConf, bllException);
    }

    public void isUsernameValid(String pseudo, BLLException bllException) throws BLLException {
        List<Utilisateur> lesUtilisateurs = utilisateurDAO.selectAll();
        String regexPatternPseudo = "^[a-zA-Z0-9]*$";
        if (pseudo.length() > 0 || pseudo.trim().equals("")) {
            if (!pseudo.matches(regexPatternPseudo)) {
                myLogger.log(Level.WARNING,"Format du nom d'utilisateur invalide",bllException);
                bllException.ajouterErreur(CodesResultatBLL.USERNAME_INVALID);
            }
            for (Utilisateur unUtilisateur : lesUtilisateurs) {
                if (unUtilisateur.getPseudo().equals(pseudo)) {
                    myLogger.log(Level.WARNING,"Nom d'utilisateur déjà pris", bllException);
                    bllException.ajouterErreur(CodesResultatBLL.USERNAME_ALREADY_USED);
                }
            }
        } else {
            myLogger.log(Level.WARNING,"Nom d'utilisateur vide ou null", bllException);
            bllException.ajouterErreur(CodesResultatBLL.USERNAME_REQUIRED);
        }
    }

    public void isEmailValid(String email, BLLException bllException) throws BLLException {
        List<Utilisateur> lesUtilisateurs = utilisateurDAO.selectAll();
        if (email.length() > 0 || email.trim().equals("")) {
            for (Utilisateur unUtilisateur : lesUtilisateurs) {
                if (unUtilisateur.getEmail().equals(email)) {
                    myLogger.log(Level.WARNING,"Email déjà utilisé", bllException);
                    bllException.ajouterErreur(CodesResultatBLL.EMAIL_ALREADY_USED);
                }
            }
        } else {
            myLogger.log(Level.WARNING,"Email vide ou null", bllException);
            bllException.ajouterErreur(CodesResultatBLL.EMAIL_REQUIRED);
        }
    }

    public void isPasswordValid(String mdp, String mdpConf, BLLException bllException) {
        String regexPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,12}$";
        if (mdp.length() > 0 || mdp.trim().equals("") || mdpConf.length() > 0 || mdpConf.trim().equals("")) {
            if (mdp.length() > 0 && mdpConf.length() > 0) {
                if (!mdp.matches(regexPattern)) {
                    myLogger.log(Level.WARNING,"Format du mdp invalide", bllException);
                    bllException.ajouterErreur(CodesResultatBLL.PWD_NOT_VALID);
                }
                if (!mdp.equals(mdpConf)) {
                    myLogger.log(Level.WARNING,"Format du mdp invalide", bllException);
                    bllException.ajouterErreur(CodesResultatBLL.PWD_PWD_CONFIRMED_NOT_IDENTICAL);
                }
            }
        } else {
            if (mdp.length() > 0 || mdp.trim().equals("")) {
                myLogger.log(Level.WARNING,"Mdp vide ou null", bllException);
                bllException.ajouterErreur(CodesResultatBLL.PWD_REQUIRED);
            }
            if (mdpConf.length() > 0 || mdpConf.trim().equals("")) {
                myLogger.log(Level.WARNING,"Confirmation mdp vide ou nulle", bllException);
                bllException.ajouterErreur(CodesResultatBLL.PWD_CONFIRMED_REQUIRED);
            }
        }
    }

    public void updateUserData(int idUser, String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse, String motDePasseConfirmation) throws BLLException {
        BLLException bllException = new BLLException();
        Utilisateur lUtilisateur;
        isUserInfoValid(pseudo, email, motDePasse, motDePasseConfirmation, bllException);
        if (!bllException.hasErreurs()) {
            lUtilisateur = new Utilisateur(idUser, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
            utilisateurDAO.updateUserData(lUtilisateur);
        } else {
            myLogger.log(Level.WARNING,"Erreur lors de la mise à jour des informations de l'utilisateur " + idUser, bllException);
            throw bllException;
        }
    }

    public void updateUserAccountStatus(int idUser) throws BLLException {
        utilisateurDAO.updateUserAccountStatus(idUser);
    }

    public void updateUserCredit(int creditSpent, int idUser) throws BLLException {
        if ((utilisateurDAO.selectById(idUser).getCredit() - creditSpent) < 0) {
            myLogger.log(Level.WARNING,"Erreur lors de la mise à jour des crédits de l'utilisateur " + idUser, new BLLException());
            throw new BLLException();
        } else {
            utilisateurDAO.updateUserCredit(utilisateurDAO.selectById(idUser).getCredit() + creditSpent, idUser);
        }
    }

    public void deleteUser(int idUser) throws BLLException {
        utilisateurDAO.deleteUser(idUser);
    }

    public Utilisateur authenticateUser(String login, String mdp) throws BLLException {
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

    public Utilisateur checkIfUserExists(String username, String email) throws BLLException {
        return utilisateurDAO.checkIfUserExists(username, email);
    }

    public void resetPassword(int userId) throws BLLException {
        String regexPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,12}$";
        //   TODO : trouver un moyen de générer un nouveau mdp selon le pattern regex
        utilisateurDAO.resetPwd(userId, "Testing456!");
    }

}
