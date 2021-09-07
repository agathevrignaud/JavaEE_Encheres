package fr.eni.dal;

import fr.eni.bo.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UtilisateurDAOJdbc implements UtilisateurDAO {

    private static final String SELECT_ALL_USERS = "SELECT * FROM UTILISATEURS";

    @Override
    public Utilisateur read() {
        //ouvre la connexion à la base
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
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return lUtilisateur;
    }
}
