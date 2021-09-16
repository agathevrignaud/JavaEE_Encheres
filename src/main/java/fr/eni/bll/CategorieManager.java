package fr.eni.bll;

import fr.eni.bo.Categorie;
import fr.eni.dal.CategorieDAO;
import fr.eni.dal.DAOFactory;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategorieManager {
    private CategorieDAO categorieDAO;
    private static final Logger myLogger = Logger.getLogger("LogsBLL_Categorie");

    public CategorieManager() {
        categorieDAO = DAOFactory.getCategorieDAO();
    }

    public List<Categorie> getAllCategories() throws BLLException {
        return categorieDAO.selectAll();
    }

    public void addNewCategory(String libelleCategorie) throws BLLException {
        BLLException bllException = new BLLException();
        if (libelleCategorie != null) {
            Categorie laCategorie = new Categorie(libelleCategorie);
            categorieDAO.createCategory(laCategorie);
        } else {
            myLogger.log(Level.WARNING,"Impossible de créer la nouvelle catégorie : le nom est nul(l)", bllException);
            throw bllException;
        }
    }

    public void updateCategory(int idCategory, String libelleCategorie) throws BLLException {
        BLLException bllException = new BLLException();
        if (libelleCategorie != null) {
            Categorie laCategorie = new Categorie(idCategory, libelleCategorie);
            categorieDAO.updateCategory(laCategorie);
        } else {
            myLogger.log(Level.WARNING,"Impossible de modifier la catégorie : le nom est nul(l)", bllException);
            throw bllException;
        }
    }

    public void deleteCategory(int idCategory) throws BLLException {
        categorieDAO.deleteCategory(idCategory);
    }

    public int getAllUses(int idCategory) throws BLLException {
        return categorieDAO.getAllUses(idCategory);
    }

}
