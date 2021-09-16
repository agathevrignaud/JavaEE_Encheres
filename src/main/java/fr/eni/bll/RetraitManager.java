package fr.eni.bll;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Retrait;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.RetraitDAO;

// TODO : ajouter une purge des lieux non utilisés en BDD sur le panneau admin
public class RetraitManager {
    private RetraitDAO retraitDAO;

    public RetraitManager() {
        retraitDAO = DAOFactory.getRetraitDAO();
    }

    public Retrait getRetraitById(int idRetrait) {
        return retraitDAO.selectById(idRetrait);
    }

    public Retrait addNewRetrait(ArticleVendu lArticle, String rue, String codePostal, String ville) {
        Retrait lieuRetrait = new Retrait(rue, codePostal, ville, lArticle);
        return retraitDAO.createRetrait(lieuRetrait);
    }

}
