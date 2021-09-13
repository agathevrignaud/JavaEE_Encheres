package fr.eni.bll;

import fr.eni.bo.Utilisateur;
import fr.eni.dal.DALException;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.UtilisateurDAO;

import java.util.List;
import java.util.regex.Pattern;

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

    public void addNewUser(String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse, String motDePasseConfirmation) throws BLLException {

        try {
            isUserInfoValid(pseudo, email, motDePasse, motDePasseConfirmation);
            Utilisateur lUtilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
            try {
                utilisateurDAO.createUser(lUtilisateur);
            } catch (DALException dalEx) {
                dalEx.printStackTrace();
            }

        } catch (BLLException bllEx) {
            throw new BLLException("Erreur lors de la création de l'utilisateur", bllEx);
        }
    }

    public void isUserInfoValid(String pseudo, String email, String mdp, String mdpConf) throws BLLException {
        isPseudoAndEmailValid(pseudo, email);
        isPasswordValid(mdp, mdpConf);
    }

    public void isPseudoAndEmailValid(String pseudo, String email) throws BLLException {
        List<Utilisateur> lesUtilisateurs = utilisateurDAO.selectAll();

        String regexEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";
        if (Pattern.compile(regexEmail).matcher(email).matches()) {
            throw new BLLException("L'adresse mail doit être correct");
        }

        // Uniquement des caractères alphanumériques
        String regexPatternPseudo = "^[a-zA-Z0-9]*$";
        if (!pseudo.matches(regexPatternPseudo)) {
            throw new BLLException("Le pseudo doit être correct");
        }
        // pseudo + email uniques
        for (Utilisateur unUtilisateur : lesUtilisateurs) {
            if (unUtilisateur.getPseudo().equals(pseudo) || unUtilisateur.getEmail().equals(email)) {
                throw new BLLException("Pseudo ou adresse mail déjà existante");
            }
        }
    }

    public void isPasswordValid(String mdp, String mdpConf) throws BLLException {
        String regexPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,12}$";

        if (mdp.length() > 0 && mdpConf.length() > 0) {
            if (!mdp.matches(regexPattern)) {
                throw new BLLException("Le mot de passe doit avoir un format correct");
            }
            if (!mdp.equals(mdpConf)) {
                throw new BLLException("Les mots de passe ne sont pas identiques");
            }
        }
    }

    public void updateUserData(int userId, String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, String motDePasse, String motDePasseConfirmation) throws BLLException {
        try {
            isUserInfoValid(pseudo, email, motDePasse, motDePasseConfirmation);
            Utilisateur lUtilisateur = new Utilisateur(userId, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
            utilisateurDAO.updateUserData(lUtilisateur);
        } catch (BLLException bllEx) {
            throw new BLLException("Erreur lors de la màj de l'utilisateur", bllEx);
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
        Utilisateur lUtilisateur = null;
        List<Utilisateur> lesUtilisateurs = utilisateurDAO.selectAll();
        for (Utilisateur unUtilisateur : lesUtilisateurs) {
            if (unUtilisateur.getPseudo().equals(login) || unUtilisateur.getEmail().equals(login)) {
                if (unUtilisateur.getMotDePasse().equals(mdp)) {
                    lUtilisateur = unUtilisateur;
                }
                ;
            }
        }
        return lUtilisateur;
    }
}
