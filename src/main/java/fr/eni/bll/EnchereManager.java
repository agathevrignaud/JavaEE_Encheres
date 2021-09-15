package fr.eni.bll;

import fr.eni.bo.Enchere;
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

    public void addNewEnchere(int idUser, int idArticle, LocalDateTime dateEnchere, int montantEnchere) throws Exception {
        Enchere lEnchere = new Enchere();

        lEnchere.setNo_article(idArticle);
        lEnchere.setNo_utilisateur(idUser);
        lEnchere.setDateEnchere(dateEnchere);
        lEnchere.setMontantEnchere(montantEnchere);

        enchereDAO.createEnchere(lEnchere);
    }
}

