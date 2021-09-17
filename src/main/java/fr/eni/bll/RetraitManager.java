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
        System.out.println(lieuRetrait.toString());
        return retraitDAO.createRetrait(lieuRetrait);
    }

    public void updateRetrait(ArticleVendu articleVendu, String rue, String cp, String ville) {
        Retrait retrait;
        retrait = new Retrait(rue, cp, ville, articleVendu);
        retraitDAO.updateRetrait(retrait);
    }

    public void deleteRetrait(int id) {
        retraitDAO.deleteRetrait(id);
    }
}
