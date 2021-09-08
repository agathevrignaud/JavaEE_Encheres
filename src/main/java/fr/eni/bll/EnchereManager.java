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

    public List<Enchere> getAllEncheresByIdArticle(int idArticle) {
        return enchereDAO.selectByIdArticle(idArticle);
    }

    public void addNewEnchere(int idUser, int idArticle, LocalDateTime dateEnchere, int montantEnchere) throws Exception {
        Enchere lEnchereAvant = getAllEncheresByIdArticle(idArticle).get(0);
        Enchere lEnchere = new Enchere();
        if (lEnchereAvant.getMontantEnchere() > montantEnchere) {
            System.out.print("Impossible d'ajouter l'enchère ! Le montant est plus bas que la dernière enchère valide !");
            throw new Exception();
        } else {
            lEnchere.setNo_article(idArticle);
            lEnchere.setNo_utilisateur(idUser);
            lEnchere.setDateEnchere(dateEnchere);
            lEnchere.setMontantEnchere(montantEnchere);
            enchereDAO.createEnchere(lEnchere);
        }
    }
}
