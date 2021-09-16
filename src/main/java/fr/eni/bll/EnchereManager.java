package fr.eni.bll;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Enchere;
import fr.eni.bo.Utilisateur;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.EnchereDAO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnchereManager {
    private EnchereDAO enchereDAO;
    private static final Logger myLogger = Logger.getLogger("LogsBLL_Retrait");

    public EnchereManager() {
        enchereDAO = DAOFactory.getEnchereDAO();
    }

    public List<Enchere> getAllBidsByIdArticle(int idArticle) {
        return enchereDAO.selectBidByIdArticle(idArticle);
    }

    public Enchere getHighestBidByIdArticle(int idArticle) {
        return enchereDAO.selectHighestBidByIdArticle(idArticle);
    }

    public Enchere addNewEnchere(Utilisateur lUtilisateur, ArticleVendu lArticle, LocalDateTime dateEnchere, int montantEnchere) throws BLLException {
        BLLException bllException = new BLLException();
        Enchere lEnchere = new Enchere(
                lUtilisateur,
                lArticle,
                dateEnchere,
                montantEnchere
        );
        try {
            enchereDAO.createEnchere(lEnchere);
        } catch (Exception e) {
            e.printStackTrace();
            myLogger.log(Level.WARNING,"Erreur lors de la création de l'enchère sur l'article " + lArticle.getNumArticle(), bllException);
            throw bllException;
        }
        return lEnchere;
    }

    public void cancelAllBidsByUser(int idUser) throws BLLException {
        enchereDAO.deleteAllBidsByUser(idUser);
    }

    public void cancelAllBidsForAuction(int idArticle) throws BLLException {
        enchereDAO.deleteAllBidsOnArticle(idArticle);
    }
}

