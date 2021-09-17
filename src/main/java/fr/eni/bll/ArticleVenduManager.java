package fr.eni.bll;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categorie;
import fr.eni.bo.Retrait;
import fr.eni.bo.Utilisateur;
import fr.eni.dal.ArticleVenduDAO;
import fr.eni.dal.DAOFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArticleVenduManager {
    private final ArticleVenduDAO articleVenduDAO;
    private static final Logger myLogger = Logger.getLogger("LogsBLL_ArticleVendu");

    public ArticleVenduManager() { articleVenduDAO = DAOFactory.getArticleVenduDAO();}

    public List<ArticleVendu> getAllArticles() throws BLLException{
        return articleVenduDAO.selectAll();
    }

    public List<ArticleVendu> getAllArticlesByUser(int idUser) throws BLLException {
        return articleVenduDAO.selectAllByUserId(idUser);
    }

    public ArticleVendu getArticleById(int idArticle) throws BLLException {
        return articleVenduDAO.selectArticleById(idArticle);
    }

    public ArticleVendu addNewArticle(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
                                      int miseAPrix, String etatVente, Utilisateur lUtilisateur, Categorie laCategorie, Retrait lieuRetrait) throws BLLException {
        BLLException bllException = new BLLException();
        ArticleVendu lArticle = new ArticleVendu(
                nomArticle,
                description,
                dateDebutEncheres,
                dateFinEncheres,
                miseAPrix,
                etatVente,
                lieuRetrait,
                laCategorie,
                lUtilisateur
        );
        try {
            lArticle = articleVenduDAO.createArticle(lArticle);
        } catch (Exception e) {
            myLogger.log(Level.WARNING,"Erreur lors de la création de l'article", bllException);
            throw bllException;
        }
        return lArticle;
    }

    public ArticleVendu updateArticle(String nomArticle, String description, LocalDate dateDebutEncheres,
                                      LocalDate dateFinEncheres, int miseAPrix, String etatVente, Retrait lieuRetrait,
                                      Categorie laCategorie, Utilisateur lUtilisateur) throws BLLException {
        BLLException bllException = new BLLException();
        ArticleVendu lArticle = new ArticleVendu(
                nomArticle,
                description,
                dateDebutEncheres,
                dateFinEncheres,
                miseAPrix,
                etatVente,
                lieuRetrait,
                laCategorie,
                lUtilisateur
        );
        try {
            lArticle = articleVenduDAO.updateArticle(lArticle);
        } catch (Exception e) {
            myLogger.log(Level.WARNING,"Erreur lors de la création de l'article", bllException);
            throw bllException;
        }
        return lArticle ;
    }

    public void updateArticlePrice(int bid, int idArticle) throws BLLException {
        articleVenduDAO.updateBidOnArticle(bid, idArticle);
    }

    public void updateAuctionStatus(String newStatus, int idArticle) throws BLLException {
        articleVenduDAO.updateAuctionStatus(newStatus, idArticle);
    }

}
