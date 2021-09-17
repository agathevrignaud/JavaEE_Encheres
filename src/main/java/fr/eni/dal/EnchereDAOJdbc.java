package fr.eni.dal;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Enchere;
import fr.eni.bo.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EnchereDAOJdbc implements EnchereDAO {
    private static final String SELECT_ALL_BIDS_BY_ARTICLE = "SELECT A.*, U.*, E.date_enchere, E.montant_enchere " +
            "FROM ENCHERES E " +
            "INNER JOIN UTILISATEURS U " +
            "ON E.no_utilisateur = U.no_utilisateur " +
            "INNER JOIN ARTICLES_VENDUS A " +
            "ON E.no_article = A.no_article " +
            "WHERE E.no_article=? " +
            "ORDER BY E.montant_enchere DESC";
    private static final String SELECT_HIGHEST_BID_BY_ARTICLE = "SELECT E.no_utilisateur, A.*, U.*, E.no_article, E.date_enchere, E.montant_enchere " +
            "FROM ENCHERES E " +
            "INNER JOIN UTILISATEURS U " +
            "ON E.no_utilisateur = U.no_utilisateur " +
            "INNER JOIN ARTICLES_VENDUS A " +
            "ON E.no_article = A.no_article " +
            "WHERE E.no_article=? " +
            "AND E.montant_enchere=(SELECT MAX(montant_enchere) FROM ENCHERES) " +
            "ORDER BY E.date_enchere DESC";
    public static final String INSERT_BID = "INSERT INTO ENCHERES VALUES(?,?,?,?)";
    public static final String DELETE_BID_BY_USER = "DELETE FROM ENCHERES WHERE no_utilisateur=?";

    @Override
    public List<Enchere> selectBidByIdArticle(int idArticle) {
        List<Enchere> lesEncheres = new ArrayList<>();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_BIDS_BY_ARTICLE);
            pstmt.setInt(1, idArticle);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Utilisateur lUtilisateur = new Utilisateur(
                        rs.getInt("no_utilisateur"),
                        rs.getString("pseudo"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        rs.getString("rue"),
                        rs.getString("code_postal"),
                        rs.getString("ville")
                );
                ArticleVendu lArticle = new ArticleVendu(
                        rs.getInt("no_article"),
                        rs.getString("nom_article"),
                        rs.getString("description"),
                        rs.getDate("date_debut_encheres").toLocalDate(),
                        rs.getDate("date_fin_encheres").toLocalDate(),
                        rs.getInt("prix_initial"),
                        rs.getInt("prix_vente"),
                        lUtilisateur
                );
                Enchere lEnchere = new Enchere(
                        lUtilisateur,
                        lArticle,
                        rs.getTimestamp("date_enchere").toLocalDateTime(),
                        rs.getInt("montant_enchere")
                );
                lesEncheres.add(lEnchere);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lesEncheres;
    }

    @Override
    public Enchere selectHighestBidByIdArticle(int idArticle) {
        Enchere lEnchere = null;
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_HIGHEST_BID_BY_ARTICLE);
            pstmt.setInt(1, idArticle);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Utilisateur lUtilisateur = new Utilisateur(
                        rs.getInt("no_utilisateur"),
                        rs.getString("pseudo"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        rs.getString("rue"),
                        rs.getString("code_postal"),
                        rs.getString("ville")
                );
                ArticleVendu lArticle = new ArticleVendu(
                        rs.getInt("no_article"),
                        rs.getString("nom_article"),
                        rs.getString("description"),
                        rs.getDate("date_debut_encheres").toLocalDate(),
                        rs.getDate("date_fin_encheres").toLocalDate(),
                        rs.getInt("prix_initial"),
                        rs.getInt("prix_vente"),
                        lUtilisateur
                );
                lEnchere = new Enchere(
                        lUtilisateur,
                        lArticle,
                        rs.getTimestamp("date_enchere").toLocalDateTime(),
                        rs.getInt("montant_enchere")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lEnchere;
    }

    @Override
    public Enchere createEnchere(Enchere lEnchere) {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(INSERT_BID);
            pstmt.setInt(1, lEnchere.getlUtilisateur().getNumUtilisateur());
            pstmt.setInt(2, lEnchere.getlArticle().getNumArticle());
            pstmt.setTimestamp(3, Timestamp.valueOf(lEnchere.getDateEnchere()));
            pstmt.setInt(4, lEnchere.getMontantEnchere());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'insertion de l'enchère en base");
            e.printStackTrace();
        }
        return lEnchere;
    }

    @Override
    public void deleteAllBidsByUser(int idUser) {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(DELETE_BID_BY_USER);
            pstmt.setInt(1, idUser);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
