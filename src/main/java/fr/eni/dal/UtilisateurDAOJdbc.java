package fr.eni.dal;

import fr.eni.bo.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAOJdbc implements UtilisateurDAO {

    private static final String SELECT_ALL_USERS = "SELECT * FROM UTILISATEURS";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM UTILISATEURS WHERE NO_UTILISATEUR=?";

    @Override
    public List<Utilisateur> selectAll() {
        List<Utilisateur> lesUtilisateurs = new ArrayList<Utilisateur>();
        Utilisateur lUtilisateur = new Utilisateur();

        try(Connection cnx = ConnectionProvider.getConnection())
        {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
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
            }
            lesUtilisateurs.add(lUtilisateur);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return lesUtilisateurs;
    }

    @Override
    public Utilisateur selectById(int userId) {
        Utilisateur lUtilisateur = new Utilisateur();
        try(Connection cnx = ConnectionProvider.getConnection())
        {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER_BY_ID);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
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
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return lUtilisateur;
    }
}
