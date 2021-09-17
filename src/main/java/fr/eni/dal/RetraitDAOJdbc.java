package fr.eni.dal;

import fr.eni.bll.BLLException;
import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Retrait;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RetraitDAOJdbc implements RetraitDAO {
    private static final Logger myLogger = Logger.getLogger("LogsDAL_Retrait");
    private static final String SELECT_RETRAIT_BY_ID = "SELECT A.*, R.rue, R.code_postal, R.ville " +
            "FROM RETRAITS R " +
            "INNER JOIN ARTICLES_VENDUS A " +
            "ON R.no_article = A.no_article " +
            "WHERE R.no_article=?";
    private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS VALUES (?,?,?,?)";
    private static final String UPDATE_RETRAIT = "UPDATE RETRAITS SET rue=?, code_postal=?, ville=? WHERE no_article=?";
    private static final String DELETE_RETRAIT = "DELETE FROM RETRAITS WHERE no_article =?";

    @Override
    public Retrait selectById(int idArticle) throws BLLException {
        Retrait lieuRetrait = null;
        BLLException bllException = new BLLException();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_RETRAIT_BY_ID);
            pstmt.setInt(1, idArticle);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                ArticleVendu lArticle = new ArticleVendu(
                        rs.getInt("A.no_article"),
                        rs.getString("A.nom_article"),
                        rs.getString("A.description"),
                        rs.getDate("A.date_debut_encheres").toLocalDate(),
                        rs.getDate("A.date_fin_encheres").toLocalDate(),
                        rs.getInt("A.prix_initial"),
                        rs.getInt("A.prix_vente"),
                        rs.getString("etat_vente")
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
            bllException.ajouterErreur(CodesResultatDAL.ERROR_SELECT_RETRAIT_BY_ID);
            myLogger.log(Level.WARNING,"Erreur lors de la lecture du lieu de retrait de l'article  " + idArticle, bllException);
            throw bllException;
        }
        return lieuRetrait;
    }

    @Override
    public Retrait createRetrait(Retrait lieuRetrait) throws BLLException {
        BLLException bllException = new BLLException();
        if (lieuRetrait == null) {
            bllException.ajouterErreur(CodesResultatDAL.ERROR_CREATE_RETRAIT);
            throw bllException;
        } else {
            try (Connection cnx = ConnectionProvider.getConnection()) {
                PreparedStatement pstmt = cnx.prepareStatement(INSERT_RETRAIT);
                pstmt.setInt(1, lieuRetrait.getlArticle().getNumArticle());
                pstmt.setString(2, lieuRetrait.getRue());
                pstmt.setString(3, lieuRetrait.getCodePostal());
                pstmt.setString(4, lieuRetrait.getVille());
                pstmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                bllException.ajouterErreur(CodesResultatDAL.ERROR_CREATE_RETRAIT);
                myLogger.log(Level.WARNING,"Erreur lors de la création d'un nouveau lieu de retrait", bllException);
                throw bllException;
            }
        }
        return lieuRetrait;
    }

    @Override
    public void updateRetrait(Retrait lieuRetrait) throws BLLException {
        BLLException bllException = new BLLException();
        if (lieuRetrait == null) {
            bllException.ajouterErreur(CodesResultatDAL.ERROR_UPDATE_CATEGORY);
            throw bllException;
        }
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(UPDATE_RETRAIT);
            pstmt.setString(1, lieuRetrait.getRue());
            pstmt.setString(2, lieuRetrait.getCodePostal());
            pstmt.setString(3, lieuRetrait.getVille());
            pstmt.setInt(4, lieuRetrait.getlArticle().getNumArticle());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            bllException.ajouterErreur(CodesResultatDAL.ERROR_UPDATE_CATEGORY);
            myLogger.log(Level.WARNING,"Erreur lors de la mise à jour du retrait lié à l'article " + lieuRetrait.getlArticle().getNumArticle(), bllException);
            throw bllException;
        }
    }

}
