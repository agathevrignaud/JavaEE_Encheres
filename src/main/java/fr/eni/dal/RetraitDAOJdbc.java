package fr.eni.dal;

import fr.eni.bo.Retrait;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RetraitDAOJdbc implements RetraitDAO {

    private static final String SELECT_RETRAIT_BY_ID = "SELECT * FROM RETRAITS WHERE no_article=?";
    private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS VALUES (?,?,?,?)";

    @Override
    public Retrait selectById(int idRetrait) {
        Retrait lieuRetrait = new Retrait();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_RETRAIT_BY_ID);
            pstmt.setInt(1, idRetrait);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                lieuRetrait.setNo_article(idRetrait);
                lieuRetrait.setRue(rs.getString("rue"));
                lieuRetrait.setCodePostal(rs.getString("code_postal"));
                lieuRetrait.setVille(rs.getString("ville"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lieuRetrait;
    }

    @Override
    public void createRetrait(Retrait lieuRetrait) {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(INSERT_RETRAIT);
            pstmt.setInt(1,lieuRetrait.getNo_article());
            pstmt.setString(2, lieuRetrait.getRue());
            pstmt.setString(3, lieuRetrait.getCodePostal());
            pstmt.setString(4, lieuRetrait.getVille());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
