package fr.eni.dal;

import fr.eni.bo.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO : Ajouter des logs + meilleure gestion des erreurs avec un système de codes/messages

public class UtilisateurDAOJdbc implements UtilisateurDAO {
    private static final String SELECT_ALL_USERS = "SELECT * FROM UTILISATEURS U";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM UTILISATEURS WHERE NO_UTILISATEUR=?";
    private static final String INSERT_USER = "INSERT INTO UTILISATEURS VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_USER_DATA = "UPDATE UTILISATEURS SET " +
            "pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? " +
            "WHERE no_utilisateur=?";
    private static final String UPDATE_USER_ACCOUNT_STATUS = "UPDATE UTILISATEURS SET compteActif=? WHERE no_utilisateur=?";
    private static final String UPDATE_USER_CREDIT = "UPDATE UTILISATEURS SET credit=? WHERE no_utilisateur=?";
    private static final String DELETE_USER = "DELETE FROM UTILISATEURS WHERE no_utilisateur=?";
    private static final String CHECK_USER_EXISTENCE = "SELECT * FROM UTILISATEURS WHERE pseudo=? AND email=?";
    private static final String RESET_PASSWORD = "UPDATE UTILISATEURS SET mot_de_passe=? WHERE no_utilisateur=?";

    @Override
    public List<Utilisateur> selectAll() throws DALException {
        List<Utilisateur> lesUtilisateurs = new ArrayList<>();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Utilisateur lUtilisateur = new Utilisateur();
                lUtilisateur.setNo_utilisateur(rs.getInt("no_utilisateur"));
                lUtilisateur.setPseudo(rs.getString("pseudo"));
                lUtilisateur.setNom(rs.getString("nom"));
                lUtilisateur.setPrenom(rs.getString("prenom"));
                lUtilisateur.setEmail(rs.getString("email"));
                lUtilisateur.setTelephone(rs.getString("telephone"));
                lUtilisateur.setRue(rs.getString("rue"));
                lUtilisateur.setCodePostal(rs.getString("code_postal"));
                lUtilisateur.setVille(rs.getString("ville"));
                lUtilisateur.setMotDePasse(rs.getString("mot_de_passe"));
                lUtilisateur.setCredit(rs.getInt("credit"));
                lUtilisateur.setAdministrateur(rs.getBoolean("administrateur"));
                lUtilisateur.setCompteActif(rs.getBoolean("compteActif"));
                lesUtilisateurs.add(lUtilisateur);
            }
        } catch (Exception e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.ajouterErreur(CodesResultatDAL.ERROR_SELECT_ALL);
            throw dalException;
        }

        return lesUtilisateurs;
    }

    @Override
    public Utilisateur selectById(int idUser) throws DALException {
        Utilisateur lUtilisateur = new Utilisateur();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER_BY_ID);
            pstmt.setInt(1, idUser);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                lUtilisateur.setNo_utilisateur(rs.getInt("no_utilisateur"));
                lUtilisateur.setPseudo(rs.getString("pseudo"));
                lUtilisateur.setNom(rs.getString("nom"));
                lUtilisateur.setPrenom(rs.getString("prenom"));
                lUtilisateur.setEmail(rs.getString("email"));
                lUtilisateur.setTelephone(rs.getString("telephone"));
                lUtilisateur.setRue(rs.getString("rue"));
                lUtilisateur.setCodePostal(rs.getString("code_postal"));
                lUtilisateur.setVille(rs.getString("ville"));
                lUtilisateur.setMotDePasse(rs.getString("mot_de_passe"));
                lUtilisateur.setCredit(rs.getInt("credit"));
                lUtilisateur.setAdministrateur(rs.getBoolean("administrateur"));
                lUtilisateur.setCompteActif(rs.getBoolean("compteActif"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.ajouterErreur(CodesResultatDAL.ERROR_SELECT_BY_ID);
            throw dalException;
        }
        return lUtilisateur;
    }

    @Override
    public Utilisateur createUser(Utilisateur lUtilisateur) throws DALException {
        if (lUtilisateur == null) {
            //
        }
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, lUtilisateur.getPseudo());
            pstmt.setString(2, lUtilisateur.getNom());
            pstmt.setString(3, lUtilisateur.getPrenom());
            pstmt.setString(4, lUtilisateur.getEmail());
            pstmt.setString(5, lUtilisateur.getTelephone());
            pstmt.setString(6, lUtilisateur.getRue());
            pstmt.setString(7, lUtilisateur.getCodePostal());
            pstmt.setString(8, lUtilisateur.getVille());
            pstmt.setString(9, lUtilisateur.getMotDePasse());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
             lUtilisateur.setNo_utilisateur(rs.getInt("no_utilisateur"));
            }
            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.ajouterErreur(CodesResultatDAL.ERROR_CREATE_USER);
            throw dalException;
        }
        return lUtilisateur;
    }

    @Override
    public void updateUserData(Utilisateur lUtilisateur) throws DALException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(UPDATE_USER_DATA);

            pstmt.setString(1, lUtilisateur.getPseudo());
            pstmt.setString(2, lUtilisateur.getNom());
            pstmt.setString(3, lUtilisateur.getPrenom());
            pstmt.setString(4, lUtilisateur.getEmail());
            pstmt.setString(5, lUtilisateur.getTelephone());
            pstmt.setString(6, lUtilisateur.getRue());
            pstmt.setString(7, lUtilisateur.getCodePostal());
            pstmt.setString(8, lUtilisateur.getVille());
            pstmt.setString(9, lUtilisateur.getMotDePasse());
            pstmt.setInt(10, lUtilisateur.getNo_utilisateur());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.ajouterErreur(CodesResultatDAL.ERROR_UPDATE_USER_DATA);
            throw dalException;
        }
    }

    @Override
    public void updateUserAccountStatus(int idUser) throws DALException {
        Utilisateur lUtilisateur = this.selectById(idUser);

        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(UPDATE_USER_ACCOUNT_STATUS);
            pstmt.setBoolean(1, !lUtilisateur.isCompteActif());
            pstmt.setInt(2, idUser);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.ajouterErreur(CodesResultatDAL.ERROR_UPDATE_USER_ACCOUNT_STATUS);
            throw dalException;
        }
    }

    @Override
    public void updateUserCredit(int newCredit, int idUser) throws DALException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(UPDATE_USER_CREDIT);
            pstmt.setInt(1, newCredit);
            pstmt.setInt(2, idUser);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.ajouterErreur(CodesResultatDAL.ERROR_UPDATE_USER_CREDIT);
            throw dalException;
        }
    }

    @Override
    public void deleteUser(int idUser) throws DALException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(DELETE_USER);
            pstmt.setInt(1, idUser);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.ajouterErreur(CodesResultatDAL.ERROR_DELETE_USER);
            throw dalException;
        }
    }

    @Override
    public Utilisateur checkIfUserExists(String username, String email) throws DALException {
        Utilisateur lUtilisateur = new Utilisateur();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(CHECK_USER_EXISTENCE);
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                lUtilisateur.setNo_utilisateur(rs.getInt("no_utilisateur"));
                lUtilisateur.setPseudo(rs.getString("pseudo"));
                lUtilisateur.setNom(rs.getString("nom"));
                lUtilisateur.setPrenom(rs.getString("prenom"));
                lUtilisateur.setEmail(rs.getString("email"));
                lUtilisateur.setEmail(rs.getString("mot_de_passe"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.ajouterErreur(CodesResultatDAL.ERROR_CHECK_USER_EXISTENCE);
            throw dalException;
        }
        return lUtilisateur;
    }

    @Override
    public void resetPwd(int idUser, String newPwd) throws DALException {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(RESET_PASSWORD);
            pstmt.setString(1, newPwd);
            pstmt.setInt(2, idUser);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            DALException dalException = new DALException();
            dalException.ajouterErreur(CodesResultatDAL.ERROR_RESET_PWD);
            throw dalException;
        }
    }
}
