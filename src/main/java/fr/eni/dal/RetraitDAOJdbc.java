package fr.eni.dal;

import fr.eni.bo.Retrait;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RetraitDAOJdbc implements RetraitDAO{

    private static final String SELECT_RETRAIT_BY_ID = "SELECT * FROM RETRAIT WHERE no_article=?";
    private static final String INSERT_RETRAIT = "INSERT INTO RETRAIT VALUES (?,?,?)";

    @Override
    public Retrait selectById(int idRetrait) {
        Retrait lieuRetrait = new Retrait();
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_RETRAIT_BY_ID);
            pstmt.setInt(1, idRetrait);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                lieuRetrait.setNo_article(idRetrait);
                lieuRetrait.setRue(rs.getString("rue"));
                lieuRetrait.setCodePostal(rs.getString("code_postal"));
                lieuRetrait.setVille(rs.getString("ville"));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return lieuRetrait;
    }

    @Override
    public void createRetrait(Retrait lieuRetrait) {
        try(Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(INSERT_RETRAIT);
            pstmt.setString(1,lieuRetrait.getRue());
            pstmt.setString(2,lieuRetrait.getCodePostal());
            pstmt.setString(3,lieuRetrait.getVille());
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
