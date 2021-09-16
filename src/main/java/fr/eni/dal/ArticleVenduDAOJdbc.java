package fr.eni.dal;

import fr.eni.bll.BLLException;
import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import fr.eni.bo.Retrait;
import fr.eni.bo.Utilisateur;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArticleVenduDAOJdbc implements ArticleVenduDAO {
    private static final Logger myLogger = Logger.getLogger("LogsDAL_ArticleVendu");
    private static final String SELECT_ALL_ARTICLES = "SELECT U.*, A.*, C.*, R.* " +
            "FROM ARTICLES_VENDUS A " +
            "INNER JOIN UTILISATEURS U " +
            "ON A.no_utilisateur = U.no_utilisateur " +
            "INNER JOIN CATEGORIES C " +
            "ON A.no_categorie = C.no_categorie " +
            "INNER JOIN RETRAITS R " +
            "ON A.no_article = R.no_article";
    private static final String SELECT_ALL_ARTICLES_BY_USER_ID = "SELECT U.*, A.*, C.*, R.* " +
            "FROM ARTICLES_VENDUS A " +
            "INNER JOIN UTILISATEURS U " +
            "ON A.no_utilisateur = U.no_utilisateur " +
            "INNER JOIN CATEGORIES C " +
            "ON A.no_categorie = C.no_categorie " +
            "INNER JOIN RETRAITS R " +
            "ON A.no_article = R.no_article " +
            "WHERE U.no_utilisateur=?";
    private static final String SELECT_ARTICLE_BY_ID = "SELECT U.*, A.*, C.*, R.* " +
            "FROM ARTICLES_VENDUS A " +
            "INNER JOIN UTILISATEURS U " +
            "ON A.no_utilisateur = U.no_utilisateur " +
            "INNER JOIN CATEGORIES C " +
            "ON A.no_categorie = C.no_categorie " +
            "INNER JOIN RETRAITS R " +
            "ON A.no_article = R.no_article " +
            "WHERE A.no_article=?";
    private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article,description,date_debut_encheres, " +
            "date_fin_encheres, prix_initial, etat_vente, no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?)";
    private static final String UPDATE_ARTICLE_BID = "UPDATE ARTICLES_VENDUS SET prix_vente=? WHERE no_article=?";
    private static final String DELETE_ALL_ARTICLES_BY_USER_ID="DELETE FROM ARTICLES_VENDUS WHERE no_utilisateur=?";
    private static final String SELECT_BY_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?";
    private static final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
    private static final String UPDATE_ARTICLE_VENDU = "UPDATE ARTICLES_VENDUS SET nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, etat_vente=?, no_utilisateur=?, no_categorie=? WHERE no_article=?";
    private static final String UPDATE_AUCTION_STATUS="UPDATE ARTICLES_VENDUS SET etat_vente=? WHERE no_article=?";

    // TODO : fournir List<ArticleVendu> avec toutes les infos, le tri se fera côté front ?

    /**
     * Séléctionner tous les articles
     */
    @Override
    public List<ArticleVendu> selectAll() {
        List<ArticleVendu> lesArticles = new ArrayList<>();

        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLES);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Utilisateur lUtilisateur = new Utilisateur(
                        rs.getInt("U.no_utilisateur"),
                        rs.getString("U.pseudo"),
                        rs.getString("U.nom"),
                        rs.getString("U.prenom"),
                        rs.getString("U.email"),
                        rs.getString("U.telephone"),
                        rs.getString("U.rue"),
                        rs.getString("U.codePostal"),
                        rs.getString("U.ville")
                );
                Retrait lieuRetrait = new Retrait(
                        rs.getString("R.rue"),
                        rs.getString("R.code_postal"),
                        rs.getString("R.ville")
                );
                Categorie laCategorie = new Categorie(
                        rs.getInt("C.no_categorie"),
                        rs.getString("C.libelle")
                );
                ArticleVendu lArticle = new ArticleVendu(
                        rs.getInt("A.no_article"),
                        rs.getString("A.nom_article"),
                        rs.getString("A.description"),
                        rs.getDate("A.date_debut_encheres").toLocalDate(),
                        rs.getDate("A.date_fin_encheres").toLocalDate(),
                        rs.getInt("A.prix_initial"),
                        rs.getInt("A.prix_vente"),
                        rs.getString("A.etat_vente"),
                        lieuRetrait,
                        laCategorie,
                        lUtilisateur
                );
                lieuRetrait.setlArticle(lArticle);
                lesArticles.add(lArticle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lesArticles;
    }

    @Override
    public List<ArticleVendu> selectAllByUserId(int idUser) {
        List<ArticleVendu> lesArticles = new ArrayList<>();

        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_ARTICLES_BY_USER_ID);
            pstmt.setInt(1, idUser);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Utilisateur lUtilisateur = new Utilisateur(
                        rs.getInt("U.no_utilisateur"),
                        rs.getString("U.pseudo"),
                        rs.getString("U.nom"),
                        rs.getString("U.prenom"),
                        rs.getString("U.email"),
                        rs.getString("U.telephone"),
                        rs.getString("U.rue"),
                        rs.getString("U.codePostal"),
                        rs.getString("U.ville")
                );
                Retrait lieuRetrait = new Retrait(
                        rs.getString("R.rue"),
                        rs.getString("R.code_postal"),
                        rs.getString("R.ville")
                );
                Categorie laCategorie = new Categorie(
                        rs.getInt("C.no_categorie"),
                        rs.getString("C.libelle")
                );
                ArticleVendu lArticle = new ArticleVendu(
                        rs.getInt("A.no_article"),
                        rs.getString("A.nom_article"),
                        rs.getString("A.description"),
                        rs.getDate("A.date_debut_encheres").toLocalDate(),
                        rs.getDate("A.date_fin_encheres").toLocalDate(),
                        rs.getInt("A.prix_initial"),
                        rs.getInt("A.prix_vente"),
                        rs.getString("A.etat_vente"),
                        lieuRetrait,
                        laCategorie,
                        lUtilisateur
                );
                lieuRetrait.setlArticle(lArticle);
                lesArticles.add(lArticle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lesArticles;
    }


    @Override
    public ArticleVendu selectArticleById(int idArticle) {
        ArticleVendu lArticle = null ;
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLE_BY_ID);
            pstmt.setInt(1, idArticle);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Utilisateur lUtilisateur = new Utilisateur(
                        rs.getInt("U.no_utilisateur"),
                        rs.getString("U.pseudo"),
                        rs.getString("U.nom"),
                        rs.getString("U.prenom"),
                        rs.getString("U.email"),
                        rs.getString("U.telephone"),
                        rs.getString("U.rue"),
                        rs.getString("U.codePostal"),
                        rs.getString("U.ville")
                );
                Retrait lieuRetrait = new Retrait(
                        rs.getString("R.rue"),
                        rs.getString("R.code_postal"),
                        rs.getString("R.ville")
                );
                Categorie laCategorie = new Categorie(
                        rs.getInt("C.no_categorie"),
                        rs.getString("C.libelle")
                );
                lArticle = new ArticleVendu(
                        rs.getInt("A.no_article"),
                        rs.getString("A.nom_article"),
                        rs.getString("A.description"),
                        rs.getDate("A.date_debut_encheres").toLocalDate(),
                        rs.getDate("A.date_fin_encheres").toLocalDate(),
                        rs.getInt("A.prix_initial"),
                        rs.getInt("A.prix_vente"),
                        rs.getString("A.etat_vente"),
                        lieuRetrait,
                        laCategorie,
                        lUtilisateur
                );
                lieuRetrait.setlArticle(lArticle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lArticle;
    }

    @Override
    public void deleteAllArticlesByUserId(int idUser) {
        // TODO : à supprimer puisque y a un on delete cascade dans la base
    }

    @Override
    public ArticleVendu createArticle(ArticleVendu lArticle) throws BLLException {
        BLLException bllException = new BLLException();
        if (lArticle == null) {
            bllException.ajouterErreur(CodesResultatDAL.ERROR_CREATE_BID);
            throw bllException;
        }
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, lArticle.getNomArticle());
            pstmt.setString(2, lArticle.getDescription());
            pstmt.setDate(3, Date.valueOf(lArticle.getDateDebutEnchere()));
            pstmt.setDate(4, Date.valueOf(lArticle.getDateFinEnchere()));
            pstmt.setInt(5, lArticle.getMiseAPrix());
            pstmt.setInt(6, lArticle.getlUtilisateur().getNumUtilisateur());
            pstmt.setInt(7, lArticle.getLaCategorie().getNumCategorie());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                lArticle.setNumArticle(rs.getInt("no_article"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            bllException.ajouterErreur(CodesResultatDAL.ERROR_CREATE_BID);
            myLogger.log(Level.WARNING,"Erreur lors de la création d'une nouvelle enchère", bllException);
            throw bllException;
        }
        return lArticle;
    }

    @Override
    public void updateBidOnArticle(int bid, int idArticle) throws BLLException {
        BLLException bllException = new BLLException();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ARTICLE_BID);
            pstmt.setInt(1, bid);
            pstmt.setInt(2, idArticle);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            bllException.ajouterErreur(CodesResultatDAL.ERROR_UPDATE_ARTICLE_BID);
            myLogger.log(Level.WARNING,"Erreur lors de la mise à jour du prix de vente de l'article " + idArticle, bllException);
            throw bllException;
        }
    }

    @Override
    public void updateAuctionStatus(String newStatus, int idArticle) throws BLLException {
        BLLException bllException = new BLLException();
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(UPDATE_AUCTION_STATUS);
            pstmt.setString(1, newStatus);
            pstmt.setInt(2, idArticle);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            bllException.ajouterErreur(CodesResultatDAL.ERROR_UPDATE_AUCTION_STATUS);
            myLogger.log(Level.WARNING,"Erreur lors de la mise à jour du prix de vente de l'article " + idArticle, bllException);
            throw bllException;
        }
    }

    /**
     * Supprimer un article à partir de son id
     */
    @Override
    public void deleteArticle(int id) {
        try (Connection cnx = ConnectionProvider.getConnection()) {
            PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override

    public void updateArticle(ArticleVendu articleVendu) {
        try(Connection cnx = ConnectionProvider.getConnection()){
            PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ARTICLE_VENDU);

            pstmt.setString(1, articleVendu.getNomArticle());
            pstmt.setString(2, articleVendu.getDescription());
            pstmt.setDate(3, Date.valueOf(articleVendu.getDateDebutEnchere()));
            pstmt.setDate(4, Date.valueOf(articleVendu.getDateFinEnchere()));
            pstmt.setInt(5, articleVendu.getMiseAPrix());
            pstmt.setString(6, articleVendu.getEtatVente());
            pstmt.setInt(7, articleVendu.getlUtilisateur().getNumUtilisateur());
            pstmt.setInt(8, articleVendu.getLaCategorie().getNumCategorie());
            pstmt.setInt(9, articleVendu.getNumArticle());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
