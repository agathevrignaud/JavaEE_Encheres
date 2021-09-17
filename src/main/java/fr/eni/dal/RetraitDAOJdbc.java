package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Retrait;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RetraitDAOJdbc implements RetraitDAO {

    private static final String SELECT_RETRAIT_BY_ID = "SELECT A.*, R.rue, R.code_postal, R.ville " +
            "FROM RETRAITS R " +
            "INNER JOIN ARTICLES_VENDUS A " +
            "ON R.no_article = A.no_article " +
            "WHERE R.no_article=?";
    private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS VALUES (?,?,?,?)";
    private static final String UPDATE_RETRAIT = "UPDATE RETRAITS SET rue=?, code_postal=?, ville=? WHERE no_article=?";
    private static final String DELETE_RETRAIT = "DELETE FROM RETRAITS WHERE no_article =?";

    @Override
    public Retrait selectById(int idRetrait) {
        Retrait lieuRetrait = null;
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_RETRAIT_BY_ID);
            pstmt.setInt(1, idRetrait);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                ArticleVendu lArticle = new ArticleVendu(
                        rs.getInt("A.no_article"),
                        rs.getString("A.nom_article"),
                        rs.getString("A.description"),
                        rs.getDate("A.date_debut_encheres").toLocalDate(),
                        rs.getDate("A.date_fin_encheres").toLocalDate(),
                        rs.getInt("A.prix_initial"),
                        rs.getInt("A.prix_vente")
                );
                lieuRetrait = new Retrait(
                        rs.getString("rue"),
                        rs.getString("code_postal"),
                        rs.getString("ville"),
                        lArticle
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lieuRetrait;
    }

    @Override
    public Retrait createRetrait(Retrait lieuRetrait) {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(INSERT_RETRAIT);
            pstmt.setInt(1, lieuRetrait.getlArticle().getNumArticle());
            pstmt.setString(2, lieuRetrait.getRue());
            pstmt.setString(3, lieuRetrait.getCodePostal());
            pstmt.setString(4, lieuRetrait.getVille());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lieuRetrait;
    }

    @Override
    public void deleteRetrait(int id) {
        try(Connection cnx = ConnectionProvider.getConnection()){
            PreparedStatement pstmt = cnx.prepareStatement(DELETE_RETRAIT);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRetrait(Retrait retrait) {
        try(Connection cnx = ConnectionProvider.getConnection()){
            PreparedStatement pstmt = cnx.prepareStatement(UPDATE_RETRAIT);

            pstmt.setInt(1, retrait.getlArticle().getNumArticle());
            pstmt.setString(2, retrait.getRue());
            pstmt.setString(3, retrait.getCodePostal());
            pstmt.setString(4, retrait.getVille());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
