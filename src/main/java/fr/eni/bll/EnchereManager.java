package fr.eni.bll;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Enchere;
import fr.eni.bo.Utilisateur;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.EnchereDAO;

import java.time.LocalDateTime;
import java.util.List;

public class EnchereManager {
    private EnchereDAO enchereDAO;

    public EnchereManager() {
        enchereDAO = DAOFactory.getEnchereDAO();
    }

    public List<Enchere> getAllBidsByIdArticle(int idArticle) {
        return enchereDAO.selectBidByIdArticle(idArticle);
    }

    public Enchere getHighestBidByIdArticle(int idArticle) {
        return enchereDAO.selectHighestBidByIdArticle(idArticle);
    }

    public Enchere addNewEnchere(Utilisateur lUtilisateur, ArticleVendu lArticle, LocalDateTime dateEnchere, int montantEnchere) throws Exception {
        Enchere lEnchere = new Enchere(
                lUtilisateur,
                lArticle,
                dateEnchere,
                montantEnchere
        );
        return enchereDAO.createEnchere(lEnchere);
    }
}

