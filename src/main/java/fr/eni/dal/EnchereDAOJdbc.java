package fr.eni.dal;

import fr.eni.bll.BLLException;
import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Enchere;
import fr.eni.bo.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnchereDAOJdbc implements EnchereDAO {
    private static final Logger myLogger = Logger.getLogger("LogsDAL_Enchere");
    private static final String SELECT_ALL = "SELECT A.*, U.*, E.* " +
            "FROM ENCHERES E " +
            "INNER JOIN UTILISATEURS U " +
            "ON E.no_utilisateur = U.no_utilisateur " +
            "INNER JOIN ARTICLES_VENDUS A " +
            "ON A.no_article = E.no_article " +
            "WHERE E.no_article=? " ;
    private static final String SELECT_ALL_BIDS_BY_ARTICLE = SELECT_ALL +
            "ORDER BY E.montant_enchere DESC";
    private static final String SELECT_HIGHEST_BID_BY_ARTICLE = SELECT_ALL +
            "AND E.montant_enchere=(SELECT MAX(montant_enchere) FROM ENCHERES) " +
            "ORDER BY E.date_enchere DESC";
    public static final String INSERT_BID = "INSERT INTO ENCHERES VALUES(?,?,?,?)";
    public static final String DELETE_BIDS_BY_USER = "DELETE FROM ENCHERES WHERE no_utilisateur=?";
    public static final String DELETE_BIDS_ON_ARTICLE = "DELETE FROM ENCHERES WHERE no_article=?";

    @Override
    public List<Enchere> selectBidByIdArticle(int idArticle) throws BLLException {
        List<Enchere> lesEncheres = new ArrayList<>();
        BLLException bllException = new BLLException();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_BIDS_BY_ARTICLE);
            pstmt.setInt(1, idArticle);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Enchere lEnchere = getEnchere(rs);
                lesEncheres.add(lEnchere);
            }
        } catch (Exception e) {
            e.printStackTrace();
            bllException.ajouterErreur(CodesResultatDAL.ERROR_SELECT_ALL_BIDS_BY_ARTICLE);
            myLogger.log(Level.WARNING,"Erreur lors de la lecture des enchères en base sur l'article " + idArticle, bllException);
            throw bllException;
        }
        return lesEncheres;
    }

    @Override
    public Enchere selectHighestBidByIdArticle(int idArticle) throws BLLException {
        Enchere lEnchere = null;
        BLLException bllException = new BLLException();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_HIGHEST_BID_BY_ARTICLE);
            pstmt.setInt(1, idArticle);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                lEnchere = getEnchere(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            bllException.ajouterErreur(CodesResultatDAL.ERROR_SELECT_HIGHEST_BID_BY_ARTICLE);
            myLogger.log(Level.WARNING,"Erreur lors de la lecture de la plus haute enchère sur l'article " + idArticle, bllException);
            throw bllException;
        }
        return lEnchere;
    }

    @Override
    public Enchere createEnchere(Enchere lEnchere) throws BLLException {
        BLLException bllException = new BLLException();
        if (lEnchere == null) {
            bllException.ajouterErreur(CodesResultatDAL.ERROR_CREATE_BID);
            throw bllException;
        } else {
            try (Connection cnx = ConnectionProvider.getConnection()) {
                PreparedStatement pstmt = cnx.prepareStatement(INSERT_BID);
                pstmt.setInt(1, lEnchere.getlUtilisateur().getNumUtilisateur());
                pstmt.setInt(2, lEnchere.getlArticle().getNumArticle());
                pstmt.setTimestamp(3, Timestamp.valueOf(lEnchere.getDateEnchere()));
                pstmt.setInt(4, lEnchere.getMontantEnchere());
                pstmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                bllException.ajouterErreur(CodesResultatDAL.ERROR_CREATE_BID);
                myLogger.log(Level.WARNING,"Erreur lors de la création d'une nouvelle enchère", bllException);
                throw bllException;
            }
        }
        return lEnchere;
    }

    @Override
    public void deleteAllBidsByUser(int idUser) throws BLLException {
        BLLException bllException = new BLLException();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(DELETE_BIDS_BY_USER);
            pstmt.setInt(1, idUser);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            bllException.ajouterErreur(CodesResultatDAL.ERROR_DELETE_BIDS_BY_USER);
            myLogger.log(Level.WARNING,"Erreur lors de la suppression des offres faites par l'utilisateur " + idUser, bllException);
            throw bllException;
        }
    }

    @Override
    public void deleteAllBidsOnArticle(int idArticle) throws BLLException {
        BLLException bllException = new BLLException();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(DELETE_BIDS_ON_ARTICLE);
            pstmt.setInt(1, idArticle);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            bllException.ajouterErreur(CodesResultatDAL.ERROR_DELETE_BIDS_ON_ARTICLE);
            myLogger.log(Level.WARNING,"Erreur lors de la suppression des offres sur l'article " + idArticle, bllException);
            throw bllException;
        }
    }

    private Enchere getEnchere(ResultSet rs) throws SQLException {
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
                rs.getString("etat_vente"),
                lUtilisateur
        );
        Enchere lEnchere = new Enchere(
                lUtilisateur,
                lArticle,
                rs.getTimestamp("date_enchere").toLocalDateTime(),
                rs.getInt("montant_enchere")
        );
        return lEnchere;
    }
}
