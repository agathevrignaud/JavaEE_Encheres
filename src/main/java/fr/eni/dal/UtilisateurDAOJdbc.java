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
    private static final String SELECT_ALL_USERS = "SELECT * FROM UTILISATEURS";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM UTILISATEURS WHERE NO_UTILISATEUR=?";
    private static final String INSERT_USER = "INSERT INTO UTILISATEURS VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_USER_DATA = "UPDATE UTILISATEURS SET " +
            "pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? " +
            "WHERE no_utilisateur=?";
    private static final String UPDATE_USER_ACCOUNT_STATUS = "UPDATE UTILISATEURS SET compteActif=? WHERE no_utilisateur=?";
    private static final String DELETE_USER = "DELETE FROM UTILISATEURS WHERE no_utilisateur=?";

    @Override
    public List<Utilisateur> selectAll() {
        List<Utilisateur> lesUtilisateurs = new ArrayList<>();
        Utilisateur lUtilisateur = new Utilisateur();

        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
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
            lesUtilisateurs.add(lUtilisateur);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return lesUtilisateurs;
    }

    @Override
    public Utilisateur selectById(int idUser) {
        Utilisateur lUtilisateur = new Utilisateur();
        try(Connection cnx = ConnectionProvider.getConnection())
        {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER_BY_ID);
            pstmt.setInt(1, idUser);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
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
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return lUtilisateur;
    }

    @Override
    public void createUser(Utilisateur lUtilisateur) {
        if(lUtilisateur==null) {
            //throw exception
        }

        // TODO : Revoir l'histoire des Generated_Keys (récupérer le no_utilisateur après création ?)
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(INSERT_USER);

            pstmt.setString(1,lUtilisateur.getPseudo());
            pstmt.setString(2,lUtilisateur.getNom());
            pstmt.setString(3,lUtilisateur.getPrenom());
            pstmt.setString(4,lUtilisateur.getEmail());
            pstmt.setString(5,lUtilisateur.getTelephone());
            pstmt.setString(6,lUtilisateur.getRue());
            pstmt.setString(7,lUtilisateur.getCodePostal());
            pstmt.setString(8,lUtilisateur.getVille());
            pstmt.setString(9,lUtilisateur.getMotDePasse());

            pstmt.executeUpdate();
            pstmt.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUserData(Utilisateur lUtilisateur) {
        try(Connection cnx = ConnectionProvider.getConnection())
        {
            PreparedStatement pstmt = cnx.prepareStatement(UPDATE_USER_DATA);

            pstmt.setString(1,lUtilisateur.getPseudo());
            pstmt.setString(2,lUtilisateur.getNom());
            pstmt.setString(3,lUtilisateur.getPrenom());
            pstmt.setString(4,lUtilisateur.getEmail());
            pstmt.setString(5,lUtilisateur.getTelephone());
            pstmt.setString(6,lUtilisateur.getRue());
            pstmt.setString(7,lUtilisateur.getCodePostal());
            pstmt.setString(8,lUtilisateur.getVille());
            pstmt.setString(9,lUtilisateur.getMotDePasse());
            pstmt.setInt(10,lUtilisateur.getNo_utilisateur());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUserAccountStatus(int idUser) {
        Utilisateur lUtilisateur = this.selectById(idUser);

        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(UPDATE_USER_ACCOUNT_STATUS);

            pstmt.setBoolean(1, !lUtilisateur.isCompteActif());
            pstmt.setInt(2, idUser);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int idUser) {
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(DELETE_USER);
            pstmt.setInt(1, idUser);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
