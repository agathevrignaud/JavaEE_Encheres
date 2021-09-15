package fr.eni.dal;

import fr.eni.bo.Enchere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EnchereDAOJdbc implements EnchereDAO {
    public static final String INSERT_BID = "INSERT INTO ENCHERES VALUES(?,?,?,?)";
    private static final String SELECT_ALL_BIDS_BY_ARTICLE = "SELECT * FROM ENCHERES WHERE no_article=? ORDER BY montant_enchere DESC";
    private static final String SELECT_HIGHEST_BID_BY_ARTICLE = "SELECT E.no_utilisateur, U.nom, E.no_article, E.date_enchere, E.montant_enchere " +
            "FROM ENCHERES E " +
            "INNER JOIN UTILISATEURS U " +
            "ON E.no_utilisateur = U.no_utilisateur " +
            "WHERE E.no_article=? " +
            "AND E.montant_enchere=(SELECT MAX(montant_enchere) FROM ENCHERES) " +
            "ORDER BY E.date_enchere DESC";

    @Override
    public List<Enchere> selectBidByIdArticle(int idArticle) {
        List<Enchere> lesEncheres = new ArrayList<>();

        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_BIDS_BY_ARTICLE);
            pstmt.setInt(1, idArticle);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Enchere lEnchere = new Enchere();
                lEnchere.setNo_utilisateur(rs.getInt("no_utilisateur"));
                lEnchere.setNo_article(rs.getInt("no_article"));
                lEnchere.setDateEnchere(rs.getTimestamp("date_enchere").toLocalDateTime());
                lEnchere.setMontantEnchere(rs.getInt("montant_enchere"));
                lesEncheres.add(lEnchere);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lesEncheres;
    }

    @Override
    public Enchere selectHighestBidByIdArticle(int idArticle) {
        Enchere lEnchere = new Enchere();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_HIGHEST_BID_BY_ARTICLE);
            pstmt.setInt(1, idArticle);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                lEnchere.setNo_utilisateur(rs.getInt("no_utilisateur"));
                lEnchere.setNomUtilisateur(rs.getString("nom"));
                lEnchere.setNo_article(rs.getInt("no_article"));
                lEnchere.setDateEnchere(rs.getTimestamp("date_enchere").toLocalDateTime());
                lEnchere.setMontantEnchere(rs.getInt("montant_enchere"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lEnchere;
    }

    @Override
    public void createEnchere(Enchere lEnchere) {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(INSERT_BID);
            pstmt.setInt(1, lEnchere.getNo_utilisateur());
            pstmt.setInt(2, lEnchere.getNo_article());
            pstmt.setTimestamp(3, Timestamp.valueOf(lEnchere.getDateEnchere()));
            pstmt.setInt(4, lEnchere.getMontantEnchere());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'insertion de l'enchère en base");
            e.printStackTrace();
        }
    }
}
