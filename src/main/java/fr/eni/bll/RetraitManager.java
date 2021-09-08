package fr.eni.bll;

import fr.eni.bo.Retrait;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.RetraitDAO;

// TODO : ajouter une purge des lieux non utilisés en BDD sur le panneau admin
public class RetraitManager {
    private RetraitDAO retraitDAO;

    public RetraitManager() { retraitDAO = DAOFactory.getRetraitDAO(); }

    public Retrait getRetraitById(int idRetrait) {
        return retraitDAO.selectById(idRetrait);
    }

    public void addNewRetrait(int idArticle, String rue, String codePostal, String ville) {
        Retrait lieuRetrait = new Retrait(idArticle, rue, codePostal, ville);
        retraitDAO.createRetrait(lieuRetrait);
    }

}
